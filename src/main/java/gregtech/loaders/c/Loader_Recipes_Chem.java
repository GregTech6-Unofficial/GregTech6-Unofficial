/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregtech.loaders.c;

import static gregapi.data.CS.*;
import static gregapi.data.OP.*;

import gregapi.data.ANY;
import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Chem implements Runnable {
	@Override public void run() {
		for (FluidStack tWater : FL.array(FL.Water.make(1000), FL.SpDew.make(1000), FL.DistW.make(1000))) {
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.NaCl   , U4), FL.mul(tWater, 3, 4, T), MT.SaltWater.liquid(U*1, F), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.NaCl       ), FL.mul(tWater, 3      ), MT.SaltWater.liquid(U*4, F), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.MgCl2      ), FL.mul(tWater, 2      ), NF, OM.dust(MT.OREMATS.Bischofite, U*1));
		RM.Mixer        .addRecipe1(T, 16,  112, OM.dust(MT.Na2SO4, U*7), FL.mul(tWater, 30     ), NF, OM.dust(MT.OREMATS.Mirabilite, U*7));
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Na2CO3     ),        tWater          , NF, OM.dust(MT.OREMATS.Trona));
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.CaSO4      ),        tWater          , NF, OM.dust(MT.Gypsum));
		
		RM.Mixer        .addRecipe1(T, 16,  112, OM.dust(MT.Si), FL.mul(tWater, 6), MT.H.gas(U*4, F), OM.dust(MT.SiO2, U*3));
		RM.Mixer        .addRecipe1(T, 16,  112, OM.dust(MT.Mn), FL.mul(tWater, 6), MT.H.gas(U*4, F), OM.dust(MT.MnO2, U*1));
		RM.Mixer        .addRecipe1(T, 16,   64, OM.dust(MT.Na), FL.mul(tWater, 3), MT.H.gas(U*1, F), OM.dust(MT.NaOH, U*3));
		RM.Mixer        .addRecipe1(T, 16,   64, OM.dust(MT.K ), FL.mul(tWater, 3), MT.H.gas(U*1, F), OM.dust(MT.KOH , U*3));
		RM.Mixer        .addRecipe1(T, 16,   64, OM.dust(MT.Li), FL.mul(tWater, 3), MT.H.gas(U*1, F), OM.dust(MT.LiOH, U*3));
		
		RM.Mixer        .addRecipe1(T, 16,  112, OM.dust(MT.Ca), FL.array(MT.CO2.gas(U*3, T), FL.mul(tWater, 3)), MT.H.gas(U*2, F), OM.dust(MT.CaCO3, U*5));
		RM.Mixer        .addRecipe1(T, 16,  112, OM.dust(MT.Mg), FL.array(MT.CO2.gas(U*3, T), FL.mul(tWater, 3)), MT.H.gas(U*2, F), OM.dust(MT.MgCO3, U*5));
		
		RM.Mixer        .addRecipe1(T, 16,  272, OM.dust(MT.FeCl3, U*8), FL.array(FL.mul(tWater, 6), MT.SO2.gas(U*3, T)), FL.array(MT.H2SO4.liquid(U*7, F), MT.HCl.fluid (U*4, F)), OM.dust(MT.FeCl2, U*6));
		RM.Mixer        .addRecipe2(T, 16,  208, OM.dust(MT.FeCl3, U*4), OM.dust(MT.NaOH, U*9), FL.mul(tWater, 18), MT.SaltWater.liquid(U*24, F), OM.dust(MT.FeO3H3, U*7));

		RM.Mixer        .addRecipe0(T, 16,   64, FL.array(FL.mul(tWater, 3), MT.Cl .gas(U*2, T)), MT.HCl  .fluid (U* 4, F), MT.O.gas(U, F));
		RM.Mixer        .addRecipe0(T, 16,   64, FL.array(FL.mul(tWater, 3), MT.SO3.gas(U*4, T)), MT.H2SO4.liquid(U* 7, F), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16,  192, FL.array(FL.mul(tWater, 3), MT.NO2.gas(U*9, T)), MT.HNO3 .liquid(U*10, F), MT.NO.gas(U*2, F));
		
		RM.Mixer        .addRecipe0(T, 16,  224, FL.array(FL.mul(tWater, 3), MT.H2S2O7.liquid(U*11, T)), MT.H2SO4.liquid(U*14, F), ZL_IS);
		
		RM.Electrolyzer .addRecipe1(T, 16, 1280, OP.dustSmall.mat(MT.NaCl, 1), FL.array(FL.mul(tWater, 3, 4, T)), FL.array(MT.Cl.gas(U8, F), MT.H.gas(3*U8, F), MT.O.gas(U8, F)), OM.dust(MT.NaOH, 3*U8));
		RM.Electrolyzer .addRecipe1(T, 16, 5120, OP.dust     .mat(MT.NaCl, 1), FL.array(FL.mul(tWater, 3      )), FL.array(MT.Cl.gas(U2, F), MT.H.gas(3*U2, F), MT.O.gas(U2, F)), OM.dust(MT.NaOH, 3*U2));
		RM.Electrolyzer .addRecipe1(T, 16, 1280, OP.dustSmall.mat(MT.KCl , 1), FL.array(FL.mul(tWater, 3, 4, T)), FL.array(MT.Cl.gas(U8, F), MT.H.gas(3*U8, F), MT.O.gas(U8, F)), OM.dust(MT.KOH , 3*U8));
		RM.Electrolyzer .addRecipe1(T, 16, 5120, OP.dust     .mat(MT.KCl , 1), FL.array(FL.mul(tWater, 3      )), FL.array(MT.Cl.gas(U2, F), MT.H.gas(3*U2, F), MT.O.gas(U2, F)), OM.dust(MT.KOH , 3*U2));
		RM.Electrolyzer .addRecipe1(T, 16, 1280, OP.dustSmall.mat(MT.LiCl, 1), FL.array(FL.mul(tWater, 3, 4, T)), FL.array(MT.Cl.gas(U8, F), MT.H.gas(3*U8, F), MT.O.gas(U8, F)), OM.dust(MT.LiOH, 3*U8));
		RM.Electrolyzer .addRecipe1(T, 16, 5120, OP.dust     .mat(MT.LiCl, 1), FL.array(FL.mul(tWater, 3      )), FL.array(MT.Cl.gas(U2, F), MT.H.gas(3*U2, F), MT.O.gas(U2, F)), OM.dust(MT.LiOH, 3*U2));

		RM.Electrolyzer .addRecipe1(T, 64,   64, ST.tag(1), FL.array(MT.BlueVitriol       .liquid(U * 6, T), FL.mul(tWater, 3      )), FL.array(MT.H2SO4.liquid( 7*U , T), MT.O.gas(  U , F)), OM.dust(MT.Cu));
		RM.Electrolyzer .addRecipe1(T, 64,   64, ST.tag(1), FL.array(MT.GreenVitriol      .liquid(U * 6, T), FL.mul(tWater, 3      )), FL.array(MT.H2SO4.liquid( 7*U , T), MT.O.gas(  U , F)), OM.dust(MT.Fe));
		RM.Electrolyzer .addRecipe1(T, 64,   64, ST.tag(1), FL.array(MT.RedVitriol        .liquid(U * 6, T), FL.mul(tWater, 3      )), FL.array(MT.H2SO4.liquid( 7*U , T), MT.O.gas(  U , F)), OM.dust(MT.Co));
		RM.Electrolyzer .addRecipe1(T, 64,   64, ST.tag(1), FL.array(MT.PinkVitriol       .liquid(U * 6, T), FL.mul(tWater, 3      )), FL.array(MT.H2SO4.liquid( 7*U , T), MT.O.gas(  U , F)), OM.dust(MT.Mg));
		RM.Electrolyzer .addRecipe1(T, 64,   64, ST.tag(1), FL.array(MT.CyanVitriol       .liquid(U * 6, T), FL.mul(tWater, 3      )), FL.array(MT.H2SO4.liquid( 7*U , T), MT.O.gas(  U , F)), OM.dust(MT.Ni));
		RM.Electrolyzer .addRecipe1(T, 64,   64, ST.tag(1), FL.array(MT.WhiteVitriol      .liquid(U * 6, T), FL.mul(tWater, 3      )), FL.array(MT.H2SO4.liquid( 7*U , T), MT.O.gas(  U , F)), OM.dust(MT.Zn));
		RM.Electrolyzer .addRecipe1(T, 64,   64, ST.tag(1), FL.array(MT.GrayVitriol       .liquid(U * 6, T), FL.mul(tWater, 3      )), FL.array(MT.H2SO4.liquid( 7*U , T), MT.O.gas(  U , F)), OM.dust(MT.Mn));
		RM.Electrolyzer .addRecipe1(T, 64,   96, ST.tag(1), FL.array(MT.MartianVitriol    .liquid(U2*17, T), FL.mul(tWater, 9, 2, T)), FL.array(MT.H2SO4.liquid(21*U2, T), MT.O.gas(3*U2, F)), OM.dust(MT.Fe));
		RM.Electrolyzer .addRecipe1(T, 64,   96, ST.tag(1), FL.array(MT.VitriolOfClay     .liquid(U2*17, T), FL.mul(tWater, 9, 2, T)), FL.array(MT.H2SO4.liquid(21*U2, T)                   ), OM.dust(MT.Al2O3, 5*U2));
		
		RM.Electrolyzer .addRecipe1(T, 64,   64, ST.tag(1), FL.array(MT.ChloroauricAcid   .liquid(U * 6, T), FL.mul(tWater, 9, 2, T)), FL.array(MT.HCl  .gas   ( 8*U , T), MT.O.gas(3*U2, F)), OM.dust(MT.Au));
		RM.Electrolyzer .addRecipe1(T, 64,   96, ST.tag(1), FL.array(MT.ChloroplatinicAcid.liquid(U * 9, T), FL.mul(tWater, 6      )), FL.array(MT.HCl  .gas   (12*U , T), MT.O.gas(2*U , F)), OM.dust(MT.Pt));
		RM.Electrolyzer .addRecipe1(T, 64,   64, ST.tag(1), FL.array(MT.StannicChloride   .liquid(U * 5, T), FL.mul(tWater, 6      )), FL.array(MT.HCl  .gas   ( 8*U , T), MT.O.gas(2*U , F)), OM.dust(MT.Sn));
		
		for (String tOxygen : FluidsGT.OXYGEN) if (FL.exists(tOxygen)) {
		RM.Lightning    .addRecipe1(T,256,   64, ST.tag(1), FL.array(FL.mul(tWater, 3), FL.make(tOxygen, 1000)), MT.H2O2.liquid(U*4, F), ZL_IS);
		}
		
		RM.Bath         .addRecipe1(T,  0, 2048, OM.dust(MT.Mn), FL.mul(tWater, 6), MT.H.gas(U*4, F), OM.dust(MT.MnO2, U*1));
		RM.Bath         .addRecipe1(T,  0, 2048, OM.dust(MT.Si), FL.mul(tWater, 6), MT.H.gas(U*4, F), OM.dust(MT.SiO2, U*3));
		
		RM.Bath         .addRecipe1(T,  0, 2048, OP.dust.mat(MT.KAlO2 , 4), FL.mul(tWater, 6), NF, OP.dust.mat(MT.AlO3H3, 7), OP.dust.mat(MT.KOH , 3));
		RM.Bath         .addRecipe1(T,  0, 2048, OP.dust.mat(MT.NaAlO2, 4), FL.mul(tWater, 6), NF, OP.dust.mat(MT.AlO3H3, 7), OP.dust.mat(MT.NaOH, 3));
		}
		
		
		for (OreDictMaterial tMat : ANY.SiO2.mToThis)
		RM.Mixer        .addRecipe1(T, 16,  240, OM.dust(tMat                   ,U * 3), FL.array(MT.HF.gas(U *12, T))                                                              , FL.array(MT.H2O.liquid(U * 6, F), MT.H2SiF6.liquid(U * 9, F)));
		RM.Mixer        .addRecipe1(T, 16,  224, OM.dust(MT.Al2O3               ,U * 5), FL.array(MT.H2SiF6.liquid(U * 9, T))                                                       , FL.array(MT.H2O.liquid(U * 3, F)), OM.dust(MT.AlF3, U*8));//, OM.dust(MT.SiO2, U*3));
		RM.Mixer        .addRecipe1(T, 16,  224, OM.ingot(MT.Al2O3              ,U * 5), FL.array(MT.H2SiF6.liquid(U * 9, T))                                                       , FL.array(MT.H2O.liquid(U * 3, F)), OM.dust(MT.AlF3, U*8));//, OM.dust(MT.SiO2, U*3));
		RM.Mixer        .addRecipe2(T, 16,  752, OP.dust.mat(MT.NaOH            ,   18), OM.dust(MT.Al2O3,U * 5), MT.HF.gas(U *24, T)                                               , FL.array(MT.H2O.liquid(U *27, F), MT.Na3AlF6.liquid(U *20, F)));
		RM.Mixer        .addRecipe2(T, 16,  752, OP.dust.mat(MT.NaOH            ,   18), OM.ingot(MT.Al2O3,U * 5), MT.HF.gas(U *24, T)                                              , FL.array(MT.H2O.liquid(U *27, F), MT.Na3AlF6.liquid(U *20, F)));
		RM.Mixer        .addRecipe2(T, 16,  752, OP.blockDust.mat(MT.NaOH       ,    2), OM.dust(MT.Al2O3,U * 5), MT.HF.gas(U *24, T)                                               , FL.array(MT.H2O.liquid(U *27, F), MT.Na3AlF6.liquid(U *20, F)));
		RM.Mixer        .addRecipe2(T, 16,  752, OP.blockDust.mat(MT.NaOH       ,    2), OM.ingot(MT.Al2O3,U * 5), MT.HF.gas(U *24, T)                                              , FL.array(MT.H2O.liquid(U *27, F), MT.Na3AlF6.liquid(U *20, F)));
		RM.Mixer        .addRecipe1(T, 16,  748, OP.dust.mat(MT.Sugar           ,   45), FL.array(MT.H2SO4.fluid(U4*7, T))                                                          , FL.array(MT.H2O.liquid(U4*135, F), MT.SO3.gas(U, F)), OM.dust(MT.C, U*12));
		RM.Mixer        .addRecipe1(T, 16,  748, OP.blockDust.mat(MT.Sugar      ,    5), FL.array(MT.H2SO4.fluid(U4*7, T))                                                          , FL.array(MT.H2O.liquid(U4*135, F), MT.SO3.gas(U, F)), OM.dust(MT.C, U*12));
		RM.Mixer        .addRecipe1(T, 16,  752, OM.dust(MT.OREMATS.Borax       ,U *43), FL.array(MT.HCl.fluid(U * 4, T))                                                           , FL.array(MT.H2O.liquid(U * 3, F), MT.SaltWater.liquid(U*16, F)), OM.dust(MT.H3BO3, U*28));
		RM.Mixer        .addRecipe1(T, 16,  176, OM.dust(MT.MnO2                ,U * 1), FL.array(MT.HCl.fluid(U * 8, T))                                                           , FL.array(MT.H2O.liquid(U * 6, F), MT.Cl.gas(U * 2, F)), OM.dust(MT.MnCl2, U*3));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.CaCO3               ,U * 5), FL.array(MT.HCl.fluid(U * 4, T))                                                           , FL.array(MT.H2O.liquid(U * 3, F), MT.CO2.gas(U * 3, F)), OM.dust(MT.CaCl2, U*3));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.MgCO3               ,U * 5), FL.array(MT.HCl.fluid(U * 4, T))                                                           , FL.array(MT.H2O.liquid(U * 3, F), MT.CO2.gas(U * 3, F)), OM.dust(MT.MgCl2, U*3));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.CaCO3               ,U * 5), FL.array(MT.HF.gas(U * 4, T))                                                              , FL.array(MT.H2O.liquid(U * 3, F), MT.CO2.gas(U * 3, F)), OM.dust(MT.CaF2, U*3));
//      RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.MgCO3               ,U * 5), FL.array(MT.HF.gas(U * 4, T))                                                              , FL.array(MT.H2O.liquid(U * 3, F), MT.CO2.gas(U * 3, F)), OM.dust(MT.MgF2, U*3));
		RM.Mixer        .addRecipe1(T, 16,  256, OM.dust(MT.K2CO3               ,U * 6), FL.array(MT.HNO3.liquid(U *10, T))                                                         , FL.array(MT.H2O.liquid(U * 3, F), MT.CO2.gas(U * 3, F)), OM.dust(MT.KNO3, U*10));
		RM.Mixer        .addRecipe1(T, 16,  256, OM.dust(MT.Na2CO3              ,U * 6), FL.array(MT.HNO3.liquid(U *10, T))                                                         , FL.array(MT.H2O.liquid(U * 3, F), MT.CO2.gas(U * 3, F)), OM.dust(MT.NaNO3, U*10));
		RM.HeatMixer    .addRecipe1(T, 16,  354, OM.dust(MT.LiOH                ,U *18), FL.array(MT.Cl.gas(U * 6, T))                                                              , FL.array(MT.H2O.liquid(U * 9, F), MT.LiClO3.liquid(U*5, F)), OM.dust(MT.LiCl, U*10));
		for (OreDictMaterial tMat : ANY.CaF2.mToThis)
		RM.Mixer        .addRecipe1(T, 16,  160, OM.dust(tMat                   ,U * 3), MT.H2SO4       .liquid(U* 7, T)                                                            , MT.HF             .gas  (U * 4, F), OM.dust(MT.CaSO4, U* 6));
		RM.Mixer        .addRecipe1(T, 16,   64, OM.dust(MT.Al                  ,U * 1), MT.F           .gas  (U * 3, T)                                                            , NF                                , OM.dust(MT.AlF3, U*4));
		RM.Mixer        .addRecipe1(T, 16,   48, OM.dust(MT.Ca                  ,U * 1), MT.F           .gas  (U * 2, T)                                                            , NF                                , OM.dust(MT.CaF2, U*3));
		RM.HeatMixer    .addRecipe1(T, 16,   48, OM.dust(MT.S                   ,U * 1), MT.H           .gas  (U * 2, T)                                                            , MT.H2S            .gas  (U * 3, F), ZL_IS);
		RM.HeatMixer    .addRecipe1(T, 16,   48, OM.dust(MT.Blaze               ,U9   ), MT.H           .gas  (U * 2, T)                                                            , MT.H2S            .gas  (U * 3, F), ZL_IS);
		RM.HeatMixer    .addRecipe1(T, 16,  160, OM.dust(MT.WO3                 ,U * 4), MT.H           .gas  (U * 6, T)                                                            , MT.H2O            .liquid(U* 9, F), OM.dust(MT.W, U));
//      RM.Mixer        .addRecipe1(T, 16,   96, OM.dust(MT.FeS                 ,U * 2), MT.HCl         .fluid(U * 4, T)                                                            , MT.H2S            .gas  (U * 3, F), OM.dust(MT.FeCl2, U*3));
		RM.Mixer        .addRecipe1(T, 16,   80, OM.dust(MT.Ca                  ,U * 1), MT.HCl         .fluid(U * 4, T)                                                            , MT.H              .gas  (U * 2, F), OM.dust(MT.CaCl2, U*3));
		RM.Mixer        .addRecipe1(T, 16,   80, OM.dust(MT.Mg                  ,U * 1), MT.HCl         .fluid(U * 4, T)                                                            , MT.H              .gas  (U * 2, F), OM.dust(MT.MgCl2, U*3));
		RM.Mixer        .addRecipe1(T, 16,   80, OM.dust(MT.NaOH                ,U * 3), MT.HCl         .fluid(U * 2, T)                                                            , MT.SaltWater      .liquid(U* 4, F), OM.dust(MT.NaCl, U*1));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.NaOH                ,U * 6), MT.SO2         .gas  (U * 3, T)                                                            , MT.H2O            .liquid(U* 3, F), OM.dust(MT.Na2SO3, U*6));
		RM.Mixer        .addRecipe1(T, 16,  128, OM.dust(MT.KOH                 ,U * 3), MT.HNO3        .liquid(U* 5, T)                                                            , MT.H2O            .liquid(U* 3, F), OM.dust(MT.KNO3, U*5));
		RM.Mixer        .addRecipe1(T, 16,  128, OM.dust(MT.NaOH                ,U * 3), MT.HNO3        .liquid(U* 5, T)                                                            , MT.H2O            .liquid(U* 3, F), OM.dust(MT.NaNO3, U*5));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.KOH                 ,U * 6), MT.CO2         .gas  (U * 3, T)                                                            , MT.H2O            .liquid(U* 3, F), OM.dust(MT.K2CO3, U*6));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.NaOH                ,U * 6), MT.CO2         .gas  (U * 3, T)                                                            , MT.H2O            .liquid(U* 3, F), OM.dust(MT.Na2CO3, U*6));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.KOH                 ,U * 6), MT.H2S         .gas  (U * 3, T)                                                            , MT.H2O            .liquid(U* 6, F), OM.dust(MT.K2S, U*3));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.NaOH                ,U * 6), MT.H2S         .gas  (U * 3, T)                                                            , MT.H2O            .liquid(U* 6, F), OM.dust(MT.Na2S, U*3));
		RM.Mixer        .addRecipe1(T, 16,  192, OM.dust(MT.KNO3                ,U * 5), MT.H2SO4       .liquid(U* 7, T)                                                            , MT.HNO3           .liquid(U* 5, F), OM.dust(MT.KHSO4, U*7));
		RM.Mixer        .addRecipe1(T, 16,  192, OM.dust(MT.NaNO3               ,U * 5), MT.H2SO4       .liquid(U* 7, T)                                                            , MT.HNO3           .liquid(U* 5, F), OM.dust(MT.NaHSO4, U*7));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.KCl                 ,U * 2), MT.H2SO4       .liquid(U* 7, T)                                                            , MT.HCl            .fluid(U * 2, F), OM.dust(MT.KHSO4, U*7));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.NaCl                ,U * 2), MT.H2SO4       .liquid(U* 7, T)                                                            , MT.HCl            .fluid(U * 2, F), OM.dust(MT.NaHSO4, U*7));
		RM.Mixer        .addRecipe1(T, 16,   32, OM.dust(MT.K2SO4               ,U * 1), MT.H2SO4       .liquid(U* 1, T)                                                            , NF                                , OM.dust(MT.KHSO4, U*2));
		RM.Mixer        .addRecipe1(T, 16,   32, OM.dust(MT.Na2SO4              ,U * 1), MT.H2SO4       .liquid(U* 1, T)                                                            , NF                                , OM.dust(MT.NaHSO4, U*2));
		RM.Mixer        .addRecipe1(T, 16,  304, OM.dust(MT.K2S                 ,U * 3), MT.H2O2        .liquid(U*16, T)                                                            , MT.H2O            .liquid(U*12, F), OM.dust(MT.K2SO4, U*7));
		RM.Mixer        .addRecipe1(T, 16,  304, OM.dust(MT.Na2S                ,U * 3), MT.H2O2        .liquid(U*16, T)                                                            , MT.H2O            .liquid(U*12, F), OM.dust(MT.Na2SO4, U*7));
		RM.Mixer        .addRecipe0(T, 16,  272, FL.array(MT.Fe2O3.liquid(U*5, T), MT.HCl.fluid(U*12, T))                                                                           , MT.H2O            .liquid(U* 9, F), OM.dust(MT.FeCl3, U*8));
		RM.Mixer        .addRecipe0(T, 16,  800, FL.array(MT.Glycerol.fluid(U*14, T), MT.HNO3.liquid(U*15, T), MT.H2SO4.liquid(U*21, T))                                            , MT.Glyceryl       .fluid(U *20, F), MT.SO2.gas(U*6, F)); // + 18 Units of Water + 1 Unit of Oxygen
		if (FL.Reikygen.exists())
		RM.Mixer        .addRecipe0(T, 16,   48, FL.array(MT.NO.gas(U*2, T), FL.Reikygen.make(1000))                                                                                , MT.NO2            .gas  (U * 3, F), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16,   48, FL.array(MT.NO.gas(U*2, T), FL.Oxygen  .make(1000))                                                                                , MT.NO2            .gas  (U * 3, F), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16,   32, FL.array(MT.H.gas(U, T), MT.F.gas(U, T))                                                                                           , MT.HF             .gas  (U * 2, F), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16,   24, FL.array(MT.H.gas(U, T), MT.S.liquid(U2, T))                                                                                       , MT.H2S            .gas  (U2* 3, F), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16,  160, FL.array(MT.NO2.gas(U*6, T), MT.H2O2.liquid(U*4, T))                                                                               , MT.HNO3           .liquid(U*10, F), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16,  176, FL.array(MT.SO3.gas(U*4, T), MT.H2SO4.liquid(U*7, T))                                                                              , MT.H2S2O7         .liquid(U*11, F), ZL_IS);
		RM.HeatMixer    .addRecipe0(T, 16,   48, FL.array(MT.H2S.gas(U*2, T), MT.SO2.gas(U*1, T))                                                                                   , MT.H2O            .liquid(U* 2, F), OM.dust(MT.S, U));
		RM.Mixer        .addRecipe2(T, 16,   48, OP.dust.mat(MT.Na, 2), OP.dust    .mat(MT.S    , 1), OP.dust.mat(MT.Na2S, 3));
		RM.Mixer        .addRecipe2(T, 16,   48, OP.dust.mat(MT.Na, 2), OP.dustTiny.mat(MT.Blaze, 1), OP.dust.mat(MT.Na2S, 3));
		RM.Mixer        .addRecipe2(T, 16,   48, OP.dust.mat(MT.K , 2), OP.dust    .mat(MT.S    , 1), OP.dust.mat(MT.K2S , 3));
		RM.Mixer        .addRecipe2(T, 16,   48, OP.dust.mat(MT.K , 2), OP.dustTiny.mat(MT.Blaze, 1), OP.dust.mat(MT.K2S , 3));
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {ItemStack tSiliconDioxide = OM.dust(tMat, U*3); if (ST.invalid(tSiliconDioxide)) continue;
		RM.HeatMixer    .addRecipe2(T, 16,  144, tSiliconDioxide, OP.dust.mat(MT.CaSO4, 6), NF, MT.SO3.gas(U*4, F), OM.dust(MT.OREMATS.Wollastonite, U*5));
		}
		for (OreDictMaterial tMat : ANY.Fe.mToThis) {ItemStack tIron = OM.dust(tMat); if (ST.invalid(tIron)) continue;
		RM.Mixer        .addRecipe1(T, 16,   64, tIron                                 , MT.Cl          .gas  (U * 3, T)                                                            , NF                                , OM.dust(MT.FeCl3, U*4));
		RM.Mixer        .addRecipe1(T, 16,   80, tIron                                 , MT.HCl         .fluid(U * 4, T)                                                            , MT.H              .gas  (U * 2, F), OM.dust(MT.FeCl2, U*3));
		RM.Mixer        .addRecipe2(T, 16,  144, tIron, OM.dust(MT.FeCl3, U*8)                                                                                                                                          , OM.dust(MT.FeCl2, U*9));
		}
		
		RM.HeatMixer    .addRecipe1(T,  16, 186, OM.dust(MT.OREMATS.Uraninite, U*1), MT.HF.gas(U*8, T), MT.H2O.liquid(U*6, F), OM.dust(MT.UF4, U*5));
		RM.Mixer        .addRecipe1(T,  16, 112, OM.dust(MT.UF4, U*5), MT.F.gas(U*2, T), NF, MT.UF6.gas(U*7, T));
		RM.Mixer        .addRecipe0(T,  16, 144, FL.array(MT.H .gas   (U*2, T), MT.UF6   .gas   (U*7, T)), MT.HF  .gas   (U*4, T), OM.dust(MT.UF4   , U*5));
		RM.Mixer        .addRecipe0(T,  16, 144, FL.array(MT.H .gas   (U*2, T), MT.U238F6.gas   (U*7, T)), MT.HF  .gas   (U*4, T), OM.dust(MT.U238F4, U*5));
		RM.Mixer        .addRecipe0(T,  16, 144, FL.array(MT.H .gas   (U*2, T), MT.U235F6.gas   (U*7, T)), MT.HF  .gas   (U*4, T), OM.dust(MT.U235F4, U*5));
		RM.Mixer        .addRecipe0(T,  16, 112, FL.array(MT.Ca.liquid(U*2, T), MT.UF4   .liquid(U*5, T)), MT.CaF2.liquid(U*6, T), MT.U_238.liquid(U*1, T));
		RM.Mixer        .addRecipe0(T,  16, 112, FL.array(MT.Ca.liquid(U*2, T), MT.U238F4.liquid(U*5, T)), MT.CaF2.liquid(U*6, T), MT.U_238.liquid(U*1, T));
		RM.Mixer        .addRecipe0(T,  16, 112, FL.array(MT.Ca.liquid(U*2, T), MT.U235F4.liquid(U*5, T)), MT.CaF2.liquid(U*6, T), MT.U_235.liquid(U*1, T));
		RM.Centrifuge   .addRecipe0(T, 512, 120, MT.UF6.gas(U*7, T), MT.U238F6.gas(U*5, T), MT.U235F6.gas(U*2, T));
		
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.NaCl                  , U *4), MT.CaCO3.liquid(U* 5, T), NF, OM.dust(MT.CaCl2, U*3), OM.dust(MT.Na2CO3, U*6));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.NaCl                  , U *4), MT.MgCO3.liquid(U* 5, T), NF, OM.dust(MT.MgCl2, U*3), OM.dust(MT.Na2CO3, U*6));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.OREMATS.Scheelite     , U *6), MT.HCl   .fluid(U* 4, T), NF, OM.dust(MT.CaCl2, U*3), OM.dust(MT.H2WO4 , U*7));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.OREMATS.Wolframite    , U *6), MT.HCl   .fluid(U* 4, T), NF, OM.dust(MT.MgCl2, U*3), OM.dust(MT.H2WO4 , U*7));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.OREMATS.Ferberite     , U *6), MT.HCl   .fluid(U* 4, T), NF, OM.dust(MT.FeCl2, U*3), OM.dust(MT.H2WO4 , U*7));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.OREMATS.Huebnerite    , U *6), MT.HCl   .fluid(U* 4, T), NF, OM.dust(MT.MnCl2, U*3), OM.dust(MT.H2WO4 , U*7));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.OREMATS.Tungstate     , U *7), MT.HCl   .fluid(U* 4, T), NF, OM.dust(MT.LiCl , U*4), OM.dust(MT.H2WO4 , U*7));
		RM.Bath         .addRecipe1(T, 0,   512, ST.make(Blocks.sand              , 1, 1), MT.HCl   .fluid(U*12, T), MT.H2O.liquid(U*9, F), OM.dust(MT.FeCl3, U*8));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.RedSand               , U *9), MT.HCl   .fluid(U*12, T), MT.H2O.liquid(U*9, F), OM.dust(MT.FeCl3, U*8));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.Fe2O3                 , U *5), MT.HCl   .fluid(U*12, T), MT.H2O.liquid(U*9, F), OM.dust(MT.FeCl3, U*8));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.OREMATS.YellowLimonite, U *4), MT.HCl   .fluid(U* 6, T), MT.H2O.liquid(U*6, F), OM.dust(MT.FeCl3, U*4));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.OREMATS.BrownLimonite , U *4), MT.HCl   .fluid(U* 6, T), MT.H2O.liquid(U*6, F), OM.dust(MT.FeCl3, U*4));
		
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.FeCl3, U*4), FL.Steam.make(9000*STEAM_PER_WATER), MT.HCl.fluid(U*6, T), OM.dust(MT.FeO3H3, U*7));
		
		
		for (String tOxygen : FluidsGT.OXYGEN) if (FL.exists(tOxygen)) {
		if (FL.Liquid_Reikygen.exists())
		RM.Freezer      .addRecipe1(T, 16,  256, ST.tag(1), FL.make(tOxygen, 1000), FL.Liquid_Reikygen.make(1000), ZL_IS);
		RM.Freezer      .addRecipe1(T, 16,  256, ST.tag(0), FL.make(tOxygen, 1000), FL.Liquid_Oxygen.make(1000), ZL_IS);
		
		RM.Lightning    .addRecipe1(T,256,   16, ST.tag(2), FL.array(MT.N.gas(U100, T), FL.make(tOxygen, 10)), MT.NO.gas(U100*2, F), ZL_IS);
		RM.Lightning    .addRecipe2(T,256,  512, ST.tag(2), OM.dust(MT.K2S , U*3), FL.make(tOxygen, 4000), NF, OM.dust(MT.K2SO4 , U*7));
		RM.Lightning    .addRecipe2(T,256,  512, ST.tag(2), OM.dust(MT.Na2S, U*3), FL.make(tOxygen, 4000), NF, OM.dust(MT.Na2SO4, U*7));
		
		RM.BurnMixer    .addRecipe0(T, 16,   24, FL.array(MT.H.gas(U, T), FL.make(tOxygen, 500)), FL.DistW.make(1500), ZL_IS);
		
		RM.Mixer        .addRecipe1(T, 16,   16, OP.dust.mat(MT.Pt  , 0), FL.array(MT.SO2.gas(3*U4, T), FL.make(tOxygen,  250)), FL.array(MT.SO3.gas(U, F)), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   16, OP.dust.mat(MT.V2O5, 0), FL.array(MT.SO2.gas(3*U4, T), FL.make(tOxygen,  250)), FL.array(MT.SO3.gas(U, F)), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,  176, OM.dust(MT.NaCl   ,3*U), FL.array(MT.SO2.gas(3*U , T), FL.make(tOxygen, 1000) , MT.SaltWater.liquid(U*4, T)), MT.HCl.fluid(U*4, F), OM.dust(MT.Na2SO4, U*7));
		RM.Mixer        .addRecipe1(T, 16,  176, OM.dust(MT.KCl    ,3*U), FL.array(MT.SO2.gas(3*U , T), FL.make(tOxygen, 1000) , MT.SaltWater.liquid(U*4, T)), MT.HCl.fluid(U*4, F), OM.dust(MT.K2SO4 , U*7));
		}
		for (String tAir : FluidsGT.AIR) if (FL.exists(tAir)) {
		RM.Mixer        .addRecipe1(T, 16,   16, OP.dust.mat(MT.Pt  , 0), FL.array(MT.SO2.gas(3*U4, T), FL.make(tAir, 1000)), FL.array(MT.SO3.gas(U, F)), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   16, OP.dust.mat(MT.V2O5, 0), FL.array(MT.SO2.gas(3*U4, T), FL.make(tAir, 1000)), FL.array(MT.SO3.gas(U, F)), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,  176, OM.dust(MT.NaCl   ,3*U), FL.array(MT.SO2.gas(3*U , T), FL.make(tAir, 4000) , MT.SaltWater.liquid(U*4, T)), MT.HCl.fluid(U*4, F), OM.dust(MT.Na2SO4, U*7));
		RM.Mixer        .addRecipe1(T, 16,  176, OM.dust(MT.KCl    ,3*U), FL.array(MT.SO2.gas(3*U , T), FL.make(tAir, 4000) , MT.SaltWater.liquid(U*4, T)), MT.HCl.fluid(U*4, F), OM.dust(MT.K2SO4 , U*7));
		}
		
		
		for (OreDictMaterial tMat : ANY.Coal.mToThis) if (tMat != MT.Graphene) {
		RM.BurnMixer    .addRecipe2(T, 16,  144, OM.dust(MT.Na2SO4, U*7), OM.dust(tMat, U*2), NF  , MT.CO2.gas(U*6, F), OM.dust(MT.Na2S, U*3));
		RM.BurnMixer    .addRecipe2(T, 16,  144, OM.dust(MT.K2SO4 , U*7), OM.dust(tMat, U*2), NF  , MT.CO2.gas(U*6, F), OM.dust(MT.K2S, U*3));
		RM.BurnMixer    .addRecipe1(T, 16,  128, OM.dust(tMat     , U*2), MT.Na2CO3.liquid(U*6, T), MT.CO .gas(U*6, F), OM.dust(MT.Na, U*2));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(tMat     , U*3), MT.HNO3.liquid(20*U, T) , MT.CO2.gas(U*9, F), MT.NO.gas(U*8, T), MT.H2O.fluid(U*6, F));
		}
		
		
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.TiO2            , U   ), OM.dust(MT.C       ,   U ), FL.array(MT.Cl.gas(U* 4, T), MT.CaCO3.liquid(U  , T)), FL.array(MT.TiCl4.liquid(U* 5, F), MT.CO2.gas(U* 3, F)), ZL_IS);
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.TiO2            , U* 2), OM.dust(MT.C       , 2*U ), FL.array(MT.Cl.gas(U* 8, T), MT.CaCO3.liquid(U*2, T)), FL.array(MT.TiCl4.liquid(U*10, F), MT.CO2.gas(U* 6, F)), ZL_IS);
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.OREMATS.Ilmenite, U* 5), OM.dust(MT.C       , 3*U ), FL.array(MT.Cl.gas(U* 7, T), MT.CaCO3.liquid(U  , T)), FL.array(MT.TiCl4.liquid(U* 5, F), MT.CO .gas(U* 6, F)), OM.dust(MT.FeCl3, U*4));
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.OREMATS.Ilmenite, U*10), OM.dust(MT.C       , 6*U ), FL.array(MT.Cl.gas(U*14, T), MT.CaCO3.liquid(U*2, T)), FL.array(MT.TiCl4.liquid(U*10, F), MT.CO .gas(U*12, F)), OM.dust(MT.FeCl3, U*8));
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.TiO2            , U   ), OM.dust(MT.Graphite,   U ), FL.array(MT.Cl.gas(U* 4, T), MT.CaCO3.liquid(U  , T)), FL.array(MT.TiCl4.liquid(U* 5, F), MT.CO2.gas(U* 3, F)), ZL_IS);
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.TiO2            , U* 2), OM.dust(MT.Graphite, 2*U ), FL.array(MT.Cl.gas(U* 8, T), MT.CaCO3.liquid(U*2, T)), FL.array(MT.TiCl4.liquid(U*10, F), MT.CO2.gas(U* 6, F)), ZL_IS);
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.OREMATS.Ilmenite, U* 5), OM.dust(MT.Graphite, 3*U ), FL.array(MT.Cl.gas(U* 7, T), MT.CaCO3.liquid(U  , T)), FL.array(MT.TiCl4.liquid(U* 5, F), MT.CO .gas(U* 6, F)), OM.dust(MT.FeCl3, U*4));
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.OREMATS.Ilmenite, U*10), OM.dust(MT.Graphite, 6*U ), FL.array(MT.Cl.gas(U*14, T), MT.CaCO3.liquid(U*2, T)), FL.array(MT.TiCl4.liquid(U*10, F), MT.CO .gas(U*12, F)), OM.dust(MT.FeCl3, U*8));
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.TiO2            , U   ), OM.dust(MT.CoalCoke,   U2), FL.array(MT.Cl.gas(U* 4, T), MT.CaCO3.liquid(U  , T)), FL.array(MT.TiCl4.liquid(U* 5, F), MT.CO2.gas(U* 3, F)), ZL_IS);
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.TiO2            , U* 2), OM.dust(MT.CoalCoke,   U ), FL.array(MT.Cl.gas(U* 8, T), MT.CaCO3.liquid(U*2, T)), FL.array(MT.TiCl4.liquid(U*10, F), MT.CO2.gas(U* 6, F)), ZL_IS);
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.OREMATS.Ilmenite, U* 5), OM.dust(MT.CoalCoke, 3*U2), FL.array(MT.Cl.gas(U* 7, T), MT.CaCO3.liquid(U  , T)), FL.array(MT.TiCl4.liquid(U* 5, F), MT.CO .gas(U* 6, F)), OM.dust(MT.FeCl3, U*4));
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.OREMATS.Ilmenite, U*10), OM.dust(MT.CoalCoke, 3*U ), FL.array(MT.Cl.gas(U*14, T), MT.CaCO3.liquid(U*2, T)), FL.array(MT.TiCl4.liquid(U*10, F), MT.CO .gas(U*12, F)), OM.dust(MT.FeCl3, U*8));
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.TiO2            , U   ), OM.dust(MT.PetCoke ,   U2), FL.array(MT.Cl.gas(U* 4, T), MT.CaCO3.liquid(U  , T)), FL.array(MT.TiCl4.liquid(U* 5, F), MT.CO2.gas(U* 3, F)), ZL_IS);
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.TiO2            , U* 2), OM.dust(MT.PetCoke ,   U ), FL.array(MT.Cl.gas(U* 8, T), MT.CaCO3.liquid(U*2, T)), FL.array(MT.TiCl4.liquid(U*10, F), MT.CO2.gas(U* 6, F)), ZL_IS);
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.OREMATS.Ilmenite, U* 5), OM.dust(MT.PetCoke , 3*U2), FL.array(MT.Cl.gas(U* 7, T), MT.CaCO3.liquid(U  , T)), FL.array(MT.TiCl4.liquid(U* 5, F), MT.CO .gas(U* 6, F)), OM.dust(MT.FeCl3, U*4));
		RM.BurnMixer    .addRecipe2(T, 16,  256, OM.dust(MT.OREMATS.Ilmenite, U*10), OM.dust(MT.PetCoke , 3*U ), FL.array(MT.Cl.gas(U*14, T), MT.CaCO3.liquid(U*2, T)), FL.array(MT.TiCl4.liquid(U*10, F), MT.CO .gas(U*12, F)), OM.dust(MT.FeCl3, U*8));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.OREMATS.Ilmenite, U*5), FL.array(MT.H2SO4.liquid(7*U, T)), FL.array(MT.GreenVitriol.liquid(6*U, T), FL.Water.make(3000)), OM.dust(MT.TiO2));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.Na, U*4), MT.TiCl4.liquid(5*U, T), NF, OM.dust(MT.Ti), OM.dust(MT.NaCl , U*2), OM.dust(MT.NaCl , U*2), OM.dust(MT.NaCl , U*2), OM.dust(MT.NaCl , U*2));
		RM.Bath         .addRecipe1(T, 0,   512, OM.dust(MT.Mg, U*2), MT.TiCl4.liquid(5*U, T), NF, OM.dust(MT.Ti), OM.dust(MT.MgCl2, U*2), OM.dust(MT.MgCl2, U*2), OM.dust(MT.MgCl2, U*2));
		
		
		RM.Mixer        .addRecipe0(T, 16,   16, FL.array(MT.Fuel.liquid(2*U5, T), MT.Glyceryl.fluid(U10, T))                                                              , MT.NitroFuel      .liquid(U2, F), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16,   64, FL.array(MT.HNO3.liquid(U*5, T), MT.HCl.fluid(U*8, T))                                                                    , MT.AquaRegia      .fluid(U*13, F), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16,   16, FL.array(MT.He.gas(U200, T), MT.Ne.gas(U200, T))                                                                          , MT.HeNe           .gas(U100, F), ZL_IS);
		
		
		RM.Autoclave    .addRecipe2(T,  0, 1500, new long[] {10000, 5000, 5000}, OP.dust     .mat(MT.OREMATS.Bauxite, 1), OP.dustSmall.mat(MT.KOH , 6), FL.Steam.make(48000), FL.DistW.make(300+ 750/*+2175*/), OP.dust.mat(MT.KAlO2 , 2), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 2), OP.crushedCentrifugedTiny.mat(MT.TiO2, 1));
		RM.Autoclave    .addRecipe2(T,  0, 1500, new long[] {10000, 5000, 5000}, OP.dustSmall.mat(MT.OREMATS.Bauxite, 4), OP.dustSmall.mat(MT.KOH , 6), FL.Steam.make(48000), FL.DistW.make(300+ 750/*+2175*/), OP.dust.mat(MT.KAlO2 , 2), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 2), OP.crushedCentrifugedTiny.mat(MT.TiO2, 1));
		RM.Autoclave    .addRecipe2(T,  0, 1500, new long[] {10000, 5000, 5000}, OP.dustTiny .mat(MT.OREMATS.Bauxite, 9), OP.dustSmall.mat(MT.KOH , 6), FL.Steam.make(48000), FL.DistW.make(300+ 750/*+2175*/), OP.dust.mat(MT.KAlO2 , 2), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 2), OP.crushedCentrifugedTiny.mat(MT.TiO2, 1));
		RM.Autoclave    .addRecipe2(T,  0, 3000, new long[] {10000, 5000, 5000}, OP.dust     .mat(MT.OREMATS.Bauxite, 2), OP.dust     .mat(MT.KOH , 3), FL.Steam.make(96000), FL.DistW.make(600+1500/*+4350*/), OP.dust.mat(MT.KAlO2 , 4), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 4), OP.crushedCentrifugedTiny.mat(MT.TiO2, 2));
		RM.Autoclave    .addRecipe2(T,  0, 3000, new long[] {10000, 5000, 5000}, OP.dustSmall.mat(MT.OREMATS.Bauxite, 8), OP.dust     .mat(MT.KOH , 3), FL.Steam.make(96000), FL.DistW.make(600+1500/*+4350*/), OP.dust.mat(MT.KAlO2 , 4), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 4), OP.crushedCentrifugedTiny.mat(MT.TiO2, 2));
		RM.Autoclave    .addRecipe2(T,  0, 3000, new long[] {10000, 5000, 5000}, OP.dustTiny .mat(MT.OREMATS.Bauxite,18), OP.dust     .mat(MT.KOH , 3), FL.Steam.make(96000), FL.DistW.make(600+1500/*+4350*/), OP.dust.mat(MT.KAlO2 , 4), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 4), OP.crushedCentrifugedTiny.mat(MT.TiO2, 2));
		RM.Autoclave    .addRecipe2(T,  0, 1500, new long[] {10000, 5000, 5000}, OP.dust     .mat(MT.OREMATS.Bauxite, 1), OP.dustSmall.mat(MT.NaOH, 6), FL.Steam.make(48000), FL.DistW.make(300+ 750/*+2175*/), OP.dust.mat(MT.NaAlO2, 2), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 2), OP.crushedCentrifugedTiny.mat(MT.TiO2, 1));
		RM.Autoclave    .addRecipe2(T,  0, 1500, new long[] {10000, 5000, 5000}, OP.dustSmall.mat(MT.OREMATS.Bauxite, 4), OP.dustSmall.mat(MT.NaOH, 6), FL.Steam.make(48000), FL.DistW.make(300+ 750/*+2175*/), OP.dust.mat(MT.NaAlO2, 2), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 2), OP.crushedCentrifugedTiny.mat(MT.TiO2, 1));
		RM.Autoclave    .addRecipe2(T,  0, 1500, new long[] {10000, 5000, 5000}, OP.dustTiny .mat(MT.OREMATS.Bauxite, 9), OP.dustSmall.mat(MT.NaOH, 6), FL.Steam.make(48000), FL.DistW.make(300+ 750/*+2175*/), OP.dust.mat(MT.NaAlO2, 2), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 2), OP.crushedCentrifugedTiny.mat(MT.TiO2, 1));
		RM.Autoclave    .addRecipe2(T,  0, 3000, new long[] {10000, 5000, 5000}, OP.dust     .mat(MT.OREMATS.Bauxite, 2), OP.dust     .mat(MT.NaOH, 3), FL.Steam.make(96000), FL.DistW.make(600+1500/*+4350*/), OP.dust.mat(MT.NaAlO2, 4), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 4), OP.crushedCentrifugedTiny.mat(MT.TiO2, 2));
		RM.Autoclave    .addRecipe2(T,  0, 3000, new long[] {10000, 5000, 5000}, OP.dustSmall.mat(MT.OREMATS.Bauxite, 8), OP.dust     .mat(MT.NaOH, 3), FL.Steam.make(96000), FL.DistW.make(600+1500/*+4350*/), OP.dust.mat(MT.NaAlO2, 4), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 4), OP.crushedCentrifugedTiny.mat(MT.TiO2, 2));
		RM.Autoclave    .addRecipe2(T,  0, 3000, new long[] {10000, 5000, 5000}, OP.dustTiny .mat(MT.OREMATS.Bauxite,18), OP.dust     .mat(MT.NaOH, 3), FL.Steam.make(96000), FL.DistW.make(600+1500/*+4350*/), OP.dust.mat(MT.NaAlO2, 4), OP.crushedCentrifugedTiny.mat(MT.OREMATS.Ilmenite, 4), OP.crushedCentrifugedTiny.mat(MT.TiO2, 2));
		
		
		// TODO Sugar + 6 KNO3 -> 9 CO + 3 N2 + 11 H2O + 3 K2CO3 ; (Rocket Candy)
		
		for (OreDictMaterial tMat : ANY.Coal.mToThis) if (tMat != MT.Graphene) {
		RM.Electrolyzer .addRecipe2(T, 16, 6144, OP.blockDust.mat(tMat, 1), OP.dust .mat(MT.Al2O3, 30), FL.array(MT.Na3AlF6.liquid(U24 , T), MT.AlF3.liquid(U12, T)), FL.array(MT.CO2.gas(U *27, F), MT.F.gas(7*U80 , F)), OM.dust(MT.Al, U *12));
		RM.Electrolyzer .addRecipe2(T, 16, 6144, OP.blockDust.mat(tMat, 1), OP.ingot.mat(MT.Al2O3, 30), FL.array(MT.Na3AlF6.liquid(U24 , T), MT.AlF3.liquid(U12, T)), FL.array(MT.CO2.gas(U *27, F), MT.F.gas(7*U80 , F)), OM.dust(MT.Al, U *12));
		RM.Electrolyzer .addRecipe2(T, 16, 2048, OP.dust     .mat(tMat, 3), OP.dust .mat(MT.Al2O3, 10), FL.array(MT.Na3AlF6.liquid(U72 , T), MT.AlF3.liquid(U36, T)), FL.array(MT.CO2.gas(U * 9, F), MT.F.gas(7*U240, F)), OM.dust(MT.Al, U * 4));
		RM.Electrolyzer .addRecipe2(T, 16, 2048, OP.dust     .mat(tMat, 3), OP.ingot.mat(MT.Al2O3, 10), FL.array(MT.Na3AlF6.liquid(U72 , T), MT.AlF3.liquid(U36, T)), FL.array(MT.CO2.gas(U * 9, F), MT.F.gas(7*U240, F)), OM.dust(MT.Al, U * 4));
		RM.Electrolyzer .addRecipe2(T, 16, 2048, OP.dustTiny .mat(tMat,27), OP.dust .mat(MT.Al2O3, 10), FL.array(MT.Na3AlF6.liquid(U72 , T), MT.AlF3.liquid(U36, T)), FL.array(MT.CO2.gas(U * 9, F), MT.F.gas(7*U240, F)), OM.dust(MT.Al, U * 4));
		RM.Electrolyzer .addRecipe2(T, 16, 2048, OP.dustTiny .mat(tMat,27), OP.ingot.mat(MT.Al2O3, 10), FL.array(MT.Na3AlF6.liquid(U72 , T), MT.AlF3.liquid(U36, T)), FL.array(MT.CO2.gas(U * 9, F), MT.F.gas(7*U240, F)), OM.dust(MT.Al, U * 4));
		RM.Electrolyzer .addRecipe2(T, 16, 1024, OP.dustSmall.mat(tMat, 6), OP.dust .mat(MT.Al2O3,  5), FL.array(MT.Na3AlF6.liquid(U144, T), MT.AlF3.liquid(U72, T)), FL.array(MT.CO2.gas(U2* 9, F), MT.F.gas(7*U480, F)), OM.dust(MT.Al, U * 2));
		RM.Electrolyzer .addRecipe2(T, 16, 1024, OP.dustSmall.mat(tMat, 6), OP.ingot.mat(MT.Al2O3,  5), FL.array(MT.Na3AlF6.liquid(U144, T), MT.AlF3.liquid(U72, T)), FL.array(MT.CO2.gas(U2* 9, F), MT.F.gas(7*U480, F)), OM.dust(MT.Al, U * 2));
		}
		RM.Electrolyzer .addRecipe2(T, 16,  512, ST.tag(0), OM.dust(MT.NaHSO4, U*7), NF, MT.H .gas(U  , T), OM.dust(MT.NaSO4, U*6));
		RM.Electrolyzer .addRecipe2(T, 16,  512, ST.tag(0), OM.dust(MT.KHSO4 , U*7), NF, MT.H .gas(U  , T), OM.dust(MT.KSO4 , U*6));
		RM.Electrolyzer .addRecipe2(T, 64,  512, ST.tag(0), OM.dust(MT.CaCl2 , U*3), NF, MT.Cl.gas(U*2, T), OM.dust(MT.Ca   , U));
		RM.Electrolyzer .addRecipe2(T, 64,  512, ST.tag(0), OM.dust(MT.MgCl2 , U*3), NF, MT.Cl.gas(U*2, T), OM.dust(MT.Mg   , U));
		RM.Electrolyzer .addRecipe1(T, 16,  128, ST.tag(0), MT.CaCl2.liquid(U*3, T), MT.Cl.gas(U*2, T), OM.dust(MT.Ca, U));
		RM.Electrolyzer .addRecipe1(T, 16,  128, ST.tag(0), MT.MgCl2.liquid(U*3, T), MT.Cl.gas(U*2, T), OM.dust(MT.Mg, U));
		
		RM.Electrolyzer .addRecipe1(T, 16,  256, ST.tag(1), FL.array(MT.LiClO3.liquid(U*5, T), MT.O.gas(U, T)), NF, OM.dust(MT.LiClO4, U*6));
		
		RM.Electrolyzer .addRecipe1(T, 16, 3840, ST.tag(0), FL.array(FL.Water.make(3000)         ), FL.array(MT.H.gas(U*2, F), MT.O.gas(U, F)));
		RM.Electrolyzer .addRecipe1(T, 16, 3840, ST.tag(0), FL.array(FL.SpDew.make(3000)         ), FL.array(MT.H.gas(U*2, F), MT.O.gas(U, F)));
		RM.Electrolyzer .addRecipe1(T, 16, 3840, ST.tag(0), FL.array(FL.DistW.make(3000)         ), FL.array(MT.H.gas(U*2, F), MT.O.gas(U, F)));
		RM.Electrolyzer .addRecipe1(T, 16, 3840, ST.tag(0), FL.array(MT.D2O.liquid(U*3, T)       ), FL.array(MT.D.gas(U*2, F), MT.O.gas(U, F)));
		RM.Electrolyzer .addRecipe1(T, 16, 3840, ST.tag(0), FL.array(MT.T2O.liquid(U*3, T)       ), FL.array(MT.T.gas(U*2, F), MT.O.gas(U, F)));
		if (FL.Tropics_Water.exists())
		RM.Electrolyzer .addRecipe1(T, 16,20480, ST.tag(0), FL.array(FL.Tropics_Water.make(16000)), FL.array(MT.Cl.gas(U4, F), MT.H.gas(83*U8, F), MT.O.gas(41*U8, F)), OM.dust(MT.NaOH, 3*U8));
		if (FL.OceanGrC.exists())
		RM.Electrolyzer .addRecipe1(T, 16,20480, ST.tag(0), FL.array(FL.OceanGrC.make(16000)     ), FL.array(MT.Cl.gas(U4, F), MT.H.gas(83*U8, F), MT.O.gas(41*U8, F)), OM.dust(MT.NaOH, 3*U8));
		RM.Electrolyzer .addRecipe1(T, 16,20480, ST.tag(0), FL.array(FL.Ocean.make(16000)        ), FL.array(MT.Cl.gas(U4, F), MT.H.gas(83*U8, F), MT.O.gas(41*U8, F)), OM.dust(MT.NaOH, 3*U8));
		RM.Electrolyzer .addRecipe1(T, 16, 1280, ST.tag(0), FL.array(MT.SaltWater.liquid(U, T)   ), FL.array(MT.Cl.gas(U8, F), MT.H.gas( 3*U8, F), MT.O.gas(   U8, F)), OM.dust(MT.NaOH, 3*U8));
		RM.Electrolyzer .addRecipe1(T, 16,  800, ST.tag(0), FL.array(MT.H2SO4.fluid(U*14, T)), FL.array(MT.H.fluid(U*2, F), MT.GT6U.H2S2O8.fluid( U*12, F)), NI);
		RM.Mixer        .addRecipe1(T, 16,  256, ST.tag(0), FL.array(MT.GT6U.H2S2O8.fluid(U*12, T), MT.H2O.fluid(U*6, T)), FL.array(MT.H2SO4.fluid(U*14, F), MT.H2O2.fluid( U*4, F)), NI);

        RM.Fermenter        .addRecipe1(T, 16,  24, ST.tag(0), FL.Biomass       .make( 40), FL.Methane.make(8), ZL_IS);
        RM.Fermenter        .addRecipe1(T, 16,  24, ST.tag(0), FL.BiomassIC2    .make( 40), FL.Methane.make(8), ZL_IS);


        RM.Distillery       .addRecipe1(T, 16,  24, ST.tag(0), FL.Biomass   .make(40), FL.Reikanol.make(12, FL.BioEthanol), FL.DistW.make(20));
		RM.Distillery       .addRecipe1(T, 16,  24, ST.tag(0), FL.Biomass       .make( 40), FL.Reikanol.make(12, FL.BioEthanol), FL.DistW.make(20));
		RM.Distillery       .addRecipe1(T, 16,  24, ST.tag(0), FL.BiomassIC2    .make( 40), FL.Reikanol.make(12, FL.BioEthanol), FL.DistW.make(20));
		RM.Distillery       .addRecipe1(T, 16,  64, ST.tag(0), FL.Oil_ExtraHeavy.make(100), FL.Fuel.make(70), FL.lube(100));
		RM.Distillery       .addRecipe1(T, 16,  64, ST.tag(0), FL.Oil_Heavy     .make(100), FL.Fuel.make(60), FL.lube( 80)); if (FL.Oil_Heavy2.exists())
		RM.Distillery       .addRecipe1(T, 16,  64, ST.tag(0), FL.Oil_Heavy2    .make(100), FL.Fuel.make(60), FL.lube( 80));
		RM.Distillery       .addRecipe1(T, 16,  64, ST.tag(0), FL.Oil_Medium    .make(100), FL.Fuel.make(50), FL.lube( 50));
		RM.Distillery       .addRecipe1(T, 16,  64, ST.tag(0), FL.Oil_Normal    .make(100), FL.Fuel.make(50), FL.lube( 50)); if (FL.Oil_HotCrude.exists())
		RM.Distillery       .addRecipe1(T, 16,  64, ST.tag(0), FL.Oil_HotCrude  .make(100), FL.Fuel.make(50), FL.lube( 50));
		RM.Distillery       .addRecipe1(T, 16,  64, ST.tag(0), FL.Oil_Light     .make(100), FL.Fuel.make(25), FL.lube( 25)); if (FL.Oil_Light2.exists())
		RM.Distillery       .addRecipe1(T, 16,  64, ST.tag(0), FL.Oil_Light2    .make(100), FL.Fuel.make(25), FL.lube( 25));
		RM.Distillery       .addRecipe1(T, 16,  64, ST.tag(0), FL.Oil_Soulsand  .make(100), FL.Fuel.make(20), FL.lube( 80));
		
		
		RM.Distillery       .addRecipe1(T, 16,  24, ST.tag(1), FL.Biomass       .make( 40), MT.Glycerol.liquid(U50, F), FL.DistW.make(20));
		RM.Distillery       .addRecipe1(T, 16,  24, ST.tag(1), FL.BiomassIC2    .make( 40), MT.Glycerol.liquid(U50, F), FL.DistW.make(20));
		
		
		RM.DistillationTower.addRecipe0(F, 64,  16                         , FL.array(FL.Biomass       .make( 80)), FL.array(FL.BioEthanol.make(20), MT.Glycerol.liquid(U50, F), FL.Methane.make(4), FL.DistW.make(50)), ZL_IS);
		RM.DistillationTower.addRecipe0(F, 64,  16                         , FL.array(FL.BiomassIC2    .make( 80)), FL.array(FL.BioEthanol.make(20), MT.Glycerol.liquid(U50, F), FL.Methane.make(4), FL.DistW.make(50)), ZL_IS);
		RM.DistillationTower.addRecipe0(F, 64, 512, new long[] {9000, 9000}, FL.array(FL.Oil_ExtraHeavy.make( 50)), FL.array(FL.SFuel.make(70), FL.SDiesel.make(45), FL.SKerosine.make(40), FL.SPetrol.make(35), FL.SNaphtha.make(25),FL.SGasoil.make(15),FL.Propane.make(10), FL.Butane.make(10), FL.lube(100)), dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1));
		RM.DistillationTower.addRecipe0(F, 64, 384, new long[] {7000, 7000}, FL.array(FL.Oil_Heavy     .make( 50)), FL.array(FL.SFuel.make(60), FL.SDiesel.make(35), FL.SKerosine.make(35), FL.SPetrol.make(30), FL.SNaphtha.make(20),FL.SGasoil.make(20),FL.Propane.make(15), FL.Butane.make(15), FL.lube( 80)), dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1)); if (FL.Oil_Heavy2.exists())
		RM.DistillationTower.addRecipe0(F, 64, 384, new long[] {7000, 7000}, FL.array(FL.Oil_Heavy2    .make( 50)), FL.array(FL.SFuel.make(60), FL.SDiesel.make(35), FL.SKerosine.make(35), FL.SPetrol.make(30), FL.SNaphtha.make(20),FL.SGasoil.make(20),FL.Propane.make(15), FL.Butane.make(15), FL.lube( 80)), dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1));
		RM.DistillationTower.addRecipe0(F, 64, 256, new long[] {5000, 5000}, FL.array(FL.Oil_Medium    .make( 50)), FL.array(FL.SFuel.make(50), FL.SDiesel.make(25), FL.SKerosine.make(25), FL.SPetrol.make(25), FL.SNaphtha.make(25),FL.SGasoil.make(20),FL.Propane.make(25), FL.Butane.make(25), FL.lube( 50)), dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1));
		RM.DistillationTower.addRecipe0(F, 64, 256, new long[] {5000, 5000}, FL.array(FL.Oil_Normal    .make( 50)), FL.array(FL.SFuel.make(50), FL.SDiesel.make(25), FL.SKerosine.make(25), FL.SPetrol.make(25), FL.SNaphtha.make(20),FL.SGasoil.make(25),FL.Propane.make(25), FL.Butane.make(25), FL.lube( 50)), dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1)); if (FL.Oil_HotCrude.exists())
		RM.DistillationTower.addRecipe0(F, 64, 128, new long[] {5000, 5000}, FL.array(FL.Oil_HotCrude  .make( 50)), FL.array(FL.SFuel.make(50), FL.SDiesel.make(25), FL.SKerosine.make(25), FL.SPetrol.make(25), FL.SNaphtha.make(15),FL.SGasoil.make(20),FL.Propane.make(25), FL.Butane.make(25), FL.lube( 50)), dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1));
		RM.DistillationTower.addRecipe0(F, 64, 128, new long[] {3000, 3000}, FL.array(FL.Oil_Light     .make( 50)), FL.array(FL.SFuel.make(25), FL.SDiesel.make(15), FL.SKerosine.make(15), FL.SPetrol.make(15), FL.SNaphtha.make(20),FL.SGasoil.make(30),FL.Propane.make(50), FL.Butane.make(50), FL.lube( 25)), dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1)); if (FL.Oil_Light2.exists())
		RM.DistillationTower.addRecipe0(F, 64, 128, new long[] {3000, 3000}, FL.array(FL.Oil_Light2    .make( 50)), FL.array(FL.SFuel.make(25), FL.SDiesel.make(15), FL.SKerosine.make(15), FL.SPetrol.make(15), FL.SNaphtha.make(20),FL.SGasoil.make(30),FL.Propane.make(50), FL.Butane.make(50), FL.lube( 25)), dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1));
		RM.DistillationTower.addRecipe0(F, 64, 128, new long[] {2000, 2000}, FL.array(FL.Oil_Soulsand  .make( 50)), FL.array(FL.SFuel.make(20), FL.SDiesel.make(10), FL.SKerosine.make(10), FL.SPetrol.make(10), FL.SNaphtha.make(10),FL.SGasoil.make(25),FL.Propane.make(10), FL.Butane.make(10), FL.lube( 80)), dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1));

		RM.Mixer            .addRecipe0(F, 16, 16, FL.array(FL.SFuel.make(16),     FL.Hydrogen.make(2)), FL.array(FL.Fuel.make(12),       MT.H2S.gas(U1000, T)), ZL_IS);
		RM.Mixer            .addRecipe0(F, 16, 16, FL.array(FL.SDiesel.make(16),   FL.Hydrogen.make(2)), FL.array(FL.Diesel.make(12),     MT.H2S.gas(U1000, T)), ZL_IS);
		RM.Mixer            .addRecipe0(F, 16, 16, FL.array(FL.SPetrol.make(16),   FL.Hydrogen.make(2)), FL.array(FL.Petrol.make(12),     MT.H2S.gas(U1000, T)), ZL_IS);
		RM.Mixer            .addRecipe0(F, 16, 16, FL.array(FL.SKerosine.make(16), FL.Hydrogen.make(2)), FL.array(FL.Kerosine.make(12),   MT.H2S.gas(U1000, T)), ZL_IS);
		RM.Mixer            .addRecipe0(F, 16, 16, FL.array(FL.SNaphtha.make(16),  FL.Hydrogen.make(2)), FL.array(FL.Naphtha.make(12),    MT.H2S.gas(U1000, T)), ZL_IS);
		RM.Mixer            .addRecipe0(F, 16, 16, FL.array(FL.SGasoil.make(16),   FL.Hydrogen.make(2)), FL.array(FL.Gasoil.make(12),     MT.H2S.gas(U1000, T)), ZL_IS);

		RM.OilCleaner       .addRecipe0(F, 16, 8, FL.array(FL.SFuel.make(16),     FL.Hydrogen.make(1)), FL.array(FL.Fuel.make(16),        MT.H2S.gas(U1000, T)), ZL_IS);
		RM.OilCleaner       .addRecipe0(F, 16, 8, FL.array(FL.SDiesel.make(16),   FL.Hydrogen.make(1)), FL.array(FL.Diesel.make(16),      MT.H2S.gas(U1000, T)), ZL_IS);
		RM.OilCleaner       .addRecipe0(F, 16, 8, FL.array(FL.SPetrol.make(16),   FL.Hydrogen.make(1)), FL.array(FL.Petrol.make(16),      MT.H2S.gas(U1000, T)), ZL_IS);
		RM.OilCleaner       .addRecipe0(F, 16, 8, FL.array(FL.SKerosine.make(16), FL.Hydrogen.make(1)), FL.array(FL.Kerosine.make(16),    MT.H2S.gas(U1000, T)), ZL_IS);
		RM.OilCleaner       .addRecipe0(F, 16, 8, FL.array(FL.SNaphtha.make(16),  FL.Hydrogen.make(1)), FL.array(FL.Naphtha.make(16),     MT.H2S.gas(U1000, T)), ZL_IS);
		RM.OilCleaner       .addRecipe0(F, 16, 8, FL.array(FL.SGasoil.make(16),   FL.Hydrogen.make(1)), FL.array(FL.Gasoil.make(16),      MT.H2S.gas(U1000, T)), ZL_IS);

		
		RM.CryoDistillationTower.addRecipe0(T, 64,  64, new long[] {9000}, FL.array(FL.Air       .make(200)), FL.array(MT.N.gas(U7, T), MT.O.gas(U20, T), MT.CO2.gas(U100, T), MT.He.gas(U1000, T), MT.Ne.gas(U1000, T), MT.Ar.gas(U1000, T)), OP.dustTiny.mat(MT.Ice, 1));
		RM.CryoDistillationTower.addRecipe0(T, 64,  64, new long[] {7000}, FL.array(FL.Air_Nether.make(200)), FL.array(MT.N.gas(U7, T), MT.O.gas(U20, T), MT.SO2.gas(U100, T), MT.He.gas(U1000, T), MT.Ne.gas(U1000, T), MT.Ar.gas(U1000, T)), OP.dustTiny.mat(MT.Ash, 1));
		RM.CryoDistillationTower.addRecipe0(T, 64,  64, new long[] {6000}, FL.array(FL.Air_End   .make(200)), FL.array(MT.N.gas(U7, T), MT.O.gas(U20, T), MT.CO2.gas(U100, T), MT.Kr.gas(U1000, T), MT.Xe.gas(U1000, T), MT.Rn.gas(U1000, T)), OP.dustTiny.mat(MT.Ice, 1));
		
		
		RM.SteamCracking    .addRecipe0(F, 16, 384, FL.array(FL.Steam.make(1000), FL.Propane.make(100)), FL.array(FL.Hydrogen.make( 2), FL.Methane.make(27), FL.Ethylene.make(42), FL.Propylene.make(19)), ZL_IS);
		RM.SteamCracking    .addRecipe0(F, 16, 384, FL.array(FL.Steam.make(1000), FL.Butane .make(100)), FL.array(FL.Hydrogen.make( 5), FL.Methane.make( 9), FL.Ethylene.make(78), FL.Propylene.make( 3)), ZL_IS);


		RM.CatalyticCracking.addRecipe1(F, 16, 384, OP.dust.mat(MT.Pt, 0), FL.array(FL.Hydrogen.make(100), FL.Petrol.make(100)), FL.array(FL.Ethylene.make(30), FL.Propylene.make(20)), ZL_IS);
		RM.CatalyticCracking.addRecipe1(F, 16, 384, OP.dust.mat(MT.Pt, 0), FL.array(FL.Hydrogen.make(100), FL.Fuel  .make(100)), FL.array(FL.Ethylene.make(40), FL.Propylene.make(10)), ZL_IS);

		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Steam.make(1000),    FL.Butane  .make(1000)), FL.array(FL.Methane.make(550),  FL.Ethylene.make(800), FL.Propylene.make(400)), dustTiny.mat(MT.C, 1));
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Hydrogen.make(1000), FL.Butane  .make(1000)), FL.array(FL.Methane.make(2000), FL.Propane.make(1000)), ZL_IS);
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Steam.make(1000),    FL.Propane .make(1000)), FL.array(FL.Methane.make(1000),  FL.Propylene.make(500)), dustTiny.mat(MT.C, 1));
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Hydrogen.make(1000), FL.Propane .make(1000)), FL.array(FL.Methane.make(1500)), ZL_IS);
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Steam.make(1000),    FL.Petrol  .make(1000)), FL.array(FL.Naphtha.make(100),  FL.benzene.make(300), FL.Methane.make(450), FL.Ethylene.make(450), FL.Propylene.make(450), FL.Fuel.make(100)), dustTiny.mat(MT.C, 1));
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Hydrogen.make(1000), FL.Petrol  .make(1000)), FL.array(FL.Methane.make(500),  FL.Butane.make(400),  FL.Naphtha.make(800), FL.Propane.make(400)), ZL_IS);
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Steam.make(1000),    FL.Naphtha .make(1000)), FL.array(FL.Fuel.make(100),     FL.Petrol.make(100),  FL.benzene.make(400), FL.Methane.make(600), FL.Ethylene.make(600), FL.Propylene.make(600)), dustTiny.mat(MT.C, 1));
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Hydrogen.make(1000), FL.Naphtha .make(1000)), FL.array(FL.Methane.make(200),  FL.Butane.make(400),  FL.Naphtha.make(800), FL.Propane.make(400)), ZL_IS);
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Steam.make(1000),    FL.Fuel    .make(1000)), FL.array(FL.Petrol.make(100),   FL.benzene.make(200), FL.Methane.make(500), FL.Ethylene.make(300), FL.Propylene.make(400)), dustTiny.mat(MT.C, 1));
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Hydrogen.make(1000), FL.Fuel    .make(1000)), FL.array(FL.Petrol.make(800),   FL.Butane.make(100),  FL.Naphtha.make(400), FL.Propane.make(100), FL.Methane.make(150)), ZL_IS);
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Steam.make(1000),    FL.Gasoil  .make(1000)), FL.array(FL.Methane.make(800), FL.Ethylene.make(200), FL.Propylene.make(150)), dustTiny.mat(MT.C, 1));
		RM.CrackingTower    .addRecipe0(F,128, 108, FL.array(FL.Hydrogen.make(1000), FL.Gasoil  .make(1000)), FL.array(FL.Methane.make(2000)), ZL_IS);

		RM.Centrifuge       .addRecipe0(F, 64,  48, FL.array(FL.Gas_Natural.make(320)), FL.array(FL.LPG  .make(290)), ZL_IS);

		//Polymer production
		RM.Mixer            .addRecipe1(T, 16,  16, OP.dust.mat(MT.MgCl2, 0), FL.array(MT.TiCl4.liquid(U1000, T), FL.Ethylene .make(144)), ZL_FS, OP.dust.mat(MT.Plastic, 1)); // TODO Fluidized Bed Reactor
		RM.Mixer            .addRecipe1(T, 16,  16, OP.dust.mat(MT.MgCl2, 0), FL.array(MT.TiCl4.liquid(U1000, T), FL.Propylene.make(144)), ZL_FS, OP.dust.mat(MT.Plastic, 1)); // TODO Fluidized Bed Reactor

        //Rubber
		RM.Mixer            .addRecipe1(T, 16, 144, ST.tag(0), FL.array(FL.Propylene.make(2000)), FL.array(FL.Isoprene.make(1000), FL.Methane.make(1000)), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16, 144, ST.tag(1), FL.array(FL.Propylene.make(1000), FL.Ethylene .make(1000)), FL.array(FL.Isoprene.make(1000), FL.Methane.make(1000)), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16, 144, ST.tag(2), FL.array(FL.Propylene.make(1000), FL.Ethylene .make(1000)), FL.array(FL.Isoprene.make(1000), MT.H      .gas   (2*U , F)), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16, 108, ST.tag(2), FL.array(FL.Isoprene.make(288),   MT.O.gas (2*U, F)), ZL_FS, OP.dust.mat(MT.Rubber, 3));

		//GT6U.PVC and PAC
        RM.HeatMixer        .addRecipe1(T, 64, 144, ST.tag(0), FL.array(FL.Ethylene.make(1000), MT.Cl.gas(U*1, T)), FL.array(FL.Vinyl_Chloride.make(1000), MT.HCl.liquid(U*1, F)), ZL_IS);
        RM.HeatMixer        .addRecipe1(T, 64, 144, OP.dust.mat(MT.MgCl2, 0), FL.array(FL.Vinyl_Chloride.make(500), MT.TiCl4.liquid(U1000*5, T)), ZL_FS, OP.dust.mat(MT.GT6U.PVC, 1));

        //GT6U.PTFE
        RM.Roasting         .addRecipe1(T, 256, 288, OP.dust.mat(MT.K2SO4, 1), FL.array(MT.SO3.gas(U*1, T)), ZL_FS, OP.dust.mat(MT.K2S2O7, 1));
        RM.HeatMixer        .addRecipe1(T, 256, 144, ST.tag(0), FL.array(FL.Methane.make(1000), MT.Cl.gas(U*3, T)), FL.array(FL.Trichloromethane.make(1000), MT.HCl.liquid(U*3, F)), ZL_IS);
        RM.HeatMixer        .addRecipe1(T, 256, 144, ST.tag(0), FL.array(FL.Trichloromethane.make(1000), MT.HF.gas(U*2, T)), FL.array(FL.Monochlorodifluoromethane.make(1000), MT.HCl.liquid(U*2, F)), ZL_IS);
        RM.HeatMixer        .addRecipe1(T, 256, 288, OP.dust.mat(MT.Pt, 0), FL.array(FL.Monochlorodifluoromethane.make(2000)), FL.array(FL.Tetrafluoroethylene.make(1000), MT.HCl.liquid(U*2, F)), ZL_IS);
        RM.HeatMixer        .addRecipe1(T, 256, 288, OP.dust.mat(MT.K2S2O7, 0), FL.array(FL.Tetrafluoroethylene.make(144), FL.DistW.make(144)), ZL_FS, OP.dust.mat(MT.GT6U.PTFE, 1));

        //Epoxid
		RM.HeatMixer        .addRecipeX(T, 16,  500,  ST.array(OP.dust.mat(MT.Al2O3, 5), OP.dust.mat(MT.C, 3)), FL.array(MT.Cl.fluid(U*6, T)), MT.CO.fluid(U*6, T), OP.dust.mat(MT.GT6U.AlCl3, 8));
		RM.HeatMixer        .addRecipe1(T, 16,  500,  OP.dust.mat(MT.PO4, 1), FL.array(MT.HCl.fluid(U*3, T)), FL.array(MT.GT6U.H3PO4.liquid(U*1, F), MT.Cl.gas(U*1, F)), ZL_IS);
        RM.HeatMixer        .addRecipe1(T, 128, 400,  OP.dust.mat(MT.MgCl2, 0), FL.array(MT.Glycerol.liquid(U*1, T), MT.HCl.fluid(U*3, T)), FL.array(FL.Dichloroisopropanol.make(1000), MT.Cl.gas(U*1, T)), ZL_IS);

        RM.HeatMixer        .addRecipe1(T, 128, 400,  OP.dust.mat(MT.KOH, 0),   FL.array(FL.Dichloroisopropanol.make(1000)), FL.array(FL.epichlorohydrin.make(500)), ZL_IS);
        RM.HeatMixer        .addRecipe1(T, 128, 400,  OP.dust.mat(MT.GT6U.AlCl3, 0), FL.array(FL.benzene.make(1000), FL.Propylene.make(1000)), FL.array(FL.Cumene.make(1000)), ZL_IS);
        RM.HeatMixer        .addRecipe0(T, 128, 400,  FL.array(FL.Cumene.make(1000), MT.H2SO4.liquid(U*1, T), MT.O.gas(U*1, T)), FL.array(FL.phenol.make(500), FL.acetone.make(500)), ZL_IS);
        RM.HeatMixer        .addRecipe0(T, 128, 400,  FL.array(FL.phenol.make(1000), FL.acetone.make(1000)), FL.array(FL.bisphenol.make(2000)), ZL_IS);
        RM.HeatMixer        .addRecipe1(T, 128, 400,  OP.dust.mat(MT.NaOH, 4), FL.array(FL.epichlorohydrin.make(1000), FL.bisphenol.make(1000), MT.GT6U.H3PO4.liquid(U100*5, T)), ZL_FS, OP.dust.mat(MT.GT6U.Epoxid, 3));

        //Photoresist
        RM.HeatMixer        .addRecipe0(T, 128, 200, FL.array(FL.Propylene.make(1000), MT.Cl.gas(U*1, T)), FL.array(FL.Allyl_Chloride.make(1000), MT.HCl.liquid(U*1, F)), ZL_IS);
        RM.HeatMixer        .addRecipeX(T, 128, 100, ST.array(ST.tag(0), OP.dust.mat(MT.Ag , 0), OP.dust.mat(MT.NaOH, 1)), FL.array(FL.Allyl_Chloride.make(1000), MT.H2O.liquid(U*1, T), MT.O.gas(U*1, T)), FL.array(FL.Acrylic_Acid.make(1000)), OP.dust.mat(MT.NaCl, 1));
        RM.HeatMixer        .addRecipe0(T, 128, 400, FL.array(FL.Acrylic_Acid.make(1000), FL.BioEthanol.make(1000), MT.H2SO4.liquid(U*1, T)), FL.array(FL.Ethyl_Acrylate.make(1000), MT.H2SO4.liquid(U10*8, F)), ZL_IS);
        RM.HeatMixer        .addRecipe1(T, 128, 200, OP.dust.mat(MT.Pt, 0), FL.array(FL.Ethylene.make(1000), FL.benzene.make(1000)), FL.array(FL.Styrene.make(1000)), ZL_IS);
        RM.HeatMixer        .addRecipe1(T, 128, 400, OP.dust.mat(MT.V2O5, 0), FL.array(FL.Ethyl_Acrylate.make(1000), FL.Styrene.make(1000), MT.TiCl4.liquid(U100, T)), FL.array(FL.Photoresist.make(2000)), ZL_IS);

        //Chromium production
        RM.HeatMixer        .addRecipe2(T, 16, 288, OM.dust(MT.OREMATS.Chromite, U*28), OP.dust.mat(MT.Na2CO3, 48), FL.array(MT.O.gas(U*14, T)), FL.array(MT.CO2.gas(U*24, F)), OP.dust.mat(MT.GT6U.Na2CrO4, 56), OM.dust(MT.OREMATS.Hematite, U*10));
        RM.HeatMixer        .addRecipe1(T, 16, 288, OP.dust.mat(MT.GT6U.Na2CrO4, 14), FL.array(MT.H2SO4.liquid(U*7, T)), FL.array(MT.H2O.liquid(U*3, F)), OP.dust.mat(MT.GT6U.Na2Cr2O7, 11), OP.dust.mat(MT.Na2SO4, 7));
        RM.HeatMixer        .addRecipe2(T, 16, 288, OP.dust.mat(MT.GT6U.Na2Cr2O7, 11), OP.dust.mat(MT.C, 2), ZL_FS, FL.array(MT.CO.gas(U*2, F)), OP.dust.mat(MT.GT6U.Cr2O3, 5), OP.dust.mat(MT.Na2CO3, 6));
        RM.BurnMixer        .addRecipe2(T, 16, 144, OP.dust.mat(MT.GT6U.Cr2O3, 5), OP.dust.mat(MT.Al, 2), ZL_FS, FL.array(MT.Cr.liquid(U*2, F)), OP.dust.mat(MT.Al2O3, 5));

        //F6LiP
		RM.HeatMixer        .addRecipe1(T, 64, 400, OP.dust.mat(MT.CaF2, 15), FL.array(MT.GT6U.H3PO4.fluid(U*16, F)), FL.array(MT.GT6U.PF5.fluid(U*12, F), MT.H2O.fluid(U*9, F)), OP.dust.mat(MT.GT6U.CaO, 10));
		RM.HeatMixer        .addRecipe1(T, 64, 300, OP.dust.mat(MT.PO4, 20), FL.array(MT.HF.fluid(U*40, F)), FL.array(MT.GT6U.PF5.fluid(U*24, F), MT.H2O.fluid(U*30, F), MT.O.fluid(U*6, F)), NI);
		RM.Bath             .addRecipe1(T, 0, 200, OP.dust.mat(MT.LiOH, 3), FL.array(MT.HF.fluid(U*2, F)), FL.array(MT.H2O.fluid(U*3, F)), OP.dust.mat(MT.GT6U.LiF, 2));
		RM.Drying           .addRecipe1(T, 16, 4000, OP.dust.mat(MT.GT6U.LiF, 2), FL.array(MT.GT6U.PF5.fluid(U*6, F)), ZL_FS, OP.dust.mat(MT.GT6U.LiPF6, 8));

		//Ethyl Methyl Carbonate
		RM.Lightning        .addRecipe0(T, 256, 300, FL.array(MT.CO.fluid(U*2, T), MT.Cl.fluid(U*2, T)), FL.array(MT.GT6U.COCl2.gas(U*4, F)), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,  400, FL.array(MT.GT6U.COCl2.gas(U*4, T), FL.BioEthanol.make(2000)), FL.array(MT.GT6U.C5H10O3.liquid(U*18, F), MT.HCl.fluid(U*4, F)), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,  400, FL.array(MT.GT6U.C5H10O3.liquid(U*72, T), FL.Methanol.make(6000)), FL.array(FL.MixedEster.make(78000)), ZL_IS);
		RM.DistillationTower.addRecipe0(T, 64, 1000, FL.array(FL.MixedEster.make(78000)), FL.array(MT.GT6U.C3H6O3.liquid(U*12, F), MT.GT6U.C5H10O3.liquid(U*18, F), MT.GT6U.C4H8O3.liquid(U*30, F), FL.Methanol.make(2000), FL.BioEthanol.make(4000)), ZL_IS);

		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.Pyrite                  ), MT. O .gas( 4*U3 , T), MT.SO2.gas( 6*U3 , F), OM.dust(MT.Fe,   U3 ));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Sphalerite      ), MT. O .gas( 2*U2 , T), MT.SO2.gas( 3*U2 , F), OM.dust(MT.Zn,   U2 ));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Molybdenite     ), MT. O .gas( 4*U3 , T), MT.SO2.gas( 6*U3 , F), OM.dust(MT.Mo,   U3 ));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Stibnite        ), MT. O .gas( 6*U5 , T), MT.SO2.gas( 9*U5 , F), OM.dust(MT.Sb, 2*U5 ));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Pentlandite     ), MT. O .gas(16*U17, T), MT.SO2.gas(24*U17, F), OM.dust(MT.Ni, 9*U17));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Chalcopyrite    ), MT. O .gas( 4*U4 , T), MT.SO2.gas( 6*U4 , F), OM.dust(MT.Fe,   U4 ), OM.dust(MT.Cu,   U4));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Arsenopyrite    ), MT. O .gas( 2*U3 , T), MT.SO2.gas( 3*U3 , F), OM.dust(MT.Fe,   U3 ), OM.dust(MT.As,   U3));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Cobaltite       ), MT. O .gas( 2*U3 , T), MT.SO2.gas( 3*U3 , F), OM.dust(MT.Co,   U3 ), OM.dust(MT.As,   U3));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Galena          ), MT. O .gas( 4*U8 , T), MT.SO2.gas( 6*U8 , F), OM.dust(MT.Ag, 3*U8 ), OM.dust(MT.Pb, 3*U8));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Cooperite       ), MT. O .gas( 2*U6 , T), MT.SO2.gas( 3*U6 , F), OM.dust(MT.Pt, 3*U6 ), OM.dust(MT.Ni, 1*U6), OM.dust(MT.Pd, 1*U6));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Tetrahedrite    ), MT. O .gas( 6*U8 , T), MT.SO2.gas( 9*U8 , F), OM.dust(MT.Cu, 3*U8 ), OM.dust(MT.Sb, 1*U8), OM.dust(MT.Fe, 1*U8));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Stannite        ), MT. O .gas( 8*U8 , T), MT.SO2.gas(12*U8 , F), OM.dust(MT.Cu, 2*U8 ), OM.dust(MT.Fe, 1*U8), OM.dust(MT.Sn, 1*U8));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Kesterite       ), MT. O .gas( 8*U8 , T), MT.SO2.gas(12*U8 , F), OM.dust(MT.Cu, 2*U8 ), OM.dust(MT.Zn, 1*U8), OM.dust(MT.Sn, 1*U8));

		RM.Roasting     .addRecipe1(T, 16,  128, OM.dust(MT.Li                      ), MT. O .gas(   U2 , T), NF                   , OM.dust(MT.Li2O, 3*U2));
		RM.Roasting     .addRecipe1(T, 16,  128, OM.dust(MT.V                       ), MT. O .gas( 5*U2 , T), NF                   , OM.dust(MT.V2O5, 7*U2));
		RM.Roasting     .addRecipe1(T, 16,  128, OM.dust(MT.Cr                      ), MT. O .gas( 2*U  , T), NF                   , OM.dust(MT.CrO2,   U ));
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.S                       ), MT. O .gas( 2*U  , T), MT.SO2.gas( 3*U  , F), ZL_IS);

		for (OreDictMaterial tMat : ANY.C.mToThis) if (tMat != MT.Graphene)
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(tMat                       ), MT. O .gas( 2*U  , T), MT.CO2.gas( 3*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.Charcoal                ), MT. O .gas( 2*U  , T), MT.CO2.gas( 3*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.Coal                    ), MT. O .gas( 4*U  , T), MT.CO2.gas( 6*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.CoalCoke                ), MT. O .gas( 4*U  , T), MT.CO2.gas( 6*U  , F), ZL_IS);
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(tMat                       ), MT. O .gas( 8*U  , T), MT.CO2.gas(12*U  , F), ZL_IS);
		
		RM.Injector         .addRecipe1(T, 64, 256, OM.dust(MT.Th), MT.LiCl.liquid(U*64, T), FL.Thorium_Salt.make(9216), ZL_IS);


		for (OreDictMaterial tMat : ANY.C.mToThis) if (tMat != MT.Graphene)
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(tMat                       ), MT.CO2.gas( 3*U  , T), MT.CO .gas( 4*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.Charcoal                ), MT.CO2.gas( 3*U  , T), MT.CO .gas( 4*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.Coal                    ), MT.CO2.gas( 6*U  , T), MT.CO .gas( 8*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.CoalCoke                ), MT.CO2.gas( 6*U  , T), MT.CO .gas( 8*U  , F), ZL_IS);
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(tMat                       ), MT.CO2.gas(12*U  , T), MT.CO .gas(16*U  , F), ZL_IS);
		
		

		for (String tOxygen : FluidsGT.OXYGEN) if (FL.exists(tOxygen)) {
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.Pyrite                  ), FL.make(tOxygen,  1834), MT.SO2.gas( 6*U3 , F), OM.dust(MT.Fe2O3, 5*U6 ));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Realgar         ), FL.make(tOxygen,  1000), MT.SO2.gas( 3*U2 , F), OM.dust(MT.As   ,   U2 ));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Sphalerite      ), FL.make(tOxygen,  1000), MT.SO2.gas( 3*U2 , F), OM.dust(MT.Zn   ,   U2 ));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Molybdenite     ), FL.make(tOxygen,  1334), MT.SO2.gas( 6*U3 , F), OM.dust(MT.Mo   ,   U3 ));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Stibnite        ), FL.make(tOxygen,  1200), MT.SO2.gas( 9*U5 , F), OM.dust(MT.Sb   , 2*U5 ));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Pentlandite     ), FL.make(tOxygen,   942), MT.SO2.gas(24*U17, F), OM.dust(MT.Ni   , 9*U17));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Chalcopyrite    ), FL.make(tOxygen,  1375), MT.SO2.gas( 6*U4 , F), OM.dust(MT.Fe2O3, 5*U8 ), OM.dust(MT.Cu,   U4));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Arsenopyrite    ), FL.make(tOxygen,  1167), MT.SO2.gas( 3*U3 , F), OM.dust(MT.Fe2O3, 5*U6 ), OM.dust(MT.As,   U3));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Cobaltite       ), FL.make(tOxygen,   667), MT.SO2.gas( 3*U3 , F), OM.dust(MT.Co   ,   U3 ), OM.dust(MT.As,   U3));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Galena          ), FL.make(tOxygen,   500), MT.SO2.gas( 6*U8 , F), OM.dust(MT.Ag   , 3*U8 ), OM.dust(MT.Pb, 3*U8));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Cooperite       ), FL.make(tOxygen,   334), MT.SO2.gas( 3*U6 , F), OM.dust(MT.Pt   , 3*U6 ), OM.dust(MT.Ni, 1*U6), OM.dust(MT.Pd   , 1*U6));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Tetrahedrite    ), FL.make(tOxygen,   938), MT.SO2.gas( 9*U8 , F), OM.dust(MT.Cu   , 3*U8 ), OM.dust(MT.Sb, 1*U8), OM.dust(MT.Fe2O3, 5*U16));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Stannite        ), FL.make(tOxygen,  1188), MT.SO2.gas(12*U8 , F), OM.dust(MT.Cu   , 2*U8 ), OM.dust(MT.Sn, 1*U8), OM.dust(MT.Fe2O3, 5*U16));
		RM.Roasting     .addRecipe1(T, 16,  512, OM.dust(MT.OREMATS.Kesterite       ), FL.make(tOxygen,  1000), MT.SO2.gas(12*U8 , F), OM.dust(MT.Cu   , 2*U8 ), OM.dust(MT.Zn, 1*U8), OM.dust(MT.Sn   , 1*U8));
		
		RM.Roasting     .addRecipe1(T, 16,  128, OM.dust(MT.Li                      ), FL.make(tOxygen,   500), NF                   , OM.dust(MT.Li2O, 3*U2));
		RM.Roasting     .addRecipe1(T, 16,  128, OM.dust(MT.V                       ), FL.make(tOxygen,  2500), NF                   , OM.dust(MT.V2O5, 7*U2));
		RM.Roasting     .addRecipe1(T, 16,  128, OM.dust(MT.Cr                      ), FL.make(tOxygen,  2000), NF                   , OM.dust(MT.CrO2,   U ));
		RM.Roasting     .addRecipe1(T, 16,   16, OP.dust.mat(MT.S                , 1), FL.make(tOxygen,  2000), MT.SO2.gas( 3*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,  144, OP.blockDust.mat(MT.S           , 1), FL.make(tOxygen,  2000), MT.SO2.gas( 3*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OP.dustTiny.mat(MT.Blaze        , 1), FL.make(tOxygen, 18000), MT.SO2.gas(27*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,  144, OP.dust.mat(MT.Blaze            , 1), FL.make(tOxygen, 18000), MT.SO2.gas(27*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16, 1296, OP.blockDust.mat(MT.Blaze       , 1), FL.make(tOxygen,162000), MT.SO2.gas(243*U , F), ZL_IS);
		
		for (OreDictMaterial tMat : ANY.C.mToThis) if (tMat != MT.Graphene)
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(tMat                       ), FL.make(tOxygen,  2000), MT.CO2.gas( 3*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.Charcoal                ), FL.make(tOxygen,  2000), MT.CO2.gas( 3*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.Coal                    ), FL.make(tOxygen,  4000), MT.CO2.gas( 6*U  , F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(MT.CoalCoke                ), FL.make(tOxygen,  4000), MT.CO2.gas( 6*U  , F), ZL_IS);
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Roasting     .addRecipe1(T, 16,   16, OM.dust(tMat                       ), FL.make(tOxygen,  8000), MT.CO2.gas(12*U  , F), ZL_IS);
		}
		
		final long[] tChances = new long[] {8000, 8000, 8000};
		
		for (String tAir : FluidsGT.AIR) if (FL.exists(tAir)) {
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.Pyrite              ), FL.make(tAir, 8000), MT.SO2.gas( 6*U3 , F), OM.dust(MT.Fe2O3, 5*U6 ));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Realgar     ), FL.make(tAir, 4000), MT.SO2.gas( 3*U2 , F), OM.dust(MT.As   ,   U2 ));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Sphalerite  ), FL.make(tAir, 4000), MT.SO2.gas( 3*U2 , F), OM.dust(MT.Zn   ,   U2 ));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Molybdenite ), FL.make(tAir, 6000), MT.SO2.gas( 6*U3 , F), OM.dust(MT.Mo   ,   U3 ));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Stibnite    ), FL.make(tAir, 5000), MT.SO2.gas( 9*U5 , F), OM.dust(MT.Sb   , 2*U5 ));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Pentlandite ), FL.make(tAir, 4000), MT.SO2.gas(24*U17, F), OM.dust(MT.Ni   , 9*U17));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Chalcopyrite), FL.make(tAir, 6000), MT.SO2.gas( 6*U4 , F), OM.dust(MT.Fe2O3, 5*U8 ), OM.dust(MT.Cu,   U4));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Arsenopyrite), FL.make(tAir, 5000), MT.SO2.gas( 3*U3 , F), OM.dust(MT.Fe2O3, 5*U6 ), OM.dust(MT.As,   U3));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Cobaltite   ), FL.make(tAir, 3000), MT.SO2.gas( 3*U3 , F), OM.dust(MT.Co   ,   U3 ), OM.dust(MT.As,   U3));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Galena      ), FL.make(tAir, 2000), MT.SO2.gas( 6*U8 , F), OM.dust(MT.Ag   , 3*U8 ), OM.dust(MT.Pb, 3*U8));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Cooperite   ), FL.make(tAir, 2000), MT.SO2.gas( 3*U6 , F), OM.dust(MT.Pt   , 3*U6 ), OM.dust(MT.Ni, 1*U6), OM.dust(MT.Pd   , 1*U6));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Tetrahedrite), FL.make(tAir, 4000), MT.SO2.gas( 9*U8 , F), OM.dust(MT.Cu   , 3*U8 ), OM.dust(MT.Sb, 1*U8), OM.dust(MT.Fe2O3, 5*U16));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Stannite    ), FL.make(tAir, 5000), MT.SO2.gas(12*U8 , F), OM.dust(MT.Cu   , 2*U8 ), OM.dust(MT.Sn, 1*U8), OM.dust(MT.Fe2O3, 5*U16));
		RM.Roasting     .addRecipe1(T, 16,  512, tChances, OM.dust(MT.OREMATS.Kesterite   ), FL.make(tAir, 4000), MT.SO2.gas(12*U8 , F), OM.dust(MT.Cu   , 2*U8 ), OM.dust(MT.Zn, 1*U8), OM.dust(MT.Sn   , 1*U8));
		RM.Roasting     .addRecipe1(T, 16,   16, OP.dust.mat(MT.S         , 1), FL.make(tAir,  8000), MT.SO2.gas(  3*U, F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,  144, OP.blockDust.mat(MT.S    , 1), FL.make(tAir,  8000), MT.SO2.gas(  3*U, F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,   16, OP.dustTiny.mat(MT.Blaze , 1), FL.make(tAir, 92000), MT.SO2.gas( 27*U, F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16,  144, OP.dust.mat(MT.Blaze     , 1), FL.make(tAir, 92000), MT.SO2.gas( 27*U, F), ZL_IS);
		RM.Roasting     .addRecipe1(T, 16, 1296, OP.blockDust.mat(MT.Blaze, 1), FL.make(tAir,648000), MT.SO2.gas(243*U, F), ZL_IS);
		}
		
		if (FL.Heavy_Reiker.exists())
		RM.Centrifuge   .addRecipe0(T, 64,   64, FL.array(FL.Heavy_Reiker.make(10000)), FL.array(MT.HDO.liquid(U10, F), MT.D2O.liquid(U100, F), MT.T2O.liquid(U1000, F)), ZL_IS);
		RM.Centrifuge   .addRecipe0(T, 64,   64, FL.array(FL.DistW      .make(100000)), FL.array(MT.HDO.liquid(U10, F), MT.D2O.liquid(U100, F), MT.T2O.liquid(U1000, F)), ZL_IS);
		RM.Centrifuge   .addRecipe0(T, 64,   64, FL.array(FL.SpDew      .make(100000)), FL.array(MT.HDO.liquid(U10, F), MT.D2O.liquid(U100, F), MT.T2O.liquid(U1000, F)), ZL_IS);
		RM.Centrifuge   .addRecipe0(T, 64,   64, FL.array(FL.Water      .make(100000)), FL.array(MT.HDO.liquid(U10, F), MT.D2O.liquid(U100, F), MT.T2O.liquid(U1000, F)), ZL_IS);
		RM.Centrifuge   .addRecipe0(T, 64,   64, FL.array(MT.HDO        .liquid(U, T)), FL.array(MT.D2O.liquid(U10, F), MT.T2O.liquid(U100, F)), ZL_IS);
		RM.Centrifuge   .addRecipe0(T, 64,   64, FL.array(MT.D2O        .liquid(U, T)), FL.array(MT.T2O.liquid(U10, F)), ZL_IS);
		
		
		RM.Drying       .addRecipe0(T, 16,   16, FL.Water.make(10), FL.DistW.make(8), ZL_IS);
		RM.Drying       .addRecipe0(T, 16,   16, FL.SpDew.make(10), FL.DistW.make(8), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   16, ST.tag(0), FL.Water.make(10), FL.DistW.make(8), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   16, ST.tag(0), FL.SpDew.make(10), FL.DistW.make(8), ZL_IS);
		
		if (FL.Tropics_Water.exists())
		RM.Drying       .addRecipe0(T, 16,12800, FL.Tropics_Water.make(8000)    , FL.DistW.make(7750), OM.dust(MT.NaCl, U4));
		if (FL.OceanGrC.exists())
		RM.Drying       .addRecipe0(T, 16,12800, FL.OceanGrC.make(8000)         , FL.DistW.make(7750), OM.dust(MT.NaCl, U4));
		RM.Drying       .addRecipe0(T, 16,12800, FL.Ocean.make(8000)            , FL.DistW.make(7750), OM.dust(MT.NaCl, U4));
		RM.Drying       .addRecipe0(T, 16, 1600, MT.SaltWater.liquid(U, T)      , FL.DistW.make( 750), OM.dust(MT.NaCl, U4));
		RM.Drying       .addRecipe0(T, 16,16000, FL.Dirty_Water.make(8000)      , FL.DistW.make(7000), ST.make(Blocks.dirt, 1, 0));
		if (FL.Swampwater.exists())
		RM.Drying       .addRecipe0(T, 16,   16, FL.Swampwater.make(10)         , FL.DistW.make(5), ZL_IS);
		if (FL.Stagnant_Water.exists())
		RM.Drying       .addRecipe0(T, 16,   16, FL.Stagnant_Water.make(10)     , FL.DistW.make(5), ZL_IS);
		
		RM.Drying       .addRecipe1(T, 16,60000, OP.dust.mat(MT.OREMATS.Mirabilite,  7), NF, FL.DistW.make(30000), OP.dust.mat(MT.Na2SO4  , 7));
		RM.Drying       .addRecipe1(T, 16, 6000, OP.dust.mat(MT.H2WO4             ,  7), NF, FL.DistW.make( 3000), OP.dust.mat(MT.WO3     , 4));
		RM.Drying       .addRecipe1(T, 16, 4000, OP.dust.mat(MT.OREMATS.Bischofite,  1), NF, FL.DistW.make( 2000), OP.dust.mat(MT.MgCl2   , 1));
		RM.Drying       .addRecipe1(T, 16, 2000, OP.dust.mat(MT.OREMATS.Trona     ,  1), NF, FL.DistW.make( 1000), OP.dust.mat(MT.Na2CO3  , 1));
		RM.Drying       .addRecipe1(T, 16, 2000, OP.dust.mat(MT.Gypsum            ,  1), NF, FL.DistW.make( 1000), OP.dust.mat(MT.CaSO4   , 1));
		RM.Drying       .addRecipe1(T, 16, 2000, OP.dust.mat(MT.OREMATS.Perlite   ,  1), NF, FL.DistW.make( 1000), OP.dust.mat(MT.Obsidian, 9));
		for (OreDictMaterial tMat : ANY.Clay.mToThis)
		RM.Drying       .addRecipe1(T, 16, 1000, OP.dust.mat(tMat                 ,  1), NF, FL.DistW.make(  500), OP.dust.mat(MT.Ceramic , 1));
	}
}
