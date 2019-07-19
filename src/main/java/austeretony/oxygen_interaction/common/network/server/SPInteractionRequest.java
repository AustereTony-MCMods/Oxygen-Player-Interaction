package austeretony.oxygen_interaction.common.network.server;

import java.util.UUID;

import austeretony.oxygen.common.OxygenManagerServer;
import austeretony.oxygen.common.api.OxygenHelperServer;
import austeretony.oxygen.common.core.api.CommonReference;
import austeretony.oxygen.common.main.OxygenMain;
import austeretony.oxygen.common.network.ProxyPacket;
import austeretony.oxygen_interaction.common.main.InteractionMain;
import austeretony.oxygen_interaction.common.network.client.CPInteractionCommand;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;

public class SPInteractionRequest extends ProxyPacket {

    private EnumRequest request;

    public SPInteractionRequest() {}

    public SPInteractionRequest(EnumRequest request) {
        this.request = request;
    }

    @Override
    public void write(PacketBuffer buffer, INetHandler netHandler) {
        buffer.writeByte(this.request.ordinal());
    }

    @Override
    public void read(PacketBuffer buffer, INetHandler netHandler) {
        EntityPlayerMP playerMP = getEntityPlayerMP(netHandler);
        UUID playerUUID = CommonReference.getPersistentUUID(playerMP);
        this.request = EnumRequest.values()[buffer.readByte()];
        switch (this.request) {
        case OPEN_PLAYER_INTERACTION_MENU:
            if (!OxygenHelperServer.isSyncing(playerUUID)) {
                OxygenManagerServer.instance().syncSharedPlayersData(playerMP, OxygenHelperServer.getSharedDataIdentifiersForScreen(InteractionMain.PLAYER_INTERACTION_MENU_SCREEN_ID));
                OxygenMain.network().sendTo(new CPInteractionCommand(CPInteractionCommand.EnumCommand.OPEN_PLAYER_INTERACTION_MENU), playerMP);
            }
            break;
        }
    }

    public enum EnumRequest {

        OPEN_PLAYER_INTERACTION_MENU
    }
}
