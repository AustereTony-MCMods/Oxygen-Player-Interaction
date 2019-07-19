package austeretony.oxygen_interaction.client.gui.interaction;

import java.util.UUID;

import austeretony.oxygen.client.interaction.IInteractionMenuExecutor;
import net.minecraft.util.ResourceLocation;

public class CloseScreenInteractionExecutor implements IInteractionMenuExecutor {

    @Override
    public String getName() {
        return "oxygen_interaction.gui.interaction.close";
    }

    @Override
    public ResourceLocation getIcon() {
        return null;
    }

    @Override
    public boolean isValid(UUID playerUUID) {
        return true;
    }

    @Override
    public void execute(UUID playerUUID) {}
}
