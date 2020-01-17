package austeretony.oxygen_pinteraction.client.gui.interaction;

import java.util.UUID;

import austeretony.alternateui.screen.core.AbstractGUIScreen;
import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.core.GUIWorkspace;
import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_core.client.api.PlayerInteractionMenuHelper;
import austeretony.oxygen_pinteraction.common.main.PlayerInteractionMain;

public class PlayerInteractionScreen extends AbstractGUIScreen {

    public final UUID playerUUID;

    protected PlayerInteractionSection interactionSection;

    public PlayerInteractionScreen(UUID playerUUID) {
        OxygenHelperClient.syncSharedData(PlayerInteractionMain.PLAYER_INTERACTION_MENU_SCREEN_ID);

        this.playerUUID = playerUUID;
    }

    @Override
    protected GUIWorkspace initWorkspace() {
        int amount = PlayerInteractionMenuHelper.MENU_ENTRIES.size();
        return new GUIWorkspace(this, 80, 13 + amount * 12 + (amount - 1)).setAlignment(EnumGUIAlignment.CENTER, 40, 0);
    }

    @Override
    protected void initSections() {
        this.getWorkspace().initSection(this.interactionSection = new PlayerInteractionSection(this));        
    }

    @Override
    protected AbstractGUISection getDefaultSection() {
        return this.interactionSection;
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element) {}

    @Override
    protected boolean doesGUIPauseGame() {
        return false;
    }

    public void sharedDataSynchronized() {
        this.interactionSection.sharedDataSynchronized();
    }
}
