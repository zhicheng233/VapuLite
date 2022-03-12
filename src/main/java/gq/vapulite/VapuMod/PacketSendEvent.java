package gq.vapulite.VapuMod;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import gq.vapulite.eventapi.events.callables.EventCancellable;

public class PacketSendEvent extends EventCancellable {
    
    
	public Packet packet;

    public PacketSendEvent(Packet packet) {
        this.packet = packet;
        if (packet instanceof C03PacketPlayer.C05PacketPlayerLook || packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
            float yaw = ((C03PacketPlayer) packet).getYaw();
            float pitch = ((C03PacketPlayer) packet).getPitch();
//            RotationUtils.serverRotation.setYaw(yaw);
//            RotationUtils.serverRotation.setPitch(pitch);
        }
    }

	public Packet getPacket() {
		// TODO 自动生成的方法存根
		return packet;
	}
}
