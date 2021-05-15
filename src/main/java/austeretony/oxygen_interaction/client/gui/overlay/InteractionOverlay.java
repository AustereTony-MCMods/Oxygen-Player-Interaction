package austeretony.oxygen_interaction.client.gui.overlay;

import austeretony.oxygen_core.client.api.OxygenClient;
import austeretony.oxygen_core.client.gui.base.GUIUtils;
import austeretony.oxygen_core.client.interaction.EntityInteraction;
import austeretony.oxygen_core.client.interaction.InteractionHelper;
import austeretony.oxygen_core.client.settings.CoreSettings;
import austeretony.oxygen_core.client.util.MinecraftClient;
import austeretony.oxygen_interaction.common.config.InteractionConfig;
import austeretony.oxygen_interaction.common.main.InteractionMain;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nullable;
import java.util.Collection;

public class InteractionOverlay {

    private boolean draw;
    private String name, interactStr;
    @Nullable
    private String profession;

    private ScaledResolution sr;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            update();
        }
    }

    @SubscribeEvent
    public void onOverlayRendering(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && !MinecraftClient.hasActiveGUI()) {
            drawHUD(event.getPartialTicks());
        }
    }

    private void update() {
        EntityPlayer player = MinecraftClient.getPlayer();
        if (player == null || player.ticksExisted % 10 != 0) return;
        draw = false;

        KeyBinding keyBinding = OxygenClient.getKeyBinding(InteractionMain.KEYBINDING_ID_INTERACT);
        if (keyBinding == null) return;
        interactStr = String.format("[%s] %s", GUIUtils.getKeyDisplayString(keyBinding.getKeyCode()), keyBinding.getKeyDescription());
        sr = GUIUtils.getScaledResolution();

        Entity entity = MinecraftClient.getPointedEntity();
        float maxDistance = InteractionConfig.INTERACTION_DISTANCE.asFloat();
        if (entity != null && MinecraftClient.getDistanceBetween(entity, MinecraftClient.getPlayer()) < maxDistance) {
            Collection<EntityInteraction> interactions = InteractionHelper.getInteractionsMap().values();
            for (EntityInteraction interaction : interactions) {
                if (interaction.isValid(entity)) {
                    name = interaction.getDisplayName(entity);
                    profession = interaction.getDisplayProfession(entity);
                    draw = true;
                }
            }
        }
    }

    private void drawHUD(float partialTicks) {
        if (!draw) return;

        int x = (int) (sr.getScaledWidth() / 2F + 10);
        int y = (int) (sr.getScaledHeight() / 2F);
        float scale = CoreSettings.SCALE_OVERLAY.asFloat();

        GUIUtils.pushMatrix();
        GUIUtils.translate(x, y);
        GUIUtils.scale(scale, scale);

        int InteractStrY = 10;
        GUIUtils.drawString(name, 0, 0, CoreSettings.SCALE_TEXT_OVERLAY.asFloat(),
                CoreSettings.COLOR_OVERLAY_TEXT_ADDITIONAL.asInt(), true);
        if (profession != null) {
            InteractStrY += 8;
            GUIUtils.drawString(profession, 0, 8, CoreSettings.SCALE_TEXT_OVERLAY.asFloat() - .05F,
                    CoreSettings.COLOR_OVERLAY_TEXT_BASE.asInt(), true);
        }

        GUIUtils.drawString(interactStr, 2, InteractStrY, CoreSettings.SCALE_TEXT_OVERLAY.asFloat() - .05F,
                CoreSettings.COLOR_OVERLAY_TEXT_ADDITIONAL.asInt(), true);

        GUIUtils.popMatrix();
    }
}
