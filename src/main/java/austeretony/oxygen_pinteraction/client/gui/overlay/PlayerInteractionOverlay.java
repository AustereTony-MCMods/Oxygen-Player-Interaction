package austeretony.oxygen_pinteraction.client.gui.overlay;

import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.api.EnumBaseClientSetting;
import austeretony.oxygen_core.client.api.EnumBaseGUISetting;
import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_core.client.gui.OxygenGUIUtils;
import austeretony.oxygen_core.client.gui.overlay.Overlay;
import austeretony.oxygen_core.common.config.OxygenConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerInteractionOverlay implements Overlay {

    Entity prev;

    //cache

    Minecraft mc;

    String keyName, playerStr, interactStr;

    boolean interactWithRMB;

    int x, y, baseOverlayTextColor, additionalOverlayTextColor, baseBackgroundColor, additionalBackgroundColor, keyNameWidth, frameWidth, frameHeight;

    float scale;

    @Override
    public boolean valid() {
        Entity pointed = ClientReference.getPointedEntity();
        if (pointed != null 
                && pointed instanceof EntityPlayer
                && ClientReference.isEntitiesNear(pointed, ClientReference.getClientPlayer(), 3.0D)) {
            if (this.prev == null || this.prev.getEntityId() != pointed.getEntityId())
                this.initOverlay();
            this.prev = pointed;
            return true;
        }
        return false;
    }

    @Override
    public boolean drawWhileInGUI() {
        return false;
    }

    private void initOverlay() {
        this.mc = ClientReference.getMinecraft();

        this.interactWithRMB = EnumBaseClientSetting.INTERACT_WITH_RMB.get().asBoolean();

        ScaledResolution scaledResolution = new ScaledResolution(this.mc);   
        this.x = scaledResolution.getScaledWidth() / 2 + 10;
        this.y = scaledResolution.getScaledHeight() / 2;

        this.baseOverlayTextColor = EnumBaseGUISetting.OVERLAY_TEXT_BASE_COLOR.get().asInt();
        this.additionalOverlayTextColor = EnumBaseGUISetting.OVERLAY_TEXT_ADDITIONAL_COLOR.get().asInt();
        this.baseBackgroundColor = EnumBaseGUISetting.BACKGROUND_BASE_COLOR.get().asInt();
        this.additionalBackgroundColor = EnumBaseGUISetting.BACKGROUND_ADDITIONAL_COLOR.get().asInt();

        this.scale = EnumBaseGUISetting.OVERLAY_SCALE.get().asFloat();

        if (OxygenConfig.ENABLE_INTERACTION_KEY.asBoolean())
            this.keyName = OxygenHelperClient.getKeyHandler().getInteractionKeybinding().getDisplayName();
        this.playerStr = ClientReference.localize("oxygen_interaction.gui.overlay.player");
        this.interactStr = ClientReference.localize("key.oxygen_core.interact");

        this.keyNameWidth = this.mc.fontRenderer.getStringWidth(this.keyName);
        this.frameWidth = this.keyNameWidth + 6;
        this.frameHeight = 12;
    }

    @Override
    public void draw(float partialTicks) {
        if (this.mc == null) return;

        GlStateManager.pushMatrix();    
        GlStateManager.translate(this.x, this.y, 0.0F);          
        GlStateManager.scale(this.scale, this.scale, 0.0F);         

        this.mc.fontRenderer.drawString(this.prev.getName(), 0, 0, this.additionalOverlayTextColor, true);

        this.mc.fontRenderer.drawString(this.playerStr, 0, 12, this.baseOverlayTextColor, true);

        if (!this.interactWithRMB) {
            OxygenGUIUtils.drawKeyFrame(0, 24, this.frameWidth, this.frameHeight, this.baseBackgroundColor, this.additionalBackgroundColor);

            this.mc.fontRenderer.drawString(this.keyName, 3, 27, this.additionalOverlayTextColor);
            this.mc.fontRenderer.drawString(this.interactStr, 10 + this.keyNameWidth, 27, this.additionalOverlayTextColor, true);
        } else
            this.mc.fontRenderer.drawString(this.interactStr, 0, 27, this.additionalOverlayTextColor, true);

        GlStateManager.popMatrix();
    }
}
