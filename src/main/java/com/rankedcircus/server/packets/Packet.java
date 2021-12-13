package com.rankedcircus.server.packets;

import java.util.ArrayList;

public abstract class Packet
{
    // Not a Byte because we're getting input from the client via BufferedReader.readLine(), which returns a String.
    // Luckily, we're not operating a large game server here. We're technically not even operating a game server.
    // We don't need to be concerned too much with things like packet size.
    // With that, we don't need a dictionary or lookup functionality on the server to correlate individual bytes
    // with larger data types.
    // That being said, if you're viewing this and it's your first time looking at a game-related network setup,
    // just understand that in an actual game, keeping the size of each individual packet as small as possible
    // is extremely important. Which is when you'd employ the strategies mentioned above.

    int                 opcode;                 // Determines the functionality of the packet.
    ArrayList<String>   expectedParameters;     // The expected parameters for the given packet.
    ArrayList<String>   receivedParameters;     // The expected parameters for the given packet.

    // Rather than calling parameterCount.size(), we specify receivedParameters[],
    // which provides us with the count to expect.
    // That lets us not have to denote the split of parameters within the packet data itself.
    // If we didn't do this, we'd be looking at something like this:
    // opcode:param1:param2:param3
    // The inclusion of receivedParameters[] lets us remove an excess 1 byte from each parameter being sent by the client.

    public Packet(int opcode, ArrayList<String>expectedParameters)
    {
        this.opcode = opcode;
        this.expectedParameters = expectedParameters;
    }

    public void parseData(String data)
    {
        this.opcode = Integer.parseInt(data.split(expectedParameters.get(0))[0]);
    }
}
