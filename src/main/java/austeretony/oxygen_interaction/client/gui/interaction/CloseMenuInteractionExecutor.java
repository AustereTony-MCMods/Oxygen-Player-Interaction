package austeretony.oxygen_interaction.client.gui.interaction;

import java.util.UUID;

import austeretony.oxygen_core.client.interaction.InteractionMenuEntry;

public class CloseMenuInteractionExecutor implements InteractionMenuEntry {

    @Override
    public String getName() {
        return "oxygen_interaction.gui.interaction.cancel";
    }

    @Override
    public boolean isValid(UUID playerUUID) {
        return true;
    }

    @Override
    public void execute(UUID playerUUID) {}
}
