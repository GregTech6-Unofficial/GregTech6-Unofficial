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

    public short mBatteryCasing = 18022;

    public short[] mBatteryCores = {18040,18041,18042,18043,18044,18045};

    public int mCoreLength = 1;

    public int mSyncLength = 1;

    public byte mSyncBatteryCoreType = 0;

    public long mSyncBatteryCoreCapacity = 0;

    @Override
    public boolean checkStructure2() {

        for (int mLength = 1; mLength <= 8; mLength++)  for (byte i = 0; i <=5; i++) {

            short mBatteryCore = mBatteryCores[i];

            int
                    tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?mCoreLength+1:1),
                    tMinY = yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?mCoreLength+1:1),
                    tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?mCoreLength+1:1),
                    tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?mCoreLength+1:1),
                    tMaxY = yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?mCoreLength+1:1),
                    tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?mCoreLength+1:1),
                    tOutX = getOffsetXN(mFacing, mCoreLength+1),
                    tOutY = getOffsetYN(mFacing, mCoreLength+1),
                    tOutZ = getOffsetZN(mFacing, mCoreLength+1);

            if (worldObj.blockExists(tMinX, tMinY, tMinZ) && worldObj.blockExists(tMaxX, tMaxY, tMaxZ)) {
                mEmitter = null;
                boolean tSuccess = T;
                for (int tX = tMinX; tX <= tMaxX; tX++) for (int tY = tMinY; tY <= tMaxY; tY++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) {

                    if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ, (SIDES_AXIS_X[mFacing] ? tX != tMinX && tX != tMaxX : SIDES_AXIS_Z[mFacing] ? tZ != tMinZ && tZ != tMaxZ : tY != tMinY && tY != tMaxY) ? mBatteryCore : mBatteryCasing, getMultiTileEntityRegistryID(), tX == tOutX && tY == tOutY && tZ == tOutZ ? 2 : 0, tX == tOutX && tY == tOutY && tZ == tOutZ ? MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN : MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
                }

                if (!tSuccess) {
                    if (mLength == 8) {mCoreLength = 1;}
                    else {mCoreLength = mLength + 1;}
                    continue;
                } else {
                    mSyncBatteryCoreType = i;
                    mSyncLength = mCoreLength;

                    mSyncBatteryCoreCapacity = this.getBatteryCoreCapacity();
                    mStorage.mCapacity = mSyncLength*9*mSyncBatteryCoreCapacity;
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
        for (int mLength = 1; mLength <= 8; mLength++) {
            result =
                            aX >= xCoord - (SIDE_X_NEG == mFacing ? 0 : SIDE_X_POS == mFacing ? mLength+1 : 1) &&
                            aY >= yCoord - (SIDE_Y_NEG == mFacing ? 0 : SIDE_Y_POS == mFacing ? mLength+1 : 1) &&
                            aZ >= zCoord - (SIDE_Z_NEG == mFacing ? 0 : SIDE_Z_POS == mFacing ? mLength+1 : 1) &&
                            aX <= xCoord + (SIDE_X_POS == mFacing ? 0 : SIDE_X_NEG == mFacing ? mLength+1 : 1) &&
                            aY <= yCoord + (SIDE_Y_POS == mFacing ? 0 : SIDE_Y_NEG == mFacing ? mLength+1 : 1) &&
                            aZ <= zCoord + (SIDE_Z_POS == mFacing ? 0 : SIDE_Z_NEG == mFacing ? mLength+1 : 1);
            if (!result) continue;
            return result;
        }
        return result;
    }

    @Override
    public void onMagnifyingGlass2(List<String> aChatReturn) {
        super.onMagnifyingGlass2(aChatReturn);
        String mBatteryCoreName = this.getBatteryCoreName();
        aChatReturn.add("Battery Core: " + mBatteryCoreName);
        aChatReturn.add("Voltage Level: " + mEnergyOUT.mRec);
    }

    public String getBatteryCoreName() {
        switch(mSyncBatteryCoreType) {
            case 0: return "Core 1";
            case 1: return "Core 2";
            case 2: return "Core 3";
            case 3: return "Core 4";
            case 4: return "Core 5";
            case 5: return "Core 6";
        }
        return "No Battery Core!";
    }

    public long getBatteryCoreCapacity() {
        switch(mSyncBatteryCoreType) {
            case 0: return V[0]*512000;
            case 1: return V[1]*512000;
            case 2: return V[2]*512000;
            case 3: return V[3]*512000;
            case 4: return V[4]*512000;
            case 5: return V[5]*512000;
        }
        return 0;
    }

    public long getBatteryInputVoltage() {
        return mSyncLength*2048;
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
