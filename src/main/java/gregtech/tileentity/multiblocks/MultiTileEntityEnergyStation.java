package gregtech.tileentity.multiblocks;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.tileentity.ITileEntityUnloadable;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase11MultiBlockEnergyStorage;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author YueSha
 */
public class MultiTileEntityEnergyStation extends TileEntityBase11MultiBlockEnergyStorage {

    MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");

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

        for (int coreLength = 1; coreLength <= 8; coreLength++) for (byte halfCoreWidth = 0; halfCoreWidth<=3; halfCoreWidth++) for (byte coreIterator = 0; coreIterator <=5; coreIterator++) for (byte electrodeIterator = 0; electrodeIterator <=1; electrodeIterator++) {

            short mBatteryCore = aBatteryCores[coreIterator];
            short mElectrode = aElectrodes[electrodeIterator];

            int
                    tMinX = xCoord-(SIDE_X_NEG==mFacing?-1:SIDE_X_POS==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMinY = yCoord-(SIDE_Y_NEG==mFacing?-1:SIDE_Y_POS==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMinZ = zCoord-(SIDE_Z_NEG==mFacing?-1:SIDE_Z_POS==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMaxX = xCoord+(SIDE_X_POS==mFacing?-1:SIDE_X_NEG==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMaxY = yCoord+(SIDE_Y_POS==mFacing?-1:SIDE_Y_NEG==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tMaxZ = zCoord+(SIDE_Z_POS==mFacing?-1:SIDE_Z_NEG==mFacing? mBatteryCoreLength +2:halfCoreWidth),
                    tOutX = getOffsetXN(mFacing, mBatteryCoreLength + 3),
                    tOutY = getOffsetYN(mFacing, mBatteryCoreLength + 3),
                    tOutZ = getOffsetZN(mFacing, mBatteryCoreLength + 3);

            if (worldObj.blockExists(tMinX, tMinY, tMinZ) && worldObj.blockExists(tMaxX, tMaxY, tMaxZ)) {
                mEmitter = null;
                boolean tSuccess = T;
                for (int tX = tMinX-1; tX <= tMaxX+1; tX++) for (int tY = tMinY-1; tY <= tMaxY+1; tY++) for (int tZ = tMinZ-1; tZ <= tMaxZ+1; tZ++) {

                    if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ,
                              (tX == tMinX-1 || tX == tMaxX+1 || tY == tMinY-1 || tY == tMaxY+1 || tZ == tMinZ-1 || tZ == tMaxZ+1) ? mBatteryCasing
                            : (SIDES_AXIS_X[mFacing] ? tX == tMinX || tX == tMaxX: SIDES_AXIS_Z[mFacing] ? tZ == tMinZ-1 || tZ == tMaxZ : tY == tMinY || tY == tMaxY) ? mElectrode
                            : mBatteryCore
                            , getMultiTileEntityRegistryID(), tX == tOutX && tY == tOutY && tZ == tOutZ ? 2 : 0, tX == tOutX && tY == tOutY && tZ == tOutZ ? MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN : MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
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
        for (int coreLength = 1; coreLength <= 8; coreLength++) for (byte halfCoreWidth = 0; halfCoreWidth<=3; halfCoreWidth++) {
            result =
                            aX >= xCoord - (SIDE_X_NEG == mFacing ? -1 : SIDE_X_POS == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aY >= yCoord - (SIDE_Y_NEG == mFacing ? -1 : SIDE_Y_POS == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aZ >= zCoord - (SIDE_Z_NEG == mFacing ? -1 : SIDE_Z_POS == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aX <= xCoord + (SIDE_X_POS == mFacing ? -1 : SIDE_X_NEG == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aY <= yCoord + (SIDE_Y_POS == mFacing ? -1 : SIDE_Y_NEG == mFacing ? coreLength+2 : halfCoreWidth) &&
                            aZ <= zCoord + (SIDE_Z_POS == mFacing ? -1 : SIDE_Z_NEG == mFacing ? coreLength+2 : halfCoreWidth);
            if (!result) continue;
            return result;
        }
        return result;
    }

    @Override
    public void onMagnifyingGlass2(List<String> aChatReturn) {
        super.onMagnifyingGlass2(aChatReturn);
        String mBatteryCoreName = this.getBatteryCoreName();
        String mElectrodeName = this.getElectodeName();
        aChatReturn.add("Core Type: " + mBatteryCoreName);
        aChatReturn.add("Electrode Type: " + mElectrodeName);
        aChatReturn.add("Voltage Level: " + mEnergyOUT.mRec);
        aChatReturn.add("Core Amount:  " + mSyncBatteryCoreWidth * mSyncBatteryCoreWidth * mSyncBatteryCoreLength);
        aChatReturn.add("Energy Stored: " + mStorage.mEnergy + mEnergyIN.mType.getLocalisedNameShort() + "/" + mStorage.mCapacity + mEnergyIN.mType.getLocalisedNameShort());
    }

    public String getBatteryCoreName() {
        switch(mSyncBatteryCoreType) {
            case 0: return "Lead-Acid";
            case 1: return "Alkaline";
            case 2: return "Nickel-Cadmium";
            case 3: return "Lithium-Cobalt";
            case 4: return "Lithium-Manganese";
            case 5: return "Lithium-Phosphate";
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
            case 0: return V[0]*256000;
            case 1: return V[1]*256000;
            case 2: return V[2]*256000;
            case 3: return V[3]*256000;
            case 4: return V[4]*256000;
            case 5: return V[5]*256000;
        }
        return 0;
    }

    public long getBatteryInputVoltage() {
        switch(mSyncElectrodeType) {
            case 0: return mSyncBatteryCoreLength * 2048;
            case 1: return mSyncBatteryCoreLength * 4096;
        }
        return 0;
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
        return "gt.multitileentity.multiblock.energystation";
    }
}
