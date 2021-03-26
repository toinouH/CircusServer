package circus.api;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Api {
    private OkHttpClient httpClient = new OkHttpClient();
    private Gson gson = new Gson();

    private static final String API_URL = "https://rankedcircus.com/api/";
    private static final String API_PLAYERS = API_URL + "players/";
    private static final String API_MATCHES = API_URL + "matches/";

    public Player getPlayer( int id ) throws IOException {
        Request request = new Request.Builder().url( API_URL + "plans/" + id + "/" ).build();

        try ( Response response = this.httpClient.newCall(request).execute() ) {
            Player player = gson.fromJson( response.body().string(), Player.class );
            return player;
        } catch ( NullPointerException e ) {
            throw new NullPointerException( "Could not find player with id of " + id );
        }
    }
}
