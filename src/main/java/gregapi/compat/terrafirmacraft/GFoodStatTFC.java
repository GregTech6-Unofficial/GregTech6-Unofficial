package gregapi.compat.terrafirmacraft;

import cpw.mods.fml.common.Optional;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.MD;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GFoodStatTFC {

    //TFC Compat
    public int mSweetness, mSourness, mSaltiness, mBitterness, mSavory = 0;

    public GFoodStatTFC(int aSweetness, int aSourness, int aSaltyness, int aBitterness, int aSavory) {
        mSweetness = aSweetness;
        mSourness = aSourness;
        mSaltiness = aSaltyness;
        mBitterness = aBitterness;
        mSavory = aSavory;
    }

    public GFoodStatTFC() {
        this(0, 0, 0, 0, 0);
    }

    @Optional.Method(modid = CS.ModIDs.TFC)
    public void addAdditionalToolTips(Item aItem, List<String> aList, ItemStack aStack, boolean aF3_H) {
        if (MD.TFC.mLoaded) {
            aList.add(LH.Chat.GRAY + "5.0 Oz");
            aList.add(LH.Chat.YELLOW + LH.get("gt.tfc.sweetness") + " - " +  mSweetness);
            aList.add(LH.Chat.YELLOW + LH.get("gt.tfc.sourness") + " - " +  mSourness);
            aList.add(LH.Chat.YELLOW + LH.get("gt.tfc.saltiness") + " - " +  mSaltiness);
            aList.add(LH.Chat.YELLOW + LH.get("gt.tfc.bitterness") + " - " +  mBitterness);
            aList.add(LH.Chat.YELLOW + LH.get("gt.tfc.savory") + " - " +  mSavory);
        }
    }

    static {
        LH.add("gt.tfc.sweetness", "Sweetness");
        LH.add("gt.tfc.sourness", "Sourness");
        LH.add("gt.tfc.saltiness", "Saltiness");
        LH.add("gt.tfc.bitterness", "Bitterness");
        LH.add("gt.tfc.savory", "Savory");
    }

}
