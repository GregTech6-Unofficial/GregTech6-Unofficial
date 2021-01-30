/**
 * Copyright (c) 2021 GT6U 
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

package gregapi.tileentity.connectors;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetDebugInfo;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataConductor;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityOpticalFiber extends TileEntityBase10ConnectorRendered implements ITileEntityQuickObstructionCheck, ITileEntityEnergy, ITileEntityEnergyDataConductor, ITileEntityProgress, IMTE_GetDebugInfo, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	public long mTransferredAmperes = 0, mTransferredWattage = 0, mWattageLast = 0, mLoss = 0, mAmperage = 1, mVoltage = 32;
	public byte mRenderType = 0, mBurnCounter = 0;

	/**
	 * Utility to quickly add a whole set of Electric Wires.
	 * May use up to 50 IDs, even if it is just 11 right now!
	 */
	public static void addOpticalFiber(int aID, int aCreativeTabID, long aVoltage, long aAmperage, long aLossWire, long aLossCable, boolean aContactDamageWire, boolean aContactDamageCable, boolean aCable, MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aBlock, Class<? extends TileEntity> aClass, OreDictMaterial aMat) {
	}

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_PIPELOSS)) mLoss = Math.max(0, aNBT.getLong(NBT_PIPELOSS));
		if (aNBT.hasKey(NBT_PIPESIZE)) mVoltage = Math.max(1, aNBT.getLong(NBT_PIPESIZE));
		if (aNBT.hasKey(NBT_PIPEBANDWIDTH)) mAmperage = Math.max(1, aNBT.getLong(NBT_PIPEBANDWIDTH));
		if (aNBT.hasKey(NBT_PIPERENDER)) mRenderType = aNBT.getByte(NBT_PIPERENDER);
	}

	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_VOLTAGE) + mVoltage + " " + TD.Energy.LP.getLocalisedNameShort() + " (" + VN[UT.Code.tierMin(mVoltage)] + ")");
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_AMPERAGE) + mAmperage);
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_LOSS) + mLoss + " " + TD.Energy.LP.getLocalisedNameShort() + "/m");
		if (mContactDamage) aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);

		if (aIsServerSide) {
			if (mBurnCounter >= 16) {
				setToFire();
			} else {
				if (aTimer % 512 == 2 && mBurnCounter > 0) mBurnCounter--;
				mWattageLast = mTransferredWattage;
				mTransferredWattage = 0;
				mTransferredAmperes = 0;
			}
		}
	}

	public long transferElectricity(byte aSide, long aVoltage, long aAmperage, long aChannel, HashSetNoNulls<TileEntity> aAlreadyPassed) {
		if (mTimer < 1 || Math.abs(aVoltage) <= mLoss) return 0;
		if (aVoltage > 0) aVoltage -= mLoss; else aVoltage += mLoss;

		long rUsedAmperes = 0;
		for (byte tSide : ALL_SIDES_VALID_BUT[aSide]) if (canEmitEnergyTo(tSide)) {
			if (aAmperage <= rUsedAmperes) break;
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (aAlreadyPassed.add(tDelegator.mTileEntity)) {
				if (tDelegator.mTileEntity instanceof MultiTileEntityOpticalFiber) {
					if (((MultiTileEntityOpticalFiber)tDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.LP, tDelegator.mSideOfTileEntity, F)) {
						rUsedAmperes += ((MultiTileEntityOpticalFiber)tDelegator.mTileEntity).transferElectricity(tDelegator.mSideOfTileEntity, aVoltage, aAmperage, aChannel, aAlreadyPassed);
					}
				} else {
						rUsedAmperes += ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.LP, aVoltage, aAmperage, this, tDelegator);
				}
			}
		}
		return rUsedAmperes > 0 ? addToEnergyTransferred(aVoltage, rUsedAmperes) ? rUsedAmperes : aAmperage : 0;
	}

	public boolean addToEnergyTransferred(long aVoltage, long aAmperage) {
		mTransferredAmperes += aAmperage;
		mTransferredWattage += Math.abs(aVoltage * aAmperage);
		if (Math.abs(aVoltage) > mVoltage || mTransferredAmperes > mAmperage) {
			if (mBurnCounter < 16) mBurnCounter++;
			return F;
		}
		return T;
	}

	@Override public boolean canConnect(byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof ITileEntityEnergy) return ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.LP, aDelegator.mSideOfTileEntity, T) || ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyEmittingTo(TD.Energy.LP, aDelegator.mSideOfTileEntity, T);
		return F;
	}

	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mContactDamage && !mFoamDried) UT.Entities.applyElectricityDamage(aEntity, mWattageLast);}

	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEnergyType == TD.Energy.LP;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.LP.AS_LIST;}

	@Override public boolean isEnergyEmittingTo   (TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, T) && canEmitEnergyTo    (aSide);}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F) && canAcceptEnergyFrom(aSide);}
	@Override public synchronized long doEnergyExtraction(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract) {return aSize != 0 && isEnergyEmittingTo(aEnergyType, aSide, F) ?  aDoExtract ? transferElectricity(aSide, aSize, aAmount, -1, new HashSetNoNulls<TileEntity>(F, this)) : aAmount : 0;}
	@Override public synchronized long doEnergyInjection (TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject ) {return aSize != 0 && isEnergyAcceptingFrom(aEnergyType, aSide, F) ?  aDoInject ? transferElectricity(aSide, aSize, aAmount, -1, new HashSetNoNulls<TileEntity>(F, this)) : aAmount : 0;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mVoltage;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mVoltage;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return mVoltage;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return mVoltage;}

	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean isObstructingBlockAt(byte aSide) {return F;} // Btw, Wires have this but Pipes don't. This is because Wires are flexible, while Pipes aren't.

	@Override public OreDictMaterial getEnergyConductorMaterial() {return mMaterial;}
	@Override public OreDictMaterial getEnergyConductorInsulation() {switch(mRenderType) {case 1: case 2: return MT.Rubber; default: return MT.NULL;}}
	@Override public boolean isEnergyConducting(TagData aEnergyType) {return aEnergyType == TD.Energy.LP;}
	@Override public long getEnergyMaxSize(TagData aEnergyType) {return aEnergyType == TD.Energy.LP ? mVoltage : 0;}
	@Override public long getEnergyMaxPackets(TagData aEnergyType) {return aEnergyType == TD.Energy.LP ? mAmperage : 0;}
	@Override public long getEnergyLossPerMeter(TagData aEnergyType) {return aEnergyType == TD.Energy.LP ? mLoss : 0;}

	public boolean canEmitEnergyTo                          (byte aSide) {return connected(aSide);}
	public boolean canAcceptEnergyFrom                      (byte aSide) {return connected(aSide);}

	@Override public long getProgressValue                  (byte aSide) {return mTransferredAmperes;}
	@Override public long getProgressMax                    (byte aSide) {return mAmperage;}

	@Override public ArrayList<String> getDebugInfo(int aScanLevel) {return aScanLevel > 0 ? new ArrayListNoNulls<>(F, "Transferred Power: " + mTransferredWattage) : null;}

	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return mRenderType == 1 || mRenderType == 2 ? BlockTextureDefault.get(Textures.BlockIcons.INSULATION_FULL, isPainted()?mRGBa:UT.Code.getRGBInt(64, 64, 64)) : BlockTextureDefault.get(mMaterial, getIconIndexSide(aSide, aConnections, aDiameter, aRenderPass), F, mRGBa);}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return mRenderType == 1 || mRenderType == 2 ? BlockTextureMulti.get(BlockTextureDefault.get(mMaterial, getIconIndexConnected(aSide, aConnections, aDiameter, aRenderPass), mIsGlowing), BlockTextureDefault.get(mRenderType==2?Textures.BlockIcons.INSULATION_BUNDLED:aDiameter<0.37F?Textures.BlockIcons.INSULATION_TINY:aDiameter<0.49F?Textures.BlockIcons.INSULATION_SMALL:aDiameter<0.74F?Textures.BlockIcons.INSULATION_MEDIUM:aDiameter<0.99F?Textures.BlockIcons.INSULATION_LARGE:Textures.BlockIcons.INSULATION_HUGE, isPainted()?mRGBa:UT.Code.getRGBInt(64, 64, 64))) : BlockTextureDefault.get(mMaterial, getIconIndexConnected(aSide, aConnections, aDiameter, aRenderPass), mIsGlowing, mRGBa);}

	@Override public int getIconIndexSide                   (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return OP.wire.mIconIndexBlock;}
	@Override public int getIconIndexConnected              (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return OP.wire.mIconIndexBlock;}

	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.WIRE_OpticalFiber.AS_LIST;}

	@Override public String getFacingTool                   () {return TOOL_cutter;}

	@Override public String getTileEntityName               () {return "gt.multitileentity.connector.wire.opticalfiber";}
}