package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.oxygen.client.core.api.ClientReference;
import austeretony.oxygen.client.sync.gui.api.IGUIHandlerClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class InteractionMenuHandler implements IGUIHandlerClient {

    @Override
    public void open() {
        Entity entity = ClientReference.getPointedEntity();
        if (entity != null && entity instanceof EntityPlayer)
            ClientReference.displayGuiScreen(new InteractionGUIScreen(ClientReference.getPersistentUUID(entity)));
    }
}
