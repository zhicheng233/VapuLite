package gq.vapulite.VapuMod;

import net.minecraft.network.Packet;
import gq.vapulite.eventapi.events.callables.EventCancellable;

public class PacketReceiveEvent extends EventCancellable {
	public Packet packet;
	public PacketReceiveEvent(Packet packet) {
		this.packet = packet;
	}

	public Packet getPacket() {
		return packet;
	}

	public void setPacket(Packet packet) {
		this.packet = packet;
	}
}
