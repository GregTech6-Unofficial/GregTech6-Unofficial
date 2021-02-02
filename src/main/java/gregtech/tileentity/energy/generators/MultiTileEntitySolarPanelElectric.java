/**
 * Copyright (c) 2021 GregTech6-Unofficial Team
 *
 * This file is part of gregtech6.
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
 * along with gregtech6. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.tileentity.energy.generators;

import static gregapi.data.CS.*;

import gregapi.code.TagData;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Capacitor;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Converter;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Stats;
import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi.tileentity.energy.TileEntityBase10EnergyConverter;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

public class MultiTileEntitySolarPanelElectric extends TileEntityBase10EnergyConverter implements ITileEntityEnergyElectricityAcceptor, ITileEntityRunningActively, ITileEntitySwitchableOnOff {
	
	@Override
	public void readEnergyBehavior(NBTTagCompound aNBT) {
		long tInput = aNBT.getLong(NBT_INPUT), tOutput = aNBT.getLong(NBT_OUTPUT);
		mStorage    = new TE_Behavior_Energy_Capacitor  (this, aNBT, tInput * 16);
		mEnergyIN   = new TE_Behavior_Energy_Stats      (this, aNBT, aNBT.hasKey(NBT_ENERGY_ACCEPTED) ? TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED)) : TD.Energy.LP   , mStorage, takesAnyLowerSize() || tInput <= 16 ? 1 : tInput / 2, tInput, tInput * 16);
		mEnergyOUT  = new TE_Behavior_Energy_Stats      (this, aNBT, aNBT.hasKey(NBT_ENERGY_EMITTED ) ? TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED )) : mEnergyIN.mType, mStorage, emitsAnyLowerSize() ? 1 : tOutput / 2, tOutput, tOutput * 16);
	}

	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		if (aDoInject) mNegativeInput = (aSize < 0);
		long tConsumed = mConverter.mEnergyIN.doInject(aSize, aAmount, aDoInject);
		if (mConverter.mEnergyIN.mOverloaded) {
			overload(aSize, aEnergyType);
			mConverter.mEnergyIN.mOverloaded = F;
		}
		return tConsumed;
	}
	
	@Override
	public void readEnergyConverter(NBTTagCompound aNBT) {
		mConverter  = new TE_Behavior_Energy_Converter  (this, aNBT, mStorage, mEnergyIN, mEnergyOUT, aNBT.hasKey(NBT_MULTIPLIER) ? aNBT.getLong(NBT_MULTIPLIER) : 1, aNBT.getBoolean(NBT_WASTE_ENERGY), F, aNBT.hasKey(NBT_LIMIT_CONSUMPTION) ? aNBT.getBoolean(NBT_LIMIT_CONSUMPTION) : TD.Energy.ALL_COMSUMPTION_LIMITED.contains(mEnergyIN.mType));
	}

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPPOSITES[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get(sOverlays[mActivity.mState][aIndex]));
	}

	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/colored/side"),
	}, sOverlays[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_active/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_blinking/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_blinking/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_blinking/side"),
	}};

	@Override public String getTileEntityName() {return "gt.multitileentity.SolarPanel";}
}