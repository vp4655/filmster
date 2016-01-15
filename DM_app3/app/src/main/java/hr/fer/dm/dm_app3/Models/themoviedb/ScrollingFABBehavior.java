package hr.fer.dm.dm_app3.Models.themoviedb;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;

import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Util.*;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by Kajkara on 9.1.2016..
 */
public class ScrollingFABBehavior extends FloatingActionButton.Behavior {
    public ScrollingFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout,
                                       final FloatingActionButton child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child,
                directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout,
                               final FloatingActionButton child,
                               final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            child.setTranslationY(200);
            //child.hide();
        } else if (dyConsumed < 0) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            child.setTranslationY(0);
            //child.show();
        }
    }
}
