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

    public Player getPlayer( int id ) throws IOException {
        Request request = new Request.Builder().url( API_URL + "plans/" + id + "/" ).build();

        try ( Response response = this.httpClient.newCall( request ).execute() ) {
            Player player = gson.fromJson( response.body().string(), Player.class );
            return player;
        } catch ( NullPointerException e ) {
            throw new NullPointerException( "Could not find player with id of " + id );
        }
    }

    public Match getMatch(int id) {
        Request request = new Request
                .Builder()
                .url(String.format("%smatches/%d/", API_URL, id))
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            Match match = gson.fromJson(response.body().string(), Match.class);
            return match;
        } catch (NullPointerException e) {
            throw new NullPointerException("Couldn't find match with the id of " + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Match with id of " + id + " does not exist.");
    }
}
