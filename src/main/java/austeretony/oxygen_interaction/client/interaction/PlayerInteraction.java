package austeretony.oxygen_interaction.client.interaction;

import austeretony.oxygen_core.client.interaction.EntityInteraction;
import austeretony.oxygen_core.client.util.MinecraftClient;
import austeretony.oxygen_interaction.client.gui.interaction.PlayerInteractionScreen;
import austeretony.oxygen_interaction.common.main.InteractionMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;

public class PlayerInteraction implements EntityInteraction {

    @Override
    public int getId() {
        return InteractionMain.INTERACTION_PLAYER;
    }

    @Override
    public boolean isValid(Entity entity) {
        return entity instanceof EntityPlayer;
    }

    @Override
    public String getDisplayName(Entity entity) {
        return MinecraftClient.getEntityName(entity);
    }

    @Nullable
    @Override
    public String getDisplayProfession(Entity entity) {
        return null;
    }

    @Override
    public void process(Entity entity) {
        PlayerInteractionScreen.open(MinecraftClient.getEntityUUID(entity), MinecraftClient.getEntityName(entity));
    }
}
