/**
 * Copyright (c) 2020 GT6U-Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregapi.tileentity.behavior;

import gregapi.data.TD;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import static gregapi.data.CS.*;

/**
 * @author YueSha
 */
public class TE_Behavior_Energy_Buffer_Converter extends TE_Behavior {
	public TE_Behavior_Energy_Stats mEnergyIN, mEnergyOUT;
	public TE_Behavior_Energy_Capacitor mStorage;
	public boolean mWasteEnergy = F, mLimitConsumption = F, mOverloaded = F, mEmitsEnergy = F, mCanEmitEnergy = F, mSizeIrrelevant = F, mFast = F;
	public long mMultiplier = 1;
	public byte mFactor = 1;

	public TE_Behavior_Energy_Buffer_Converter(TileEntity aTileEntity, NBTTagCompound aNBT, TE_Behavior_Energy_Capacitor aStorage, TE_Behavior_Energy_Stats aEnergyIN, TE_Behavior_Energy_Stats aEnergyOUT, long aMultiplier, boolean aWasteEnergy, boolean aNegativeOutput, boolean aLimitConsumption) {
		super(aTileEntity, aNBT);
		mStorage = aStorage;
		mEnergyIN = aEnergyIN;
		mEnergyOUT = aEnergyOUT;
		mMultiplier = aMultiplier;
		mWasteEnergy = aWasteEnergy;
		mSizeIrrelevant = TD.Energy.ALL_SIZE_IRRELEVANT.contains(mEnergyOUT.mType);
		mLimitConsumption = aLimitConsumption;
		if (aNegativeOutput) mFactor = -1;
	}
	
	@Override
	public void load(NBTTagCompound aNBT) {
		mEmitsEnergy = aNBT.getBoolean(NBT_ACTIVE_ENERGY);
		mCanEmitEnergy = aNBT.getBoolean(NBT_CAN_ENERGY);
	}
	
	@Override
	public void save(NBTTagCompound aNBT) {
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE_ENERGY, mEmitsEnergy);
		UT.NBT.setBoolean(aNBT, NBT_CAN_ENERGY, mCanEmitEnergy);
	}
	
	public boolean onTickEnergy(long aTimer, TileEntity aEmitter, byte aSide, byte aMode, boolean aNegative) {
		long tOutput = mEnergyOUT.mRec;
		long tEmittedPackets;
		mCanEmitEnergy = mStorage.mEnergy >= tOutput;
		mEmitsEnergy = F;
		if (mCanEmitEnergy) {

			if (SIDES_VALID[aSide]) {
				tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyOUT.mType, aNegative ? -tOutput*mFactor : tOutput*mFactor, mMultiplier, (ITileEntityEnergy)aEmitter);
			} else {
				tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToSide   (mEnergyOUT.mType, aSide, aNegative ? -tOutput*mFactor : tOutput*mFactor, mMultiplier, aEmitter);
			}

			if (tEmittedPackets > 0) {
				mStorage.mEnergy = Math.max(0, mStorage.mEnergy - UT.Code.units(tEmittedPackets * tOutput, mEnergyOUT.mRec * mMultiplier, mEnergyIN.mRec, T));
				mEmitsEnergy = T;
			}

		}


		return mCanEmitEnergy;
	}
}
