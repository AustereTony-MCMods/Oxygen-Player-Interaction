package austeretony.oxygen_interaction.client;

import austeretony.oxygen_core.client.interaction.EntityInteraction;
import austeretony.oxygen_core.client.interaction.InteractionHelper;
import austeretony.oxygen_core.client.util.MinecraftClient;
import austeretony.oxygen_interaction.common.config.InteractionConfig;
import net.minecraft.entity.Entity;

import java.util.Collection;

public final class InteractionManagerClient {

    private static InteractionManagerClient instance;

    private InteractionManagerClient() {}

    public static InteractionManagerClient instance() {
        if (instance == null) {
            instance = new InteractionManagerClient();
        }
        return instance;
    }

    public void tryProcessInteraction() {
        Entity entity = MinecraftClient.getPointedEntity();
        float maxDistance = InteractionConfig.INTERACTION_DISTANCE.asFloat();
        if (entity != null && MinecraftClient.getDistanceBetween(entity, MinecraftClient.getPlayer()) < maxDistance) {
            Collection<EntityInteraction> interactions = InteractionHelper.getInteractionsMap().values();
            for (EntityInteraction interaction : interactions) {
                if (interaction.isValid(entity)) {
                    interaction.process(entity);
                }
            }
        }
    }
}
