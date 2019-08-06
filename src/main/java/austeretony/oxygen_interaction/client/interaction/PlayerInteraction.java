package austeretony.oxygen_interaction.client.interaction;

import austeretony.oxygen.client.api.OxygenGUIHelper;
import austeretony.oxygen.client.core.api.ClientReference;
import austeretony.oxygen.client.interaction.IInteraction;
import austeretony.oxygen_interaction.common.main.InteractionMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerInteraction implements IInteraction {

    @Override
    public boolean isValid() {
        Entity pointed = ClientReference.getPointedEntity();
        return pointed != null && pointed instanceof EntityPlayer;
    }

    @Override
    public void execute() {
        OxygenGUIHelper.openSharedDataListenerScreen(InteractionMain.PLAYER_INTERACTION_MENU_SCREEN_ID);
    }
}
