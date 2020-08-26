package gregtech.tileentity.multiblocks;

import gregapi.data.LH;
import gregapi.tileentity.ITileEntityUnloadable;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase11MultiBlockEnergyStorage;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author YueSha
 */
public class MultiTileEntityMultiblockEnergyStorageUnit extends TileEntityBase11MultiBlockEnergyStorage {

    public short mBatteryCasing = 18122;

    public short[] aBatteryCores = {18124,18125,18126,18127,18128,18129};

    public short[] aElectrodes = {18120, 18121};

    public int mBatteryCoreLength = 1;

    public int mSyncBatteryCoreLength = 1;

    public int mSyncBatteryCoreWidth = 0;

    public byte mSyncBatteryCoreType = 0;

    public byte mSyncElectrodeType = 0;

    public long mSyncBatteryCoreCapacity = 0;

    @Override
    public boolean checkStructure2() {

        for (int coreLength = 1; coreLength <= 8; coreLength++) for (byte halfCoreWidth = 0; halfCoreWidth<=2; halfCoreWidth++) for (byte coreIterator = 0; coreIterator <=5; coreIterator++) for (byte electrodeIterator = 0; electrodeIterator <=1; electrodeIterator++) {

            short mBatteryCore = aBatteryCores[coreIterator];
            short mElectrode = aElectrodes[electrodeIterator];

            int
                    tMinX = xCoord-(SIDE_X_NEG==mFacing?-1:SIDE_X_POS==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMinY = yCoord-(SIDE_Y_NEG==mFacing?-1:SIDE_Y_POS==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMinZ = zCoord-(SIDE_Z_NEG==mFacing?-1:SIDE_Z_POS==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMaxX = xCoord+(SIDE_X_POS==mFacing?-1:SIDE_X_NEG==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMaxY = yCoord+(SIDE_Y_POS==mFacing?-1:SIDE_Y_NEG==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMaxZ = zCoord+(SIDE_Z_POS==mFacing?-1:SIDE_Z_NEG==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tInX = getOffsetXN(mFacing, mBatteryCoreLength + 3),
                    tInY = getOffsetYN(mFacing, mBatteryCoreLength + 3),
                    tInZ = getOffsetZN(mFacing, mBatteryCoreLength + 3);

            if (worldObj.checkChunksExist(tMinX, tMinY, tMinZ, tMaxX, tMaxY, tMaxZ)) {
                mEmitter = null;
                boolean tSuccess = T;
                for (int tX = tMinX-1; tX <= tMaxX+1; tX++) for (int tY = tMinY-1; tY <= tMaxY+1; tY++) for (int tZ = tMinZ-1; tZ <= tMaxZ+1; tZ++) {

                    if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ,
                              (tX == tMinX-1 || tX == tMaxX+1 || tY == tMinY-1 || tY == tMaxY+1 || tZ == tMinZ-1 || tZ == tMaxZ+1) ? mBatteryCasing
                            : (SIDES_AXIS_X[mFacing] ? tX == tMinX || tX == tMaxX: SIDES_AXIS_Z[mFacing] ? tZ == tMinZ || tZ == tMaxZ : tY == tMinY || tY == tMaxY) ? mElectrode
                            : mBatteryCore
                            , getMultiTileEntityRegistryID(), 0,
                            (SIDES_AXIS_X[mFacing] ? tX == tInX : SIDES_AXIS_Z[mFacing] ? tZ == tInZ : tY == tInY) ? MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN : MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
                }

                if (!tSuccess) {
                    if (coreLength == 8) {
                        mBatteryCoreLength = 1;}
                    else {
                        mBatteryCoreLength = coreLength + 1;}
                    continue;
                } else {
                    mSyncBatteryCoreType = coreIterator;
                    mSyncElectrodeType = electrodeIterator;

                    mSyncBatteryCoreWidth = halfCoreWidth*2+1;
                    mSyncBatteryCoreLength = mBatteryCoreLength;

                    mSyncBatteryCoreCapacity = this.getBatteryCoreCapacity();
                    mStorage.mCapacity = mSyncBatteryCoreLength * mSyncBatteryCoreWidth * mSyncBatteryCoreWidth * mSyncBatteryCoreCapacity;

                    mEnergyIN.mRec = mEnergyOUT.mRec = this.getBatteryInputVoltage();
                    mEnergyIN.mMax = mEnergyOUT.mMax = this.getBatteryInputVoltage()*2;
                    mEnergyOUT.mMin = this.getBatteryInputVoltage();
                }

                return tSuccess;
            }
            return mStructureOkay;
        }
        return F;
    }

    @Override
    public boolean isInsideStructure(int aX, int aY, int aZ) {
        boolean result = F;
        for (int coreLength = 1; coreLength <= 8; coreLength++) for (byte halfCoreWidth = 0; halfCoreWidth<=2; halfCoreWidth++) {
            result =
                            aX >= xCoord - (SIDE_X_NEG == mFacing ? 0: SIDE_X_POS == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aY >= yCoord - (SIDE_Y_NEG == mFacing ? 0: SIDE_Y_POS == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aZ >= zCoord - (SIDE_Z_NEG == mFacing ? 0: SIDE_Z_POS == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aX <= xCoord + (SIDE_X_POS == mFacing ? 0: SIDE_X_NEG == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aY <= yCoord + (SIDE_Y_POS == mFacing ? 0: SIDE_Y_NEG == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aZ <= zCoord + (SIDE_Z_POS == mFacing ? 0: SIDE_Z_NEG == mFacing ? coreLength+2 : halfCoreWidth);
            if (!result) continue;
            return result;
        }
        return result;
    }

    static {
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.1", "Casing Structure: 3x5x3 to 7x12x7 hollow cube of MESU Casings");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.2", "Core Structure: 1x3x1 to 5x10x5 cube filled inside Casing");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.3", "The Core consists Battery Cores between two plates of Electrode Parts");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.4", "Main Block centered on Side parallel to electrode plate and facing outwards");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.5", "Energy output at Main Block, input at the opposite face of the Casing");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.6", "Type and amount of Battery Core used affects its capacity");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.7", "Type of Electrode Part used affects its voltage per block");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.8", "Longer Core (excludes Electrodes) has greater voltage");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.9", "See energy properties with a magnifying glass");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.10","Change output current with a screwdriver (sneak to toggle larger value)");
        LH.add("gt.tooltip.multiblock.multiblockenergystorageunit.11","Have AT LEAST 7 blocks in all directions between two MESU Mainblocks");
    }

    @Override
    public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
        aList.add(LH.Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.1"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.2"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.3"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.4"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.5"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.6"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.7"));
        aList.add(LH.Chat.WHITE    + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.8"));
        aList.add(LH.Chat.GRAY     + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.9"));
        aList.add(LH.Chat.GRAY     + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.10"));
        aList.add(LH.Chat.RED      + LH.get("gt.tooltip.multiblock.multiblockenergystorageunit.11"));
        aList.add(LH.Chat.GREEN    + LH.get(LH.ENERGY_OUTPUT) + ": " + LH.Chat.WHITE + 2048 + " - " + 65536 + " " + mEnergyOUT.mType.getChatFormat() + mEnergyOUT.mType.getLocalisedNameShort() + LH.Chat.WHITE + "/t (up to 32 Amps) depending on structure");
        aList.add(LH.Chat.GREEN    + "Capacity: " + LH.Chat.WHITE + "131072000 - 157286400000" + " " + mEnergyOUT.mType.getChatFormat() + mEnergyOUT.mType.getLocalisedNameShort() +  LH.Chat.WHITE + " depending on structure");
        aList.add(LH.Chat.GREEN    + "Base maintenance cost: " + LH.Chat.WHITE + "10 " + mEnergyOUT.mType.getChatFormat() + mEnergyOUT.mType.getLocalisedNameShort() + LH.Chat.WHITE + "/t per battery core");
        super.addToolTips(aList, aStack, aF3_H);
    }

    @Override
    public void onTickEnergy(long aTimer) {
        super.onTickEnergy(aTimer);
        int mCoreAmount = mSyncBatteryCoreLength * mSyncBatteryCoreWidth * mSyncBatteryCoreWidth;
        mStorage.mEnergy = Math.max(0, mStorage.mEnergy - Math.max(mCoreAmount*10, 1));
    }

    public String getBatteryCoreName() {
        switch(mSyncBatteryCoreType) {
            case 0: return "Lead-Acid";
            case 1: return "Alkaline";
            case 2: return "Nickel-Cadmium";
            case 3: return "Lithium-Cobalt";
            case 4: return "Lithium-Manganese";
            case 5: return "Lithium-F-Phosphate";
        }
        return "No Battery Core!";
    }

    public String getElectodeName() {
        switch(mSyncElectrodeType) {
            case 0: return "Graphite";
            case 1: return "Graphene";
        }
        return "No Electrode!";
    }

    public long getBatteryCoreCapacity() {
        switch(mSyncBatteryCoreType) {
            case 0: return V[4]*64000;
            case 1: return V[4]*96000;
            case 2: return V[4]*128000;
            case 3: return V[4]*192000;
            case 4: return V[4]*256000;
            case 5: return V[4]*384000;
        }
        return 0;
    }

    public long getBatteryInputVoltage() {
        switch(mSyncElectrodeType) {
            case 0: return mSyncBatteryCoreLength * 2048;
            case 1: return mSyncBatteryCoreLength * 8192;
        }
        return 0;
    }

    @Override
    public void onMagnifyingGlass(List<String> aChatReturn) {
        if (checkStructure(F)) {
            onMagnifyingGlass2(aChatReturn);
        } else {
            if (checkStructure(T)) {
                aChatReturn.add("Structure did form just now!");
            } else {
                aChatReturn.add("Structure did not form!");
                aChatReturn.add("If you think you have the structure correct:");
                aChatReturn.add("1. Re-place the mainblock");
                aChatReturn.add("2. Another MESU might be to close (< 7 block) to this one");
            }
        }
    }

    @Override
    public void onMagnifyingGlass2(List<String> aChatReturn) {
        super.onMagnifyingGlass2(aChatReturn);
        String mBatteryCoreName = this.getBatteryCoreName();
        String mElectrodeName = this.getElectodeName();
        aChatReturn.add("Core Type: " + mBatteryCoreName);
        aChatReturn.add("Electrode Type: " + mElectrodeName);
        aChatReturn.add("Voltage: " + mEnergyOUT.mRec + "V");
        aChatReturn.add("Current: " + mBufferConverter.mMultiplier + "A");
        aChatReturn.add("Core Amount: " + mSyncBatteryCoreWidth * mSyncBatteryCoreWidth * mSyncBatteryCoreLength);
        aChatReturn.add("Energy Stored: " + mStorage.mEnergy + mEnergyIN.mType.getLocalisedNameShort() + "/" + mStorage.mCapacity + mEnergyIN.mType.getLocalisedNameShort());
    }

    @Override
    public void onScrewdriver(List<String> aChatReturn) {
        super.onScrewdriver(aChatReturn);
        if (mBufferConverter.mMultiplier == 32) mBufferConverter.mMultiplier = 1;
        else mBufferConverter.mMultiplier += 1;
        aChatReturn.add("Output Current: " + mBufferConverter.mMultiplier + "A");
    }

    @Override
    public void onScrewdriverSneaking(List<String> aChatReturn) {
        super.onScrewdriverSneaking(aChatReturn);
        if (mBufferConverter.mMultiplier < 32 && mBufferConverter.mMultiplier > 28)  mBufferConverter.mMultiplier = 32;
        if (mBufferConverter.mMultiplier == 32) mBufferConverter.mMultiplier = 1;
        else mBufferConverter.mMultiplier += 4;
        aChatReturn.add("Output Current: " + mBufferConverter.mMultiplier + "A");
    }

    public ITileEntityUnloadable mEmitter = null;

    @Override
    public TileEntity getEmittingTileEntity() {
        if (mEmitter == null || mEmitter.isDead()) {
            mEmitter = null;
            TileEntity tTileEntity = this;
            if (tTileEntity instanceof ITileEntityUnloadable) mEmitter = (ITileEntityUnloadable) tTileEntity;
        }
        return mEmitter == null ? this : (TileEntity) mEmitter;
    }

    @Override
    public byte getEmittingSide() {
        return mFacing;
    }

    @Override
    public boolean isInput(byte aSide) {
        return aSide == OPPOSITES[mFacing];
    }

    @Override
    public boolean isOutput(byte aSide) {
        return aSide == mFacing;
    }

    @Override
    public byte getDefaultSide() {
        return SIDE_BOTTOM;
    }

    @Override
    public boolean[] getValidSides() {
        return SIDES_VALID;
    }

    @Override
    public String getTileEntityName() {
        return "gt.multitileentity.multiblock.multiblockenergystorageunit";
    }
}
