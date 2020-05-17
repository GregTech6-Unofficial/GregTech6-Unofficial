/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregtech.tileentity.multiblocks;

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

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityDrainingWell extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing)-1, tY = yCoord, tZ = getOffsetZN(mFacing)-1;
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			
			if (getAir(tX+1, tY+1, tZ+1)) worldObj.setBlockToAir(tX+1, tY+1, tZ+1); else tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ  , 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+1, 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2, 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+2, 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+2, 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
			
			int tID = 18001;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ  , tID  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ  , 18009  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ  , tID  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+1, 18009  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+1, 18009  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+2, tID  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+2, 18009  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+2, tID  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ  , tID  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2, tZ  , 18009  , getMultiTileEntityRegistryID(), 1, SIDES_AXIS_Z[mFacing]?MultiTileEntityMultiBlockPart.NOTHING:MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2, tZ  , tID  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ+1, 18009  , getMultiTileEntityRegistryID(), 1, SIDES_AXIS_X[mFacing]?MultiTileEntityMultiBlockPart.NOTHING:MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2, tZ+1, 18009  , getMultiTileEntityRegistryID(), 1, SIDES_AXIS_X[mFacing]?MultiTileEntityMultiBlockPart.NOTHING:MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ+2, tID  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2, tZ+2, 18009  , getMultiTileEntityRegistryID(), 1, SIDES_AXIS_Z[mFacing]?MultiTileEntityMultiBlockPart.NOTHING:MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2, tZ+2, tID  , getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
	
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+3, tZ  , tID, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+3, tZ  , 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+3, tZ  , tID, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+3, tZ+1, 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+3, tZ+1, 18009, getMultiTileEntityRegistryID(), 3, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+3, tZ+1, 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+3, tZ+2, tID, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+3, tZ+2, 18009, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+3, tZ+2, tID, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			//TODO: WELL PIPE

			return tSuccess;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.drainingwell.1", "Bottom: 3x3 of Steel Walls");
		LH.add("gt.tooltip.multiblock.drainingwell.2", "Then: 3x3 of 4 Steel Walls on Edges, 4 Wood Walls on Corners, Hollow");
		LH.add("gt.tooltip.multiblock.drainingwell.3", "Then: 3x3 of 4 Steel Walls on Edges, 4 Wood Walls on Corners, Hollow");
		LH.add("gt.tooltip.multiblock.drainingwell.4", "Top: 3x3 of 5 Steel Walls on Edges and Center, 4 Wood Walls on Corners");
		LH.add("gt.tooltip.multiblock.drainingwell.5", "Main Block centered on Side-Bottom and facing outwards");
		LH.add("gt.tooltip.multiblock.drainingwell.6", "Centered Below: 1x1 Pillar of Well Pipes");
		LH.add("gt.tooltip.multiblock.drainingwell.7", "The Tip of the Rod has to be at Y = 30 or below");
		LH.add("gt.tooltip.multiblock.drainingwell.8", "Liquid output at Holes on Wood Walls and beneath Main Block, Item Input at Bottom Layer");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.drainingwell.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.drainingwell.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.drainingwell.3"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.drainingwell.4"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.drainingwell.5"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.drainingwell.6"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.drainingwell.7"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.drainingwell.8"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 2 && aZ <= tZ + 1;
	}

	@Override
	public void updateAdjacentToggleableEnergySources() {
		DelegatorTileEntity<TileEntity>
				tDelegator = WD.te(worldObj, getOffsetXN(mFacing), yCoord+3, getOffsetZN(mFacing), SIDE_TOP, F);
		if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) {
			((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
		}
	}

	@Override
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		return getAdjacentTank(SIDE_BOTTOM);
	}

	//TODO: SIDE AUTO OUTPUT
	@Override public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) { return null; }

	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}

	@Override public boolean refreshStructureOnActiveStateChange() {return T;}

	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.drainingwell";}
}
