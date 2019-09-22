package austeretony.oxygen_interaction.client.gui.interaction;

import java.util.UUID;

import austeretony.alternateui.screen.core.AbstractGUIScreen;
import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.core.GUIWorkspace;
import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_core.client.interaction.InteractionHelperClient;
import austeretony.oxygen_interaction.common.main.InteractionMain;

public class InteractionMenuGUIScreen extends AbstractGUIScreen {

    public final UUID playerUUID;

    protected InteractionGUISection interactionSection;

    public InteractionMenuGUIScreen(UUID playerUUID) {
        OxygenHelperClient.syncSharedData(InteractionMain.PLAYER_INTERACTION_MENU_SCREEN_ID);
        
        this.playerUUID = playerUUID;
    }

    @Override
    protected GUIWorkspace initWorkspace() {
        int amount = InteractionHelperClient.MENU_ENTRIES.size();
        return new GUIWorkspace(this, 90, 15 + amount * 14 + (amount - 1)).setAlignment(EnumGUIAlignment.CENTER, 45, 0);
    }

    @Override
    protected void initSections() {
        this.getWorkspace().initSection(this.interactionSection = new InteractionGUISection(this));        
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
