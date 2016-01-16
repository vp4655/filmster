package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

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
    private Drawer drawer;
    private AccountHeader headerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

//        frameLayout = (FrameLayout) findViewById( R.id.mainmenu);
//        frameLayout.getForeground().setAlpha(0);

        Toolbar cl = (Toolbar) findViewById(R.id.toolbar_movie_list);
        setSupportActionBar(cl);


        SharedPreferences sp = getSharedPreferences("facebookApp", Activity.MODE_PRIVATE);
        String name = sp.getString("name", "NN");
        String uri = sp.getString("uri", "NN");
        String lastName = sp.getString("last_name", "NN");
        String email = sp.getString("email", "NN");

        getSupportActionBar().setTitle("Hello, " + name + "!");

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }
        });

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(name + " " + lastName).withEmail(email).withIcon(uri))
                .withSavedInstance(savedInstanceState)
                .build();

        drawer = new DrawerBuilder().withActivity(this)
                .withDrawerGravity(Gravity.END)
                .withAccountHeader(headerResult)
                .withSliderBackgroundColor(getResources().getColor(R.color.primary))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(R.drawable.ic_action_action_home).withTextColor(getResources().getColor(R.color.text_icons)).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_search).withIcon(R.drawable.ic_search_white_24dp).withTextColor(getResources().getColor(R.color.text_icons)).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_watchlist).withIcon(R.drawable.ic_local_movies_white_24dp).withTextColor(getResources().getColor(R.color.text_icons)).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_watched).withIcon(R.drawable.ic_movie_creation_white_24dp).withTextColor(getResources().getColor(R.color.text_icons)).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_logout).withIcon(R.drawable.ic_power_settings_new_white_24dp).withTextColor(getResources().getColor(R.color.text_icons)).withIdentifier(4)
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        if(iDrawerItem != null){
                            Intent intent = null;
                            if(iDrawerItem.getIdentifier() == 1){
                                intent = new Intent(HomeActivity.this, HomeActivity.class);
                            }
                            else if (iDrawerItem.getIdentifier() == 2){
                                intent = new Intent(HomeActivity.this, HomeActivity.class);
                            }
                            else if (iDrawerItem.getIdentifier() == 3){
                                intent = new Intent(HomeActivity.this, HomeActivity.class);
                            }
                            else if (iDrawerItem.getIdentifier() == 4){
                                LoginManager.getInstance().logOut();
                                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            }
                        }
                        return false;
                    }
                }).build();


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
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Search", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
            if(drawer.isDrawerOpen()){
                drawer.closeDrawer();
            }
            else {
                drawer.openDrawer();
            }
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
