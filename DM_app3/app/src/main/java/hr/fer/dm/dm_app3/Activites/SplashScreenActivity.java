package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import hr.fer.dm.dm_app3.R;

public class SplashScreenActivity extends Activity {
    private MyCounter mCounter;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Intent i=new Intent(this,SearchActivity.class);
        startActivity(i);

        mCounter = new MyCounter(1000, 1000);
        mCounter.start();
    }
    private class MyCounter extends CountDownTimer {

        public MyCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            mIntent = new Intent(SplashScreenActivity .this, LoginActivity.class);
            startActivity(mIntent);
            finish();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

        }

    }
}