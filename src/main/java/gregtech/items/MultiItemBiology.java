/**
 * Copyright (c) 2020 GregTech-6U Team
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

package gregtech.items;

import gregapi.data.*;
import gregapi.item.CreativeTab;
import gregapi.item.multiitem.MultiItemRandom;

import static gregapi.data.CS.*;

public class MultiItemBiology extends MultiItemRandom{

    public MultiItemBiology() {
        super(MD.GT.mID, "gt.multiitem.biology");
        setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Biology", this, (short)1000));
    }

    @Override
    public void addItems() {
        IL.Culture_Dish_Empty.set(addItem(1000, "Culture Dish (Empty)", "A fundamental tool of laboratory biology"));

        RM.Sharpening.addRecipe1(T, 16, 128, OP.plate.mat(MT.PTFE, 1), ZL_FS, ZL_FS, IL.Culture_Dish_Empty.get(1));

        //给鸭蛋的加配方介绍：

        //初级
        //RM.Sharpening 相当于mtu keys
        //addRecipe1 输入一种物品的配方 addRecipe2 输入两种物品的配方，addRecipeX 输入大于2种物品的配方
        //ST.tag(1) 是集成电路 数字代表集成电路编号
        //aEUt 表示能量需求
        //aDuration 表示时长
        //这里之后可以选用加一个 new long[] {10000, 2000} 之类的，代表输出物品的几率。同mtu
        //然后参数就是MTU里一样的顺序的：输入物品（或物品数组）输入流体（或流体数组）输出流体（或流体数组）输出物品（或物品数组）

        //中级：
        //无论是物品还是流体，输入还是输出，如果大于一种：物品必须要用ST.array(物品1，物品2)，流体要用FL.array(流体1，流体2)
        //若输入/输出为空：物品用ZL_IS代替，流体用ZL_FS代替
        //物品表示方法：IL.物品名.get(数量) 或者 OP.<板/锭/粉/小粉/等等的标志，可以打完OP.自己翻，多试试就懂>.mat(MT.材料名, 数量)
        //流体表示方法：FL.流体名.make(一个整数，升为单位) 或者 MT.材料名.liquid/gas/fluid(U*份数，T或者F) U一般对于流体代表1000L，U10代表U/10，U100代表U/100，以此类推。 T代表这个流体是输入，F代表这个流体是输出，非常重要！！


    }

}
