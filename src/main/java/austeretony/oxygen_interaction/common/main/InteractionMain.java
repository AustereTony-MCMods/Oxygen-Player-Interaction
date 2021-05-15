package austeretony.oxygen_interaction.common.main;

import austeretony.oxygen_core.client.api.OxygenClient;
import austeretony.oxygen_core.client.interaction.InteractionHelper;
import austeretony.oxygen_core.common.api.OxygenCommon;
import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_core.common.util.MinecraftCommon;
import austeretony.oxygen_interaction.client.InteractionManagerClient;
import austeretony.oxygen_interaction.client.gui.overlay.InteractionOverlay;
import austeretony.oxygen_interaction.client.interaction.PlayerInteraction;
import austeretony.oxygen_interaction.common.config.InteractionConfig;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = InteractionMain.MOD_ID,
        name = InteractionMain.NAME,
        version = InteractionMain.VERSION,
        dependencies = "required-after:oxygen_core@[0.12.0,);",
        clientSideOnly = true,
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = InteractionMain.VERSIONS_FORGE_URL)
public class InteractionMain {

    public static final String
            MOD_ID = "oxygen_interaction",
            NAME = "Oxygen: Interaction",
            VERSION = "0.12.0",
            VERSION_CUSTOM = VERSION + ":beta:0",
            VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Interaction/info/versions.json";

    //oxygen module index
    public static final int MODULE_INDEX = 11;

    //screen id
    public static final int SCREEN_ID_PLAYER_INTERACTION = 110;

    //key binding id
    public static final int KEYBINDING_ID_INTERACT = 110;

    //interaction id
    public static final int INTERACTION_PLAYER = 110;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        OxygenCommon.registerConfig(new InteractionConfig());
        if (event.getSide() == Side.CLIENT) {
            InteractionHelper.addInteraction(new PlayerInteraction());
            OxygenClient.registerKeyBind(
                    KEYBINDING_ID_INTERACT,
                    "key.oxygen_interaction.interact",
                    OxygenMain.KEY_BINDINGS_CATEGORY,
                    InteractionConfig.INTERACTION_KEY_ID::asInt,
                    InteractionConfig.ENABLE_INTERACTION_KEY::asBoolean,
                    false,
                    InteractionManagerClient.instance()::tryProcessInteraction);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            MinecraftCommon.registerEventHandler(new InteractionOverlay());
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            KeyBinding keyBinding = OxygenClient.getKeyBinding(KEYBINDING_ID_INTERACT);
            if (keyBinding != null) {
                InteractionHelper.setInteractionKeyBinding(keyBinding);
            }
        }
    }
}
