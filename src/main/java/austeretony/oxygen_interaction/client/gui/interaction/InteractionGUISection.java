package austeretony.oxygen_interaction.client.gui.interaction;

import java.util.HashSet;
import java.util.Set;

import austeretony.alternateui.screen.button.GUIButton;
import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.text.GUITextLabel;
import austeretony.oxygen.client.api.OxygenHelperClient;
import austeretony.oxygen.client.core.api.ClientReference;
import austeretony.oxygen.client.gui.IndexedGUIButton;
import austeretony.oxygen.client.gui.OxygenGUITextures;
import austeretony.oxygen.client.gui.settings.GUISettings;
import austeretony.oxygen.client.input.OxygenKeyHandler;
import austeretony.oxygen.client.interaction.IInteractionMenuExecutor;
import austeretony.oxygen.client.interaction.InteractionHelperClient;
import austeretony.oxygen.common.main.OxygenSoundEffects;

public class InteractionGUISection extends AbstractGUISection {

    public static final int MAX_MENU_ACTIONS_AMOUNT = 6;

    private final InteractionGUIScreen screen;

    private final Set<GUIButton> buttons = new HashSet<GUIButton>(MAX_MENU_ACTIONS_AMOUNT + 1);

    private GUITextLabel actionNameTextLabel;

    private GUIButton lastHoveredButton;

    public InteractionGUISection(InteractionGUIScreen screen) {
        super(screen);
        this.screen = screen;
    }

    @Override
    public void init() {
        this.addElement(new InteractionGUIFiller(0, 0, this.getWidth(), this.getHeight()));
        String username = OxygenHelperClient.getSharedPlayerData(this.screen.getPlayerUUID()).getUsername();
        this.addElement(new GUITextLabel((this.getWidth() - this.textWidth(username, GUISettings.instance().getTitleScale())) / 2, 46, username).enableTextShadow());
        this.addElement(this.actionNameTextLabel = new GUITextLabel(0, 60).setVisible(false));
    }

    public void initActions() {
        int 
        counter = 0, 
        radius = 55,
        xStart = 52,
        yStart = 50,
        x, y;
        double 
        angleStart = 140.0F,//to make 'close' button placed at the bottom of circle.
        angle = 360.0D / (MAX_MENU_ACTIONS_AMOUNT + 1) + 0.5D;
        GUIButton button;
        //existing actions
        for (IInteractionMenuExecutor action : InteractionHelperClient.MENU_ACTIONS) {
            x = xStart + (int) (radius * Math.cos(Math.toRadians(angleStart + counter * angle)));
            y = yStart + (int) (radius * Math.sin(Math.toRadians(angleStart + counter * angle)));
            this.addElement(button = new IndexedGUIButton<IInteractionMenuExecutor>(action).setPosition(x, y).setSize(16, 16).setSound(OxygenSoundEffects.BUTTON_CLICK.soundEvent).setTexture(action.getIcon(), 16, 16).setEnabled(action.isValid(this.screen.getPlayerUUID())));
            this.buttons.add(button);
            counter++;
        }
        //undefined actions - dummy buttons just to create full circle
        if (counter < MAX_MENU_ACTIONS_AMOUNT) {
            for (int i = counter; i < MAX_MENU_ACTIONS_AMOUNT; i++) {
                x = xStart + (int) (radius * Math.cos(Math.toRadians(angleStart + counter * angle)));
                y = yStart + (int) (radius * Math.sin(Math.toRadians(angleStart + counter * angle)));
                this.addElement(button = new IndexedGUIButton<IInteractionMenuExecutor>(null).setPosition(x, y).setSize(16, 16).setTexture(OxygenGUITextures.UNDEFINED_ICONS, 16, 16).setEnabled(false));
                this.buttons.add(button);
                counter++;
            } 
        }
        //last action - close screen
        x = xStart + (int) (radius * Math.cos(Math.toRadians(angleStart + counter * angle)));   
        y = yStart + (int) (radius * Math.sin(Math.toRadians(angleStart + counter * angle)));
        this.addElement(button = new IndexedGUIButton<IInteractionMenuExecutor>(new CloseScreenInteractionExecutor()).setPosition(x, y).setSize(16, 16).setSound(OxygenSoundEffects.BUTTON_CLICK.soundEvent).setTexture(OxygenGUITextures.CROSS_ICONS, 16, 16));
        this.buttons.add(button);
    }

    @Override
    public void mouseOver(int mouseX, int mouseY) {
        super.mouseOver(mouseX, mouseY);
        for (GUIButton button : this.buttons) {
            if (button.isHovered()) {
                this.lastHoveredButton = button;
                this.actionNameTextLabel.setVisible(true);
                this.actionNameTextLabel.setDisplayText(ClientReference.localize(((IndexedGUIButton<IInteractionMenuExecutor>) button).index.getName()), true, GUISettings.instance().getTextScale());
                this.actionNameTextLabel.setX((this.getWidth() - this.textWidth(this.actionNameTextLabel.getDisplayText(), GUISettings.instance().getTextScale())) / 2);
            }
        }
        if (this.lastHoveredButton != null && !this.lastHoveredButton.isHovered()) 
            this.actionNameTextLabel.setVisible(false);
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element, int mouseButton) {
        if (element instanceof IndexedGUIButton) {
            ((IndexedGUIButton<IInteractionMenuExecutor>) element).index.execute(this.screen.getPlayerUUID());
            this.screen.close();
        }
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {   
        if (keyCode == OxygenKeyHandler.INTERACT.getKeyCode())
            this.screen.close();
        return super.keyTyped(typedChar, keyCode); 
    }
}
