package austeretony.oxygen_interaction.client;

import austeretony.oxygen.client.core.api.ClientReference;
import austeretony.oxygen.client.interaction.IInteraction;
import austeretony.oxygen.client.interaction.InteractionHelperClient;
import austeretony.oxygen.common.api.OxygenGUIHelper;
import austeretony.oxygen.common.main.OxygenMain;
import austeretony.oxygen_interaction.client.gui.interaction.InteractionGUIScreen;
import austeretony.oxygen_interaction.common.main.InteractionMain;
import austeretony.oxygen_interaction.common.network.server.SPInteractionRequest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class InteractionManagerClient {

    public static void openPlayerInteractionMenuSynced() {
        OxygenGUIHelper.needSync(InteractionMain.PLAYER_INTERACTION_MENU_SCREEN_ID);
        OxygenMain.network().sendToServer(new SPInteractionRequest(SPInteractionRequest.EnumRequest.OPEN_PLAYER_INTERACTION_MENU));
    }

    public static void openPlayerInteractionMenuDelegated() {
        ClientReference.getMinecraft().addScheduledTask(new Runnable() {

            @Override
            public void run() {
                openPlayerInteractionMenu();
            }
        });
    }

    public static void openPlayerInteractionMenu() {
        Entity pointed = ClientReference.getPointedEntity();
        if (pointed != null && pointed instanceof EntityPlayer)
            ClientReference.displayGuiScreen(new InteractionGUIScreen(ClientReference.getPersistentUUID(pointed)));
    }

    public static void processInteraction() {
        for (IInteraction interaction : InteractionHelperClient.INTERACTIONS) {
            if (interaction.isValid())
                interaction.execute();
        }
    }
}
