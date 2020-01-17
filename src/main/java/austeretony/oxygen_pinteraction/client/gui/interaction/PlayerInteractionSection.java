package austeretony.oxygen_pinteraction.client.gui.interaction;

import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.oxygen_core.client.api.EnumBaseGUISetting;
import austeretony.oxygen_core.client.api.PlayerInteractionMenuHelper;
import austeretony.oxygen_core.client.gui.elements.OxygenScrollablePanel;
import austeretony.oxygen_core.client.interaction.PlayerInteractionMenuEntry;

public class PlayerInteractionSection extends AbstractGUISection {

    private final PlayerInteractionScreen screen;

    private OxygenScrollablePanel actionsPanel;

    public PlayerInteractionSection(PlayerInteractionScreen screen) {
        super(screen);
        this.screen = screen;
    }

    @Override
    public void init() {
        this.addElement(new InteractionMenuBackgroundFiller(0, 0, this.getWidth(), this.getHeight()));

        int amount = PlayerInteractionMenuHelper.MENU_ENTRIES.size() + 1;
        this.addElement(this.actionsPanel = new OxygenScrollablePanel(this.screen, 0, 0, 80, 12, 1, amount, amount, EnumBaseGUISetting.TEXT_SCALE.get().asFloat(), false));

        this.actionsPanel.<ActionPanelEntry>setClickListener((previous, clicked, mouseX, mouseY, mouseButton)->{
            if (mouseButton == 0) {
                this.screen.close();
                clicked.index.execute(this.screen.playerUUID);
            }
        });
    }

    private void initActions() {
        for (PlayerInteractionMenuEntry entry : PlayerInteractionMenuHelper.MENU_ENTRIES)
            this.actionsPanel.addEntry(new ActionPanelEntry(entry).setEnabled(entry.isValid(this.screen.playerUUID)));
        this.actionsPanel.addEntry(new ActionPanelEntry(new CancelMenuEntry()).enableFull());
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element, int mouseButton) {}

    public void sharedDataSynchronized() {
        this.initActions();
    }
}
