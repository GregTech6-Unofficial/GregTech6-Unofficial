/**
 * Copyright (c) 2021 GregTech6-Unofficial
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

package gregtech.tileentity.sensors;

import static gregapi.data.CS.*;

import gregapi.data.BI;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.tileentity.connectors.MultiTileEntityOpticalFiber;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.MultiTileEntitySensorTE;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.NodeStats;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLPmeter extends MultiTileEntitySensorTE {
	static {LH.add("gt.tooltip.sensor.LPmeter", "Measures Electricity Flow (In LP)");}
	@Override public String getSensorDescription() {return LH.get("gt.tooltip.sensor.LPmeter");}
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof MultiTileEntityOpticalFiber) return ((MultiTileEntityOpticalFiber)aDelegator.mTileEntity).mWattageLast;
	
		if (EnergyNet.instance != null) {
			TileEntity tTileEntity = EnergyNet.instance.getTileEntity(aDelegator.mWorld, aDelegator.mX, aDelegator.mY, aDelegator.mZ);
			if (tTileEntity != null) {
				NodeStats tStats = EnergyNet.instance.getNodeStats(tTileEntity);
				if (tStats != null) {
					if (tTileEntity instanceof IEnergyConductor ) return (long)tStats.getEnergyOut();
					if (tTileEntity instanceof IEnergyEmitter   ) return (long)tStats.getEnergyOut();
					if (tTileEntity instanceof IEnergyAcceptor  ) return (long)tStats.getEnergyIn();
				}
			}
		}
		return 0;
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof MultiTileEntityOpticalFiber) return ((MultiTileEntityOpticalFiber)aDelegator.mTileEntity).mAmperage * ((MultiTileEntityOpticalFiber)aDelegator.mTileEntity).mVoltage;
		return 0;
	}
	
	@Override public short[] getSymbolColor() {return CA_RED_255;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_EU;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/electrometer/colored/front"),
	sTextureBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/electrometer/colored/back"),
	sTextureSide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/electrometer/colored/side"),
	sOverlayFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/electrometer/overlay/front"),
	sOverlayBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/electrometer/overlay/back"),
	sOverlaySide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/electrometer/overlay/side");
	
	@Override public String getTileEntityName() {return "gt.multitileentity.redstone.sensors.lpctrometer";}
}
