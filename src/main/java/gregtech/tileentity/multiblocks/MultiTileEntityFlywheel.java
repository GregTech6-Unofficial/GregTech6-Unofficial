package gregtech.tileentity.multiblocks;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.tileentity.ITileEntityUnloadable;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase11MultiBlockEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import static gregapi.data.CS.*;

/**
 * @author YueSha
 */
public class MultiTileEntityFlywheel extends TileEntityBase11MultiBlockEnergyStorage {

    MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");

    public short mBatteryCore = 18022;

    public int mTheBatteryLength = 0;

    @Override
    public long getDynamicCapacity() {
        return mTheBatteryLength*100000000;
    }

    @Override
    public void readFromNBT2(NBTTagCompound aNBT) {
        super.readFromNBT2(aNBT);
        if (aNBT.hasKey(NBT_DESIGN)) mBatteryCore = aNBT.getShort(NBT_DESIGN);
    }

    @Override
    public void writeToNBT2(NBTTagCompound aNBT) {
        super.writeToNBT2(aNBT);
    }

    @Override
    public boolean checkStructure2() {

        for (int mLength = 1; mLength <= 8; mLength++) {
            int
                    tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?mLength+1:1),
                    tMinY = yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?mLength+1:1),
                    tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?mLength+1:1),
                    tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?mLength+1:1),
                    tMaxY = yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?mLength+1:1),
                    tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?mLength+1:1),
                    tOutX = getOffsetXN(mFacing, mLength+1),
                    tOutY = getOffsetYN(mFacing, mLength+1),
                    tOutZ = getOffsetZN(mFacing, mLength+1);

            if (worldObj.blockExists(tMinX, tMinY, tMinZ) && worldObj.blockExists(tMaxX, tMaxY, tMaxZ)) {
                mEmitter = null;
                boolean tSuccess = T;
                for (int tX = tMinX; tX <= tMaxX; tX++) for (int tY = tMinY; tY <= tMaxY; tY++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) {
                    if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ, (SIDES_AXIS_X[mFacing] ? tX != tMinX && tX != tMaxX : SIDES_AXIS_Z[mFacing] ? tZ != tMinZ && tZ != tMaxZ : tY != tMinY && tY != tMaxY) ? 18040 : mBatteryCore, getMultiTileEntityRegistryID(), tX == tOutX && tY == tOutY && tZ == tOutZ ? 2 : 0, tX == tOutX && tY == tOutY && tZ == tOutZ ? MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN : MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
                }

                if (tSuccess) {mTheBatteryLength = mLength;} else continue;
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
        return "gt.multitileentity.multiblock.flywheel";
    }
}
