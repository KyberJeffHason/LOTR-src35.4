package lotr.common.world.spawning;

import java.util.*;
import cpw.mods.fml.common.eventhandler.Event;
import lotr.common.LOTRConfig;
import lotr.common.LOTRSpawnDamping;
import lotr.common.entity.animal.LOTRAnimalSpawnConditions;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.ForgeEventFactory;

public class LOTRSpawnerAnimals {
    private static Set<ChunkCoordIntPair> eligibleSpawnChunks = new HashSet<ChunkCoordIntPair>();
    private static Map<Integer, Integer> ticksSinceCycle = new HashMap<Integer, Integer>();
    private static Map<Integer, DimInfo> dimInfos = new HashMap<Integer, DimInfo>();
    private static final int FAIL_THRESHOLD = 10;
    private static final int FAIL_BLOCKINGS = 100;

    private static TypeInfo forDimAndType(World world, EnumCreatureType type) {
        TypeInfo typeInfo;
        int dimID = world.provider.dimensionId;
        DimInfo dimInfo = dimInfos.get(dimID);
        if(dimInfo == null) {
            dimInfo = new DimInfo();
            dimInfos.put(dimID, dimInfo);
        }
        if((typeInfo = dimInfo.types.get(type)) == null) {
            typeInfo = new TypeInfo();
            dimInfo.types.put(type, typeInfo);
        }
        return typeInfo;
    }

    public static int performSpawning(WorldServer world, boolean hostiles, boolean peacefuls, boolean rareTick) {
        int interval;
        int n = interval = rareTick ? 0 : LOTRConfig.mobSpawnInterval;
        if(interval > 0) {
            int ticks = 0;
            int dimID = world.provider.dimensionId;
            if(ticksSinceCycle.containsKey(dimID)) {
                ticks = ticksSinceCycle.get(dimID);
            }
            ticksSinceCycle.put(dimID, --ticks);
            if(ticks > 0) {
                return 0;
            }
            ticks = interval;
            ticksSinceCycle.put(dimID, ticks);
        }
        if(!hostiles && !peacefuls) {
            return 0;
        }
        int totalSpawned = 0;
        LOTRSpawnerNPCs.getSpawnableChunks(world, eligibleSpawnChunks);
        ChunkCoordinates spawnPoint = world.getSpawnPoint();
        block2: for(EnumCreatureType creatureType : EnumCreatureType.values()) {
            int maxCount;
            int count;
            TypeInfo typeInfo = LOTRSpawnerAnimals.forDimAndType(world, creatureType);
            boolean canSpawnType = true;
            canSpawnType = creatureType.getPeacefulCreature() ? peacefuls : hostiles;
            if(creatureType.getAnimal()) {
                canSpawnType = rareTick;
            }
            if(!canSpawnType || (count = world.countEntities(creatureType, true)) > (maxCount = LOTRSpawnDamping.getCreatureSpawnCap(creatureType, world) * eligibleSpawnChunks.size() / 196)) continue;
            int cycles = Math.max(1, interval);
            for(int c = 0; c < cycles; ++c) {
                if(typeInfo.blockedCycles > 0) {
                    --typeInfo.blockedCycles;
                    continue;
                }
                int newlySpawned = 0;
                List<ChunkCoordIntPair> shuffled = LOTRSpawnerNPCs.shuffle(eligibleSpawnChunks);
                block4: for(ChunkCoordIntPair chunkCoords : shuffled) {
                    int k;
                    int j;
                    int i;
                    ChunkPosition chunkposition = LOTRSpawnerNPCs.getRandomSpawningPointInChunk(world, chunkCoords);
                    if(chunkposition == null || world.spawnRandomCreature(creatureType, i = chunkposition.chunkPosX, j = chunkposition.chunkPosY, k = chunkposition.chunkPosZ) == null || world.getBlock(i, j, k).isNormalCube() || world.getBlock(i, j, k).getMaterial() != creatureType.getCreatureMaterial()) continue;
                    for(int groupsSpawned = 0; groupsSpawned < 3; ++groupsSpawned) {
                        int i1 = i;
                        int j1 = j;
                        int k1 = k;
                        int range = 6;
                        BiomeGenBase.SpawnListEntry spawnEntry = null;
                        IEntityLivingData entityData = null;
                        for(int attempts = 0; attempts < 4; ++attempts) {
                            float distSq;
                            float f5;
                            float f4;
                            EntityLiving entity;
                            float f2;
                            float f3;
                            float f;
                            float f1;
                            if(!world.blockExists(i1 += world.rand.nextInt(range) - world.rand.nextInt(range), j1 += world.rand.nextInt(1) - world.rand.nextInt(1), k1 += world.rand.nextInt(range) - world.rand.nextInt(range)) || !SpawnerAnimals.canCreatureTypeSpawnAtLocation(creatureType, world, i1, j1, k1) || world.getClosestPlayer(f = i1 + 0.5f, f1 = j1, f2 = k1 + 0.5f, 24.0) != null || !((distSq = (f3 = f - spawnPoint.posX) * f3 + (f4 = f1 - spawnPoint.posY) * f4 + (f5 = f2 - spawnPoint.posZ) * f5) >= 576.0f)) continue;
                            if(spawnEntry == null && (spawnEntry = world.spawnRandomCreature(creatureType, i1, j1, k1)) == null) continue block4;
                            try {
                                entity = (EntityLiving) spawnEntry.entityClass.getConstructor(World.class).newInstance(world);
                            }
                            catch(Exception e) {
                                e.printStackTrace();
                                return totalSpawned;
                            }
                            entity.setLocationAndAngles(f, f1, f2, world.rand.nextFloat() * 360.0f, 0.0f);
                            Event.Result canSpawn = ForgeEventFactory.canEntitySpawn(entity, world, f, f1, f2);
                            if(canSpawn != Event.Result.ALLOW && (canSpawn != Event.Result.DEFAULT || !entity.getCanSpawnHere())) continue;
                            ++totalSpawned;
                            world.spawnEntityInWorld(entity);
                            if(!ForgeEventFactory.doSpecialSpawn(entity, world, f, f1, f2)) {
                                entityData = entity.onSpawnWithEgg(entityData);
                            }
                            ++newlySpawned;
                            if(c > 0 && ++count > maxCount) continue block2;
                            if(groupsSpawned >= ForgeEventFactory.getMaxSpawnPackSize(entity)) continue block4;
                        }
                    }
                }
                if(newlySpawned == 0) {
                    ++typeInfo.failedCycles;
                    if(typeInfo.failedCycles < 10) continue;
                    typeInfo.failedCycles = 0;
                    typeInfo.blockedCycles = 100;
                    continue;
                }
                if(typeInfo.failedCycles <= 0) continue;
                --typeInfo.failedCycles;
            }
        }
        return totalSpawned;
    }

    public static void worldGenSpawnAnimals(World world, LOTRBiome biome, LOTRBiomeVariant variant, int i, int k, Random rand) {
        int spawnRange = 16;
        int spawnFuzz = 5;
        List spawnList = biome.getSpawnableList(EnumCreatureType.creature);
        if(!spawnList.isEmpty()) {
            while(rand.nextFloat() < biome.getSpawningChance()) {
                BiomeGenBase.SpawnListEntry spawnEntry = (BiomeGenBase.SpawnListEntry) WeightedRandom.getRandomItem(world.rand, spawnList);
                int count = MathHelper.getRandomIntegerInRange(rand, spawnEntry.minGroupCount, spawnEntry.maxGroupCount);
                IEntityLivingData entityData = null;
                int packX = i + rand.nextInt(spawnRange);
                int packZ = k + rand.nextInt(spawnRange);
                int i1 = packX;
                int k1 = packZ;
                for(int l = 0; l < count; ++l) {
                    int attempts = 4;
                    boolean spawned = false;
                    for(int a = 0; !spawned && a < attempts; ++a) {
                        int j1 = world.getTopSolidOrLiquidBlock(i1, k1);
                        if(SpawnerAnimals.canCreatureTypeSpawnAtLocation(EnumCreatureType.creature, world, i1, j1, k1)) {
                            EntityLiving entity;
                            LOTRAnimalSpawnConditions conditions;
                            float f = i1 + 0.5f;
                            float f1 = j1;
                            float f2 = k1 + 0.5f;
                            try {
                                entity = (EntityLiving) spawnEntry.entityClass.getConstructor(World.class).newInstance(world);
                            }
                            catch(Exception exception) {
                                exception.printStackTrace();
                                continue;
                            }
                            boolean canSpawn = true;
                            if(entity instanceof LOTRAnimalSpawnConditions && !(conditions = (LOTRAnimalSpawnConditions) (entity)).canWorldGenSpawnAt(i1, j1, k1, biome, variant)) {
                                canSpawn = false;
                            }
                            if(canSpawn) {
                                entity.setLocationAndAngles(f, f1, f2, rand.nextFloat() * 360.0f, 0.0f);
                                world.spawnEntityInWorld(entity);
                                entityData = entity.onSpawnWithEgg(entityData);
                                spawned = true;
                            }
                        }
                        i1 += rand.nextInt(spawnFuzz) - rand.nextInt(spawnFuzz);
                        k1 += rand.nextInt(spawnFuzz) - rand.nextInt(spawnFuzz);
                        while(i1 < i || i1 >= i + spawnRange || k1 < k || k1 >= k + spawnRange) {
                            i1 = packX + rand.nextInt(spawnFuzz) - rand.nextInt(spawnFuzz);
                            k1 = packZ + rand.nextInt(spawnFuzz) - rand.nextInt(spawnFuzz);
                        }
                    }
                }
            }
        }
    }

    private static class TypeInfo {
        public int failedCycles;
        public int blockedCycles;

        private TypeInfo() {
        }
    }

    private static class DimInfo {
        public Map<EnumCreatureType, TypeInfo> types = new HashMap<EnumCreatureType, TypeInfo>();

        private DimInfo() {
        }
    }

}
