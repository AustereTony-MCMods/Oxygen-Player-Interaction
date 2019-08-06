package austeretony.oxygen_interaction.client.gui.interaction;

import java.util.UUID;

import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.core.GUIWorkspace;
import austeretony.oxygen.client.gui.SynchronizedGUIScreen;
import austeretony.oxygen.common.main.OxygenMain;
import austeretony.oxygen_interaction.common.main.InteractionMain;
import net.minecraft.util.ResourceLocation;

public class InteractionGUIScreen extends SynchronizedGUIScreen {

    public static final ResourceLocation INTERACTION_MENU_BACKGROUND = new ResourceLocation(OxygenMain.MODID, "textures/gui/interaction/interaction_menu.png");

    private final UUID playerUUID;

    protected InteractionGUISection interactionSection;

    public InteractionGUIScreen(UUID playerUUID) {
        super(InteractionMain.PLAYER_INTERACTION_MENU_SCREEN_ID);
        this.playerUUID = playerUUID;
    }

    @Override
    protected GUIWorkspace initWorkspace() {
        return new GUIWorkspace(this, 120, 120);
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

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }

    @Override
    public void loadData() {
        this.interactionSection.initActions();
    }
}
