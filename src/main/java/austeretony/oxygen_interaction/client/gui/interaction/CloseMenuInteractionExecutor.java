package austeretony.oxygen_interaction.client.gui.interaction;

import java.util.UUID;

import austeretony.oxygen.client.gui.OxygenGUITextures;
import austeretony.oxygen.client.interaction.IInteractionMenuExecutor;
import net.minecraft.util.ResourceLocation;

public class CloseMenuInteractionExecutor implements IInteractionMenuExecutor {

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
