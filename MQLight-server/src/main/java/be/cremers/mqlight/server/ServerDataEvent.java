package be.cremers.mqlight.server;

import java.nio.channels.SocketChannel;

public final class ServerDataEvent {
    public final ServerSocketHandler server;
    public final SocketChannel socket;
    public final byte[] data;

    public ServerDataEvent(ServerSocketHandler server, SocketChannel socket, byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;
    }
}