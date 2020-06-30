package gregtech.loaders.c;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import java.util.concurrent.ConcurrentHashMap;

import static gregapi.data.CS.*;
import static net.minecraft.stats.AchievementList.*;

public class Loader_Achievements implements Runnable {

    MultiTileEntityRegistry aRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");

    public ConcurrentHashMap<String, Achievement> achievementList;
    public ConcurrentHashMap<String, Boolean> issuedAchievements;
    public int adjX = 5;
    public int adjY = 9;

    public void run() {

        OUT.println("GT_Mod: Registering Achievements.");

        this.achievementList = new ConcurrentHashMap<>();
        this.issuedAchievements = new ConcurrentHashMap<>();

        //registerAchievement("flintpickaxe", 0, 0, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PICKAXE, MT.Flint, MT.Wood), "", false);
        registerAchievement("stone_anvil", 0, 2, aRegistry.getItem(32025), buildFurnace, false);
        registerAchievement("ceramic_crucible", 2, 2, aRegistry.getItem(1005), "", false);

        // Register GT6U achievement page
        AchievementPage.registerAchievementPage(new AchievementPage("GregTech 6 Unofficial", (Achievement[]) this.achievementList.values().toArray(
                new Achievement[this.achievementList.size()])));
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);

    }

    /** Register an achievement. */
    public Achievement registerAchievement(String textId, int x, int y, ItemStack icon, Achievement requirement, boolean special) {

        //LH.add("achievement." +textId, aLocalizedName);
        //LH.add("achievement." +textId+ ".desc", aLocalizedDescription);

        Achievement achievement = new Achievement(textId, textId, this.adjX + x, this.adjY + y, icon, requirement);
        if (special) {
            achievement.setSpecial();
        }
        achievement.registerStat();



        OUT.println("achievement." + textId + "=");
        OUT.println("achievement." + textId + ".desc=");

        this.achievementList.put(textId, achievement);
        return achievement;
    }

    /** Register an achievement. */
    public Achievement registerAchievement(String textId, int x, int y, ItemStack icon, String requirement, boolean special) {

        Achievement achievement = new Achievement(textId, textId, this.adjX + x, this.adjY + y, icon, getAchievement(requirement));
        if (special) {
            achievement.setSpecial();
        }
        achievement.registerStat();

        OUT.println("achievement." + textId + "=");
        OUT.println("achievement." + textId + ".desc=");

        this.achievementList.put(textId, achievement);
        return achievement;
    }

    /** Get the achievement from its textId */
    public Achievement getAchievement(String textId) {
        if (this.achievementList.containsKey(textId)) {
            return (Achievement) this.achievementList.get(textId);
        }
        return null;
    }

    /** Trigger the achievement */
    public void issueAchievement(EntityPlayer entityplayer, String textId) {
        if (entityplayer == null) {
            return;
        }

        entityplayer.triggerAchievement((StatBase) this.achievementList.get(textId));

    }

    /** Check the crafted ItemStack to trigger achievement. */
    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        EntityPlayer player = event.player;
        ItemStack stack = event.crafting;
        if (player == null || stack == null) {
            return;
        }

//        if (stack.getUnlocalizedName().equals("gt.multitileentity.32025") || stack.getUnlocalizedName().equals("gt.multitileentity.32026") || stack.getUnlocalizedName().equals("gt.multitileentity.32027")) {
//            issueAchievement(player, "stone_anvil");
//        }

    }

    /** Check the picked up ItemStack to trigger achievement. */
    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack stack = event.item.getEntityItem();
        if (player == null || stack == null) {
            return;
        }

//        if (stack.getUnlocalizedName().equals("gt.multitileentity.1005")) {
//            issueAchievement(player, "crucible");
//        }

    }

    /** Check whether the player has the ItemStack in InventoryPlayer to trigger achievement. */
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {

        if (event.entityLiving instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) event.entityLiving;
            InventoryPlayer inv = player.inventory;
            if (player == null) {
                return;
            }

            if (inv.hasItemStack(aRegistry.getItem(1005))) {
                issueAchievement(player, "ceramic_crucible");
            }
            if (inv.hasItemStack(aRegistry.getItem(32025)) || inv.hasItemStack(aRegistry.getItem(32026)) || inv.hasItemStack(aRegistry.getItem(32027))) {
                issueAchievement(player, "stone_anvil");
            }

        }
    }

}
