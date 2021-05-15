package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.oxygen_core.client.api.OxygenClient;
import austeretony.oxygen_core.client.gui.base.core.OxygenScreen;
import austeretony.oxygen_core.client.gui.base.core.Section;
import austeretony.oxygen_core.client.gui.base.core.Workspace;
import austeretony.oxygen_core.client.interaction.InteractionHelper;
import austeretony.oxygen_core.client.util.MinecraftClient;
import austeretony.oxygen_interaction.common.main.InteractionMain;

import java.util.UUID;

public class PlayerInteractionScreen extends OxygenScreen {

    public static final int MENU_ENTRY_HEIGHT = 10;

    private final UUID playerUUID;
    private final String playerUsername;

    private PlayerInteractionSection section;

    private PlayerInteractionScreen(UUID playerUUID, String playerUsername) {
        this.playerUUID = playerUUID;
        this.playerUsername = playerUsername;
    }

    @Override
    public void initGui() {
        super.initGui();
        OxygenClient.requestSharedDataSync(InteractionMain.SCREEN_ID_PLAYER_INTERACTION, false);
    }

    @Override
    public int getScreenId() {
        return InteractionMain.SCREEN_ID_PLAYER_INTERACTION;
    }

    @Override
    public Workspace createWorkspace() {
        int entriesAmount = InteractionHelper.getPlayerInteractionsMap().size();
        int height = MENU_ENTRY_HEIGHT + 1 + entriesAmount * MENU_ENTRY_HEIGHT + (entriesAmount - 1);
        return new Workspace(this, 100, height);
    }

    @Override
    public void addSections() {
        getWorkspace().addSection(section = new PlayerInteractionSection(this));
    }

    @Override
    public Section getDefaultSection() {
        return section;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public static void open(UUID playerUUID, String playerUsername) {
        MinecraftClient.displayGuiScreen(new PlayerInteractionScreen(playerUUID, playerUsername));
    }
}
