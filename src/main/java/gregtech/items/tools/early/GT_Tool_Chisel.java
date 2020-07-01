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

package gregtech.items.tools.early;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.metatype.BlockStones;
import gregapi.data.CS.SFX;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregtech.GT6_Main;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class GT_Tool_Chisel extends ToolStats {
	@Override public int getToolDamagePerBlockBreak()                                       {return 50;}
	@Override public int getToolDamagePerDropConversion()                                   {return 100;}
	@Override public int getToolDamagePerContainerCraft()                                   {return 400;}
	@Override public int getToolDamagePerEntityAttack()                                     {return 200;}
	@Override public int getBaseQuality()                                                   {return 0;}
	@Override public float getBaseDamage()                                                  {return 1.5F;}
	@Override public float getSpeedMultiplier()                                             {return 1.0F;}
	@Override public float getMaxDurabilityMultiplier()                                     {return 1.0F;}
	@Override public boolean isMiningTool()                                                 {return T;}
	@Override public boolean canCollect()                                                   {return T;}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		if (aBlock == Blocks.stone) {
			aDrops.clear();
			aDrops.add(ST.make(Blocks.stonebrick, 1, 3));
			return 0;
		}
		if (aBlock == Blocks.stonebrick) {
			aDrops.clear();
			switch(aMetaData) {
			case  0: aDrops.add(ST.make(Blocks.stonebrick, 1, 2)); break;
			case  1: aDrops.add(ST.make(Blocks.mossy_cobblestone, 1, 0)); break;
			case  2: aDrops.add(ST.make(Blocks.cobblestone, 1, 0)); break;
			default: aDrops.add(ST.make(aBlock, 1, aMetaData)); break;
			}
			return 0;
		}
		if (aBlock instanceof BlockStones) {
			aDrops.clear();
			aDrops.add(ST.make(aBlock, 1, BlockStones.CHISEL_MAPPINGS[aMetaData & 15]));
			return 0;
		}
		return 0;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && tTool.equalsIgnoreCase(TOOL_chisel)) || aBlock == Blocks.monster_egg || aBlock == Blocks.stone || aBlock == Blocks.stonebrick || aBlock instanceof BlockStones;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadChisel.mIconIndexItem) : Textures.ItemIcons.HANDLE_CHISEL;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mRGBaSolid;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_chisel, SFX.MC_DIG_ROCK, 100, !canBlock()));
	}

	@Override
	public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
		super.onToolCrafted(aStack, aPlayer);
		try { GT6_Main.achievements.issueAchievement(aPlayer, "chisel"); } catch (Exception e) { }
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got a Statue made by [KILLER]";
	}
}
