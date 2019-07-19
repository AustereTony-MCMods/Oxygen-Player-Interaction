package austeretony.oxygen_interaction.client.gui.overlay;

import austeretony.alternateui.screen.core.GUISimpleElement;
import austeretony.oxygen.client.core.api.ClientReference;
import austeretony.oxygen.client.gui.settings.GUISettings;
import austeretony.oxygen.client.input.OxygenKeyHandler;
import austeretony.oxygen.client.interaction.IInteractionOverlay;
import austeretony.oxygen.common.config.OxygenConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerInteractionOverlay implements IInteractionOverlay {

    private Entity pointed;

    @Override
    public boolean isValid() {
        this.pointed = ClientReference.getPointedEntity();
        return this.pointed != null 
                && this.pointed instanceof EntityPlayer
                && ClientReference.isEntitiesNear(this.pointed, ClientReference.getClientPlayer(), 3.0D);
    }

    @Override
    public void draw(float partialTicks) {
        Minecraft mc = ClientReference.getMinecraft();
        ScaledResolution scaledResolution = new ScaledResolution(mc);  
        String interactionKeyName = OxygenKeyHandler.INTERACT.getDisplayName();
        int 
        x = scaledResolution.getScaledWidth() / 2 - 20,
        y = scaledResolution.getScaledHeight() / 2 + 10,
        keyNameWidth = mc.fontRenderer.getStringWidth(interactionKeyName);

        GlStateManager.pushMatrix();    
        GlStateManager.translate(x, y, 0.0F);          
        GlStateManager.scale(GUISettings.instance().getOverlayScale(), GUISettings.instance().getOverlayScale(), 0.0F);         

        mc.fontRenderer.drawString(this.pointed.getName(), 0, 0, GUISettings.instance().getAdditionalOverlayTextColor(), true);

        if (!OxygenConfig.INTERACT_WITH_RMB.getBooleanValue()) {
            GUISimpleElement.drawRect(0, 12, keyNameWidth + 6, 24, GUISettings.instance().getBaseGUIBackgroundColor());
            GUISimpleElement.drawRect(1, 13, keyNameWidth + 5, 23, GUISettings.instance().getAdditionalGUIBackgroundColor());
            mc.fontRenderer.drawString(interactionKeyName, 3, 15, GUISettings.instance().getAdditionalOverlayTextColor());
            mc.fontRenderer.drawString(ClientReference.localize(OxygenKeyHandler.INTERACT.getKeyDescription()), 10 + keyNameWidth, 15, GUISettings.instance().getBaseOverlayTextColor(), true);
        } else
            mc.fontRenderer.drawString(ClientReference.localize(OxygenKeyHandler.INTERACT.getKeyDescription()), 0, 15, GUISettings.instance().getBaseOverlayTextColor(), true);

        GlStateManager.popMatrix();
    }
}
