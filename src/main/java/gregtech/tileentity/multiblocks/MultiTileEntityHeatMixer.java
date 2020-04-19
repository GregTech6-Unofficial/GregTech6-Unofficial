package gregtech.tileentity.multiblocks;

import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockMachine;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.List;

import static gregapi.data.CS.*;
import static gregapi.data.CS.SIDE_BOTTOM;

public class MultiTileEntityHeatMixer extends TileEntityBase10MultiBlockMachine {
    public static final IIconContainer mTextureActive   = new Textures.BlockIcons.CustomIcon("machines/multiblockmains/largeheatmixer_active");
    public static final IIconContainer mTextureInactive = new Textures.BlockIcons.CustomIcon("machines/multiblockmains/largeheatmixer");

    @Override
    public boolean checkStructure2() {
        int
                tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1),
                tMinY = yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1),
                tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1),
                tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1),
                tMaxY = yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1),
                tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1),
                tOutX = getOffsetXN(mFacing, 3),
                tOutY = getOffsetYN(mFacing, 3),
                tOutZ = getOffsetZN(mFacing, 3);

        if (worldObj.blockExists(tMinX, tMinY, tMinZ) && worldObj.blockExists(tMaxX, tMaxY, tMaxZ)) {
            boolean tSuccess = T;
            for (int tX = tMinX; tX <= tMaxX; tX++) for (int tY = tMinY; tY <= tMaxY; tY++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) {
                int tBits = 0;
                if (tX == tOutX && tY == tOutY && tZ == tOutZ) {
                    tBits = MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN;
                } else {
                    if (SIDES_AXIS_X[mFacing] && tX == xCoord || SIDES_AXIS_Y[mFacing] && tY == yCoord || SIDES_AXIS_Z[mFacing] && tZ == zCoord) {
                        tBits = (tY == tMinY ? MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID : MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN);
                    } else {
                        tBits = MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT;
                    }
                }
                if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ, 18009, getMultiTileEntityRegistryID(), tX == tOutX && tY == tOutY && tZ == tOutZ ? 2 : 0, tBits)) tSuccess = F;
            }

            return tSuccess;
        }
        return mStructureOkay;
    }

    static {
        LH.add("gt.tooltip.multiblock.heatmixer.1", "3x3x4 of Steel Walls");
        LH.add("gt.tooltip.multiblock.heatmixer.2", "Main centered on the 3x3 facing outwards");
        LH.add("gt.tooltip.multiblock.heatmixer.3", "Input only possible at frontal 3x3");
        LH.add("gt.tooltip.multiblock.heatmixer.4", "Energy input at back center");
        LH.add("gt.tooltip.multiblock.heatmixer.5", "Output possible at all Steel Walls except frontal 3x3");
    }

    @Override
    public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
        aList.add(LH.Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.heatmixer.1"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.heatmixer.2"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.heatmixer.3"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.heatmixer.4"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.heatmixer.5"));
        super.addToolTips(aList, aStack, aF3_H);
    }

    @Override
    public boolean isInsideStructure(int aX, int aY, int aZ) {
        return
                aX >= xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1) &&
                        aY >= yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1) &&
                        aZ >= zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1) &&
                        aX <= xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1) &&
                        aY <= yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1) &&
                        aZ <= zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1);
    }

    @Override
    public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
        return mStructureOkay ? 2 : 1;
    }

    @Override
    public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
        if (aRenderPass == 1) switch(mFacing) {
            case SIDE_X_NEG: case SIDE_X_POS: return box(aBlock, -0.001, -0.999, -0.999,  1.001,  1.999,  1.999);
            case SIDE_Y_NEG: case SIDE_Y_POS: return box(aBlock, -0.999, -0.001, -0.999,  1.999,  1.001,  1.999);
            case SIDE_Z_NEG: case SIDE_Z_POS: return box(aBlock, -0.999, -0.999, -0.001,  1.999,  1.999,  1.001);
        }
        return F;
    }

    @Override
    public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
        return aRenderPass == 0 ? super.getTexture2(aBlock, aRenderPass, aSide, aShouldSideBeRendered) : aSide != mFacing ? null : BlockTextureDefault.get(mActive ? mTextureActive : mTextureInactive);
    }

    /*
    @Override
    public boolean isInsideStructure(int aX, int aY, int aZ) {
        int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);
        return aX >= tX - 1 && aY >= tY && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 1 && aZ <= tZ + 1;
    }
    */

    @Override
    public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
        return getAdjacentTank(SIDE_BOTTOM);
    }

    @Override
    public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {
        return getAdjacentTileEntity(SIDE_BOTTOM);
    }

    @Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
    @Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}

    @Override public String getTileEntityName() {return "gt.multitileentity.multiblock.heatmixer";}
}
