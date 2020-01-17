package austeretony.oxygen_pinteraction.client.gui.interaction;

import austeretony.alternateui.screen.core.GUISimpleElement;
import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.api.EnumBaseGUISetting;
import austeretony.oxygen_core.client.gui.OxygenGUIUtils;
import net.minecraft.client.renderer.GlStateManager;

public class InteractionMenuBackgroundFiller extends GUISimpleElement<InteractionMenuBackgroundFiller> {

    public InteractionMenuBackgroundFiller(int xPosition, int yPosition, int width, int height) {             
        this.setPosition(xPosition, yPosition);         
        this.setSize(width, height);
        this.setDynamicBackgroundColor(EnumBaseGUISetting.BACKGROUND_BASE_COLOR.get().asInt(), 0, 0);
    }

    @Override
    public void draw(int mouseX, int mouseY) {  
        GlStateManager.pushMatrix();            
        GlStateManager.translate(this.getX(), this.getY(), 0.0F);            
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);   

        OxygenGUIUtils.drawGradientRect(0.0D, 0.0D, this.getWidth(), this.getHeight(), 0x00000000, this.getEnabledBackgroundColor(), EnumGUIAlignment.LEFT);

        GlStateManager.popMatrix();            
    }
}
