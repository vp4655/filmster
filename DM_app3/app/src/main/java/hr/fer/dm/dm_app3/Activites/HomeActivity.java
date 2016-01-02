package hr.fer.dm.dm_app3.Activites;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.login.LoginManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import hr.fer.dm.dm_app3.Classes.SectionsPagerAdapter;
import hr.fer.dm.dm_app3.R;

public class HomeActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Toolbar toolbar;

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        frameLayout = (FrameLayout) findViewById( R.id.mainmenu);
        frameLayout.getForeground().setAlpha(0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("What2watch");

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for(int i=0; i<mSectionsPagerAdapter.getCount();i++)
        {
            tabLayout.addTab(tabLayout.newTab().setIcon(mSectionsPagerAdapter.getIcon(i)));
        }
//        tabLayout.addTab(tabLayout.newTab().setIcon());//  setText("Recommendation1"));
//        tabLayout.addTab(tabLayout.newTab().setText("Hot 100"));
//        tabLayout.addTab(tabLayout.newTab().setText("Recommendation2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);;


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "DELETE delete DELETE", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

//    public void onActivityCreated (Bundle savedInstanceState) {
//        doImages();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            initiatePopupWindow();
        }
        else if(id==R.id.action_logout)
        {
            LoginManager.getInstance().logOut();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private PopupWindow pw;
    TextView popUpText;
    private FrameLayout frameLayoutPopUp;

    private void initiatePopupWindow(){
        try {
            //frameLayout = (FrameLayout) findViewById( R.id.mainmenu_popup);
            //frameLayout.getForeground().setAlpha( 200);
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) HomeActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.pop_up, (ViewGroup) findViewById(R.id.popup_element));
            // create a 300px width and 470px height PopupWindow
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            pw = new PopupWindow(layout, Math.round(width*0.7f), Math.round(height*0.7f), true);
            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

            //popUpText = (TextView) layout.findViewById(R.id.tvPopUpTxt);
           // popUpText.setText(readFile("about.txt"));

            Button cancelButton = (Button) layout.findViewById(R.id.end_data_send_button);
            //makeBlack(cancelButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frameLayout.getForeground().setAlpha(0);
                    pw.dismiss();
                }
            });

            frameLayout.getForeground().setAlpha( 210); // dim

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
    //*************************************************//

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    /**
     * A placeholder fragment containing a simple view.
     */
}
