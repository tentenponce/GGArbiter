package com.tenten.gameofthegeneralsarbiter.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;

import com.tenten.gameofthegeneralsarbiter.R;
import com.tenten.gameofthegeneralsarbiter.fragments.HomeFragment;
import com.tenten.gameofthegeneralsarbiter.fragments.SettingsFragment;

/**
 * Drawer for all activities
 * Created by Exequiel Egbert V. Ponce on 7/1/2016.
 */
public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String HOME_TITLE = "GGArbiter";
    public static final String SETTINGS_TITLE = "Settings";

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;

    NavigationView navigationView;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onCreateDrawer();
        setupFragment(HomeFragment.class, HOME_TITLE);
    }

    protected void onCreateDrawer() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        if (drawerLayout != null) {
            drawerLayout.addDrawerListener(toggle);
            drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START); //add shadow
        }

        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) { //setup the name on navigation drawer header
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            DrawerActivity.super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) { //toggle drawer open close
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        Class fragmentClass = null;
        String title = HOME_TITLE;

        if (item.isChecked()) { //check if they are on the fragment already
            return false;
        }

        switch (item.getItemId()) {
            case R.id.nav_home:
                fragmentClass = HomeFragment.class;
                title = HOME_TITLE;
                break;
            case R.id.nav_settings:
                fragmentClass = SettingsFragment.class;
                title = SETTINGS_TITLE;
                break;
        }
        
        if (fragmentClass != null) {
            setupFragment(fragmentClass, title);
        }

        return true;
    }

    private void setupFragment(final Class fragmentClass, final String title) {
        Fragment fragment = null;

        try {
            if (fragmentClass != null) {
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                    Fade fade = new Fade();
                    fade.setDuration(1000);
                    if (fragment != null) {
                        fragment.setEnterTransition(fade);
                    }

                    Slide slide = new Slide(Gravity.BOTTOM);
                    slide.setDuration(500);
                    if (fragment != null) {
                        fragment.setExitTransition(slide);
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);

        fragmentTransaction.commit();

        setTitle(title);
    }
}
