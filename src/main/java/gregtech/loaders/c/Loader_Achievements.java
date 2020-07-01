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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

public class Loader_Achievements {

    MultiTileEntityRegistry aRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
    //public static Loader_Achievements achievements;

    public ConcurrentHashMap<String, Achievement> achievementList;
    public ConcurrentHashMap<String, Boolean> issuedAchievements;
    public int adjX = 5;
    public int adjY = 9;

    public Loader_Achievements () {

        OUT.println("GT_Mod: Registering Achievements.");

        this.achievementList = new ConcurrentHashMap<>();
        this.issuedAchievements = new ConcurrentHashMap<>();

        registerAchievement("flint",               0,  0,  ST.make(Items.flint, 1, 0), "", false);
        registerAchievement("pickaxe",             0,  2,  ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PICKAXE, MT.Flint, MT.Wood), "flint", false);
        registerAchievement("knife",               2,  0,  ToolsGT.sMetaTool.getToolWithStats(ToolsGT.KNIFE, MT.Flint, MT.Wood), "flint", false);
        registerAchievement("drygrass",            4,  0,  IL.Bale_Dry.get(1), "knife", false);
        registerAchievement("furnace",             2,  2,  ST.make(Blocks.furnace, 1, 0), "drygrass", false);
        registerAchievement("stone_anvil",         2,  4,  aRegistry.getItem(32025), "furnace", false);
        registerAchievement("file",                0,  4,  ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FILE, MT.Bronze, MT.Wood), "stone_anvil", false);
        registerAchievement("chisel",              2,  6,  ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CHISEL, MT.Bronze, MT.Bronze), "file", false);
        registerAchievement("wrench",              0,  8,  ToolsGT.sMetaTool.getToolWithStats(ToolsGT.WRENCH, MT.Bronze, MT.Bronze), "file", false);
        registerAchievement("burningbox",          2,  8,  aRegistry.getItem(1100), "wrench", false);
        registerAchievement("ceramic_crucible",    4,  6,  aRegistry.getItem(1005), "chisel", false);

        registerAchievement("bronze",              6,  4,  OP.ingot.dat(MT.Bronze).getStack(1), "ceramic_crucible", false);
        registerAchievement("iron",                6,  6,  OP.ingot.dat(MT.WroughtIron).getStack(1), "ceramic_crucible", false);
        registerAchievement("stainlesssteel",      6,  8,  OP.ingot.dat(MT.StainlessSteel).getStack(1), "ceramic_crucible", false);
        registerAchievement("bath",                8,  8,  aRegistry.getItem(32708), "stainlesssteel", false);
        registerAchievement("distillationtower",   6,  12, aRegistry.getItem(17101), "stainlesssteel", false);
        registerAchievement("bronzeturbine_steam", 8,  4,  aRegistry.getItem(1512), "bronze", false);
        registerAchievement("bronzeengine_steam",  8,  6,  aRegistry.getItem(1302), "bronze", false);
        registerAchievement("steel",               10, 6,  OP.ingot.dat(ANY.Steel).getStack(1), "bronzeengine_steam", false);
        registerAchievement("steelturbine_steam",  10, 4,  aRegistry.getItem(1522), "steel", false);
        registerAchievement("steelengine_steam",   12, 6,  aRegistry.getItem(1304), "steel", false);
        registerAchievement("galvanizedsteel",     10, 10, OP.casingMachine.dat(MT.SteelGalvanized).getStack(1), "steel", false);
        registerAchievement("dynamo_lv",           12, 12, aRegistry.getItem(10111), "galvanizedsteel", false);

        registerAchievement("electrolyzer",           12+2, 12, aRegistry.getItem(20091), "dynamo_lv", false);
        registerAchievement("heatmixer",           12, 12+2, aRegistry.getItem(20506), "dynamo_lv", false);
        registerAchievement("titanium",           12+2, 12+2, OP.dust.dat(MT.Ti).getStack(1), "electrolyzer", false);
        registerAchievement("aluminium",           12+4, 12, OP.dust.dat(MT.Al).getStack(1), "electrolyzer", false);
        registerAchievement("tungsten",           12, 12+4, OP.dust.dat(MT.W).getStack(1), "heatmixer", false);
        registerAchievement("tungstensteel",           12, 12+6, OP.ingot.dat(MT.TungstenSteel).getStack(1), "tungsten", false);
        registerAchievement("cryodistill",           12+6, 12, aRegistry.getItem(17111), "aluminium", false);
        registerAchievement("circuit_t6",           12+8, 12, IL.Circuit_Ultimate.get(1), "cryodistill", false);
        registerAchievement("crystalprocessor",           12+8, 12+2, IL.Processor_Crystal_Empty.get(1), "circuit_t6", false);
        registerAchievement("bedrockdrill",           12+10, 12+2, aRegistry.getItem(17999), "crystalprocessor", false);
        registerAchievement("fusionmk1",           12+8, 12+6, aRegistry.getItem(17198), "crystalprocessor", false);

        // Register GT6U achievement page
        AchievementPage.registerAchievementPage(new AchievementPage("GregTech 6 Unofficial", (Achievement[]) this.achievementList.values().toArray(
                new Achievement[this.achievementList.size()])));
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);

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

    }

    /** Check the picked up ItemStack to trigger achievement. */
    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack stack = event.item.getEntityItem();
        if (player == null || stack == null) {
            return;
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

            if (inv.hasItemStack(ST.make(Items.flint, 1, 0))) {
                issueAchievement(player, "flint");
            }
            if (inv.hasItemStack(IL.Bale_Dry.get(1))) {
                issueAchievement(player, "drygrass");
            }
            if (inv.hasItemStack(ST.make(Blocks.furnace, 1, 0))) {
                issueAchievement(player, "furnace");
            }
            if (inv.hasItemStack(aRegistry.getItem(32025)) || inv.hasItemStack(aRegistry.getItem(32026)) || inv.hasItemStack(aRegistry.getItem(32027))) {
                issueAchievement(player, "stone_anvil");
            }
            if (inv.hasItemStack(aRegistry.getItem(1005))) {
                issueAchievement(player, "ceramic_crucible");
            }
            if (inv.hasItemStack(aRegistry.getItem(1100)) || inv.hasItemStack(aRegistry.getItem(1101)) || inv.hasItemStack(aRegistry.getItem(1102))) {
                issueAchievement(player, "burningbox");
            }
            if (inv.hasItemStack(OP.ingot.dat(MT.Bronze).getStack(1))) {
                issueAchievement(player, "bronze");
            }
            if (inv.hasItemStack(OP.ingot.dat(ANY.Iron).getStack(1))) {
                issueAchievement(player, "iron");
            }
            if (inv.hasItemStack(OP.ingot.dat(MT.StainlessSteel).getStack(1))) {
                issueAchievement(player, "stainlesssteel");
            }
            if (inv.hasItemStack(aRegistry.getItem(32708))) {
                issueAchievement(player, "bath");
            }
            if (inv.hasItemStack(aRegistry.getItem(17101))) {
                issueAchievement(player, "distillationtower");
            }
            if (inv.hasItemStack(aRegistry.getItem(1512))) {
                issueAchievement(player, "bronzeturbine_steam");
            }
            if (inv.hasItemStack(aRegistry.getItem(1302))) {
                issueAchievement(player, "bronzeengine_steam");
            }
            if (inv.hasItemStack(OP.ingot.dat(ANY.Steel).getStack(1))) {
                issueAchievement(player, "steel");
            }
            if (inv.hasItemStack(aRegistry.getItem(1522))) {
                issueAchievement(player, "steelturbine_steam");
            }
            if (inv.hasItemStack(aRegistry.getItem(1304))) {
                issueAchievement(player, "steelengine_steam");
            }
            if (inv.hasItemStack(OP.casingMachineDouble.dat(MT.SteelGalvanized).getStack(1))) {
                issueAchievement(player, "galvanizedsteel");
            }
            if (inv.hasItemStack(aRegistry.getItem(10111))) {
                issueAchievement(player, "dynamo_lv");
            }
            if (inv.hasItemStack(aRegistry.getItem(20091))) {
                issueAchievement(player, "electrolyzer");
            }
            if (inv.hasItemStack(aRegistry.getItem(20506))) {
                issueAchievement(player, "heatmixer");
            }
            if (inv.hasItemStack(OP.dust.dat(MT.Ti).getStack(1))) {
                issueAchievement(player, "titanium");
            }
            if (inv.hasItemStack(OP.dust.dat(MT.Al).getStack(1))) {
                issueAchievement(player, "aluminium");
            }
            if (inv.hasItemStack(OP.dust.dat(MT.Tungsten).getStack(1))) {
                issueAchievement(player, "tungsten");
            }
            if (inv.hasItemStack(OP.ingot.dat(MT.TungstenSteel).getStack(1))) {
                issueAchievement(player, "tungstensteel");
            }
            if (inv.hasItemStack(aRegistry.getItem(17111))) {
                issueAchievement(player, "cryodistill");
            }
            if (inv.hasItemStack(IL.Circuit_Ultimate.get(1))) {
                issueAchievement(player, "circuit_t6");
            }
            if (inv.hasItemStack(IL.Processor_Crystal_Empty.get(1))) {
                issueAchievement(player, "crystalprocessor");
            }
            if (inv.hasItemStack(aRegistry.getItem(17999))) {
                issueAchievement(player, "bedrockdrill");
            }
            if (inv.hasItemStack(aRegistry.getItem(17198))) {
                issueAchievement(player, "fusionmk1");
            }

        }
    }

}
