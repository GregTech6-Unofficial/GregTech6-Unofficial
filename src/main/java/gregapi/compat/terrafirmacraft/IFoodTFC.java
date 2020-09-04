package gregapi.compat.terrafirmacraft;

import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Interfaces.IFood;
import cpw.mods.fml.common.Optional;
import gregapi.data.CS;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author YueSha
 */
@Optional.InterfaceList(value = {
        @Optional.Interface(iface = "com.bioxx.tfc.api.Interfaces.IFoodk", modid = CS.ModIDs.TFC)
})
public interface IFoodTFC extends IFood {

    default EnumFoodGroup getFoodGroup() {
        return EnumFoodGroup.Grain;
    }

    default int getFoodID() {
        return 0;
    }

    default float getDecayRate(ItemStack is) {
        return 0.0F;
    }

    default float getFoodMaxWeight(ItemStack is) {
        return 5.0F;
    }

    default ItemStack onDecayed(ItemStack is, World world, int i, int j, int k) {
        return null;
    }

    default boolean isEdible(ItemStack is) {
        return T;
    }

    default boolean isUsable(ItemStack is) {
        return F;
    }

    default boolean renderDecay() {
        return F;
    }

    default boolean renderWeight() {
        return F;
    }

    int getTasteSweet(ItemStack is);

    int getTasteSour(ItemStack is);

    int getTasteSalty(ItemStack is);

    int getTasteBitter(ItemStack is);

    int getTasteSavory(ItemStack is);

//    void addAdditionalToolTips(Item aItem, List<String> aList, ItemStack aStack, boolean aF3_H);

}
