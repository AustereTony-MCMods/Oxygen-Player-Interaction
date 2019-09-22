package austeretony.oxygen_interaction.client.interaction;

import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.interaction.Interaction;
import austeretony.oxygen_interaction.client.gui.interaction.InteractionMenuGUIScreen;
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
        ClientReference.displayGuiScreen(new InteractionMenuGUIScreen(ClientReference.getPersistentUUID(this.pointed)));
    }
}
