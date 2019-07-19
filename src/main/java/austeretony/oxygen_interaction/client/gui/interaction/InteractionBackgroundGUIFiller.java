package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.alternateui.screen.core.GUIAdvancedElement;
import austeretony.oxygen.client.core.api.ClientReference;
import austeretony.oxygen.client.gui.BackgroundGUIFiller;
import austeretony.oxygen.client.gui.settings.GUISettings;
import net.minecraft.client.renderer.GlStateManager;

public class InteractionBackgroundGUIFiller extends BackgroundGUIFiller {

    private final boolean textureExist;

    public InteractionBackgroundGUIFiller(int xPosition, int yPosition, int width, int height) {             
        super(xPosition, yPosition, width, height);
        this.textureExist = ClientReference.isTextureExist(InteractionGUIScreen.BACKGROUND_TEXTURE);
    }

    @Override
    public void draw(int mouseX, int mouseY) {  
        GlStateManager.pushMatrix();            
        GlStateManager.translate(this.getX(), this.getY(), 0.0F);            
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);                      
        if (this.textureExist) {  
            GlStateManager.enableBlend();    
            this.mc.getTextureManager().bindTexture(InteractionGUIScreen.BACKGROUND_TEXTURE);                         
            GUIAdvancedElement.drawCustomSizedTexturedRect( - GUISettings.instance().getTextureOffsetX(), - GUISettings.instance().getTextureOffsetY(), 
                    0, 0, this.getTextureWidth(), this.getTextureHeight(), this.getTextureWidth(), this.getTextureHeight());             
            GlStateManager.disableBlend();   
        } else {                
            drawCircle(60, 58, 50, 42, 68, GUISettings.instance().getBaseGUIBackgroundColor());
            drawCircle(60, 58, 50, 43, 67, GUISettings.instance().getAdditionalGUIBackgroundColor());
        }
        GlStateManager.popMatrix();            
    }
}
