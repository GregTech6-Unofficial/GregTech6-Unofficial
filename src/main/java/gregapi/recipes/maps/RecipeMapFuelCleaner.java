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

package gregapi.recipes.maps;

import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IIndividual;
import gregapi.data.CS.*;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.bumble.IItemBumbleBee;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collection;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapFuelCleaner extends RecipeMap {
	public RecipeMapFuelCleaner(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || aFluids == null || aFluids.length < 1 || aFluids[0] == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		if (aFluids.length > 1) {
			for (FluidStack tFuel : aFluids) {
				if (tFuel != null && !FL.Hydrogen.is(tFuel)) {
					if (tFuel.amount >= 16) {
						if (FL.SPetrol.is(tFuel))
							return new Recipe(F, F, F, ZL_IS, ZL_IS, null, null, FL.array(FL.Hydrogen.make(1), FL.amount(tFuel, 16)), FL.array(FL.Petrol.make(16), MT.H2S.gas(U1000, T)), 8, 16, 0);
						if (FL.SFuel.is(tFuel))
							return new Recipe(F, F, F, ZL_IS, ZL_IS, null, null, FL.array(FL.Hydrogen.make(1), FL.amount(tFuel, 16)), FL.array(FL.Fuel.make(16), MT.H2S.gas(U1000, T)), 8, 16, 0);
						if (FL.SGasoil.is(tFuel))
							return new Recipe(F, F, F, ZL_IS, ZL_IS, null, null, FL.array(FL.Hydrogen.make(1), FL.amount(tFuel, 16)), FL.array(FL.Gasoil.make(16), MT.H2S.gas(U1000, T)), 8, 16, 0);
						if (FL.SKerosine.is(tFuel))
							return new Recipe(F, F, F, ZL_IS, ZL_IS, null, null, FL.array(FL.Hydrogen.make(1), FL.amount(tFuel, 16)), FL.array(FL.Kerosine.make(16), MT.H2S.gas(U1000, T)), 8, 16, 0);
						if (FL.SDiesel.is(tFuel))
							return new Recipe(F, F, F, ZL_IS, ZL_IS, null, null, FL.array(FL.Hydrogen.make(1), FL.amount(tFuel, 16)), FL.array(FL.Diesel.make(16), MT.H2S.gas(U1000, T)), 8, 16, 0);
						if (FL.SNaphtha.is(tFuel))
							return new Recipe(F, F, F, ZL_IS, ZL_IS, null, null, FL.array(FL.Hydrogen.make(1), FL.amount(tFuel, 16)), FL.array(FL.Naphtha.make(16), MT.H2S.gas(U1000, T)), 8, 16, 0);
					}
				}
			}
			return rRecipe;
		}
		return rRecipe;
	}
}
