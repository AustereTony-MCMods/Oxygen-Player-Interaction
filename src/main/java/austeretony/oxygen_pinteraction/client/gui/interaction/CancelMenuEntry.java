package austeretony.oxygen_pinteraction.client.gui.interaction;

import java.util.UUID;

import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.interaction.PlayerInteractionMenuEntry;

public class CancelMenuEntry implements PlayerInteractionMenuEntry {

    @Override
    public String getLocalizedName() {
        return ClientReference.localize("oxygen_interaction.gui.interaction.cancel");
    }

    @Override
    public boolean isValid(UUID playerUUID) {
        return true;
    }

    @Override
    public void execute(UUID playerUUID) {}
}
