package austeretony.oxygen_interaction.client.gui.interaction;

import java.util.UUID;

import austeretony.oxygen_core.client.gui.OxygenGUITextures;
import austeretony.oxygen_core.client.interaction.InteractionMenuEntry;
import net.minecraft.util.ResourceLocation;

public class CloseMenuInteractionExecutor implements InteractionMenuEntry {

    @Override
    public String getName() {
        return "oxygen_interaction.gui.interaction.cancel";
    }

    @Override
    public ResourceLocation getIcon() {
        return OxygenGUITextures.CROSS_ICONS;
    }

    @Override
    public boolean isValid(UUID playerUUID) {
        return true;
    }

    @Override
    public void execute(UUID playerUUID) {}
}
