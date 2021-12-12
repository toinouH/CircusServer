package com.rankedcircus.api;

import com.rankedcircus.Config;
import com.rankedcircus.SGson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

// Singleton
public class Api
{
    private static Api api;

    static
    {
        api = new Api();
        if ( Config.getConfig().read("circus.dev_mode").equalsIgnoreCase("true") )
        {
            api.setApiurl(Config.getConfig().read("circus.api_dev"));
            System.out.println("[API] Launched in development mode, swapped apiUrl to " + api.getApiUrl());
        }
    }

    public static Api getInstance()
    {
        return api;
    }

    private final OkHttpClient httpClient = new OkHttpClient();
    private String apiUrl = Config.getConfig().read("circus.api_prod");

    public Api()
    {
        System.out.println("Api constructor called.");
    }

    public void setApiurl(String newUrl)    {   this.apiUrl = newUrl;   }
    public String getApiUrl()               {   return this.apiUrl;     }

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
