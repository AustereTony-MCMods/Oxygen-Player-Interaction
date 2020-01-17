package austeretony.oxygen_pinteraction.client.interaction;

import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.interaction.Interaction;
import austeretony.oxygen_pinteraction.client.gui.interaction.PlayerInteractionScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerInteraction implements Interaction {

    private Entity pointed;

    @Override
    public boolean isValid() {
        this.pointed = ClientReference.getPointedEntity();
        return this.pointed != null && this.pointed instanceof EntityPlayer;
    }

    @Override
    public void execute() {
        ClientReference.displayGuiScreen(new PlayerInteractionScreen(ClientReference.getPersistentUUID(this.pointed)));
    }
}
