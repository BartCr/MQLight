package be.cremers.mqlight.server.worker;

import be.cremers.mqlight.core.exception.MQLightException;
import be.cremers.mqlight.core.message.BaseMQLightMessage;
import be.cremers.mqlight.core.message.MessageFactory;
import be.cremers.mqlight.server.ServerDataEvent;
import be.cremers.mqlight.server.ServerSocketHandler;
import be.cremers.mqlight.server.Worker;

import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessagingWorker extends Worker {

    private final Queue<ServerDataEvent> queue = new ConcurrentLinkedQueue<>();

    private final AtomicBoolean running = new AtomicBoolean(true);

    public void run() {
        ServerDataEvent dataEvent;

        while (running.get()) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException ignored) {
                    }
                }
                dataEvent = queue.poll();
            }
            dataEvent.server.send(dataEvent.socket, dataEvent.data);
        }
    }

    public void processData(ServerSocketHandler server, SocketChannel socket, byte[] data, int count) {
        byte[] dataCopy = new byte[count];
        System.arraycopy(data, 0, dataCopy, 0, count);

        synchronized (queue) {
            try {
                BaseMQLightMessage message = MessageFactory.fromByteArray(dataCopy);
                System.out.println(message);
                queue.offer(new ServerDataEvent(server, socket, "ACK".getBytes()));
                queue.notify();
            } catch (MQLightException e) {
                e.printStackTrace();
            }
        }
    }


}
