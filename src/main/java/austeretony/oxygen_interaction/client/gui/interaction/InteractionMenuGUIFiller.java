package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.oxygen.client.gui.BackgroundGUIFiller;
import austeretony.oxygen.client.gui.settings.GUISettings;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class InteractionMenuGUIFiller extends BackgroundGUIFiller {

    public InteractionMenuGUIFiller(int xPosition, int yPosition, int width, int height) {             
        super(xPosition, yPosition, width, height, InteractionMenuGUIScreen.INTERACTION_MENU_BACKGROUND);
    }

    @Override
    public void drawDefaultBackground() {
        drawGradient(0, 0, this.getWidth(), this.getHeight(), 0x00000000, GUISettings.instance().getBaseGUIBackgroundColor());
    }

    public static void drawGradient(int xStart, int yStart, int xEnd, int yEnd, int colorDec1, int colorDec2) {         
        float 
        alpha1 = (float) (colorDec1 >> 24 & 255) / 255.0F,
        red1 = (float) (colorDec1 >> 16 & 255) / 255.0F,
        green1 = (float) (colorDec1 >> 8 & 255) / 255.0F,
        blue1 = (float) (colorDec1 & 255) / 255.0F,
        alpha2 = (float) (colorDec2 >> 24 & 255) / 255.0F,
        red2 = (float) (colorDec2 >> 16 & 255) / 255.0F,
        green2 = (float) (colorDec2 >> 8 & 255) / 255.0F,
        blue2 = (float) (colorDec2 & 255) / 255.0F;   

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);  

        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double) xStart, (double) yEnd, 0.0D).color(red2, green2, blue2, alpha2).endVertex();
        bufferBuilder.pos((double) xEnd, (double) yEnd, 0.0D).color(red1, green1, blue1, alpha1).endVertex();
        bufferBuilder.pos((double) xEnd, (double) yStart, 0.0D).color(red1, green1, blue1, alpha1).endVertex();
        bufferBuilder.pos((double) xStart, (double) yStart, 0.0D).color(red2, green2, blue2, alpha2).endVertex();
        tessellator.draw();

        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    } 
}