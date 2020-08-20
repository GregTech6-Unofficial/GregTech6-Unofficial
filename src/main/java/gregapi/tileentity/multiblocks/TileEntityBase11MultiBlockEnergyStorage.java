package gregapi.tileentity.multiblocks;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.data.TD;
import gregapi.tileentity.behavior.*;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author YueSha
 */
public abstract class TileEntityBase11MultiBlockEnergyStorage extends TileEntityBase10MultiBlockBase implements ITileEntityEnergy, IMultiBlockEnergy, ITileEntityRunningActively {
    public long mCapacity = 99999999;

    protected boolean mStopped = F;
    protected byte mExplosionPrevention = 0;

    public TE_Behavior_Energy_Stats mEnergyIN = null, mEnergyOUT = null;
    public TE_Behavior_Energy_Capacitor mStorage = null;
    public TE_Behavior_Energy_Buffer_Converter mBufferConverter = null;
    public TE_Behavior_Active_Trinary mActivity = null;

    public long getDynamicCapacity() {
        return mCapacity;
    }

    @Override
    public void readFromNBT2(NBTTagCompound aNBT) {
        super.readFromNBT2(aNBT);
        if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
        mActivity = new TE_Behavior_Active_Trinary(this, aNBT);
        readEnergyBehavior(aNBT);
        readEnergyConverter(aNBT);
    }

    @Override
    public void writeToNBT2(NBTTagCompound aNBT) {
        super.writeToNBT2(aNBT);
        UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
        mActivity.save(aNBT);
        writeEnergyBehavior(aNBT);
    }

    public void readEnergyBehavior(NBTTagCompound aNBT) {
        long tInput = aNBT.getLong(NBT_INPUT), tOutput = aNBT.getLong(NBT_OUTPUT);
        mStorage    = new TE_Behavior_Energy_Capacitor  (this, aNBT, this.getDynamicCapacity());
        mEnergyIN   = new TE_Behavior_Energy_Stats      (this, aNBT, aNBT.hasKey(NBT_ENERGY_ACCEPTED) ? TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED)) : TD.Energy.QU   , mStorage, tInput <= 16 ? 1 : tInput / 2, tInput, tInput * 2);
        mEnergyOUT  = new TE_Behavior_Energy_Stats      (this, aNBT, aNBT.hasKey(NBT_ENERGY_EMITTED ) ? TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED )) : mEnergyIN.mType, mStorage, tOutput / 2, tOutput, tOutput * 2);
    }

    public void readEnergyConverter(NBTTagCompound aNBT) {
        mBufferConverter = new TE_Behavior_Energy_Buffer_Converter  (this, aNBT, mStorage, mEnergyIN, mEnergyOUT, aNBT.hasKey(NBT_MULTIPLIER) ? aNBT.getLong(NBT_MULTIPLIER) : 1, aNBT.getBoolean(NBT_WASTE_ENERGY), F, aNBT.hasKey(NBT_LIMIT_CONSUMPTION) ? aNBT.getBoolean(NBT_LIMIT_CONSUMPTION) : TD.Energy.ALL_COMSUMPTION_LIMITED.contains(mEnergyIN.mType));
    }

    public void writeEnergyBehavior(NBTTagCompound aNBT) {
        mStorage.save(aNBT);
        mBufferConverter.save(aNBT);
    }

    @Override
    public void onMagnifyingGlass2(List<String> aChatReturn) {
        aChatReturn.add("Structure is formed already!");
        aChatReturn.add("Energy Stored: " + mStorage.mEnergy + "EU/" + mStorage.mCapacity + "EU");
    }

    @Override
    public void onTick2(long aTimer, boolean aIsServerSide) {
        super.onTick2(aTimer, aIsServerSide);
        if (aIsServerSide && checkStructure(F)) {
            doEmitEnergy(aTimer);
        }
    }

    public void doEmitEnergy(long aTimer) {
        mActivity.mActive = mBufferConverter.doEmitEnergy(aTimer, getEmittingTileEntity(), getEmittingSide(), (byte)0, F);
    }

    @Override
    public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
        aSize = Math.abs(aSize);

        long tConsumed = mEnergyIN.doInject(aSize, aAmount, aDoInject);
        // if(aDoInject) mStorage.mEnergy += tConsumed * aSize;
        return tConsumed;
    }

    @Override public boolean onTickCheck(long aTimer) {return mActivity.check(mStopped) || super.onTickCheck(aTimer);}
    @Override public void setVisualData(byte aData) {mActivity.mState = aData;}
    @Override public byte getVisualData() {return mActivity.mState;}

    public abstract TileEntity getEmittingTileEntity();
    public abstract byte getEmittingSide();

    @Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting ? aEnergyType == mEnergyOUT.mType : aEnergyType == mEnergyIN.mType;}
    @Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || (!mStopped && (mBufferConverter.mWasteEnergy || (mBufferConverter.mEmitsEnergy == mBufferConverter.mCanEmitEnergy)))) && (SIDES_INVALID[aSide] || isInput (aSide)) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
    @Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return                                                                                                                         (SIDES_INVALID[aSide] || isOutput(aSide)) && super.isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
    @Override public long getEnergySizeOutputMin            (TagData aEnergyType, byte aSide) {return mEnergyOUT.sizeMin(aEnergyType);}
    @Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mEnergyOUT.sizeRec(aEnergyType);}
    @Override public long getEnergySizeOutputMax            (TagData aEnergyType, byte aSide) {return mEnergyOUT.sizeMax(aEnergyType);}
    @Override public long getEnergySizeInputMin             (TagData aEnergyType, byte aSide) {return mEnergyIN .sizeMin(aEnergyType);}
    @Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mEnergyIN .sizeRec(aEnergyType);}
    @Override public long getEnergySizeInputMax             (TagData aEnergyType, byte aSide) {return mEnergyIN .sizeMax(aEnergyType);}
    @Override public Collection<TagData> getEnergyTypes(byte aSide) {return new ArrayListNoNulls<>(F, mEnergyIN.mType, mEnergyOUT.mType);}

    @Override public boolean getStateRunningPossible() {return T;}
    @Override public boolean getStateRunningPassively() {return mActivity.mActive;}
    @Override public boolean getStateRunningActively() {return mBufferConverter.mEmitsEnergy;}
    public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
    public boolean getStateOnOff() {return !mStopped;}

    @Override public boolean canDrop(int aInventorySlot) {return T;}

    @Override public byte getDefaultSide() {return SIDE_BOTTOM;}

    public boolean isInput (byte aSide) {return aSide == mFacing;}
    public boolean isOutput(byte aSide) {return aSide != mFacing;}
}
