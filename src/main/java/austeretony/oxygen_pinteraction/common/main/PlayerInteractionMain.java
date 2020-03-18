package austeretony.oxygen_pinteraction.common.main;

import austeretony.oxygen_core.client.api.InteractionHelper;
import austeretony.oxygen_core.client.api.OxygenGUIHelper;
import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_pinteraction.client.InteractionMenuSharedDataListener;
import austeretony.oxygen_pinteraction.client.gui.overlay.PlayerInteractionOverlay;
import austeretony.oxygen_pinteraction.client.interaction.PlayerInteraction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = PlayerInteractionMain.MODID, 
        name = PlayerInteractionMain.NAME, 
        version = PlayerInteractionMain.VERSION,
        dependencies = "required-after:oxygen_core@[0.11.0,);",
        clientSideOnly = true,
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = PlayerInteractionMain.VERSIONS_FORGE_URL)
public class PlayerInteractionMain {

    public static final String 
    MODID = "oxygen_pinteraction", 
    NAME = "Oxygen: Player Interaction", 
    VERSION = "0.11.0", 
    VERSION_CUSTOM = VERSION + ":beta:0",
    GAME_VERSION = "1.12.2",
    VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Player-Interaction/info/mod_versions_forge.json";

    public static final int 
    PLAYER_INTERACTION_MOD_INDEX = 7,

    PLAYER_INTERACTION_MENU_SCREEN_ID = 70;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {     
            InteractionHelper.registerInteraction(new PlayerInteraction());
            OxygenGUIHelper.registerOverlay(new PlayerInteractionOverlay());
            OxygenHelperClient.registerSharedDataSyncListener(PLAYER_INTERACTION_MENU_SCREEN_ID, new InteractionMenuSharedDataListener());
        }
    }
}
