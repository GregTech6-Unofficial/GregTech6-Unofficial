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
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockMachine;
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
public class MultiTileEntityFishery extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int
				tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?6:1),
				tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?6:1),
				tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?6:1),
				tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?6:1),
				tD = (mActive?mFacing+2:mFacing-2);

		if (worldObj.blockExists(tMinX, yCoord, tMinZ) && worldObj.blockExists(tMaxX, yCoord+2, tMaxZ)) {
			boolean tSuccess = T;
			for (int tX = tMinX; tX <= tMaxX; tX++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) {
				// Layer 1
				boolean isSides = (tX == tMinX || tX == tMaxX || tZ == tMinZ || tZ == tMaxZ);
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord, tZ, isSides ? 18002 : 18298, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;

				// Layer 2
				if (tX != tMinX && tX != tMaxX && tZ != tMinZ && tZ != tMaxZ) {
					if (getAir(tX, yCoord + 1, tZ)) worldObj.setBlockToAir(tX, yCoord + 1, tZ); else tSuccess = F;
				} else {
					boolean isCorners = (tX == tMinX && tZ == tMinZ || tX == tMinX && tZ == tMaxZ || tX == tMaxX && tZ == tMinZ || tX == tMaxX && tZ == tMaxZ);
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord + 1, tZ, isCorners ? 18002 : 18298, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
				}

				// Layer 3
				if (tX != tMinX && tX != tMaxX && tZ != tMinZ && tZ != tMaxZ) {
					if (getAir(tX, yCoord + 2, tZ)) worldObj.setBlockToAir(tX, yCoord + 2, tZ); else tSuccess = F;
				} else {
					boolean isCorners = (tX == tMinX && tZ == tMinZ || tX == tMinX && tZ == tMaxZ || tX == tMaxX && tZ == tMinZ || tX == tMaxX && tZ == tMaxZ);
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord + 2, tZ, isCorners ? 18002 : 18298, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
				}

				// Layer 4
				if (tX != tMinX && tX != tMaxX && tZ != tMinZ && tZ != tMaxZ) {
					if (getAir(tX, yCoord + 3, tZ)) worldObj.setBlockToAir(tX, yCoord + 3, tZ); else tSuccess = F;
				} else {
					boolean isCorners = (tX == tMinX && tZ == tMinZ || tX == tMinX && tZ == tMaxZ || tX == tMaxX && tZ == tMinZ || tX == tMaxX && tZ == tMaxZ);
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord + 3, tZ, isCorners ? 18002 : 18298, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
				}

				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord + 4, tZ, isSides ? 18002 : 18298, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;

			}
			return tSuccess;
		}
		return mStructureOkay;
		
	}

	
	static {
		LH.add("gt.tooltip.multiblock.fishery.1", "5x11x5 Hollow Cube");
		LH.add("gt.tooltip.multiblock.fishery.2", "The Edges of the Cube are Stainless Steel Walls");
		LH.add("gt.tooltip.multiblock.fishery.3", "The Faces of the Cube are Fishery Blocks");
		LH.add("gt.tooltip.multiblock.fishery.4", "Stuff can go in and out on any of the Stainless Steel Walls");
		LH.add("gt.tooltip.multiblock.fishery.5", "Main Block centered on Slim-Side-Bottom and facing outwards");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN  + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.1"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.2"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.3"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.4"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.5"));
		super.addToolTips(aList, aStack, aF3_H);
	}


	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return
				aX >= xCoord - (SIDE_X_NEG == mFacing ? 0 : SIDE_X_POS == mFacing ? 6 : 1) &&
						aY >= yCoord &&
						aZ >= zCoord - (SIDE_Z_NEG == mFacing ? 0 : SIDE_Z_POS == mFacing ? 6 : 1) &&
						aX <= xCoord + (SIDE_X_POS == mFacing ? 0 : SIDE_X_NEG == mFacing ? 6 : 1) &&
						aY <= yCoord + 4 &&
						aZ <= zCoord + (SIDE_Z_POS == mFacing ? 0 : SIDE_Z_NEG == mFacing ? 6 : 1);
	}

	@Override
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		return getAdjacentTank(SIDE_BOTTOM);
	}
	
	@Override
	public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {
		return getAdjacentTileEntity(SIDE_BOTTOM);
	}
	
	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.fishery";}
}