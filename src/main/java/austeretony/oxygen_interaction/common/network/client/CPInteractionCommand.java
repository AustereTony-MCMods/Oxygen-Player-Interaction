package austeretony.oxygen_interaction.common.network.client;

import austeretony.oxygen.common.network.ProxyPacket;
import austeretony.oxygen_interaction.client.InteractionManagerClient;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;

public class CPInteractionCommand extends ProxyPacket {

    private EnumCommand command;

    public CPInteractionCommand() {}

    public CPInteractionCommand(EnumCommand command) {
        this.command = command;
    }

    @Override
    public void write(PacketBuffer buffer, INetHandler netHandler) {
        buffer.writeByte(this.command.ordinal());
    }

    @Override
    public void read(PacketBuffer buffer, INetHandler netHandler) {
        this.command = EnumCommand.values()[buffer.readByte()];
        switch (this.command) {
        case OPEN_PLAYER_INTERACTION_MENU:
            InteractionManagerClient.openPlayerInteractionMenuDelegated();
            break;
        }
    }

    public enum EnumCommand {

        OPEN_PLAYER_INTERACTION_MENU
    }
}
