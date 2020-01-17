package austeretony.oxygen_pinteraction.client;

import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.shared.SharedDataSyncManagerClient.SharedDataSyncListener;
import austeretony.oxygen_pinteraction.client.gui.interaction.PlayerInteractionScreen;

public class InteractionMenuSharedDataListener implements SharedDataSyncListener {

    @Override
    public void synced() {
        ClientReference.delegateToClientThread(()->{
            if (ClientReference.hasActiveGUI() && ClientReference.getCurrentScreen() instanceof PlayerInteractionScreen)
                ((PlayerInteractionScreen) ClientReference.getCurrentScreen()).sharedDataSynchronized();
        });
    }
}
