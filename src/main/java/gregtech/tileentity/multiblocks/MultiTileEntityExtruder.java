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
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityExtruder extends TileEntityBase10MultiBlockMachine {
	public short mDryerWalls = 18035;

	@Override
	public boolean checkStructure2() {
		int
				tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1),
				tMinY = yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1) + 1,
				tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1),
				tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1),
				tMaxY = yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1) + 1,
				tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1);

		if (worldObj.blockExists(tMinX, tMinY, tMinZ) && worldObj.blockExists(tMaxX, tMaxY, tMaxZ)) {
			boolean tSuccess = T;
			for (int tX = tMinX; tX <= tMaxX; tX++) for (int tY = tMinY; tY <= tMaxY; tY++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ, (SIDES_AXIS_X[mFacing]?tX!=tMinX&&tX!=tMaxX:SIDES_AXIS_Z[mFacing]?tZ!=tMinZ&&tZ!=tMaxZ:tY!=tMinY&&tY!=tMaxY) ? 18043 : mDryerWalls, getMultiTileEntityRegistryID(),0, (SIDES_AXIS_X[mFacing]?tX!=tMinX&&tX!=tMaxX:SIDES_AXIS_Z[mFacing]?tZ!=tMinZ&&tZ!=tMaxZ:tY!=tMinY&&tY!=tMaxY) ? MultiTileEntityMultiBlockPart.NOTHING : MultiTileEntityMultiBlockPart.EVERYTHING)) tSuccess = F;
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.extruder.1", "Two 3x3s with 2m inbetween made of the Block you crafted this of");
		LH.add("gt.tooltip.multiblock.extruder.2", "a 3x3x2 of 18 Large Carborundum Coils inbetween");
		LH.add("gt.tooltip.multiblock.extruder.3", "Main centered on Side-Bottom of one of the 3x3s facing outwards");
		LH.add("gt.tooltip.multiblock.extruder.4", "Input and Output at anywhere except the Coils");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN  + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.extruder.1"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.extruder.2"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.extruder.3"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.extruder.4"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return
				aX >= xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1) &&
						aY >= yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1) + 1 &&
						aZ >= zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1) &&
						aX <= xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1) &&
						aY <= yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1) + 1 &&
						aZ <= zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1);
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
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.extruder";}
}
