package club.marlo.circus.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 6210;
    private static final String HOST = "127.0.0.1";
    private ServerSocket listener;

    private ArrayList<ClientHandler> connectedClients = new ArrayList<>();
    private ExecutorService clientPool = Executors.newFixedThreadPool(16);

    public Server() throws IOException {
        listener = new ServerSocket(PORT);
        while (true) {
            Socket client = listener.accept();
            System.out.println("New client connection instantiating.");

            ClientHandler clientThread = new ClientHandler(client);
            connectedClients.add(clientThread);

            clientPool.execute(clientThread);
        }
    }

    public ServerSocket getListener() { return this.listener; }
}
