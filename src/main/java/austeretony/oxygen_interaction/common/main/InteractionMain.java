package austeretony.oxygen_interaction.common.main;

import austeretony.oxygen_core.client.api.OxygenGUIHelper;
import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_core.client.interaction.InteractionHelperClient;
import austeretony.oxygen_interaction.client.InteractionMenuSharedDataListener;
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
        dependencies = "required-after:oxygen_core@[0.9.1,);",
        clientSideOnly = true,
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = InteractionMain.VERSIONS_FORGE_URL)
public class InteractionMain {

    public static final String 
    MODID = "oxygen_interaction", 
    NAME = "Oxygen: Interaction", 
    VERSION = "0.9.0", 
    VERSION_CUSTOM = VERSION + ":beta:0",
    GAME_VERSION = "1.12.2",
    VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Interaction/info/mod_versions_forge.json";

    public static final int 
    INTERACTION_MOD_INDEX = 7,

    PLAYER_INTERACTION_MENU_SCREEN_ID = 70;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {     
            InteractionHelperClient.registerInteraction(new PlayerInteraction());
            OxygenGUIHelper.registerOverlay(new PlayerInteractionOverlay());
            OxygenHelperClient.registerSharedDataSyncListener(PLAYER_INTERACTION_MENU_SCREEN_ID, new InteractionMenuSharedDataListener());
        }
    }
}
