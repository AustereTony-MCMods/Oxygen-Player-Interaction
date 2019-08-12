package austeretony.oxygen_interaction.common.main;

import austeretony.oxygen.client.api.OxygenGUIHelper;
import austeretony.oxygen.client.interaction.InteractionHelperClient;
import austeretony.oxygen.common.api.OxygenHelperServer;
import austeretony.oxygen.common.main.OxygenMain;
import austeretony.oxygen_interaction.client.InteractionMenuHandler;
import austeretony.oxygen_interaction.client.gui.overlay.PlayerInteractionOverlay;
import austeretony.oxygen_interaction.client.interaction.PlayerInteraction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = InteractionMain.MODID, 
        name = InteractionMain.NAME, 
        version = InteractionMain.VERSION,
        dependencies = "required-after:oxygen@[0.8.2,);",//TODO Always check required Oxygen version before build
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = InteractionMain.VERSIONS_FORGE_URL)
public class InteractionMain {

    public static final String 
    MODID = "oxygen_interaction", 
    NAME = "Oxygen: Interaction", 
    VERSION = "0.8.1", 
    VERSION_CUSTOM = VERSION + ":beta:0",
    GAME_VERSION = "1.12.2",
    VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Interaction/info/mod_versions_forge.json";

    public static final int 
    INTERACTION_MOD_INDEX = 7,//Oxygen - 0, Teleportation - 1, Groups - 2, Exchange - 3, Merchants - 4, Players List - 5, Friends List - 6, Mail - 8, Chat - 9

    PLAYER_INTERACTION_MENU_SCREEN_ID = 70;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        OxygenHelperServer.registerSharedDataIdentifierForScreen(PLAYER_INTERACTION_MENU_SCREEN_ID, OxygenMain.ACTIVITY_STATUS_SHARED_DATA_ID);
        if (event.getSide() == Side.CLIENT) {     
            InteractionHelperClient.registerInteraction(new PlayerInteraction());
            OxygenGUIHelper.registerScreenId(PLAYER_INTERACTION_MENU_SCREEN_ID);
            OxygenGUIHelper.registerSharedDataListenerScreen(PLAYER_INTERACTION_MENU_SCREEN_ID, new InteractionMenuHandler());
            OxygenGUIHelper.registerOverlay(new PlayerInteractionOverlay());
        }
    }
}