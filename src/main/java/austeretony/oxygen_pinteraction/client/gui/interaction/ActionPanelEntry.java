package austeretony.oxygen_pinteraction.client.gui.interaction;

import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.api.EnumBaseClientSetting;
import austeretony.oxygen_core.client.api.EnumBaseGUISetting;
import austeretony.oxygen_core.client.gui.OxygenGUIUtils;
import austeretony.oxygen_core.client.gui.elements.OxygenWrapperPanelEntry;
import austeretony.oxygen_core.client.interaction.PlayerInteractionMenuEntry;
import austeretony.oxygen_core.common.sound.OxygenSoundEffects;
import net.minecraft.client.renderer.GlStateManager;

public class ActionPanelEntry extends OxygenWrapperPanelEntry<PlayerInteractionMenuEntry> {

    public ActionPanelEntry(PlayerInteractionMenuEntry entry) {
        super(entry);
        this.setDisplayText(entry.getLocalizedName());
        this.setDynamicBackgroundColor(EnumBaseGUISetting.ELEMENT_ENABLED_COLOR.get().asInt(), EnumBaseGUISetting.ELEMENT_DISABLED_COLOR.get().asInt(), EnumBaseGUISetting.ELEMENT_HOVERED_COLOR.get().asInt());
        this.setTextDynamicColor(EnumBaseGUISetting.TEXT_ENABLED_COLOR.get().asInt(), EnumBaseGUISetting.TEXT_DISABLED_COLOR.get().asInt(), EnumBaseGUISetting.TEXT_HOVERED_COLOR.get().asInt());
        if (EnumBaseClientSetting.ENABLE_SOUND_EFFECTS.get().asBoolean())
            this.setSound(OxygenSoundEffects.BUTTON_CLICK.getSoundEvent());
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
            textColor = this.getEnabledTextColor();                      
            if (!this.isEnabled()) {                 
                color = this.getDisabledBackgroundColor();
                textColor = this.getDisabledTextColor(); 
            } else if (this.isHovered() || this.isToggled()) {                 
                color = this.getHoveredBackgroundColor();
                textColor = this.getHoveredTextColor();
            }

            OxygenGUIUtils.drawGradientRect(0.0D, 0.0D, this.getWidth(), this.getHeight(), 0x00000000, color, EnumGUIAlignment.LEFT);

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.pushMatrix();           
            GlStateManager.translate(4.0F, (this.getHeight() - this.textHeight(this.getTextScale())) / 2.0F + 1.0F, 0.0F); 
            GlStateManager.scale(this.getTextScale(), this.getTextScale(), 0.0F); 
            this.mc.fontRenderer.drawString(this.getDisplayText(), 0, 0, textColor, this.isTextShadowEnabled());
            GlStateManager.popMatrix();

            GlStateManager.popMatrix();
        }
    }
}
