package gregtech.loaders.c;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.ANY;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
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
    //public static Loader_Achievements achievements;

    public ConcurrentHashMap<String, Achievement> achievementList;
    public ConcurrentHashMap<String, Boolean> issuedAchievements;
    public int adjX = 5;
    public int adjY = 9;

    public void run() {

        OUT.println("GT_Mod: Registering Achievements.");

        this.achievementList = new ConcurrentHashMap<>();
        this.issuedAchievements = new ConcurrentHashMap<>();

        //registerAchievement("flintpickaxe", 0, 0, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PICKAXE, MT.Flint, MT.Wood), "", false);
        registerAchievement("rock", 0, 0, 32757, "", false);
        registerAchievement("pickaxe", 0, 2, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PICKAXE, MT.Flint, MT.Wood), "rock", false);
        registerAchievement("knife", 2, 0, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.KNIFE, MT.Flint, MT.Wood), "rock", false);
        registerAchievement("drygrass", 4, 0, IL.Bale_Dry.get(1), "knife", false);
        registerAchievement("furnace", 4, 2, ST.make(Blocks.furnace, 1, 0), "drygrass", false);
        registerAchievement("stone_anvil", 2, 4, 32025, "furnace", false);
        registerAchievement("file", 0, 6, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FILE, MT.Bronze, MT.Wood), "stoneanvil", false);
        registerAchievement("chisel", 2, 6, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CHISEL, MT.Bronze, MT.Wood), "file", false);
        registerAchievement("wrench", 0, 8, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.WRENCH, MT.Bronze, MT.Wood), "file", false);
        registerAchievement("burningbox", 2, 8, 1100, "wrench", false);
        registerAchievement("ceramic_crucible", 4, 6, 1005, "chisel", false);

        registerAchievement("bronze", 6, 4, OP.ingot.dat(MT.Bronze).getStack(1), "ceramic_crucible", false);
        registerAchievement("iron", 6, 6, OP.ingot.dat(MT.WroughtIron).getStack(1), "ceramic_crucible", false);
        registerAchievement("stainlesssteel", 6, 8, OP.ingot.dat(MT.StainlessSteel).getStack(1), "ceramic_crucible", false);
        registerAchievement("bath", 8, 8, 32708, "stainlesssteel", false);
        registerAchievement("distillationtower", 6, 12, 17101, "stainlesssteel", false);
        registerAchievement("bronzeturbine_steam", 8, 4, 1512, "bronze", false);
        registerAchievement("bronzeengine_steam", 8, 6, 1302, "bronze", false);
        registerAchievement("steel", 10, 6, OP.ingot.dat(ANY.Steel).getStack(1), "bronzeengine_steam", false);
        registerAchievement("steelturbine_steam", 10, 4, 1522, "steel", false);
        registerAchievement("steelengine_steam", 12, 6, 1304, "steel", false);
        registerAchievement("galvanizedsteel", 10, 10, OP.casingMachine.dat(MT.SteelGalvanized).getStack(1), "steel", false);
        registerAchievement("dynamo_lv", 12, 12, 10111, "galvanizedsteel", false);

        // Register GT6U achievement page
        AchievementPage.registerAchievementPage(new AchievementPage("GregTech 6 Unofficial", (Achievement[]) this.achievementList.values().toArray(
                new Achievement[this.achievementList.size()])));
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);

    }

    /** Register an achievement with icon from mID. */
    public Achievement registerAchievement(String textId, int x, int y, int mID, String requirement, boolean special) {

        Achievement achievement = new Achievement(textId, textId, this.adjX + x - 10, this.adjY + y - 10, aRegistry.getItem(mID), getAchievement(requirement));
        if (special) {
            achievement.setSpecial();
        }
        achievement.registerStat();

        this.achievementList.put(textId, achievement);
        return achievement;
    }

    /** Register an achievement with icon from ItemStack. */
    public Achievement registerAchievement(String textId, int x, int y, ItemStack icon, String requirement, boolean special) {

        Achievement achievement = new Achievement(textId, textId, this.adjX + x - 10, this.adjY + y - 10, icon, getAchievement(requirement));
        if (special) {
            achievement.setSpecial();
        }
        achievement.registerStat();

        this.achievementList.put(textId, achievement);
        return achievement;
    }

    /** Register an achievement with requirement as an Achievement */
    public Achievement registerAchievement(String textId, int x, int y, ItemStack icon, Achievement requirement, boolean special) {

        //LH.add("achievement." +textId, aLocalizedName);
        //LH.add("achievement." +textId+ ".desc", aLocalizedDescription);

        Achievement achievement = new Achievement(textId, textId, this.adjX + x - 10, this.adjY + y - 10, icon, requirement);
        if (special) {
            achievement.setSpecial();
        }
        achievement.registerStat();

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

        if (stack.getUnlocalizedName().startsWith("oredict.rockGt")) {
            issueAchievement(player, "rock");
        }

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
