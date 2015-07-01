package be.cremers.mqlight.server;

import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Worker implements Runnable {

    private final Queue<ServerDataEvent> queue = new ConcurrentLinkedQueue<>();
    private final AtomicBoolean running = new AtomicBoolean(true);

    protected abstract void processData(ServerSocketHandler server, SocketChannel socket, byte[] data, int count);

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

    public final void start() {
        new Thread(this).start();
    }

    public final void stop() {
        running.set(false);
    }
}
