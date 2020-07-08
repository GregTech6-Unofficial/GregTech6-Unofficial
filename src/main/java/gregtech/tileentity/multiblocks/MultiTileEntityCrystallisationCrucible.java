/**
 * Copyright (c) 2020 Gregtech6-Unofficial Team
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
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockMachine;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCrystallisationCrucible extends TileEntityBase10MultiBlockMachine {
	public short mCrucibleWalls = 18035;
	
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing), tY = yCoord-2, tZ = getOffsetZN(mFacing);
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			
			if (getAir(tX, tY+1, tZ)) worldObj.setBlockToAir(tX, tY+1, tZ); else tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY-1, tZ-1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY-1, tZ-1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY-1, tZ-1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY-1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY-1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY-1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY-1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY-1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY-1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY  , tZ-1, 18016, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ-1, 18016, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ-1, 18016, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY  , tZ  , 18016, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18016, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , 18016, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY  , tZ+1, 18016, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, 18016, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, 18016, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ  , mCrucibleWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
			for (int i = 1; i < 3; i++) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY+i, tZ-1, mCrucibleWalls, getMultiTileEntityRegistryID(),                0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+i, tZ-1, mCrucibleWalls, getMultiTileEntityRegistryID(),   i == 1 ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+i, tZ-1, mCrucibleWalls, getMultiTileEntityRegistryID(),                0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY+i, tZ  , mCrucibleWalls, getMultiTileEntityRegistryID(),   i == 1 ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+i, tZ  , mCrucibleWalls, getMultiTileEntityRegistryID(),   i == 1 ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY+i, tZ+1, mCrucibleWalls, getMultiTileEntityRegistryID(),                0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+i, tZ+1, mCrucibleWalls, getMultiTileEntityRegistryID(),   i == 1 ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+i, tZ+1, mCrucibleWalls, getMultiTileEntityRegistryID(),                0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT)) tSuccess = F;
			}
			
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.largecrystallisationcrucible.1", "3x3 Base of Heat Transmitters");
		LH.add("gt.tooltip.multiblock.largecrystallisationcrucible.2", "Then: 3x3 of Iridium Walls");
		LH.add("gt.tooltip.multiblock.largecrystallisationcrucible.3", "Then: 3x3x2 Hollow of the Block you crafted this one with");
		LH.add("gt.tooltip.multiblock.largecrystallisationcrucible.4", "Main centered on Side-Top of Crystallisation Crucible facing outwards");
		LH.add("gt.tooltip.multiblock.largecrystallisationcrucible.5", "Input only possible at the Iridium Walls");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN  + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.largecrystallisationcrucible.1"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.largecrystallisationcrucible.2"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.largecrystallisationcrucible.3"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.largecrystallisationcrucible.4"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.largecrystallisationcrucible.5"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = yCoord-2, tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY - 1 && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 2 && aZ <= tZ + 1;
	}
	
	@Override
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		return getAdjacentTank(SIDE_TOP);
	}
	
	@Override
	public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {
		return getAdjacentTileEntity(SIDE_TOP);
	}
	
	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.largecrystallisationcrucible";}
}
