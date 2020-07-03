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
import gregapi.recipes.Recipe;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Items;


import static gregapi.data.CS.*;

import static gregapi.data.OP.*;
import static gregapi.util.CR.*;
import gregapi.config.ConfigCategories;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.OreDictToolNames;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
//不管有没有用，先import再说

public class MultiItemBiology extends MultiItemRandom{

    public MultiItemBiology() {
        super(MD.GT.mID, "gt.multiitem.biology");
        setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Biology", this, (short)1000));
    }

    @Override
    public void addItems() {
        IL.Culture_Dish_Empty.set(addItem(1000, "Culture Dish (Empty)", "A fundamental tool of laboratory biology"));
        IL.Culture_Dish_MSC.set(addItem(1001,"Culture Dish (MSC)","Stem cell culture medium inside"));
        IL.Culture_Dish_Stem_Cell_Embryonic.set(addItem(1002,"Culture Dish (Inoculated stem cells)","can be cultured in Incubator"));
        IL.Culture_Dish_Stem_Cell_Somatic.set(addItem(1003,"Culture Dish (stem cells)","Stem cells inside"));
        IL.dust_Inorganic_Salt_Mixture.set(addItem(1004,"Inorganic Salt Mixture Dust","Inorganic salts for cell culture"));
        IL.dust_Tiny_Inorganic_Salt_Mixture.set(addItem(1005,"Tiny InorganicSalt Mixture Dust","Inorganic salts for cell culture"));
        IL.dust_Glucose.set(addItem(1006,"Glucose Dust","Can't be eaten now"));
        IL.Stem_Cell.set(addItem(1007,"Stem Cell","It can differentiate into various cells(But now there are only neuron cells)"));
        IL.Neuron_Cell.set(addItem(1008,"Culture Dish (Neuron Cell)","It's clever"));
        IL.Unformed_Embryo.set(addItem(1009,"Unformed Embryo","It's alive,and it will die for science."));
        IL.Precision_Manipulator.set(addItem(1010,"Percision Manipulator","Very accurate"));
        IL.Lauryl_Sulfate.set(addItem(1011,"Lauryl Sulfate",""));
        IL.SDS.set(addItem(1012,"SDS",""));

        RM.Sharpening.addRecipe1(T, 16, 128, OP.plate.mat(MT.PTFE, 1), ZL_FS, ZL_FS, IL.Culture_Dish_Empty.get(1));
        //空培养皿的合成

        RM.Centrifuge.addRecipe1(T,1024,200, ST.make(Items.bone,1,0), ZL_FS, FL.FieryBlood.make(1),OP.dust.mat(MT.Bone,4));
        //骨髓血

        RM.Mixer.addRecipe1(T,16,64,OP.dustDiv72.mat(MT.NaCl,81), MT.DistWater.liquid(U*125,T), FL.SPSS.make(125000),ZL_IS);
        RM.Mixer.addRecipe1(T,64,128,OP.dust.mat(MT.NaCl,9),MT.DistWater.liquid(U*1000,T), FL.SPSS.make(1000000),ZL_IS);
        RM.Mixer.addRecipe1(T,256,256,OP.dustSmall.mat(MT.NaCl, 9),MT.DistWater.liquid(U*250,T), FL.SPSS.make(250000),ZL_IS);
        //生理盐水

        RM.Welder.addRecipeX(T,256,256, ST.array(OP.plateTiny.mat(MT.Ti,4),OP.gearGtSmall.mat(MT.Ti,1),OP.stick.mat(MT.Ti, 2) , OP.stick.mat(MT.NeodymiumMagnetic,1), OP.stickLong.mat(MT.Rh,1),IL.Processor_Crystal_Emerald.get(1)) ,ZL_FS,ZL_FS,IL.Precision_Manipulator.get(1));
        //精密机械臂，晶体处理器要在MT中换成MF8

        RM.Mixer.addRecipeX(T, 64, 64, ST.array(OP.dustSmall.mat(MT.PO4 , 1) , OP.dustSmall.mat(MT.KNO3 , 1) , OP.dustSmall.mat(MT.CaSO4, 1), OP.dustSmall.mat(MT.MgCl2,1)),ZL_FS,ZL_FS, IL.dust_Inorganic_Salt_Mixture.get(1));
        //无机盐混合物粉

        CR.shaped(IL.dust_Tiny_Inorganic_Salt_Mixture.get(9),DEF , "X  ", 'X',IL.dust_Inorganic_Salt_Mixture);
        CR.shaped(IL.dust_Inorganic_Salt_Mixture.get(1),DEF_NCC, "XXX", "XXX" ,"XXX" , 'X', IL.dust_Tiny_Inorganic_Salt_Mixture.get(9));
        //无机盐混合物粉与其小撮粉的相互转化

        RM.Juicer.addRecipe1(T, 16, 114, ST.make(Items.ghast_tear, 1, 0), ZL_FS, FL.HormoneSolution.make(1), ZL_IS);
        RM.HeatMixer.addRecipe1(T, 256, 810, ST.make(Items.golden_apple, 8, 0), FL.HormoneSolution.make(8), FL.HormoneSolution.make(9), ZL_IS);
        //激素溶液

        RM.Mixer.addRecipe1(T, 16, 64, OP.dust.mat(ANY.Flour, 2), FL.Water.make(1000), FL.AmylumSolution.make(1000), IL.Food_Dough.get(1));
        //淀粉溶液

        RM.HeatMixer.addRecipeX(T, 16, 500, ZL_IS, FL.array(FL.AmylumSolution.make(2000), MT.SulfuricAcid.fluid(U10, T)), FL.Holywater.make(1000), IL.dust_Glucose.get(1));
        //葡萄糖

        RM.Mixer.addRecipeX(T, 16, 512, ZL_IS, FL.array(FL.Juice_Tomato.make(1000), MT.Ethanol.liquid(U, T)), FL.VitaminSolution.make(1), ZL_IS);
        //维生素溶液

        RM.CryoMixer.addRecipeX(T, 256, 514, ST.array(IL.dust_Glucose.get(1), IL.dust_Tiny_Inorganic_Salt_Mixture.get(1)) , FL.array(FL.SPSS.make(1000), FL.Protein.make(10), FL.VitaminSolution.make(1), FL.HormoneSolution.make(1)), FL.MSCBasalMedium.make(1000), ZL_IS);
        //干细胞无血清营养添加物

        RM.CryoMixer.addRecipeX(T, 64, 1919, ST.array(IL.dust_Glucose.get(4), IL.dust_Inorganic_Salt_Mixture.get(1)), FL.array(FL.SPSS.make(1000), FL.Protein.make(1000)), FL.MSCSupplement.make(1000), ZL_IS);
        //干细胞无血清基础培养基

        RM.BioLab.addRecipe1(T, 64, 512, new long[] {1000}, IL.Culture_Dish_Empty.get(1), FL.array(FL.MSCBasalMedium.make(1000), FL.BoneMarrow.make(1)), ZL_FS,IL.Culture_Dish_Stem_Cell_Embryonic.get(1)).setSpecialNumber(200000);
        //接种干细胞
        RM.BioLab.addRecipe2(T, 64, 256, IL.Culture_Dish_Empty.get(4), IL.Stem_Cell.get(1), FL.MSCBasalMedium.make(1000), NF, IL.Culture_Dish_Stem_Cell_Embryonic.get(1)).setSpecialNumber(200000);
        //还是接种干细胞

        for(int duck_egg = 1; duck_egg <=16; duck_egg = duck_egg*2){
            RM.Incubator.addRecipe2(T, 16, 20000 + 4096*duck_egg, IL.Culture_Dish_Stem_Cell_Embryonic.get(duck_egg), ST.tag(duck_egg), FL.MSCSupplement.make(1000*duck_egg), NF, IL.Culture_Dish_Stem_Cell_Somatic.get(duck_egg));
            //培养干细胞，可以一次培养多个
        }

        RM.BioLab.addRecipe1(T, 16, 64, IL.Culture_Dish_Stem_Cell_Somatic.get(1), ZL_FS, ZL_FS, OP.scrapGt.mat(MT.PTFE, 8));
        //把干细胞从培养皿里拿出来
        //生物垃圾不可回收（确信）

        //RM.DistillationTower.addRecipe0(16, 64, ZL_IS, MT.Petrol.liquid(U, T), );





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
