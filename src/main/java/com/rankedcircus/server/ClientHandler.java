package com.rankedcircus.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final BufferedReader input;
    private final PrintWriter output;
    private final MatchServer serverReference;

    public ClientHandler(Socket clientSocket, MatchServer serverReference) throws IOException
    {
        this.serverReference = serverReference;
        input  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    // TODO: Setup actual packet intake system that doesn't require 18 bytes to communicate a single command.
    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                String request = input.readLine();
                if (request != null && request.contains("create_match id="))
                {
                    int matchId = Integer.parseInt(request
                            .split("create_match id=")[1]
                            .split("\\\\")[0]);

                    System.out.println("[MatchServer] received command \"" + request + "\"");
                    System.out.println("[MatchServer] Creating new match with id of " + matchId);

                    this.serverReference
                            .getStateHandler()
                            .setCurrentMatchId(Integer.parseInt(request.split("create_match id=")[1]));

                }
                else
                {
                    output.println("Unrecognized input.");
                }
            }
        }
        catch (IOException e)
        {
            e.getStackTrace();
        }
        finally
        {
            output.close();
            try
            {
                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
