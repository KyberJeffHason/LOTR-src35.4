package lotr.common.block;

import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityUnsmeltery;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockUnsmeltery extends LOTRBlockForgeBase {
    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
        return this.getIcon(side, world.getBlockMetadata(i, j, k));
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        return Blocks.cobblestone.getIcon(i, j);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityUnsmeltery();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return LOTRMod.proxy.getUnsmelteryRenderID();
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        if(!world.isRemote) {
            entityplayer.openGui(LOTRMod.instance, 38, world, i, j, k);
        }
        return true;
    }

    @Override
    protected boolean useLargeSmoke() {
        return false;
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        super.randomDisplayTick(world, i, j, k, random);
        if(LOTRBlockForgeBase.isForgeActive(world, i, j, k)) {
            for(int l = 0; l < 3; ++l) {
                float f = i + 0.25f + random.nextFloat() * 0.5f;
                float f1 = j + 0.5f + random.nextFloat() * 0.5f;
                float f2 = k + 0.25f + random.nextFloat() * 0.5f;
                world.spawnParticle("largesmoke", f, f1, f2, 0.0, 0.0, 0.0);
            }
        }
    }
}
