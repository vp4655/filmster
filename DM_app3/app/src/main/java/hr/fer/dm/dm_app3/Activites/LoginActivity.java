package hr.fer.dm.dm_app3.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import hr.fer.dm.dm_app3.R;

public class LoginActivity extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    private Button myloginButton;
    private ProgressDialog pDialog;

    // odmah na početku definiramo bindanje
    @Bind(R.id.btnSkip) Button skipButton;
    @Bind(R.id.actor_btn) Button actorButton;

//    @Bind(R.id.login_button) LoginButton loginButton;
//    @Bind(R.id.info) Button info;

    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w500";
    public static final String ACTOR_DETAIL_KEY = "actor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //***********************FACEBOOK****************************//

//        FacebookSdk.sdkInitialize(this.getApplicationContext());

        FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
            @Override
            public void onInitialized() {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken == null) {
                    //System.out.println("not logged in yet");
                } else {
                    //System.out.println("Logged in");
                    String token = AccessToken.getCurrentAccessToken().getToken(); //Facebook doesn't allow devs to Log "session.getAccessToken" directly, because it may cause leaks

                    // do something with token

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            }
        });

        setContentView(R.layout.activity_login);


        // za onaj plavi login button

//        info = (TextView)findViewById(R.id.info);
//        loginButton = (LoginButton)findViewById(R.id.login_button);
//
//        callbackManager = CallbackManager.Factory.create();

//        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
//        loginButton.setReadPermissions(permissionNeeds);
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
////                info.setText(
////                        "\n\n\nUser ID: "
////                                + loginResult.getAccessToken().getUserId()
////                                + "\n" +
////                                "Auth Token: "
////                                + loginResult.getAccessToken().getToken()
////                );
//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                finish();
//            }
//
//            @Override
//            public void onCancel() {
//                info.setText("Login attempt canceled.");
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                info.setText("Login attempt failed.");
//            }
//        });
        //***********************************************************//

        // handmade button

        myloginButton = (Button)findViewById(R.id.login_button_my);
        myloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login with facebook on clicking custom button
                // izgleda da sam facebook sdk ima svoj loader
//                pDialog = new ProgressDialog(LoginActivity.this);
//                // Showing progress dialog before making http request
//                pDialog.setMessage("Logging in...");
//                pDialog.show();
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends"));
                //hidePDialog();
            }
        });

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
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


        ButterKnife.bind(this);                         // u onCreate dodati ovo kako bi se bindanje obavilo

        skipButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                }
        );


        //actorButton = (Button) findViewById(R.id.actor_btn);
        // linija gore sad ne treba jer butterknife to lijepo odmah binda

        actorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ActorDetailActivity.class);
                intent.putExtra(ACTOR_DETAIL_KEY, 0);
                startActivity(intent);
            }
        });

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

//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        // Logs 'app deactivate' App Event.
//        AppEventsLogger.deactivateApp(this);
//    }
}
