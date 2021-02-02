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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
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
				tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?6:2),
				tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?6:2),
				tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?6:2),
				tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?6:2);
		int
				tD = mActive?2:mRunning?1:0;

		if (worldObj.blockExists(tMinX, yCoord, tMinZ) && worldObj.blockExists(tMaxX, yCoord+2, tMaxZ)) {
			boolean tSuccess = T;
			for (int tX = tMinX; tX <= tMaxX; tX++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) {

				// Conditions
				boolean isSides = tX == tMinX || tX == tMaxX || tZ == tMinZ || tZ == tMaxZ;
				boolean isCorners = tX == tMinX && tZ == tMinZ || tX == tMinX && tZ == tMaxZ || tX == tMaxX && tZ == tMinZ || tX == tMaxX && tZ == tMaxZ;
				boolean isNotSides = tX != tMinX && tX != tMaxX && tZ != tMinZ && tZ != tMaxZ;

				// Layer 1
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord, tZ, isSides ? 18002 : 18298, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY)) tSuccess = F;

				// Layer 2
				if (isNotSides) {
					if (getWater(tX, yCoord + 1, tZ)) worldObj.setBlock(tX, yCoord + 1, tZ, Blocks.water, 0, 3); else tSuccess = F;
				} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord + 1, tZ, isCorners ? 18002 : 18298, getMultiTileEntityRegistryID(), isCorners ? 0 : tD, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
				}

				// Layer 3
				if (isNotSides) {
					if (getWater(tX, yCoord + 2, tZ)) worldObj.setBlock(tX, yCoord + 2, tZ, Blocks.water, 0, 3); else tSuccess = F;
				} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord + 2, tZ, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
				}

			}
			return tSuccess;
		}
		return mStructureOkay;
		
	}

	private boolean getWater(int aX, int aY, int aZ) {
		if (worldObj == null) return T;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !worldObj.blockExists(aX, aY, aZ)) return T;
		return worldObj.getBlock(aX, aY, aZ).getMaterial() == Material.water;
	}

	
	static {
		LH.add("gt.tooltip.multiblock.fishery.1", "5(Width)x3(Height)x7(Length) Hollow Cube with top side opened");
		LH.add("gt.tooltip.multiblock.fishery.2", "Main Block centered on 5x3-Side-Bottom and facing outwards");
		LH.add("gt.tooltip.multiblock.fishery.3", "The Edges of the Cube are Stainless Steel Walls");
		LH.add("gt.tooltip.multiblock.fishery.4", "The Faces of the Cube are Fishery Blocks");
		LH.add("gt.tooltip.multiblock.fishery.5", "Fill the Hollow space with Still Water");
		LH.add("gt.tooltip.multiblock.fishery.6", "Stuff can go in and out at middle and top layer");
		LH.add("gt.tooltip.multiblock.fishery.7", "Energy input at the bottom layer");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN  + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.1"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.2"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.3"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.4"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.5"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.6"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fishery.7"));
		super.addToolTips(aList, aStack, aF3_H);
	}


	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return
				aX >= xCoord - (SIDE_X_NEG == mFacing ? 0 : SIDE_X_POS == mFacing ? 6 : 2) &&
						aY >= yCoord &&
						aZ >= zCoord - (SIDE_Z_NEG == mFacing ? 0 : SIDE_Z_POS == mFacing ? 6 : 2) &&
						aX <= xCoord + (SIDE_X_POS == mFacing ? 0 : SIDE_X_NEG == mFacing ? 6 : 2) &&
						aY <= yCoord + 2 &&
						aZ <= zCoord + (SIDE_Z_POS == mFacing ? 0 : SIDE_Z_NEG == mFacing ? 6 : 2);
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
