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
public class MultiTileEntityBiolab extends TileEntityBase10MultiBlockMachine {

	@Override
    public boolean checkStructure2() {
        int tX = getOffsetXN(mFacing)-1, tY = yCoord, tZ = getOffsetZN(mFacing)-1;

        if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
            boolean tSuccess = T;

            //Center
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY  , tZ  , 18119, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;

            //Left half
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1  , tY  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-2  , tY  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1  , tY  , tZ+1  , 18040, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-2  , tY  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1  , tY  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-2  , tY  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;

            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1  , tY+1  , tZ  , 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-2  , tY+1  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1  , tZ+1  , 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (getAir(tX-1, tY+1, tZ+1)) worldObj.setBlockToAir(tX-1, tY+1, tZ+1); else tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-2  , tY+1  , tZ+1  , 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1  , tY+1  , tZ+2  , 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-2  , tY+1  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;

            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1  , tY+2  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-2  , tY+2  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1  , tY+2  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-2  , tY+2  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1  , tY+2  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-2  , tY+2  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;


            tX += 2;

            //Right half
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2  , tY  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY  , tZ+1  , 18040, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2  , tY  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2  , tY  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;

            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY+1  , tZ  , 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2  , tY+1  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1  , tZ+1  , 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (getAir(tX-1, tY+1, tZ+1)) worldObj.setBlockToAir(tX+1, tY+1, tZ+1); else tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2  , tY+1  , tZ+1  , 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY+1  , tZ+2  , 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2  , tY+1  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;

            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY+2  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2  , tY+2  , tZ  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY+2  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2  , tY+2  , tZ+1  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1  , tY+2  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2  , tY+2  , tZ+2  , 18119, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;


            return tSuccess;
        }
        return mStructureOkay;
    }
	
	static {
		LH.add("gt.tooltip.multiblock.biolab.1", "The Biolab consists two exactly same modules and a connection part");
        LH.add("gt.tooltip.multiblock.biolab.2", "Each module is 3x3x3 Hollow Sterile Machine Casing with its bottom centers a Large Copper Coil");
        LH.add("gt.tooltip.multiblock.biolab.3", "On the Side-centers locate 4 Ventilation Units");
        LH.add("gt.tooltip.multiblock.biolab.4", "Place the two modules on same level with one block space between them");
        LH.add("gt.tooltip.multiblock.biolab.5", "Main at Side-Bottom of the spacing right between the two modules facing outwards");
        LH.add("gt.tooltip.multiblock.biolab.6", "Fill the bottom layer of the spacing with 2 Sterile Machine Casing");
        LH.add("gt.tooltip.multiblock.biolab.7", "Input and Output Items and Fluids at any Sterile Machin Casing");
        LH.add("gt.tooltip.multiblock.biolab.8", "Energy input at the top centers of the two modules");
        LH.add("gt.tooltip.multiblock.biolab.9", "Requires LU to disinfect before start");
    }
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN  + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.biolab.1"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.biolab.2"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.biolab.3"));
        aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.biolab.4"));
        aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.biolab.5"));
        aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.biolab.6"));
        aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.biolab.7"));
        aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.biolab.8"));
        aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.biolab.9"));
        super.addToolTips(aList, aStack, aF3_H);
	}

    @Override
    public boolean isInsideStructure(int aX, int aY, int aZ) {
        int tX = getOffsetXN(mFacing, 1), tY = yCoord, tZ = getOffsetZN(mFacing, 1);
        return aX >= tX - 1 && aY >= tY && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 2 && aZ <= tZ + 1;
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

    @Override public String getTileEntityName() {return "gt.multitileentity.multiblock.biolab";}
}
