package hr.fer.dm.dm_app3.Activites;

import android.animation.Animator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Switch;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import hr.fer.dm.dm_app3.Classes.BaseFragment;
import hr.fer.dm.dm_app3.Classes.Fab.FilterOptions;
import hr.fer.dm.dm_app3.Classes.HomeFragment;
import hr.fer.dm.dm_app3.Classes.Recommendation1Fragment;
import hr.fer.dm.dm_app3.Classes.Recommendation2Fragment;
import hr.fer.dm.dm_app3.Classes.SectionsPagerAdapter;
import hr.fer.dm.dm_app3.R;

public class HomeActivity extends AppCompatActivity {

    public BaseFragment fragment1;
    public BaseFragment fragment2;
    public BaseFragment fragment3;

    public void setF1(Fragment f) {
        fragment1=(HomeFragment)f;
    }

    public void setF2(Fragment f) {
        fragment2=(Recommendation1Fragment)f;
    }

    public void setF3(Fragment f) {
        fragment3=(Recommendation2Fragment)f;
    }

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

    public FilterOptions filterOptions;
    final Context context = this;
    NumberPicker npFrom;
    NumberPicker npTo;
    ListView listview;
    Switch switchYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

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
                            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                            if(iDrawerItem.getIdentifier() == 1){
                                startActivity(new Intent(HomeActivity.this, SearchMoviesActivity.class));
                            }
                            else if (iDrawerItem.getIdentifier() == 2){
                                intent = new Intent(HomeActivity.this, WatchLaterActivity.class);
                            }
                            else if (iDrawerItem.getIdentifier() == 3){
                                intent = new Intent(HomeActivity.this, WatchedActivity.class);
                            }
                            else if (iDrawerItem.getIdentifier() == 4){
                                LoginManager.getInstance().logOut();
                                intent = new Intent(HomeActivity.this, LoginActivity.class);
                            }
                            startActivity(intent);
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


        filterOptions = new FilterOptions();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.fillter, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.etFilterTitle);

                //years for picker
                npFrom = (NumberPicker) promptsView.findViewById(R.id.npYears);
                npTo = (NumberPicker) promptsView.findViewById(R.id.npYearsTo);
                npFrom.setMinValue(1900);
                Calendar now = Calendar.getInstance();   // Gets the current date and time
                int year = now.get(Calendar.YEAR);
                npFrom.setMaxValue(year);
                npTo.setMaxValue(year);
                npFrom.setValue(year);
                npTo.setValue(year);
                npTo.setMinValue(year);       // ne može manje od ovog
//                npTo.setEnabled(true);
//                npFrom.setEnabled(true);

                switchYear = (Switch) promptsView.findViewById(R.id.swYear);
                switchYear.setChecked(true);

                switchYear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        npFrom.setEnabled(isChecked);
                        npTo.setEnabled(isChecked);
                    }
                });

                npFrom.setOnScrollListener(new NumberPicker.OnScrollListener() {
                    @Override
                    public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            //We get the different between oldValue and the new value
                            int newValue = numberPicker.getValue();
                            npTo.setMinValue(numberPicker.getValue());
                        }
                    }
                });

                // lista zanrova
                //TODO: listu genereova dohvatiti??
                listview = (ListView) promptsView.findViewById(R.id.lvGenresDialog);
                String[] values = new String[]{"Action ", "Adventure ", "Animation ", "Biography ", "Comedy ", "Crime ", "Documentary ", "Drama ", "Family ", "Fantasy ", "Film-Noir ", "History ", "Horror ", "Music ", "Musical ", "Mystery ", "Romance ", "Sci-Fi ", "Sport ", "Thriller ", "War ", "Western"};

                DialogAdapter adapter = new DialogAdapter((Activity) view.getContext(), filterOptions.stringList());

                listview.setAdapter(adapter);

                // setiraj visinu liste
                int totalHeight = 0;
                for (int i = 0; i < adapter.getNumItemToShow(); i++) {
                    View listItem = adapter.getView(i, null, listview);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
                }

                ViewGroup.LayoutParams params = listview.getLayoutParams();
                params.height = totalHeight + (listview.getDividerHeight() * (adapter.getNumItemToShow() - 1));
                listview.setLayoutParams(params);
                listview.requestLayout();

                // set dialog message
                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        filterOptions.setTitle(userInput.getText().toString());

                                        List<String> checkedGenres = new ArrayList<String>();

                                        SparseBooleanArray checked = listview.getCheckedItemPositions();
                                        for (int i = 0; i < listview.getAdapter().getCount(); i++) {
                                            if (checked.get(i)) {
                                                // Do something
                                                checkedGenres.add((String) listview.getAdapter().getItem(i));
                                            }
                                        }

                                        filterOptions.setGenres(checkedGenres);
                                        filterOptions.setNumFrom(npFrom.getValue());
                                        filterOptions.setNumTo(npTo.getValue());

                                        fragment1.setFilterOptions(filterOptions);
                                        fragment2.setFilterOptions(filterOptions);
                                        fragment3.setFilterOptions(filterOptions);

                                        fragment1.reload();
                                        fragment2.reload();
                                        //fragment3.reload();   // jer je s imdb pa nema search


                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

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
//TODO: https://guides.codepath.com/android/Circular-Reveal-Animation
//    void enterReveal() {
//        // previously invisible view
//        final View myView = findViewById(R.id.fab);
//
//        // get the center for the clipping circle
//        int cx = myView.getMeasuredWidth() / 2;
//        int cy = myView.getMeasuredHeight() / 2;
//
//        // get the final radius for the clipping circle
//        int finalRadius = Math.max(myView.getWidth(), myView.getHeight()) / 2;
//
//        // create the animator for this view (the start radius is zero)
//        Animator anim =
//                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
//
//        // make the view visible and start the animation
//        myView.setVisibility(View.VISIBLE);
//        anim.start();
//    }


}
