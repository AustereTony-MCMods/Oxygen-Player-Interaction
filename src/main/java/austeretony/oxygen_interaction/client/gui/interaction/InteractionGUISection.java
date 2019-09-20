package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.panel.GUIButtonPanel;
import austeretony.alternateui.screen.text.GUITextLabel;
import austeretony.alternateui.util.EnumGUIOrientation;
import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_core.client.gui.IndexedGUIButton;
import austeretony.oxygen_core.client.gui.settings.GUISettings;
import austeretony.oxygen_core.client.interaction.InteractionHelperClient;
import austeretony.oxygen_core.client.interaction.InteractionMenuEntry;

public class InteractionGUISection extends AbstractGUISection {

    private final InteractionMenuGUIScreen screen;

    private GUIButtonPanel actionsPanel;

    public InteractionGUISection(InteractionMenuGUIScreen screen) {
        super(screen);
        this.screen = screen;
    }

    @Override
    public void init() {
        this.addElement(new InteractionMenuGUIFiller(0, 0, this.getWidth(), this.getHeight()));
        String username = OxygenHelperClient.getPlayerUsername();
        this.addElement(new GUITextLabel(- this.textWidth(username, GUISettings.get().getTitleScale()) - 6, this.getHeight() / 2 - 3).setDisplayText(username, true, GUISettings.get().getTitleScale()));
        this.addElement(this.actionsPanel = new GUIButtonPanel(EnumGUIOrientation.VERTICAL, 0, 0, 90, 14).setButtonsOffset(1).setTextScale(GUISettings.get().getTextScale()).enableTextShadow());
    }

    private void initActions() {
        for (InteractionMenuEntry executor : InteractionHelperClient.MENU_ENTRIES)
            this.actionsPanel.addButton(new ActionGUIButton(executor).setEnabled(executor.isValid(this.screen.playerUUID)));
        this.actionsPanel.addButton(new ActionGUIButton(new CloseMenuInteractionExecutor()));
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element, int mouseButton) {
        if (mouseButton == 0 && element instanceof IndexedGUIButton) {
            ((IndexedGUIButton<InteractionMenuEntry>) element).index.execute(this.screen.playerUUID);
            this.screen.close();
        }
    }

    public void sharedDataSynchronized() {
        this.initActions();
    }
}
