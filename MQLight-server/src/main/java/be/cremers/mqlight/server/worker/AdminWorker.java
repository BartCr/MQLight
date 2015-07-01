package be.cremers.mqlight.server.worker;

import be.cremers.mqlight.server.ServerSocketHandler;
import be.cremers.mqlight.server.Worker;

import java.nio.channels.SocketChannel;

public class AdminWorker extends Worker{

    @Override
    public void processData(ServerSocketHandler server, SocketChannel socket, byte[] data, int count) {

    }
}
