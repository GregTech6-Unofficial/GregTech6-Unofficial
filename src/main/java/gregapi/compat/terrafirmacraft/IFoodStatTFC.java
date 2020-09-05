/**
 * Copyright (c) 2020 GT6U-Team
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

package gregapi.compat.terrafirmacraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author YueSha
 *
 * Implement this interface for TFC Food System Comptability.
 */
public interface IFoodStatTFC {
    /** Warning: the "aPlayer" Parameter may be null! Get the food sweetness (TFC). */
    public int getSweetness(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
    /** Warning: the "aPlayer" Parameter may be null! Get the food sourness (TFC). */
    public int getSourness(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
    /** Warning: the "aPlayer" Parameter may be null! Get the food saltiness (TFC). */
    public int getSaltiness(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
    /** Warning: the "aPlayer" Parameter may be null! Get the food bitterness (TFC). */
    public int getBitterness(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
    /** Warning: the "aPlayer" Parameter may be null! Get the food savory (TFC). */
    public int getSavory(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
    /** Warning: the "aPlayer" Parameter may be null! Whether the food can be eaten. */
    public boolean isEdible(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
    /** Warning: the "aPlayer" Parameter may be null! Get the food weight in Ounce. */
    public int getFoodWeight(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
    /** Warning: the "aPlayer" Parameter may be null! Get an integer that represents six matching com.bioxx.tfc.api.Enums.EnumFoodGroup. The matching is: 0-Diary, 1-Fruit, 2-Grain, 3-Protein, 4-Vegetable, 5-None*/
    public int getFoodGroupTFC(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
}
