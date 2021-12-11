package com.rankedcircus.api;

import com.rankedcircus.Config;
import com.rankedcircus.SGson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Api
{
    private final OkHttpClient httpClient = new OkHttpClient();
    private String apiUrl = Config.getConfig().read("circus.api_prod");

    public Api()
    {
        if ( Config.getConfig().read("circus.dev_mode").equalsIgnoreCase("true") )
        {
            this.apiUrl = Config.getConfig().read("circus.api_dev");
            System.out.println("[API] Launched in development mode, swapped apiUrl to " + this.apiUrl);
        }
    }

    public Player getPlayer( int id ) throws IOException
    {
        Request request = new Request.Builder().url( apiUrl + "plans/" + id + "/" ).build();

        try ( Response response = this.httpClient.newCall( request ).execute() )
        {
            return SGson.getGson().fromJson( response.body().string(), Player.class );
        }
        catch ( NullPointerException e )
        {
            throw new NullPointerException( "Could not find player with id of " + id );
        }
    }

    public Match getMatch(int id)
    {
        Request request = new Request
                .Builder()
                .url(String.format("%smatches/%d/", apiUrl, id))
                .build();

        try (Response response = this.httpClient.newCall(request).execute())
        {
            return SGson.getGson().fromJson(response.body().string(), Match.class);
        }
        catch (NullPointerException e)
        {
            throw new NullPointerException("Couldn't find match with the id of " + id);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Match with id of " + id + " does not exist.");
    }
}
