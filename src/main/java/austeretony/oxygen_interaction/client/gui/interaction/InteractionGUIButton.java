package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.alternateui.screen.button.GUIButton;
import austeretony.oxygen.client.interaction.IInteractionMenuExecutor;

public class InteractionGUIButton extends GUIButton {

    public final IInteractionMenuExecutor executor;

    public InteractionGUIButton(int x, int y, int width, int height, IInteractionMenuExecutor executor) {
        super(x, y, width, height);
        this.executor = executor;
    }
}
