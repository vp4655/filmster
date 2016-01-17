package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.fer.dm.dm_app3.Models.genres.Genre;
import hr.fer.dm.dm_app3.Models.genres.Genredx;
import hr.fer.dm.dm_app3.Models.login.LoginResponse;
import hr.fer.dm.dm_app3.Network.ApiManager;
import hr.fer.dm.dm_app3.Network.ApiManagerMovie;
import hr.fer.dm.dm_app3.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    private static final long DELAY = 3000; // u ms

    private TextView info;
    private CallbackManager callbackManager;

    private Button myloginButton;
    private ProgressDialog pDialog;

    // odmah na početku definiramo bindanje
    @Bind(R.id.btnSkip) Button skipButton;

    // TODO: obrisati
    @Bind(R.id.btnAnimate) Button animateButton;

    @Bind(R.id.ivPopcorn) ImageView ivPopcorn;

    private Animation myFadeInAnimation;
    private Animation myFadeOutAnimation;

//    @Bind(R.id.actor_btn) Button actorButton;

//    @Bind(R.id.login_button) LoginButton loginButton;
//    @Bind(R.id.info) Button info;

    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w300";
    public static final String ACTOR_DETAIL_KEY = "actor";
    public static final String IMDB_MOVIE_LINK = "http://www.imdb.com/title/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //***********************FACEBOOK****************************//
        FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
            @Override
            public void onInitialized() {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken != null) {
                    //System.out.println("Logged in");
//                    String token = AccessToken.getCurrentAccessToken().getToken(); //Facebook doesn't allow devs to Log "session.getAccessToken" directly, because it may cause leaks
//
//                    // do something with token
//
//                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                    loggedin();

                }
            }
        });
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_login);

        // handmade button

        myloginButton = (Button)findViewById(R.id.login_button_my);
        //myloginButton.setAlpha(0);
        myloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends", "email", "user_likes", "user_photos"));
            }
        });

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loggedin();
            }

            @Override
            public void onCancel() {
                // do sth
            }

            @Override
            public void onError(FacebookException exception) {
                // do sth
            }
        });


        //***********************************************************//


        ButterKnife.bind(this);      // u onCreate dodati ovo kako bi se bindanje obavilo

        skipButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                }
        );
//
//
//        actorButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, ActorDetailActivity.class);
//                intent.putExtra(ACTOR_DETAIL_KEY, 0);
//                startActivity(intent);
//            }
//        });


        //TODO: obrisati:
        myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        animate();



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
//        AppEventsLogger.activateApp(this);
        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token != null) {
            //Toast.makeText(getActivity(), token, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        //moveTaskToBack(true) leaves your back stack as it is, just puts all Activities in background (same as if user pressed Home button).
    }

    protected void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    void loggedin()
    {
        final String token = AccessToken.getCurrentAccessToken().getToken(); //Facebook doesn't allow devs to Log "session.getAccessToken" directly, because it may cause leaks

        ApiManagerMovie.getService().getFToken(token, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse loginResponse, Response response) {
                try {
                    SharedPreferences sp = getSharedPreferences("facebookApp", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", token);
                    editor.putString("name", loginResponse.getUserData().getUser().getFirstName());
                    editor.putString("uri", loginResponse.getUserData().getUser().getPhoto());
                    editor.putString("email", loginResponse.getUserData().getUser().getEmail());
                    editor.putString("last_name", loginResponse.getUserData().getUser().getLastName());
                    editor.commit();
//
//                    // get token
//                    SharedPreferences sp = getSharedPreferences("facebookApp", Activity.MODE_PRIVATE);
//                    String storedToken = sp.getString("token", "0");

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();

                } catch (Exception exc) {

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(LoginActivity.this, "Something happened :(", Toast.LENGTH_LONG).show();

            }
        });
    }

    private float xCurrentPos, yCurrentPos;
    private ImageView logoFocus;

    private void animate()
    {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                long durationFadeOut = myFadeOutAnimation.getDuration();
                long durationFadeIn = myFadeInAnimation.getDuration()+durationFadeOut;

                myFadeOutAnimation.setInterpolator(new AccelerateInterpolator());
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //ivPopcorn.clearAnimation();
                        ivPopcorn.setVisibility(View.GONE);

                    }
                }, durationFadeOut);
                ivPopcorn.startAnimation(myFadeOutAnimation);


                myFadeInAnimation.setStartOffset(myFadeOutAnimation.getDuration());
                myFadeInAnimation.setInterpolator(new AccelerateInterpolator());
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //myloginButton.clearAnimation();
                        myloginButton.setVisibility(View.VISIBLE);
                        skipButton.setVisibility(View.VISIBLE);

                    }
                }, durationFadeIn);
                myloginButton.startAnimation(myFadeInAnimation);

            }
        }, DELAY);



//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                myloginButton.startAnimation(myFadeInAnimation);
//                skipButton.startAnimation(myFadeInAnimation);
//
//            }
//        }, 100);
    }

}
