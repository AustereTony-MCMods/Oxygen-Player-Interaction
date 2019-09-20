package austeretony.oxygen_interaction.client;

import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.sync.shared.SharedDataSyncManagerClient.SharedDataSyncListener;
import austeretony.oxygen_interaction.client.gui.interaction.InteractionMenuGUIScreen;

public class InteractionMenuSharedDataListener implements SharedDataSyncListener {

    @Override
    public void synced() {
        ClientReference.delegateToClientThread(()->{
            if (ClientReference.hasActiveGUI() && ClientReference.getCurrentScreen() instanceof InteractionMenuGUIScreen)
                ((InteractionMenuGUIScreen) ClientReference.getCurrentScreen()).sharedDataSynchronized();
        });
    }
}
