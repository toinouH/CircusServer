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

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        input  = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(), true);
    }

    // TODO: Handle string constants via xml file to avoid clutter.
    @Override
    public void run() {
        try {
            while (true) {
                String request = input.readLine();
                if (request.contains("create_match")) {
                    output.println("Creating match...");
                    System.out.println(request);
                    // TODO: Handle lobby creation.

                } else {
                    output.println("Unrecognized input.");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
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
