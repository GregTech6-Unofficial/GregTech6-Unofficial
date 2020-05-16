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

package gregtech.tileentity.misc;

import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.FL;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase04MultiTileEntities;
import gregapi.tileentity.data.ITileEntitySurface;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityFluidWell extends TileEntityBase04MultiTileEntities implements IMTE_OnRegistration, ITileEntitySurface, IMTE_IsSideSolid, IMTE_GetExplosionResistance, IMTE_GetBlockHardness, IMTE_GetLightOpacity, IMTE_SyncDataShort {
	public FluidStack mFluid = FL.Water.make(1);
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("gt.well")) mFluid = FL.load(aNBT, "gt.well");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		FL.save(aNBT, "gt.well", mFluid);
	}
	
	@Override
	public final NBTTagCompound writeItemNBT(NBTTagCompound aNBT) {
		aNBT = super.writeItemNBT(aNBT);
		FL.save(aNBT, "gt.well", mFluid);
		return aNBT;
	}
	
	public static MultiTileEntityRegistry MTE_REGISTRY = null;
	public static MultiTileEntityFluidWell INSTANCE;
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketShort(aSendAll, (short)mFluid.getFluid().getID());
	}
	
	@Override
	public boolean receiveDataShort(short aData, INetworkHandler aNetworkHandler) {
		mFluid = FL.make(FL.fluid(aData), 600);
		return T;
	}
	
	@Override
	public void onRegistration(MultiTileEntityRegistry aRegistry, short aID) {
		INSTANCE = this;
		MTE_REGISTRY = aRegistry;
	}
	
	@Override
	public void onTick(long aTimer, boolean aIsServerSide) {
		super.onTick(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (rng(mFluid.amount) == 0) {
				Block tBlock = mFluid.getFluid().getBlock(), tAbove = getBlockAtSide(SIDE_UP);
				if (tBlock instanceof BlockFluidFinite) {
					if (tAbove == tBlock) {
						worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, UT.Code.bind4(getMetaDataAtSide(SIDE_UP)+8), 3);
					} else if (tAbove.isAir(worldObj, xCoord, yCoord+1, zCoord)) {
						worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, 7, 3);
					}
				} else {
					if (tAbove == tBlock) {
						if (getMetaDataAtSide(SIDE_UP) == 0) {
							for (byte tSide : ALL_SIDES_HORIZONTAL) {
								tAbove = getBlock(xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide]);
								if (tAbove == tBlock) {
									if (0 != getMetaData(xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide])) {
										worldObj.setBlock(xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide], tBlock, 0, 3);
										break;
									}
								} else if (tAbove.isAir(worldObj, xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide])) {
									worldObj.setBlock(xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide], tBlock, 0, 3);
									break;
								}
							}
						} else {
							worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, 0, 3);
						}
					} else if (tAbove.isAir(worldObj, xCoord, yCoord+1, zCoord)) {
						worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, 0, 3);
					}
				}
			}
		}
	}
	
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {return F;}
	@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
	@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureFluid.get(mFluid), BlockTextureDefault.get(Textures.BlockIcons.FLUID_WELL)) : null;}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_MAX;}
	
	@Override public float getExplosionResistance2() {return Blocks.stone.getExplosionResistance(null);}
	@Override public float getBlockHardness() {return 30;}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return T;}
	@Override public boolean isSurfaceOpaque        (byte aSide) {return T;}
	@Override public boolean isSideSolid            (byte aSide) {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.fluid.well";}
}
