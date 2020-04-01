package gregtech.tileentity.multiblocks;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockMachine;
import gregapi.util.WD;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

public class MultiTileEntityMaskAligner extends TileEntityBase10MultiBlockMachine {
    @Override
    public boolean checkStructure2() {
        int tX = getOffsetXN(mFacing)-1, tY = yCoord, tZ = getOffsetZN(mFacing)-1;
        if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
            boolean tSuccess = T;

            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;

            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ  , 18116, getMultiTileEntityRegistryID(), mActive?2:7, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ  , 18116, getMultiTileEntityRegistryID(),mActive?2:7 , MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ  , 18116, getMultiTileEntityRegistryID(), mActive?2:7, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+1, 18116, getMultiTileEntityRegistryID(), mActive?2:7, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+1, 18116, getMultiTileEntityRegistryID(), mActive?2:7, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+2, 18116, getMultiTileEntityRegistryID(), mActive?2:7, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+2, 18116, getMultiTileEntityRegistryID(), mActive?2:7, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+2, 18116, getMultiTileEntityRegistryID(), mActive?2:7, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;

            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;

            return tSuccess;
        }
        return mStructureOkay;
    }

    static {
        LH.add("gt.tooltip.multiblock.maskaligner.1", "Bottom and Top layer: 3x3 of stainless steel walls");
        LH.add("gt.tooltip.multiblock.maskaligner.2", "Middle Layer: 3x3 ring of aligner units surrounding a stainless steel wall");
        LH.add("gt.tooltip.multiblock.maskaligner.3", "Main Block centered on Side-Bottom and facing outwards");
        LH.add("gt.tooltip.multiblock.maskaligner.4", "Bottom layer accepts Input items and fluids, Top layer and Main Block emit Output");
        LH.add("gt.tooltip.multiblock.maskaligner.5", "Energy only accepted at Aligner Units");
    }

    @Override
    public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
        aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
        aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.maskaligner.1"));
        aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.maskaligner.2"));
        aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.maskaligner.3"));
        aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.maskaligner.4"));
        aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.maskaligner.5"));
        super.addToolTips(aList, aStack, aF3_H);
    }

    @Override
    public boolean isInsideStructure(int aX, int aY, int aZ) {
        int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);
        return aX >= tX - 1 && aY >= tY && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 2 && aZ <= tZ + 1;
    }

    @Override
    public void updateAdjacentToggleableEnergySources() {
        DelegatorTileEntity<TileEntity> tDelegator = WD.te(worldObj, getOffsetXN(mFacing), yCoord-1, getOffsetZN(mFacing), SIDE_TOP, F);
        if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) {
            ((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
        }
    }

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

    @Override public boolean refreshStructureOnActiveStateChange() {return T;}

    @Override public String getTileEntityName() {return "gt.multitileentity.multiblock.maskaligner";}

}
