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
 * @author YueSha
 */
public class MultiTileEntityParticleCollider extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing, 2), tY = yCoord, tZ = getOffsetZN(mFacing, 2);
		if (worldObj.blockExists(tX-19, tY, tZ-19) && worldObj.blockExists(tX+19, tY, tZ-19) && worldObj.blockExists(tX-19, tY, tZ+19) && worldObj.blockExists(tX+19, tY, tZ+19)) {
			boolean tSuccess = T;

			//control center 18200-Versatile 18201-Logic 18202-Control 18008-galvanized steel wall 18299-ventilation unit
			int tVersatile = 3, tLogic = 12, tControl = 12;


			//the core
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) for (int k = -2; k <= 2; k++) {
				if (i*i + j*j + k*k < 4) {
					if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18200, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						tVersatile--;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18201, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						tLogic--;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18202, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						tControl--;
					} else {
						tSuccess = F;
					}
				} else if (i*i + j*j + k*k > 6 || (j == 0 && (((i == -2 || i == 2) && k == 0) || (((k == -2 || k == 2) && i == 0))))) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18014, getMultiTileEntityRegistryID(), mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				}
			}
			
			if (tVersatile > 0 || tLogic > 0 || tControl > 0) tSuccess = F;


			//the connecting arms
			if (mFacing != SIDE_X_NEG) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-3, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-4, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-5, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-6, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-7, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-8, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-9, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-10, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-11, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-12, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			}
			if (mFacing != SIDE_X_POS) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+5, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+6, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+7, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+8, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+9, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+10, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+11, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+12, tY, tZ  , 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			}
			if (mFacing != SIDE_Z_NEG) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-3, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-4, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-5, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-6, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-7, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-8, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-9, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-10, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-11, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-12, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			}
			if (mFacing != SIDE_Z_POS) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+3, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+4, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+5, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+6, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+7, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+8, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+9, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+10, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+11, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+12, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			}


			
			tX -= 19; tZ -= 19;
			
			for (int i = 0; i < 39; i++) for (int j = 0; j < 39; j++) {

				if (OCTAGONS[0][i][j]) { //first and fifth row layer
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1, tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
					if ((i == 19 && (j == 0 || j == 38)) || (j == 19 && (i == 0 || i == 38))) { //energy
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18014, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
					} else { //glowing ring
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18014, getMultiTileEntityRegistryID(), mActive ? 6 : 5, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
					}
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1, tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
				}
				if (OCTAGONS[1][i][j]) { //second and fourth row layer
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-2, tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1, tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18046, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1, tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2, tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
				}
				if (OCTAGONS[2][i][j]) { //central row layer
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-2, tZ+j, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1, tZ+j, 18046, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1, tZ+j, 18046, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2, tZ+j, 18014, getMultiTileEntityRegistryID(),  mActive ? 6 : 5, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
				}

				if (OCTAGONS[3][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-2  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1  , tZ+j, 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18014, getMultiTileEntityRegistryID(), mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1  , tZ+j, 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				}
				if (OCTAGONS[4][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-3  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-2  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1  , tZ+j, 18204, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1  , tZ+j, 18204, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+3  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				}
				if (OCTAGONS[5][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-3  , tZ+j, 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-3  , tZ+j, 18202, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18046, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2  , tZ+j, 18202, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+3  , tZ+j, 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				}
				if (OCTAGONS[6][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-3  , tZ+j, 18014, getMultiTileEntityRegistryID(), mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-2  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1  , tZ+j, 18046, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1  , tZ+j, 18046, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2  , tZ+j, 18014, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+3  , tZ+j, 18014, getMultiTileEntityRegistryID(), mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				}
			}

			return tSuccess;
		}
		return mStructureOkay;
	}
	
	public static boolean[][][] OCTAGONS = {
	   { //RING THIRD LAYER
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,F,F,F,F,F,F,F,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,T,F,F,F,T,T,T,F,F,F,F,F,F,F,T,T,T,F,F,F,T,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F},
			{F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F},
			{F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F},
			{F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F},
			{F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F},
			{F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F},
			{F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F},
			{F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F},
			{F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,T,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,F,F,F,T,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	}, { //RING SECOND LAYER
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,F,F,F,F,F,F,F,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,T,F,T,T,T,F,F,F,F,F,F,F,T,T,T,F,T,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F},
			{F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F},
			{F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F},
			{F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F},
			{F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F},
			{F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F},
			{F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,T,F,T,T,T,T,T,T,T,T,T,T,T,T,T,F,T,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	}, { //RING CENTRAL
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,F,F,F,F,F,F,F,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F},
			{F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F},
			{F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
			{F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F},
			{F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	},{ //INJECTOR OUTERMOST
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{T,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,T},
			{T,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,T},
			{T,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,T},
			{T,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,T},
			{T,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,T},
			{T,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,T},
			{T,F,F,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,F,F,T},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	},{ //INJECTOR SECOND
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,T,F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F,T,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	},{ //INJECTOR THIRD
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	},{ //INJECTOR CENTRAL
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
			{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	}};
	
	static {
		LH.add("gt.tooltip.multiblock.particlecollider.1", "For Construction Instructions read the Manual or the GUI.");
		LH.add("gt.tooltip.multiblock.particlecollider.2", "144 Superconducting Coils, 576 Regular Osmiridium Walls, 50 Ventilation Units.");
		LH.add("gt.tooltip.multiblock.particlecollider.3", "36 Regular Stainless Steel Walls, 53 Galvanized Steel Walls.");
		LH.add("gt.tooltip.multiblock.particlecollider.4", "3 Versatile, 12 Logic and 12 Control Quadcore Processing Units.");
		LH.add("gt.tooltip.multiblock.particlecollider.5", "Input energy for start.Then for process");
		LH.add("gt.tooltip.multiblock.particlecollider.6", "Electric power Input at the 'Glass' Ring");
		LH.add("gt.tooltip.multiblock.particlecollider.7", "Items and Fluids are handeled at the normal Walls");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN  + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.particlecollider.1"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.particlecollider.2"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.particlecollider.3"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.particlecollider.4"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.particlecollider.5"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.particlecollider.6"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.particlecollider.7"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing, 2), tY = yCoord-3, tZ = getOffsetZN(mFacing, 2);
		return aX >= tX - 19 && aY >= tY && aZ >= tZ - 19 && aX <= tX + 19 && aY <= tY + 7 && aZ <= tZ + 19;
	}

	@Override public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) { return null; }
	@Override public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) { return null; }
	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}
	@Override public boolean refreshStructureOnActiveStateChange() {return T;}
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.particlecollider";}
}
