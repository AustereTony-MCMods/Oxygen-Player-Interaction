package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.oxygen.client.gui.BackgroundGUIFiller;
import austeretony.oxygen.client.gui.settings.GUISettings;

public class InteractionGUIFiller extends BackgroundGUIFiller {

    public InteractionGUIFiller(int xPosition, int yPosition, int width, int height) {             
        super(xPosition, yPosition, width, height, InteractionGUIScreen.INTERACTION_MENU_BACKGROUND);
    }

    @Override
    public void drawDefaultBackground() {
        drawCircle(60, 58, 50, 42, 68, GUISettings.instance().getBaseGUIBackgroundColor());
        drawCircle(60, 58, 50, 43, 67, GUISettings.instance().getAdditionalGUIBackgroundColor());
    }
}
