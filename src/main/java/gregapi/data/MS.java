/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregapi.data;

import static gregapi.data.CS.*;
import static gregapi.data.TD.Atomic.*;
import static gregapi.data.TD.Compounds.*;
import static gregapi.data.TD.ItemGenerator.*;
import static gregapi.data.TD.Processing.*;
import static gregapi.data.TD.Properties.*;
import static gregapi.render.TextureSet.*;

import gregapi.code.HashSetNoNulls;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.configurations.OreDictConfigurationComponent;
import gregapi.render.TextureSet;
import gregapi.util.OM;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.stats.AchievementList;

public class MS {
	public static final HashSetNoNulls<OreDictMaterial> ALL_MATERIALS_REGISTERED_HERE = new HashSetNoNulls<>();
	
	/** Making the Table a little bit more overviewable. DO NOT USE THESE FUNCTIONS YOURSELF!!! Use "OreDictMaterial.createMaterial(YOUR-ID-AS-SPECIFIED-IN-THE-ID-RANGES, OREDICT-NAME, LOCALISED-NAME)" */
	static OreDictMaterial tier         (String aNameOreDict) {return create(-1, aNameOreDict).put(UNUSED_MATERIAL, DONT_SHOW_THIS_COMPONENT, IGNORE_IN_COLOR_LOG).setAllToTheOutputOf(null, 0, 1);}
	static OreDictMaterial unused       (String aNameOreDict) {return create(-1, aNameOreDict).put(UNUSED_MATERIAL, DONT_SHOW_THIS_COMPONENT);}
	static OreDictMaterial depricated   (String aNameOreDict) {return create(-1, aNameOreDict).put(UNUSED_MATERIAL, DONT_SHOW_THIS_COMPONENT);}
	static OreDictMaterial invalid      (String aNameOreDict) {return unused(aNameOreDict).put(INVALID_MATERIAL);}
	static OreDictMaterial create       (int aID, String aNameOreDict) {OreDictMaterial rMaterial = OreDictMaterial.createMaterial(aID, aNameOreDict, aNameOreDict); ALL_MATERIALS_REGISTERED_HERE.add(rMaterial); return rMaterial;}
	static OreDictMaterial create       (int aID, String aNameOreDict, TextureSet[] aSets) {return create(aID, aNameOreDict).setTextures(aSets);}
	static OreDictMaterial create       (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA) {return create(aID, aNameOreDict, aSets).setRGBa(aR, aG, aB, aA).put(aR==256?UNUSED_MATERIAL:null).hide(aR==256);}
	
	/** Making the Table a little bit more overviewable. DO NOT USE THESE FUNCTIONS YOURSELF!!! Use "OreDictMaterial.createMaterial(YOUR-ID-AS-SPECIFIED-IN-THE-ID-RANGES, OREDICT-NAME, LOCALISED-NAME)" */
	static OreDictMaterial element      (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return create      (aID, aNameOreDict, aSets, aR, aG, aB, aA).setStats(aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter).put(ELEMENT).setTooltip(aSymbol);}
	static OreDictMaterial metal        (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(METAL, SMITHABLE, MELTING, EXTRUDER, aMeltingPoint < 1200 ? new Object[] {EXTRUDER_SIMPLE, FURNACE, MORTAR} : null);}
	static OreDictMaterial metalloid    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(METALLOID, SMITHABLE, MELTING, MOLTEN, EXTRUDER, aMeltingPoint < 1200 ? new Object[] {EXTRUDER_SIMPLE, FURNACE, MORTAR} : null);}
	static OreDictMaterial nonmetal     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(NONMETAL);}
	static OreDictMaterial diatomic     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return nonmetal    (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(DIATOMIC_NONMETAL);}
	static OreDictMaterial diatomicgas  (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return diatomic    (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(GASSES);}
	static OreDictMaterial polyatomic   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return nonmetal    (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(POLYATOMIC_NONMETAL);}
	static OreDictMaterial noblegas     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return nonmetal    (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(NOBLE_GAS, GASSES);}
	static OreDictMaterial alkali       (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(ALKALI_METAL, MOLTEN);}
	static OreDictMaterial alkaline     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(ALKALINE_EARTH_METAL, MOLTEN);}
	static OreDictMaterial lanthanide   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(LANTHANIDE);}
	static OreDictMaterial actinide     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(ACTINIDE);}
	static OreDictMaterial transmetal   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(TRANSITION_METAL);}
	static OreDictMaterial precmetal    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return transmetal  (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(PRECIOUS_METAL);}
	static OreDictMaterial noblemetal   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return precmetal   (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(NOBLE_METAL);}
	static OreDictMaterial refractmetal (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return transmetal  (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(REFRACTORY_METAL);}
	static OreDictMaterial platingroup  (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return precmetal   (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(PLATINUM_GROUP);}
	static OreDictMaterial posttrans    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(POST_TRANSITION_METAL);}
	
	static OreDictMaterial element      (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return create      (aID, aNameOreDict, aSets, 256, 256, 256, 255).setStats(aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter).put(ELEMENT).setTooltip(aSymbol);}
	static OreDictMaterial unknown      (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons                                                                                            )   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, 1000, 3000, 0, SET_SHINY).hide().aspects(TC.RADIO, 1);}
	static OreDictMaterial metalloid    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(METALLOID, SMITHABLE, MELTING, MOLTEN, EXTRUDER, aMeltingPoint < 1200 ? new Object[] {EXTRUDER_SIMPLE, FURNACE, MORTAR} : null);}
	static OreDictMaterial alkali       (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(ALKALI_METAL, MOLTEN);}
	static OreDictMaterial alkaline     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(ALKALINE_EARTH_METAL, MOLTEN);}
	static OreDictMaterial lanthanide   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(LANTHANIDE);}
	static OreDictMaterial actinide     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(ACTINIDE);}
	static OreDictMaterial transmetal   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(TRANSITION_METAL);}
	static OreDictMaterial posttrans    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(POST_TRANSITION_METAL);}
	
	/** Making the Table a little bit more overviewable. DO NOT USE THESE FUNCTIONS YOURSELF!!! Use "OreDictMaterial.createMaterial(YOUR-ID-AS-SPECIFIED-IN-THE-ID-RANGES, OREDICT-NAME, LOCALISED-NAME)" */
	static OreDictMaterial dcmp         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial cent         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dcmp            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial elec         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dcmp            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial gas          (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_GAS);}
	static OreDictMaterial gasdcmp      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gas             (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gascent      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasdcmp         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gaselec      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasdcmp         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial lqud         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_FLUID);}
	static OreDictMaterial lquddcmp     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqud            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial lqudcent     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lquddcmp        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial lqudelec     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lquddcmp        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial gaschem      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_GAS);}
	static OreDictMaterial gaschemdcmp  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gaschem         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gaschemcent  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gaschemdcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gaschemelec  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gaschemdcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial lqudchem     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_FLUID);}
	static OreDictMaterial lqudchemdcmp (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudchem        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial lqudchemcent (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudchemdcmp    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial lqudchemelec (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudchemdcmp    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial gasacid      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_GAS, ACID);}
	static OreDictMaterial gasaciddcmp  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasacid         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gasacidcent  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasaciddcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gasacidelec  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasaciddcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial lqudacid     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_FLUID, ACID);}
	static OreDictMaterial lqudaciddcmp (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudacid        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial lqudacidcent (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudaciddcmp    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial lqudacidelec (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudaciddcmp    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial dust         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_DUST, MORTAR).setPriorityPrefix(2);}
	static OreDictMaterial dustdcmp     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dust            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial dustcent     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dustdcmp        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial dustelec     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dustdcmp        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial glowstone    (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredustcent     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(PLATES, STICKS, MORTAR, BRITTLE, UNBURNABLE, MELTING, CRYSTAL, GLOWING, LIGHTING).setPriorityPrefix(2).addReRegistrations(ANY.Glowstone).setOreMultiplier(4);}
	static OreDictMaterial redstone     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredustcent     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(PLATES, STICKS, MORTAR, BRITTLE, UNBURNABLE, MELTING, CRYSTAL, G_GEM_ORES_TRANSPARENT).setPriorityPrefix(2).setOreMultiplier(4);}
	static OreDictMaterial coal         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return elec            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_GEM_ORES, BRITTLE, FLAMMABLE, MORTAR, INGOTS, COAL).setPriorityPrefix(1);}
	static OreDictMaterial wax          (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return dust            (aID, aNameOreDict, SET_FOOD        , aR, aG, aB, aA).put(FOILS, PLATES, INGOTS, PARTS, FURNACE, MELTING, BRITTLE, MORTAR, EXTRUDER, EXTRUDER_SIMPLE).addReRegistrations(ANY.Wax);}
	static OreDictMaterial meat         (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return dustfood        (aID, aNameOreDict, SET_FINE        , aR, aG, aB, aA).put(MEAT, INGOTS, MELTING, EXTRUDER, EXTRUDER_SIMPLE);}
	static OreDictMaterial grain        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return dustfood        (aID, aNameOreDict, SET_POWDER      , aR, aG, aB, aA).put(FLAMMABLE).addReRegistrations(ANY.Grains, ANY.FlourGrains);}
	static OreDictMaterial food         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial orefood      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredust         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial dustfood     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dust            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial mixfood      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return mixdust         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial food         (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_FOOD        , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial orefood      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return oredust         (aID, aNameOreDict, SET_FINE        , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial dustfood     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return dust            (aID, aNameOreDict, SET_FINE        , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial mixfood      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return mixdust         (aID, aNameOreDict, SET_FINE        , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial dye          (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return dust            (aID, aNameOreDict, SET_FOOD        , aR, aG, aB, 255).aspects(TC.SENSUS, 1).put(DONT_SHOW_THIS_COMPONENT);}
	static OreDictMaterial gem          (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_GEM_ORES).setPriorityPrefix(1);}
	static OreDictMaterial gemdcmp      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gem             (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gemcent      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gemdcmp         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gemelec      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gemdcmp         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial stone        (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_STONE, STONE, BRITTLE, MORTAR, FURNACE, EXTRUDER, EXTRUDER_SIMPLE).addReRegistrations(ANY.Stone);}
	static OreDictMaterial stonedcmp    (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return stone           (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial stonecent    (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return stonedcmp       (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial stoneelec    (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return stonedcmp       (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial stone        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_STONE       , aR, aG, aB, aA).put(G_STONE, STONE, BRITTLE, MORTAR, FURNACE, EXTRUDER, EXTRUDER_SIMPLE).addReRegistrations(ANY.Stone);}
	static OreDictMaterial stonedcmp    (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return stone           (aID, aNameOreDict, SET_STONE       , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial stonecent    (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return stonedcmp       (aID, aNameOreDict, SET_STONE       , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial stoneelec    (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return stonedcmp       (aID, aNameOreDict, SET_STONE       , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial crystal      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_GEM_ORES_TRANSPARENT, CRYSTAL).setPriorityPrefix(1);}
	static OreDictMaterial crystal_tc   (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return crystal         (aID, aNameOreDict, SET_SHARDS      , aR, aG, aB, 255).lens(aColor).put(MAGICAL, UNBURNABLE, MD.TC).addReRegistrations(ANY.ThaumCrystal).setOreMultiplier(2).visDefault();}
	static OreDictMaterial crystaldcmp  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return crystal         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial crystalcent  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return crystaldcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial crystalelec  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return crystaldcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial valgem       (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gem             (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_GEM_ORES_TRANSPARENT, CRYSTAL, VALUABLE);}
	static OreDictMaterial valgemdcmp   (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return valgem          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE).setSmelting(null, 0);}
	static OreDictMaterial valgemcent   (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return valgemdcmp      (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial valgemelec   (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return valgemdcmp      (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial garnet       (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return valgemelec      (aID, aNameOreDict, SET_RUBY        , aR, aG, aB,127).lens(aColor).put(MD.GT  ).aspects(TC.VITREUS, 3, TC.LUCRUM , 1                ).qual(3, 7.0, 128, 2).addReRegistrations(ANY.Garnet    );}
	static OreDictMaterial mix          (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dcmp            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial mixdust      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dustcent        (aID, aNameOreDict, aSets           , aR, aG, aB, aA);}
	static OreDictMaterial oredust      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_DUST_ORES);}
	static OreDictMaterial oredustdcmp  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredust         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial oredustcent  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredustdcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial oredustelec  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredustdcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial metal        (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial metalore     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_ORES);}
	static OreDictMaterial metalmachine (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_MACHINE);}
	static OreDictMaterial metalmachore (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_MACHINE_ORES);}
	static OreDictMaterial metal        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(G_INGOT, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial metalore     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(G_INGOT_ORES);}
	static OreDictMaterial metalmachine (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(G_INGOT_MACHINE);}
	static OreDictMaterial metalmachore (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(G_INGOT_MACHINE_ORES);}
	static OreDictMaterial setal        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(G_INGOT, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial setalore     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(G_INGOT_ORES);}
	static OreDictMaterial setalmachine (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(G_INGOT_MACHINE);}
	static OreDictMaterial setalmachore (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(G_INGOT_MACHINE_ORES);}
	static OreDictMaterial alloy        (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloyore     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalore        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachine (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachore (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalmachore    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloy        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloyore     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalore        (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachine (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachore (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachore    (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloy        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloyore     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalore        (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloymachine (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloymachore (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachore    (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial metalnd      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_ND, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial metalmachnd  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalnd         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_ND_MACHINE);}
	static OreDictMaterial alloynd      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalnd         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachnd  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalmachnd     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial wood         (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return wood            (aID, aNameOreDict, SET_WOOD        , aR, aG, aB, aA);}
	
	static OreDictMaterial wood(int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA)  {
		OreDictMaterial rMaterial = create(aID, aNameOreDict, aSets, aR, aG, aB, aA).put(G_WOOD, WOOD, MORTAR).addReRegistrations(ANY.Wood, ANY.WoodPlastic);
		String tPlank = "plank"+rMaterial.mNameInternal;
		OreDictManager.INSTANCE.addAutoBlackListing(tPlank);
		OreDictManager.INSTANCE.addReRegistration(tPlank, OD.plankAnyWood);
		if ("Wood".equalsIgnoreCase(rMaterial.mNameInternal)) return rMaterial;
		OreDictManager.INSTANCE.setAutomaticItemData(tPlank, new OreDictItemData(rMaterial, U));
		OreDictManager.INSTANCE.addReRegistrationWithReversal("plate"+rMaterial.mNameInternal, tPlank);
		return rMaterial;
	}
	
	static OreDictMaterial gem_aa(int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA, OreDictMaterial aCopy) {
		return dcmp(aID, aNameOreDict, aSets, aR, aG, aB, aA).put(G_GEM_TRANSPARENT, CRYSTAL, BRITTLE, MD.AA).uumMcfg(0, aCopy, U).steal(aCopy).setAllToTheOutputOf(aCopy).visDefault();
	}
	
	static OreDictMaterial unknown(int aID, long aNeutrons) {
		int aProtonsAndElectrons = aID / 10;
		String aNameOreDict, aSymbol;
		
		switch (aProtonsAndElectrons / 100) {
		case 1: aNameOreDict  = "Un"     ; aSymbol  = "U"; break;
		case 2: aNameOreDict  = "Bi"     ; aSymbol  = "B"; break;
		case 3: aNameOreDict  = "Tri"    ; aSymbol  = "T"; break;
		case 4: aNameOreDict  = "Quad"   ; aSymbol  = "Q"; break;
		case 5: aNameOreDict  = "Pent"   ; aSymbol  = "P"; break;
		case 6: aNameOreDict  = "Hex"    ; aSymbol  = "H"; break;
		case 7: aNameOreDict  = "Sept"   ; aSymbol  = "S"; break;
		case 8: aNameOreDict  = "Oct"    ; aSymbol  = "O"; break;
		case 9: aNameOreDict  = "Enn"    ; aSymbol  = "E"; break;
		default: throw new IllegalArgumentException("Disallowed Parameter");
		}
		switch ((aProtonsAndElectrons / 10) % 10) {
		case 0: aNameOreDict += "nil"    ; aSymbol += "n"; break;
		case 1: aNameOreDict += "un"     ; aSymbol += "u"; break;
		case 2: aNameOreDict += "bi"     ; aSymbol += "b"; break;
		case 3: aNameOreDict += "tri"    ; aSymbol += "t"; break;
		case 4: aNameOreDict += "quad"   ; aSymbol += "q"; break;
		case 5: aNameOreDict += "pent"   ; aSymbol += "p"; break;
		case 6: aNameOreDict += "hex"    ; aSymbol += "h"; break;
		case 7: aNameOreDict += "sept"   ; aSymbol += "s"; break;
		case 8: aNameOreDict += "oct"    ; aSymbol += "o"; break;
		case 9: aNameOreDict += "enn"    ; aSymbol += "e"; break;
		}
		switch (aProtonsAndElectrons % 10) {
		case 0: aNameOreDict += "nilium" ; aSymbol += "n"; break;
		case 1: aNameOreDict += "unium"  ; aSymbol += "u"; break;
		case 2: aNameOreDict += "bium"   ; aSymbol += "b"; break;
		case 3: aNameOreDict += "trium"  ; aSymbol += "t"; break;
		case 4: aNameOreDict += "quadium"; aSymbol += "q"; break;
		case 5: aNameOreDict += "pentium"; aSymbol += "p"; break;
		case 6: aNameOreDict += "hexium" ; aSymbol += "h"; break;
		case 7: aNameOreDict += "septium"; aSymbol += "s"; break;
		case 8: aNameOreDict += "octium" ; aSymbol += "o"; break;
		case 9: aNameOreDict += "ennium" ; aSymbol += "e"; break;
		}
		return element(aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, 1000, 3000, 0, SET_SHINY).hide().aspects(TC.RADIO, aProtonsAndElectrons / 50);
	}
	
	public static final OreDictMaterial
	Ethane                   = create        ( 9911, "Butane"                , SET_FLUID             , 255,  41,  41, 255).put(LIQUID, G_CONTAINERS, EXPLOSIVE, FLAMMABLE)                                                                                                                                                                                                                                                                   .heat( 100,  200).aspects(TC.MORTUUS, 1, TC.POTENTIA, 1);

}
