package circus.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    private MatchServer serverReference;

    public ClientHandler(Socket clientSocket, MatchServer serverReference) throws IOException {
        this.client = clientSocket;
        this.serverReference = serverReference;
        input  = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(), true);
    }

    // TODO: Handle string constants via xml file to avoid clutter.
    @Override
    public void run() {
        try {
            while (true) {
                String request = input.readLine();
                if (request != null && request.contains("create_match id=")) {
                    int matchId = Integer.parseInt(request
                            .split("create_match id=")[1]
                            .split("\\\\")[0]);

                    System.out.println("[MatchServer] DRAFT_NODE_1 says \"" + request + "\"");
                    System.out.println("[MatchServer] Creating new match with id of " + matchId);

                    this.serverReference
                            .getStateHandler()
                            .setCurrentMatchId(Integer.parseInt(request.split("create_match id=")[1]));

                } else {
                    output.println("Unrecognized input.");
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            output.close();
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
