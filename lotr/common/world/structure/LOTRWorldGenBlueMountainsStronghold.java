package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.*;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenBlueMountainsStronghold extends LOTRWorldGenStructureBase {
    public LOTRWorldGenBlueMountainsStronghold(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k) {
        Block block;
        int i1;
        int i12;
        int k1;
        if(this.restrictions && (block = world.getBlock(i, j - 1, k)) != Blocks.grass && block != Blocks.stone && block != Blocks.dirt && block != LOTRMod.rock && block != Blocks.snow) {
            return false;
        }
        --j;
        int rotation = random.nextInt(4);
        if(!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
        }
        switch(rotation) {
            case 0: {
                k += 8;
                break;
            }
            case 1: {
                i -= 8;
                break;
            }
            case 2: {
                k -= 8;
                break;
            }
            case 3: {
                i += 8;
            }
        }
        if(this.restrictions) {
            int minHeight = j;
            int maxHeight = j;
            for(int i13 = i - 6; i13 <= i + 6; ++i13) {
                for(int k12 = k - 6; k12 <= k + 6; ++k12) {
                    int j1 = world.getTopSolidOrLiquidBlock(i13, k12) - 1;
                    Block block2 = world.getBlock(i13, j1, k12);
                    if(block2 != Blocks.grass && block2 != Blocks.stone && block2 != Blocks.dirt && block2 != LOTRMod.rock && block2 != Blocks.snow) {
                        return false;
                    }
                    if(j1 < minHeight) {
                        minHeight = j1;
                    }
                    if(j1 <= maxHeight) continue;
                    maxHeight = j1;
                }
            }
            if(maxHeight - minHeight > 10) {
                return false;
            }
        }
        for(k1 = k - 6; k1 <= k + 6; ++k1) {
            for(i12 = i - 6; i12 <= i + 6; ++i12) {
                boolean flag = Math.abs(k1 - k) == 6 && Math.abs(i12 - i) == 6;
                for(int j1 = j + 7; !(j1 < j && LOTRMod.isOpaque(world, i12, j1, k1) || j1 < 0); --j1) {
                    if(flag) {
                        this.setBlockAndNotifyAdequately(world, i12, j1, k1, LOTRMod.pillar, 3);
                    }
                    else {
                        if(Math.abs(i12 - i) < 6 && Math.abs(k1 - k) < 6) {
                            if(j1 >= j + 1 && j1 <= j + 3) {
                                this.setAir(world, i12, j1, k1);
                                continue;
                            }
                            if(j1 >= j + 4 && j1 <= j + 7) {
                                this.setAir(world, i12, j1, k1);
                                continue;
                            }
                            if(j1 == j) {
                                this.setBlockAndNotifyAdequately(world, i12, j1, k1, Blocks.planks, 1);
                                continue;
                            }
                        }
                        this.setBlockAndNotifyAdequately(world, i12, j1, k1, LOTRMod.brick, 14);
                    }
                    if(j1 > j) continue;
                    this.setGrassToDirt(world, i12, j1 - 1, k1);
                }
            }
        }
        for(i1 = i - 6; i1 <= i + 6; ++i1) {
            this.setBlockAndNotifyAdequately(world, i1, j + 8, k - 6, LOTRMod.stairsDwarvenBrick, 2);
            this.setBlockAndNotifyAdequately(world, i1, j + 8, k + 6, LOTRMod.stairsDwarvenBrick, 3);
        }
        for(k1 = k - 6; k1 <= k + 6; ++k1) {
            this.setBlockAndNotifyAdequately(world, i - 6, j + 8, k1, LOTRMod.stairsDwarvenBrick, 0);
            this.setBlockAndNotifyAdequately(world, i + 6, j + 8, k1, LOTRMod.stairsDwarvenBrick, 1);
        }
        for(k1 = k - 5; k1 <= k + 5; ++k1) {
            for(i12 = i - 5; i12 <= i + 5; ++i12) {
                this.setBlockAndNotifyAdequately(world, i12, j + 4, k1, LOTRMod.slabDouble3, 0);
                this.setBlockAndNotifyAdequately(world, i12, j + 8, k1, LOTRMod.slabDouble3, 0);
                int i2 = Math.abs(i12 - i);
                int k2 = Math.abs(k1 - k);
                int l = -1;
                if(i2 == 5) {
                    l = k2 % 2;
                }
                else if(k2 == 5) {
                    l = i2 % 2;
                }
                if(l <= -1) continue;
                if(l == 1) {
                    for(int j1 = j + 9; j1 <= j + 11; ++j1) {
                        this.setBlockAndNotifyAdequately(world, i12, j1, k1, LOTRMod.pillar, 3);
                    }
                    continue;
                }
                this.setBlockAndNotifyAdequately(world, i12, j + 9, k1, LOTRMod.wall, 14);
            }
        }
        for(i1 = i - 5; i1 <= i + 5; ++i1) {
            this.setBlockAndNotifyAdequately(world, i1, j + 12, k - 5, LOTRMod.stairsDwarvenBrick, 2);
            this.setBlockAndNotifyAdequately(world, i1, j + 12, k + 5, LOTRMod.stairsDwarvenBrick, 3);
        }
        for(k1 = k - 5; k1 <= k + 5; ++k1) {
            this.setBlockAndNotifyAdequately(world, i - 5, j + 12, k1, LOTRMod.stairsDwarvenBrick, 0);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 12, k1, LOTRMod.stairsDwarvenBrick, 1);
        }
        for(k1 = k - 4; k1 <= k + 4; ++k1) {
            for(i12 = i - 4; i12 <= i + 4; ++i12) {
                this.setBlockAndNotifyAdequately(world, i12, j + 12, k1, LOTRMod.slabSingle, 15);
            }
        }
        this.setBlockAndNotifyAdequately(world, i, j + 7, k, LOTRMod.chandelier, 11);
        this.setBlockAndNotifyAdequately(world, i, j + 11, k, LOTRMod.chandelier, 11);
        this.setBlockAndNotifyAdequately(world, i, j + 12, k, LOTRMod.brick, 6);
        switch(rotation) {
            case 0: {
                this.generateFacingSouth(world, random, i, j, k);
                break;
            }
            case 1: {
                this.generateFacingWest(world, random, i, j, k);
                break;
            }
            case 2: {
                this.generateFacingNorth(world, random, i, j, k);
                break;
            }
            case 3: {
                this.generateFacingEast(world, random, i, j, k);
            }
        }
        this.spawnDwarfCommander(world, i, j + 9, k);
        for(int l = 0; l < 4; ++l) {
            this.spawnDwarf(world, i, j + 5, k);
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityBlueDwarfWarrior.class, LOTREntityBlueDwarfAxeThrower.class);
        respawner.setCheckRanges(8, -8, 16, 8);
        respawner.setSpawnRanges(8, 1, 10, 16);
        this.placeNPCRespawner(respawner, world, i, j, k);
        return true;
    }

    private void generateFacingSouth(World world, Random random, int i, int j, int k) {
        int j1;
        int j12;
        int j2;
        int k1;
        int i1;
        int i12;
        int i13;
        for(i12 = i - 6; i12 <= i + 6; ++i12) {
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k - 7, LOTRMod.slabSingle3, 0);
            this.setGrassToDirt(world, i12, j, k - 7);
            for(j12 = j; !LOTRMod.isOpaque(world, i12, j12, k - 7) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i12, j12, k - 7, LOTRMod.pillar, 3);
                this.setGrassToDirt(world, i12, j12 - 1, k - 7);
            }
        }
        for(j1 = j + 1; j1 <= j + 2; ++j1) {
            this.setAir(world, i, j1, k - 6);
            this.setBlockAndNotifyAdequately(world, i - 1, j1, k - 7, LOTRMod.pillar, 3);
            this.setBlockAndNotifyAdequately(world, i + 1, j1, k - 7, LOTRMod.pillar, 3);
        }
        this.setBlockAndNotifyAdequately(world, i, j, k - 7, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i, j, k - 6, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 1, k - 7, LOTRMod.doorSpruce, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 2, k - 7, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i - 1, j + 3, k - 7, LOTRMod.stairsDwarvenBrick, 0);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k - 7, LOTRMod.brick3, 12);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 3, k - 7, LOTRMod.stairsDwarvenBrick, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 4, k - 7, LOTRMod.slabSingle, 7);
        this.placeWallBanner(world, i, j + 6, k - 6, 2, LOTRItemBanner.BannerType.BLUE_MOUNTAINS);
        for(j1 = j + 1; j1 <= j + 3; ++j1) {
            for(k1 = k - 4; k1 <= k - 1; ++k1) {
                for(i1 = i - 5; i1 <= i - 1; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, LOTRMod.brick, 14);
                }
                for(i1 = i + 1; i1 <= i + 5; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, LOTRMod.brick, 14);
                }
            }
            for(k1 = k - 2; k1 <= k + 5; ++k1) {
                this.setBlockAndNotifyAdequately(world, i - 1, j1, k1, LOTRMod.brick, 14);
                this.setBlockAndNotifyAdequately(world, i + 1, j1, k1, LOTRMod.brick, 14);
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 1, j + 1, k + 3, LOTRMod.doorSpruce, 0);
        this.setBlockAndNotifyAdequately(world, i - 1, j + 2, k + 3, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 1, k + 3, LOTRMod.doorSpruce, 2);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 2, k + 3, LOTRMod.doorSpruce, 8);
        for(i12 = i - 5; i12 <= i + 2; i12 += 7) {
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k + 1, LOTRMod.dwarvenBed, 2);
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k, LOTRMod.dwarvenBed, 10);
            this.setBlockAndNotifyAdequately(world, i12 + 3, j + 1, k + 1, LOTRMod.dwarvenBed, 2);
            this.setBlockAndNotifyAdequately(world, i12 + 3, j + 1, k, LOTRMod.dwarvenBed, 10);
            this.setBlockAndNotifyAdequately(world, i12 + 1, j + 1, k, Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i12 + 2, j + 1, k, Blocks.chest, 0);
            LOTRChestContents.fillChest(world, random, i12 + 1, j + 1, k, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, i12 + 2, j + 1, k, LOTRChestContents.BLUE_MOUNTAINS_STRONGHOLD);
            this.setBlockAndNotifyAdequately(world, i12 + 1, j + 3, k + 3, LOTRMod.chandelier, 11);
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k + 5, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i12 + 3, j + 1, k + 5, Blocks.planks, 1);
            this.placeBarrel(world, random, i12, j + 2, k + 5, 2, LOTRFoods.DWARF_DRINK);
            this.placeBarrel(world, random, i12 + 3, j + 2, k + 5, 2, LOTRFoods.DWARF_DRINK);
            this.setBlockAndNotifyAdequately(world, i12 + 1, j + 1, k + 5, Blocks.furnace, 0);
            this.setBlockMetadata(world, i12 + 1, j + 1, k + 5, 2);
            this.setBlockAndNotifyAdequately(world, i12 + 2, j + 1, k + 5, Blocks.furnace, 0);
            this.setBlockMetadata(world, i12 + 2, j + 1, k + 5, 2);
        }
        this.setAir(world, i, j + 4, k - 5);
        int stairX = 1;
        for(j12 = j + 1; j12 <= j + 4; ++j12) {
            this.setAir(world, i - stairX, j + 4, k - 5);
            this.setAir(world, i + stairX, j + 4, k - 5);
            this.setBlockAndNotifyAdequately(world, i - stairX, j12, k - 5, LOTRMod.stairsDwarvenBrick, 1);
            this.setBlockAndNotifyAdequately(world, i + stairX, j12, k - 5, LOTRMod.stairsDwarvenBrick, 0);
            for(j2 = j12 - 1; j2 > j; --j2) {
                this.setBlockAndNotifyAdequately(world, i - stairX, j2, k - 5, LOTRMod.brick, 6);
                this.setBlockAndNotifyAdequately(world, i + stairX, j2, k - 5, LOTRMod.brick, 6);
            }
            ++stairX;
        }
        for(j12 = j + 1; j12 <= j + 3; ++j12) {
            this.setBlockAndNotifyAdequately(world, i - stairX, j12, k - 5, LOTRMod.brick, 6);
            this.setBlockAndNotifyAdequately(world, i + stairX, j12, k - 5, LOTRMod.brick, 6);
        }
        for(i13 = i - 5; i13 <= i + 5; i13 += 10) {
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k - 2, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k - 2, Blocks.wooden_slab, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k + 2, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k + 2, Blocks.wooden_slab, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k, LOTRMod.blueDwarvenTable, 0);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k - 1, LOTRMod.dwarvenForge, 0);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k + 1, LOTRMod.dwarvenForge, 0);
        }
        this.setBlockAndNotifyAdequately(world, i - 3, j + 6, k + 6, LOTRMod.brick3, 12);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 6, k + 6, LOTRMod.brick3, 12);
        stairX = 4;
        for(j12 = j + 5; j12 <= j + 8; ++j12) {
            this.setAir(world, i - stairX, j + 8, k - 4);
            this.setAir(world, i + stairX, j + 8, k - 4);
            this.setBlockAndNotifyAdequately(world, i - stairX, j12, k - 4, LOTRMod.stairsDwarvenBrick, 0);
            this.setBlockAndNotifyAdequately(world, i + stairX, j12, k - 4, LOTRMod.stairsDwarvenBrick, 1);
            for(j2 = j12 - 1; j2 > j + 4; --j2) {
                this.setBlockAndNotifyAdequately(world, i - stairX, j2, k - 4, LOTRMod.brick, 6);
                this.setBlockAndNotifyAdequately(world, i + stairX, j2, k - 4, LOTRMod.brick, 6);
            }
            --stairX;
        }
        for(j12 = j + 5; j12 <= j + 7; ++j12) {
            this.setBlockAndNotifyAdequately(world, i, j12, k - 4, LOTRMod.brick, 6);
        }
        this.setBlockAndNotifyAdequately(world, i, j + 6, k - 4, LOTRMod.brick3, 12);
        for(k1 = k + 7; k1 <= k + 8; ++k1) {
            for(i1 = i - 4; i1 <= i + 4; ++i1) {
                this.placeBalconySection(world, i1, j, k1, false, false);
            }
            this.placeBalconySection(world, i - 5, j, k1, true, false);
            this.placeBalconySection(world, i + 5, j, k1, true, false);
        }
        for(i13 = i - 2; i13 <= i + 2; ++i13) {
            this.placeBalconySection(world, i13, j, k + 9, false, false);
        }
        for(i13 = i - 5; i13 <= i + 5; ++i13) {
            if(Math.abs(i13 - i) < 3) continue;
            this.placeBalconySection(world, i13, j, k + 9, true, false);
        }
        for(i13 = i - 1; i13 <= i + 1; ++i13) {
            this.placeBalconySection(world, i13, j, k + 10, false, false);
        }
        for(i13 = i - 3; i13 <= i + 3; ++i13) {
            if(Math.abs(i13 - i) < 2) continue;
            this.placeBalconySection(world, i13, j, k + 10, true, false);
        }
        for(i13 = i - 2; i13 <= i + 2; ++i13) {
            if(Math.abs(i13 - i) == 0) {
                this.placeBalconySection(world, i13, j, k + 11, true, true);
                continue;
            }
            this.placeBalconySection(world, i13, j, k + 11, true, false);
        }
        this.setBlockAndNotifyAdequately(world, i, j + 4, k + 6, LOTRMod.slabDouble3, 0);
        this.setAir(world, i, j + 5, k + 6);
        this.setAir(world, i, j + 6, k + 6);
        this.setBlockAndNotifyAdequately(world, i, j, k + 6, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 1, k + 6, LOTRMod.doorSpruce, 3);
        this.setBlockAndNotifyAdequately(world, i, j + 2, k + 6, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k + 8, LOTRMod.chandelier, 11);
        for(j12 = j + 1; j12 <= j + 2; ++j12) {
            for(int k12 = k + 7; k12 <= k + 8; ++k12) {
                this.placeRandomOre(world, random, i - 4, j12, k12);
                this.placeRandomOre(world, random, i - 3, j12, k12);
                this.placeRandomOre(world, random, i + 3, j12, k12);
                this.placeRandomOre(world, random, i + 4, j12, k12);
            }
            this.placeRandomOre(world, random, i - 2, j12, k + 9);
            this.placeRandomOre(world, random, i + 2, j12, k + 9);
            for(i1 = i - 1; i1 <= i + 1; ++i1) {
                this.placeRandomOre(world, random, i1, j12, k + 10);
            }
        }
        this.setBlockAndNotifyAdequately(world, i, j + 9, k + 3, LOTRMod.commandTable, 0);
    }

    private void generateFacingWest(World world, Random random, int i, int j, int k) {
        int j1;
        int j12;
        int j2;
        int k1;
        int k12;
        int k13;
        int i1;
        for(k13 = k - 6; k13 <= k + 6; ++k13) {
            this.setBlockAndNotifyAdequately(world, i + 7, j + 1, k13, LOTRMod.slabSingle3, 0);
            this.setGrassToDirt(world, i + 7, j, k13);
            for(j12 = j; !LOTRMod.isOpaque(world, i + 7, j12, k13) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i + 7, j12, k13, LOTRMod.pillar, 3);
                this.setGrassToDirt(world, i + 7, j12 - 1, k13);
            }
        }
        for(j1 = j + 1; j1 <= j + 2; ++j1) {
            this.setAir(world, i + 6, j1, k);
            this.setBlockAndNotifyAdequately(world, i + 7, j1, k - 1, LOTRMod.pillar, 3);
            this.setBlockAndNotifyAdequately(world, i + 7, j1, k + 1, LOTRMod.pillar, 3);
        }
        this.setBlockAndNotifyAdequately(world, i + 7, j, k, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i + 6, j, k, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 1, k, LOTRMod.doorSpruce, 2);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 2, k, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 3, k - 1, LOTRMod.stairsDwarvenBrick, 2);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 3, k, LOTRMod.brick3, 12);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 3, k + 1, LOTRMod.stairsDwarvenBrick, 3);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 4, k, LOTRMod.slabSingle, 7);
        this.placeWallBanner(world, i + 6, j + 6, k, 3, LOTRItemBanner.BannerType.BLUE_MOUNTAINS);
        for(j1 = j + 1; j1 <= j + 3; ++j1) {
            for(i1 = i + 4; i1 >= i + 1; --i1) {
                for(k12 = k - 5; k12 <= k - 1; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k12, LOTRMod.brick, 14);
                }
                for(k12 = k + 1; k12 <= k + 5; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k12, LOTRMod.brick, 14);
                }
            }
            for(i1 = i + 2; i1 >= i - 5; --i1) {
                this.setBlockAndNotifyAdequately(world, i1, j1, k - 1, LOTRMod.brick, 14);
                this.setBlockAndNotifyAdequately(world, i1, j1, k + 1, LOTRMod.brick, 14);
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 3, j + 1, k - 1, LOTRMod.doorSpruce, 1);
        this.setBlockAndNotifyAdequately(world, i - 3, j + 2, k - 1, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i - 3, j + 1, k + 1, LOTRMod.doorSpruce, 3);
        this.setBlockAndNotifyAdequately(world, i - 3, j + 2, k + 1, LOTRMod.doorSpruce, 8);
        for(k13 = k - 5; k13 <= k + 2; k13 += 7) {
            this.setBlockAndNotifyAdequately(world, i - 1, j + 1, k13, LOTRMod.dwarvenBed, 3);
            this.setBlockAndNotifyAdequately(world, i, j + 1, k13, LOTRMod.dwarvenBed, 11);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 1, k13 + 3, LOTRMod.dwarvenBed, 3);
            this.setBlockAndNotifyAdequately(world, i, j + 1, k13 + 3, LOTRMod.dwarvenBed, 11);
            this.setBlockAndNotifyAdequately(world, i, j + 1, k13 + 1, Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 1, k13 + 2, Blocks.chest, 0);
            LOTRChestContents.fillChest(world, random, i, j + 1, k13 + 1, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, i, j + 1, k13 + 2, LOTRChestContents.BLUE_MOUNTAINS_STRONGHOLD);
            this.setBlockAndNotifyAdequately(world, i - 3, j + 3, k13 + 1, LOTRMod.chandelier, 11);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 1, k13, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 1, k13 + 3, Blocks.planks, 1);
            this.placeBarrel(world, random, i - 5, j + 2, k13, 5, LOTRFoods.DWARF_DRINK);
            this.placeBarrel(world, random, i - 5, j + 2, k13 + 3, 5, LOTRFoods.DWARF_DRINK);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 1, k13 + 1, Blocks.furnace, 0);
            this.setBlockMetadata(world, i - 5, j + 1, k13 + 1, 5);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 1, k13 + 2, Blocks.furnace, 0);
            this.setBlockMetadata(world, i - 5, j + 1, k13 + 2, 5);
        }
        this.setAir(world, i + 5, j + 4, k);
        int stairX = 1;
        for(j12 = j + 1; j12 <= j + 4; ++j12) {
            this.setAir(world, i + 5, j + 4, k - stairX);
            this.setAir(world, i + 5, j + 4, k + stairX);
            this.setBlockAndNotifyAdequately(world, i + 5, j12, k - stairX, LOTRMod.stairsDwarvenBrick, 3);
            this.setBlockAndNotifyAdequately(world, i + 5, j12, k + stairX, LOTRMod.stairsDwarvenBrick, 2);
            for(j2 = j12 - 1; j2 > j; --j2) {
                this.setBlockAndNotifyAdequately(world, i + 5, j2, k - stairX, LOTRMod.brick, 6);
                this.setBlockAndNotifyAdequately(world, i + 5, j2, k + stairX, LOTRMod.brick, 6);
            }
            ++stairX;
        }
        for(j12 = j + 1; j12 <= j + 3; ++j12) {
            this.setBlockAndNotifyAdequately(world, i + 5, j12, k - stairX, LOTRMod.brick, 6);
            this.setBlockAndNotifyAdequately(world, i + 5, j12, k + stairX, LOTRMod.brick, 6);
        }
        for(k1 = k - 5; k1 <= k + 5; k1 += 10) {
            this.setBlockAndNotifyAdequately(world, i - 2, j + 5, k1, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 6, k1, Blocks.wooden_slab, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 5, k1, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 6, k1, Blocks.wooden_slab, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 5, k1, LOTRMod.blueDwarvenTable, 0);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 5, k1, LOTRMod.dwarvenForge, 0);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 5, k1, LOTRMod.dwarvenForge, 0);
        }
        this.setBlockAndNotifyAdequately(world, i - 6, j + 6, k - 3, LOTRMod.brick3, 12);
        this.setBlockAndNotifyAdequately(world, i - 6, j + 6, k + 3, LOTRMod.brick3, 12);
        stairX = 4;
        for(j12 = j + 5; j12 <= j + 8; ++j12) {
            this.setAir(world, i + 4, j + 8, k - stairX);
            this.setAir(world, i + 4, j + 8, k + stairX);
            this.setBlockAndNotifyAdequately(world, i + 4, j12, k - stairX, LOTRMod.stairsDwarvenBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 4, j12, k + stairX, LOTRMod.stairsDwarvenBrick, 3);
            for(j2 = j12 - 1; j2 > j + 4; --j2) {
                this.setBlockAndNotifyAdequately(world, i + 4, j2, k - stairX, LOTRMod.brick, 6);
                this.setBlockAndNotifyAdequately(world, i + 4, j2, k + stairX, LOTRMod.brick, 6);
            }
            --stairX;
        }
        for(j12 = j + 5; j12 <= j + 7; ++j12) {
            this.setBlockAndNotifyAdequately(world, i + 4, j12, k, LOTRMod.brick, 6);
        }
        this.setBlockAndNotifyAdequately(world, i + 4, j + 6, k, LOTRMod.brick3, 12);
        for(i1 = i - 7; i1 >= i - 8; --i1) {
            for(k12 = k - 4; k12 <= k + 4; ++k12) {
                this.placeBalconySection(world, i1, j, k12, false, false);
            }
            this.placeBalconySection(world, i1, j, k - 5, true, false);
            this.placeBalconySection(world, i1, j, k + 5, true, false);
        }
        for(k1 = k - 2; k1 <= k + 2; ++k1) {
            this.placeBalconySection(world, i - 9, j, k1, false, false);
        }
        for(k1 = k - 5; k1 <= k + 5; ++k1) {
            if(Math.abs(k1 - k) < 3) continue;
            this.placeBalconySection(world, i - 9, j, k1, true, false);
        }
        for(k1 = k - 1; k1 <= k + 1; ++k1) {
            this.placeBalconySection(world, i - 10, j, k1, false, false);
        }
        for(k1 = k - 3; k1 <= k + 3; ++k1) {
            if(Math.abs(k1 - k) < 2) continue;
            this.placeBalconySection(world, i - 10, j, k1, true, false);
        }
        for(k1 = k - 2; k1 <= k + 2; ++k1) {
            if(Math.abs(k1 - k) == 0) {
                this.placeBalconySection(world, i - 11, j, k1, true, true);
                continue;
            }
            this.placeBalconySection(world, i - 11, j, k1, true, false);
        }
        this.setBlockAndNotifyAdequately(world, i - 6, j + 4, k, LOTRMod.slabDouble3, 0);
        this.setAir(world, i - 6, j + 5, k);
        this.setAir(world, i - 6, j + 6, k);
        this.setBlockAndNotifyAdequately(world, i - 6, j, k, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i - 6, j + 1, k, LOTRMod.doorSpruce, 0);
        this.setBlockAndNotifyAdequately(world, i - 6, j + 2, k, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i - 8, j + 3, k, LOTRMod.chandelier, 11);
        for(j12 = j + 1; j12 <= j + 2; ++j12) {
            for(int i12 = i - 7; i12 >= i - 8; --i12) {
                this.placeRandomOre(world, random, i12, j12, k - 4);
                this.placeRandomOre(world, random, i12, j12, k - 3);
                this.placeRandomOre(world, random, i12, j12, k + 3);
                this.placeRandomOre(world, random, i12, j12, k + 4);
            }
            this.placeRandomOre(world, random, i - 9, j12, k - 2);
            this.placeRandomOre(world, random, i - 9, j12, k + 2);
            for(k12 = k - 1; k12 <= k + 1; ++k12) {
                this.placeRandomOre(world, random, i - 10, j12, k12);
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 3, j + 9, k, LOTRMod.commandTable, 0);
    }

    private void generateFacingNorth(World world, Random random, int i, int j, int k) {
        int j1;
        int j12;
        int j2;
        int k1;
        int i1;
        int i12;
        int i13;
        for(i12 = i - 6; i12 <= i + 6; ++i12) {
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k + 7, LOTRMod.slabSingle3, 0);
            this.setGrassToDirt(world, i12, j, k + 7);
            for(j12 = j; !LOTRMod.isOpaque(world, i12, j12, k + 7) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i12, j12, k + 7, LOTRMod.pillar, 3);
                this.setGrassToDirt(world, i12, j12 - 1, k + 7);
            }
        }
        for(j1 = j + 1; j1 <= j + 2; ++j1) {
            this.setAir(world, i, j1, k + 6);
            this.setBlockAndNotifyAdequately(world, i - 1, j1, k + 7, LOTRMod.pillar, 3);
            this.setBlockAndNotifyAdequately(world, i + 1, j1, k + 7, LOTRMod.pillar, 3);
        }
        this.setBlockAndNotifyAdequately(world, i, j, k + 7, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i, j, k + 6, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 1, k + 7, LOTRMod.doorSpruce, 3);
        this.setBlockAndNotifyAdequately(world, i, j + 2, k + 7, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i - 1, j + 3, k + 7, LOTRMod.stairsDwarvenBrick, 0);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k + 7, LOTRMod.brick3, 12);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 3, k + 7, LOTRMod.stairsDwarvenBrick, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 4, k + 7, LOTRMod.slabSingle, 7);
        this.placeWallBanner(world, i, j + 6, k + 6, 0, LOTRItemBanner.BannerType.BLUE_MOUNTAINS);
        for(j1 = j + 1; j1 <= j + 3; ++j1) {
            for(k1 = k + 4; k1 >= k + 1; --k1) {
                for(i1 = i - 5; i1 <= i - 1; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, LOTRMod.brick, 14);
                }
                for(i1 = i + 1; i1 <= i + 5; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, LOTRMod.brick, 14);
                }
            }
            for(k1 = k + 2; k1 >= k - 5; --k1) {
                this.setBlockAndNotifyAdequately(world, i - 1, j1, k1, LOTRMod.brick, 14);
                this.setBlockAndNotifyAdequately(world, i + 1, j1, k1, LOTRMod.brick, 14);
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 1, j + 1, k - 3, LOTRMod.doorSpruce, 0);
        this.setBlockAndNotifyAdequately(world, i - 1, j + 2, k - 3, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 1, k - 3, LOTRMod.doorSpruce, 2);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 2, k - 3, LOTRMod.doorSpruce, 8);
        for(i12 = i - 5; i12 <= i + 2; i12 += 7) {
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k - 1, LOTRMod.dwarvenBed, 0);
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k, LOTRMod.dwarvenBed, 8);
            this.setBlockAndNotifyAdequately(world, i12 + 3, j + 1, k - 1, LOTRMod.dwarvenBed, 0);
            this.setBlockAndNotifyAdequately(world, i12 + 3, j + 1, k, LOTRMod.dwarvenBed, 8);
            this.setBlockAndNotifyAdequately(world, i12 + 1, j + 1, k, Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i12 + 2, j + 1, k, Blocks.chest, 0);
            LOTRChestContents.fillChest(world, random, i12 + 1, j + 1, k, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, i12 + 2, j + 1, k, LOTRChestContents.BLUE_MOUNTAINS_STRONGHOLD);
            this.setBlockAndNotifyAdequately(world, i12 + 1, j + 3, k - 3, LOTRMod.chandelier, 11);
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k - 5, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i12 + 3, j + 1, k - 5, Blocks.planks, 1);
            this.placeBarrel(world, random, i12, j + 2, k - 5, 3, LOTRFoods.DWARF_DRINK);
            this.placeBarrel(world, random, i12 + 3, j + 2, k - 5, 3, LOTRFoods.DWARF_DRINK);
            this.setBlockAndNotifyAdequately(world, i12 + 1, j + 1, k - 5, Blocks.furnace, 0);
            this.setBlockMetadata(world, i12 + 1, j + 1, k - 5, 3);
            this.setBlockAndNotifyAdequately(world, i12 + 2, j + 1, k - 5, Blocks.furnace, 0);
            this.setBlockMetadata(world, i12 + 2, j + 1, k - 5, 3);
        }
        this.setAir(world, i, j + 4, k + 5);
        int stairX = 1;
        for(j12 = j + 1; j12 <= j + 4; ++j12) {
            this.setAir(world, i - stairX, j + 4, k + 5);
            this.setAir(world, i + stairX, j + 4, k + 5);
            this.setBlockAndNotifyAdequately(world, i - stairX, j12, k + 5, LOTRMod.stairsDwarvenBrick, 1);
            this.setBlockAndNotifyAdequately(world, i + stairX, j12, k + 5, LOTRMod.stairsDwarvenBrick, 0);
            for(j2 = j12 - 1; j2 > j; --j2) {
                this.setBlockAndNotifyAdequately(world, i - stairX, j2, k + 5, LOTRMod.brick, 6);
                this.setBlockAndNotifyAdequately(world, i + stairX, j2, k + 5, LOTRMod.brick, 6);
            }
            ++stairX;
        }
        for(j12 = j + 1; j12 <= j + 3; ++j12) {
            this.setBlockAndNotifyAdequately(world, i - stairX, j12, k + 5, LOTRMod.brick, 6);
            this.setBlockAndNotifyAdequately(world, i + stairX, j12, k + 5, LOTRMod.brick, 6);
        }
        for(i13 = i - 5; i13 <= i + 5; i13 += 10) {
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k + 2, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k + 2, Blocks.wooden_slab, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k - 2, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k - 2, Blocks.wooden_slab, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k, LOTRMod.blueDwarvenTable, 0);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k + 1, LOTRMod.dwarvenForge, 0);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k - 1, LOTRMod.dwarvenForge, 0);
        }
        this.setBlockAndNotifyAdequately(world, i - 3, j + 6, k - 6, LOTRMod.brick3, 12);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 6, k - 6, LOTRMod.brick3, 12);
        stairX = 4;
        for(j12 = j + 5; j12 <= j + 8; ++j12) {
            this.setAir(world, i - stairX, j + 8, k + 4);
            this.setAir(world, i + stairX, j + 8, k + 4);
            this.setBlockAndNotifyAdequately(world, i - stairX, j12, k + 4, LOTRMod.stairsDwarvenBrick, 0);
            this.setBlockAndNotifyAdequately(world, i + stairX, j12, k + 4, LOTRMod.stairsDwarvenBrick, 1);
            for(j2 = j12 - 1; j2 > j + 4; --j2) {
                this.setBlockAndNotifyAdequately(world, i - stairX, j2, k + 4, LOTRMod.brick, 6);
                this.setBlockAndNotifyAdequately(world, i + stairX, j2, k + 4, LOTRMod.brick, 6);
            }
            --stairX;
        }
        for(j12 = j + 5; j12 <= j + 7; ++j12) {
            this.setBlockAndNotifyAdequately(world, i, j12, k + 4, LOTRMod.brick, 6);
        }
        this.setBlockAndNotifyAdequately(world, i, j + 6, k + 4, LOTRMod.brick3, 12);
        for(k1 = k - 7; k1 >= k - 8; --k1) {
            for(i1 = i - 4; i1 <= i + 4; ++i1) {
                this.placeBalconySection(world, i1, j, k1, false, false);
            }
            this.placeBalconySection(world, i - 5, j, k1, true, false);
            this.placeBalconySection(world, i + 5, j, k1, true, false);
        }
        for(i13 = i - 2; i13 <= i + 2; ++i13) {
            this.placeBalconySection(world, i13, j, k - 9, false, false);
        }
        for(i13 = i - 5; i13 <= i + 5; ++i13) {
            if(Math.abs(i13 - i) < 3) continue;
            this.placeBalconySection(world, i13, j, k - 9, true, false);
        }
        for(i13 = i - 1; i13 <= i + 1; ++i13) {
            this.placeBalconySection(world, i13, j, k - 10, false, false);
        }
        for(i13 = i - 3; i13 <= i + 3; ++i13) {
            if(Math.abs(i13 - i) < 2) continue;
            this.placeBalconySection(world, i13, j, k - 10, true, false);
        }
        for(i13 = i - 2; i13 <= i + 2; ++i13) {
            if(Math.abs(i13 - i) == 0) {
                this.placeBalconySection(world, i13, j, k - 11, true, true);
                continue;
            }
            this.placeBalconySection(world, i13, j, k - 11, true, false);
        }
        this.setBlockAndNotifyAdequately(world, i, j + 4, k - 6, LOTRMod.slabDouble3, 0);
        this.setAir(world, i, j + 5, k - 6);
        this.setAir(world, i, j + 6, k - 6);
        this.setBlockAndNotifyAdequately(world, i, j, k - 6, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 1, k - 6, LOTRMod.doorSpruce, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 2, k - 6, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k - 8, LOTRMod.chandelier, 11);
        for(j12 = j + 1; j12 <= j + 2; ++j12) {
            for(int k12 = k - 7; k12 >= k - 8; --k12) {
                this.placeRandomOre(world, random, i - 4, j12, k12);
                this.placeRandomOre(world, random, i - 3, j12, k12);
                this.placeRandomOre(world, random, i + 3, j12, k12);
                this.placeRandomOre(world, random, i + 4, j12, k12);
            }
            this.placeRandomOre(world, random, i - 2, j12, k - 9);
            this.placeRandomOre(world, random, i + 2, j12, k - 9);
            for(i1 = i - 1; i1 <= i + 1; ++i1) {
                this.placeRandomOre(world, random, i1, j12, k - 10);
            }
        }
        this.setBlockAndNotifyAdequately(world, i, j + 9, k - 3, LOTRMod.commandTable, 0);
    }

    private void generateFacingEast(World world, Random random, int i, int j, int k) {
        int j1;
        int j12;
        int j2;
        int k1;
        int k12;
        int k13;
        int i1;
        for(k13 = k - 6; k13 <= k + 6; ++k13) {
            this.setBlockAndNotifyAdequately(world, i - 7, j + 1, k13, LOTRMod.slabSingle3, 0);
            this.setGrassToDirt(world, i - 7, j, k13);
            for(j12 = j; !LOTRMod.isOpaque(world, i - 7, j12, k13) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i - 7, j12, k13, LOTRMod.pillar, 3);
                this.setGrassToDirt(world, i - 7, j12 - 1, k13);
            }
        }
        for(j1 = j + 1; j1 <= j + 2; ++j1) {
            this.setAir(world, i - 6, j1, k);
            this.setBlockAndNotifyAdequately(world, i - 7, j1, k - 1, LOTRMod.pillar, 3);
            this.setBlockAndNotifyAdequately(world, i - 7, j1, k + 1, LOTRMod.pillar, 3);
        }
        this.setBlockAndNotifyAdequately(world, i - 7, j, k, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i - 6, j, k, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 1, k, LOTRMod.doorSpruce, 0);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 2, k, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 3, k - 1, LOTRMod.stairsDwarvenBrick, 2);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 3, k, LOTRMod.brick3, 12);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 3, k + 1, LOTRMod.stairsDwarvenBrick, 3);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 4, k, LOTRMod.slabSingle, 7);
        this.placeWallBanner(world, i - 6, j + 6, k, 1, LOTRItemBanner.BannerType.BLUE_MOUNTAINS);
        for(j1 = j + 1; j1 <= j + 3; ++j1) {
            for(i1 = i - 4; i1 <= i - 1; ++i1) {
                for(k12 = k - 5; k12 <= k - 1; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k12, LOTRMod.brick, 14);
                }
                for(k12 = k + 1; k12 <= k + 5; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k12, LOTRMod.brick, 14);
                }
            }
            for(i1 = i - 2; i1 <= i + 5; ++i1) {
                this.setBlockAndNotifyAdequately(world, i1, j1, k - 1, LOTRMod.brick, 14);
                this.setBlockAndNotifyAdequately(world, i1, j1, k + 1, LOTRMod.brick, 14);
            }
        }
        this.setBlockAndNotifyAdequately(world, i + 3, j + 1, k - 1, LOTRMod.doorSpruce, 1);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 2, k - 1, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 1, k + 1, LOTRMod.doorSpruce, 3);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 2, k + 1, LOTRMod.doorSpruce, 8);
        for(k13 = k - 5; k13 <= k + 2; k13 += 7) {
            this.setBlockAndNotifyAdequately(world, i + 1, j + 1, k13, LOTRMod.dwarvenBed, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 1, k13, LOTRMod.dwarvenBed, 9);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 1, k13 + 3, LOTRMod.dwarvenBed, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 1, k13 + 3, LOTRMod.dwarvenBed, 9);
            this.setBlockAndNotifyAdequately(world, i, j + 1, k13 + 1, Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 1, k13 + 2, Blocks.chest, 0);
            LOTRChestContents.fillChest(world, random, i, j + 1, k13 + 1, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, i, j + 1, k13 + 2, LOTRChestContents.BLUE_MOUNTAINS_STRONGHOLD);
            this.setBlockAndNotifyAdequately(world, i + 3, j + 3, k13 + 1, LOTRMod.chandelier, 11);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 1, k13, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 1, k13 + 3, Blocks.planks, 1);
            this.placeBarrel(world, random, i + 5, j + 2, k13, 4, LOTRFoods.DWARF_DRINK);
            this.placeBarrel(world, random, i + 5, j + 2, k13 + 3, 4, LOTRFoods.DWARF_DRINK);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 1, k13 + 1, Blocks.furnace, 0);
            this.setBlockMetadata(world, i + 5, j + 1, k13 + 1, 4);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 1, k13 + 2, Blocks.furnace, 0);
            this.setBlockMetadata(world, i + 5, j + 1, k13 + 2, 4);
        }
        this.setAir(world, i - 5, j + 4, k);
        int stairX = 1;
        for(j12 = j + 1; j12 <= j + 4; ++j12) {
            this.setAir(world, i - 5, j + 4, k - stairX);
            this.setAir(world, i - 5, j + 4, k + stairX);
            this.setBlockAndNotifyAdequately(world, i - 5, j12, k - stairX, LOTRMod.stairsDwarvenBrick, 3);
            this.setBlockAndNotifyAdequately(world, i - 5, j12, k + stairX, LOTRMod.stairsDwarvenBrick, 2);
            for(j2 = j12 - 1; j2 > j; --j2) {
                this.setBlockAndNotifyAdequately(world, i - 5, j2, k - stairX, LOTRMod.brick, 6);
                this.setBlockAndNotifyAdequately(world, i - 5, j2, k + stairX, LOTRMod.brick, 6);
            }
            ++stairX;
        }
        for(j12 = j + 1; j12 <= j + 3; ++j12) {
            this.setBlockAndNotifyAdequately(world, i - 5, j12, k - stairX, LOTRMod.brick, 6);
            this.setBlockAndNotifyAdequately(world, i - 5, j12, k + stairX, LOTRMod.brick, 6);
        }
        for(k1 = k - 5; k1 <= k + 5; k1 += 10) {
            this.setBlockAndNotifyAdequately(world, i - 2, j + 5, k1, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 6, k1, Blocks.wooden_slab, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 5, k1, Blocks.planks, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 6, k1, Blocks.wooden_slab, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 5, k1, LOTRMod.blueDwarvenTable, 0);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 5, k1, LOTRMod.dwarvenForge, 0);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 5, k1, LOTRMod.dwarvenForge, 0);
        }
        this.setBlockAndNotifyAdequately(world, i + 6, j + 6, k - 3, LOTRMod.brick3, 12);
        this.setBlockAndNotifyAdequately(world, i + 6, j + 6, k + 3, LOTRMod.brick3, 12);
        stairX = 4;
        for(j12 = j + 5; j12 <= j + 8; ++j12) {
            this.setAir(world, i - 4, j + 8, k - stairX);
            this.setAir(world, i - 4, j + 8, k + stairX);
            this.setBlockAndNotifyAdequately(world, i - 4, j12, k - stairX, LOTRMod.stairsDwarvenBrick, 2);
            this.setBlockAndNotifyAdequately(world, i - 4, j12, k + stairX, LOTRMod.stairsDwarvenBrick, 3);
            for(j2 = j12 - 1; j2 > j + 4; --j2) {
                this.setBlockAndNotifyAdequately(world, i - 4, j2, k - stairX, LOTRMod.brick, 6);
                this.setBlockAndNotifyAdequately(world, i - 4, j2, k + stairX, LOTRMod.brick, 6);
            }
            --stairX;
        }
        for(j12 = j + 5; j12 <= j + 7; ++j12) {
            this.setBlockAndNotifyAdequately(world, i - 4, j12, k, LOTRMod.brick, 6);
        }
        this.setBlockAndNotifyAdequately(world, i - 4, j + 6, k, LOTRMod.brick3, 12);
        for(i1 = i + 7; i1 <= i + 8; ++i1) {
            for(k12 = k - 4; k12 <= k + 4; ++k12) {
                this.placeBalconySection(world, i1, j, k12, false, false);
            }
            this.placeBalconySection(world, i1, j, k - 5, true, false);
            this.placeBalconySection(world, i1, j, k + 5, true, false);
        }
        for(k1 = k - 2; k1 <= k + 2; ++k1) {
            this.placeBalconySection(world, i + 9, j, k1, false, false);
        }
        for(k1 = k - 5; k1 <= k + 5; ++k1) {
            if(Math.abs(k1 - k) < 3) continue;
            this.placeBalconySection(world, i + 9, j, k1, true, false);
        }
        for(k1 = k - 1; k1 <= k + 1; ++k1) {
            this.placeBalconySection(world, i + 10, j, k1, false, false);
        }
        for(k1 = k - 3; k1 <= k + 3; ++k1) {
            if(Math.abs(k1 - k) < 2) continue;
            this.placeBalconySection(world, i + 10, j, k1, true, false);
        }
        for(k1 = k - 2; k1 <= k + 2; ++k1) {
            if(Math.abs(k1 - k) == 0) {
                this.placeBalconySection(world, i + 11, j, k1, true, true);
                continue;
            }
            this.placeBalconySection(world, i + 11, j, k1, true, false);
        }
        this.setBlockAndNotifyAdequately(world, i + 6, j + 4, k, LOTRMod.slabDouble3, 0);
        this.setAir(world, i + 6, j + 5, k);
        this.setAir(world, i + 6, j + 6, k);
        this.setBlockAndNotifyAdequately(world, i + 6, j, k, Blocks.planks, 1);
        this.setBlockAndNotifyAdequately(world, i + 6, j + 1, k, LOTRMod.doorSpruce, 2);
        this.setBlockAndNotifyAdequately(world, i + 6, j + 2, k, LOTRMod.doorSpruce, 8);
        this.setBlockAndNotifyAdequately(world, i + 8, j + 3, k, LOTRMod.chandelier, 11);
        for(j12 = j + 1; j12 <= j + 2; ++j12) {
            for(int i12 = i + 7; i12 <= i + 8; ++i12) {
                this.placeRandomOre(world, random, i12, j12, k - 4);
                this.placeRandomOre(world, random, i12, j12, k - 3);
                this.placeRandomOre(world, random, i12, j12, k + 3);
                this.placeRandomOre(world, random, i12, j12, k + 4);
            }
            this.placeRandomOre(world, random, i + 9, j12, k - 2);
            this.placeRandomOre(world, random, i + 9, j12, k + 2);
            for(k12 = k - 1; k12 <= k + 1; ++k12) {
                this.placeRandomOre(world, random, i + 10, j12, k12);
            }
        }
        this.setBlockAndNotifyAdequately(world, i + 3, j + 9, k, LOTRMod.commandTable, 0);
    }

    private void spawnDwarfCommander(World world, int i, int j, int k) {
        LOTREntityBlueDwarfCommander dwarf = new LOTREntityBlueDwarfCommander(world);
        dwarf.setLocationAndAngles(i + 0.5, j, k + 0.5, 0.0f, 0.0f);
        ((LOTREntityDwarf) dwarf).onSpawnWithEgg(null);
        dwarf.setHomeArea(i, j, k, 16);
        world.spawnEntityInWorld(dwarf);
    }

    private void spawnDwarf(World world, int i, int j, int k) {
        LOTREntityBlueDwarfWarrior dwarf = world.rand.nextInt(3) == 0 ? new LOTREntityBlueDwarfAxeThrower(world) : new LOTREntityBlueDwarfWarrior(world);
        dwarf.setLocationAndAngles(i + 0.5, j, k + 0.5, 0.0f, 0.0f);
        ((LOTREntityBlueDwarf) dwarf).onSpawnWithEgg(null);
        dwarf.isNPCPersistent = true;
        dwarf.setHomeArea(i, j, k, 16);
        world.spawnEntityInWorld(dwarf);
    }

    private void placeBalconySection(World world, int i, int j, int k, boolean isEdge, boolean isPillar) {
        if(isEdge) {
            for(int j1 = j + 4; !(j1 < j && LOTRMod.isOpaque(world, i, j1, k) || j1 < 0); --j1) {
                if(isPillar) {
                    this.setBlockAndNotifyAdequately(world, i, j1, k, LOTRMod.pillar, 3);
                }
                else {
                    this.setBlockAndNotifyAdequately(world, i, j1, k, LOTRMod.brick, 14);
                }
                this.setGrassToDirt(world, i, j1 - 1, k);
            }
            if(isPillar) {
                this.setBlockAndNotifyAdequately(world, i, j + 4, k, LOTRMod.brick3, 12);
            }
            this.setBlockAndNotifyAdequately(world, i, j + 5, k, LOTRMod.brick, 6);
            this.setBlockAndNotifyAdequately(world, i, j + 6, k, LOTRMod.wall, 14);
        }
        else {
            int j1;
            for(j1 = j - 1; !LOTRMod.isOpaque(world, i, j1, k) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i, j1, k, LOTRMod.brick, 14);
                this.setGrassToDirt(world, i, j1 - 1, k);
            }
            this.setBlockAndNotifyAdequately(world, i, j, k, Blocks.planks, 1);
            for(j1 = j + 1; j1 <= j + 6; ++j1) {
                this.setAir(world, i, j1, k);
            }
            this.setBlockAndNotifyAdequately(world, i, j + 4, k, LOTRMod.slabDouble3, 0);
        }
    }

    private void placeRandomOre(World world, Random random, int i, int j, int k) {
        if(!LOTRMod.isOpaque(world, i, j - 1, k) || !random.nextBoolean()) {
            return;
        }
        int l = random.nextInt(5);
        Block block = null;
        switch(l) {
            case 0: {
                block = Blocks.iron_ore;
                break;
            }
            case 1: {
                block = Blocks.gold_ore;
                break;
            }
            case 2: {
                block = LOTRMod.oreCopper;
                break;
            }
            case 3: {
                block = LOTRMod.oreTin;
                break;
            }
            case 4: {
                block = LOTRMod.oreSilver;
            }
        }
        this.setBlockAndNotifyAdequately(world, i, j, k, block, 0);
    }
}
