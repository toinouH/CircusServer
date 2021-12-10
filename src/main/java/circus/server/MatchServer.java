package circus.server;

import circus.StateHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatchServer implements Runnable {
    private static final int PORT = 6210;
    private static final String HOST = "127.0.0.1";
    private ServerSocket listener;

    private ArrayList<ClientHandler> connectedClients = new ArrayList<>();
    private final ExecutorService clientPool = Executors.newFixedThreadPool(16);
    private final StateHandler stateHandler;

    public MatchServer(StateHandler stateHandler)
    {
        this.stateHandler = stateHandler;
    }

    public StateHandler getStateHandler()
    {
        return this.stateHandler;
    }

    @Override
    public void run()
    {
        try
        {
            listener = new ServerSocket(PORT);
            //noinspection InfiniteLoopStatement
            for ( ;; )
            {
                Socket client = listener.accept();
                System.out.println("[MatchServer] New client connection instantiating...");

                ClientHandler clientThread = new ClientHandler(client, this);
                connectedClients.add(clientThread);

                clientPool.execute(clientThread);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
