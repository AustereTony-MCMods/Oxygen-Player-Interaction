package austeretony.oxygen_interaction.common.config;

import austeretony.oxygen_core.common.config.AbstractConfig;
import austeretony.oxygen_core.common.config.ConfigValue;
import austeretony.oxygen_core.common.config.ConfigValueUtils;
import austeretony.oxygen_interaction.common.main.InteractionMain;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class InteractionConfig extends AbstractConfig {

    public static final ConfigValue
            ENABLE_INTERACTION_KEY = ConfigValueUtils.getBoolean("client", "enable_interaction_key", true),
            INTERACTION_KEY_ID = ConfigValueUtils.getInt("client", "interaction_key_id", Keyboard.KEY_F),
            INTERACTION_DISTANCE = ConfigValueUtils.getFloat("client", "interaction_distance", 5F);

    @Override
    public String getDomain() {
        return InteractionMain.MOD_ID;
    }

    @Override
    public String getVersion() {
        return InteractionMain.VERSION_CUSTOM;
    }

    @Override
    public String getFileName() {
        return "interaction.json";
    }

    @Override
    public void getValues(List<ConfigValue> values) {
        values.add(ENABLE_INTERACTION_KEY);
        values.add(INTERACTION_KEY_ID);
        values.add(INTERACTION_DISTANCE);
    }
}
