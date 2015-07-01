package be.cremers.mqlight.server;

import be.cremers.mqlight.server.worker.AdminWorker;
import be.cremers.mqlight.server.worker.MessagingWorker;

import java.io.IOException;

public class MQLightServer {
    public static void main(String[] args) {
        try {
            Worker messagingWorker = new MessagingWorker();
            Worker adminWorker = new AdminWorker();

            messagingWorker.start();
            adminWorker.start();

            System.out.println("Starting messaging channel on port 1234...");
            new ServerSocketHandler(null, 1234, messagingWorker).start();
            System.out.println("Starting admin channel on port 1334...");
            new ServerSocketHandler(null, 1334, adminWorker).start();
            System.out.println("MQLightServer ready");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
