package hr.fer.dm.dm_app3.Network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.Date;

import hr.fer.dm.dm_app3.Network.Deserializers.DateDeserializer;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class ApiActorManager {

    private static final String TAG = "Network";

    public static final String API_ENDPOINT = "http://api.themoviedb.org";

    private static final CookieHandler COOKIE_HANDLER = new CookieManager();

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient().setCookieHandler(COOKIE_HANDLER);

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateDeserializer())
            .create();

    private static final RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    };

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setLog(LOG)
            .setClient(new OkClient(OK_HTTP_CLIENT))
            .setEndpoint(API_ENDPOINT)
            .setConverter(new GsonConverter(GSON))
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final ActorService ACTOR_SERVICE = REST_ADAPTER.create(ActorService.class);

    public static ActorService getService() {
        return ACTOR_SERVICE ;
    }

}
