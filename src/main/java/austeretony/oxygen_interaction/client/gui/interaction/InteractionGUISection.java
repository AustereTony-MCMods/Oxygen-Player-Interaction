package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.alternateui.screen.button.GUIButton;
import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.panel.GUIButtonPanel;
import austeretony.alternateui.screen.text.GUITextLabel;
import austeretony.alternateui.util.EnumGUIOrientation;
import austeretony.oxygen.client.api.OxygenHelperClient;
import austeretony.oxygen.client.gui.IndexedGUIButton;
import austeretony.oxygen.client.gui.settings.GUISettings;
import austeretony.oxygen.client.interaction.IInteractionMenuExecutor;
import austeretony.oxygen.client.interaction.InteractionHelperClient;

public class InteractionGUISection extends AbstractGUISection {

    private final InteractionMenuGUIScreen screen;

    private GUITextLabel actionNameTextLabel;

    private GUIButton lastHoveredButton;

    private GUIButtonPanel actionsPanel;

    public InteractionGUISection(InteractionMenuGUIScreen screen) {
        super(screen);
        this.screen = screen;
    }

    @Override
    public void init() {
        this.addElement(new InteractionMenuGUIFiller(0, 0, this.getWidth(), this.getHeight()));
        String username = OxygenHelperClient.getSharedPlayerData(this.screen.playerUUID).getUsername();
        this.addElement(new GUITextLabel(- this.textWidth(username, GUISettings.instance().getTitleScale()) - 6, this.getHeight() / 2 - 3).setDisplayText(username, true, GUISettings.instance().getTitleScale()));
        this.addElement(this.actionsPanel = new GUIButtonPanel(EnumGUIOrientation.VERTICAL, 0, 0, 90, 14).setButtonsOffset(1).setTextScale(GUISettings.instance().getTextScale()).enableTextShadow());
    }

    public void initActions() {
        for (IInteractionMenuExecutor executor : InteractionHelperClient.MENU_ACTIONS)
            this.actionsPanel.addButton(new ActionGUIButton(executor).setEnabled(executor.isValid(this.screen.playerUUID)));
        this.actionsPanel.addButton(new ActionGUIButton(new CloseMenuInteractionExecutor()));
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element, int mouseButton) {
        if (element instanceof IndexedGUIButton) {
            ((IndexedGUIButton<IInteractionMenuExecutor>) element).index.execute(this.screen.playerUUID);
            this.screen.close();
        }
    }
}
