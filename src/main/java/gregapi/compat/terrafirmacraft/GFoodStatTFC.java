package gregapi.compat.terrafirmacraft;

import cpw.mods.fml.common.Optional;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.MD;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

import static gregapi.data.CS.T;

public class GFoodStatTFC {

    //TFC Compat
    public int mSweetness, mSourness, mSaltiness, mBitterness, mSavory = 0;
    public boolean mEdible;
    public int mFoodGroup;
    public int mWeight;

    public GFoodStatTFC(boolean aEdible, int aFoodGroup, int aSweetness, int aSourness, int aSaltyness, int aBitterness, int aSavory) {
        mSweetness = aSweetness;
        mSourness = aSourness;
        mSaltiness = aSaltyness;
        mBitterness = aBitterness;
        mSavory = aSavory;
        mEdible = aEdible;
        mFoodGroup = aFoodGroup;
        mWeight = 5;
    }

    public GFoodStatTFC(int aFoodGroup) {
        this(T, aFoodGroup, 0, 0, 0, 0, 10);
    }

    public GFoodStatTFC() {
        this(T, 5, 0, 0, 0, 0, 10);
    }

    @Optional.Method(modid = CS.ModIDs.TFC)
    public void addAdditionalToolTips(Item aItem, List<String> aList, ItemStack aStack, boolean aF3_H) {
        if (MD.TFC.mLoaded) {
            aList.add("\u2696" + com.bioxx.tfc.Core.TFC_Core.translate("gui.Weight.Light") + " \u21F2" + com.bioxx.tfc.Core.TFC_Core.translate("gui.Size.VerySmall"));


            if (mFoodGroup == 0) aList.add(LH.Chat.WHITE + com.bioxx.tfc.Core.TFC_Core.translate("gui.food.dairy"));
            if (mFoodGroup == 1) aList.add(LH.Chat.PURPLE + com.bioxx.tfc.Core.TFC_Core.translate("gui.food.fruit"));
            if (mFoodGroup == 2) aList.add(LH.Chat.YELLOW + com.bioxx.tfc.Core.TFC_Core.translate("gui.food.grain"));
            if (mFoodGroup == 3) aList.add(LH.Chat.DRED + com.bioxx.tfc.Core.TFC_Core.translate("gui.food.protein"));
            if (mFoodGroup == 4) aList.add(LH.Chat.GREEN + com.bioxx.tfc.Core.TFC_Core.translate("gui.food.vegetable"));
            if (mFoodGroup == 5) aList.add(LH.Chat.RED + "None");

            aList.add(com.bioxx.tfc.Core.TFC_Core.translate("gui.food.amount") + LH.Chat.GRAY + " 5.0 oz / 5.0 oz");

            aList.add(com.bioxx.tfc.Core.TFC_Core.translate("gui.showtaste"));

            if (com.bioxx.tfc.Core.TFC_Core.showCtrlInformation()) {
                aList.add(LH.Chat.DGRAY + com.bioxx.tfc.Core.TFC_Core.translate("gui.taste.sweet") + " - " +  LH.Chat.WHITE + mSweetness);
                aList.add(LH.Chat.DGRAY + com.bioxx.tfc.Core.TFC_Core.translate("gui.taste.sour") + " - " +  LH.Chat.WHITE + mSourness);
                aList.add(LH.Chat.DGRAY + com.bioxx.tfc.Core.TFC_Core.translate("gui.taste.salty") + " - " +  LH.Chat.WHITE + mSaltiness);
                aList.add(LH.Chat.DGRAY + com.bioxx.tfc.Core.TFC_Core.translate("gui.taste.bitter") + " - " +  LH.Chat.WHITE + mBitterness);
                aList.add(LH.Chat.DGRAY + com.bioxx.tfc.Core.TFC_Core.translate("gui.taste.savory") + " - " +  LH.Chat.WHITE + mSavory);
            }
        }
    }

}
