package hr.fer.dm.dm_app3.Classes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hr.fer.dm.dm_app3.R;

/**
 * Created by Kajkara on 9.12.2015..
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position==0)
            return Recommendation1Fragment.newInstance(position+1);
        else if(position==1)
            return Recommendation2Fragment.newInstance(position+1);
        else
            return HomeFragment.newInstance(position+1);
    }

    public int getIcon(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position==0)
            return R.drawable.friends;
        else if(position==1)
            return R.drawable.trending;
        else
            return R.drawable.graph;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}

