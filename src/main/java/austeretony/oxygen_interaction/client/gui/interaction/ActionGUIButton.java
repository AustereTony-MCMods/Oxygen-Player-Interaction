package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.oxygen.client.core.api.ClientReference;
import austeretony.oxygen.client.gui.IndexedGUIButton;
import austeretony.oxygen.client.gui.settings.GUISettings;
import austeretony.oxygen.client.interaction.IInteractionMenuExecutor;
import austeretony.oxygen.common.main.OxygenSoundEffects;
import net.minecraft.client.renderer.GlStateManager;

public class ActionGUIButton extends IndexedGUIButton<IInteractionMenuExecutor> {

    public ActionGUIButton(IInteractionMenuExecutor index) {
        super(index);
        this.setTexture(index.getIcon());
        this.setDisplayText(ClientReference.localize(index.getName()));
        this.enableDynamicBackground(GUISettings.instance().getEnabledElementColor(), GUISettings.instance().getEnabledElementColor(), GUISettings.instance().getHoveredElementColor());
        this.setSound(OxygenSoundEffects.BUTTON_CLICK.soundEvent);
        this.enableFull();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if (this.isVisible()) {
            GlStateManager.pushMatrix();           
            GlStateManager.translate(this.getX(), this.getY(), 0.0F);   
            GlStateManager.scale(this.getScale(), this.getScale(), 0.0F); 
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            int 
            color = this.getEnabledBackgroundColor(), 
            textColor = this.getEnabledTextColor(), 
            textY = (this.getHeight() - this.textHeight(this.getTextScale())) / 2,
            iconU = 0;                      
            if (!this.isEnabled()) {                 
                color = this.getDisabledBackgroundColor();
                textColor = this.getDisabledTextColor(); 
                iconU = 10;                      
            } else if (this.isHovered() || this.isToggled()) {                 
                color = this.getHoveredBackgroundColor();
                textColor = this.getHoveredTextColor();
                iconU = 20;                      
            }

            InteractionMenuGUIFiller.drawGradient(0, 0, this.getWidth(), this.getHeight(), 0x00000000, color);

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.pushMatrix();           
            GlStateManager.translate(14.0F, textY, 0.0F); 
            GlStateManager.scale(this.getTextScale(), this.getTextScale(), 0.0F); 
            this.mc.fontRenderer.drawString(this.getDisplayText(), 0, 0, textColor, this.isTextShadowEnabled());
            GlStateManager.popMatrix();

            GlStateManager.enableBlend(); 
            this.mc.getTextureManager().bindTexture(this.getTexture());                        
            drawCustomSizedTexturedRect(2, 2, iconU, 0, 10, 10, 30, 10);
            GlStateManager.disableBlend(); 

            GlStateManager.popMatrix();
        }
    }
}