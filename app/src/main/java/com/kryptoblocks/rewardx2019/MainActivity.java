package com.kryptoblocks.rewardx2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.fragments.AccountsFragment;
import com.kryptoblocks.rewardx2019.fragments.ActivitiesFragment;
import com.kryptoblocks.rewardx2019.fragments.DiscoverFragment;
import com.kryptoblocks.rewardx2019.fragments.IntroScreenOneFragment;
import com.kryptoblocks.rewardx2019.fragments.OffersFragment;
import com.kryptoblocks.rewardx2019.fragments.RedeemFragment;
import com.squareup.leakcanary.LeakCanary;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{




    private void ActivitiesFragment() {

        ActivitiesFragment activitiesFragment = new ActivitiesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, activitiesFragment);
        fragmentTransaction.commit();
    }

    private void OffersFragment() {

        OffersFragment offersFragment = new OffersFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, offersFragment);
        fragmentTransaction.commit();
    }

    private void DiscoverFragment() {

        DiscoverFragment discoverFragment = new DiscoverFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, discoverFragment);
        fragmentTransaction.commit();
    }

    private void AccountsFragment() {

        AccountsFragment accountsFragment = new AccountsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, accountsFragment);
        fragmentTransaction.commit();
    }

    public static TabLayout tab_home;
    public static Toolbar toolbar;
    TextView text_search_view;
    ImageView search_image_view;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         toolbar = findViewById(R.id.toolbar);

        //collapsing toolbar
      //  collapsingToolbarLayout = findViewById(R.id.CollapsingToolbarLayout1);
        //collapsingToolbarLayout.setTitle("Title collapsing");

        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        OffersFragment();
       // IntroScreenOneFragment();

       // text_search_view = findViewById(R.id.search_textView);
        search_image_view = findViewById(R.id.search_image_main);
        tab_home = findViewById(R.id.tabLayout_main);

        tab_home.addTab(tab_home.newTab().setText("Offers"));
        tab_home.addTab(tab_home.newTab().setText("Discover"));
        tab_home.addTab(tab_home.newTab().setText("Redeem"));


        tab_home.getTabAt(0).select();

        tab_home.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new OffersFragment();
                        break;
                    case 1:
                        fragment = new DiscoverFragment();

                        break;
                    case 2:
                        fragment = new RedeemFragment();
                        break;

                }


                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        search_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(),ExpandedSearchActivity.class);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
/*
            Intent i = new Intent(getApplication(), DashboardActivity.class);
            startActivity(i);*/


        } else if (id == R.id.nav_activities) {

            ActivitiesFragment();

            /*Intent i = new Intent(getApplication(), Share_RateActivity.class);
            startActivity(i);*/
            //PersonalProfileFragment();
            //Toast.makeText(this, "fragemnttttt personal", Toast.LENGTH_SHORT).show();



        } else if (id == R.id.nav_coupons) {
            Toast.makeText(this, "fragemnttttt personal", Toast.LENGTH_SHORT).show();
           // CouponFragment();



        } else if (id == R.id.nav_compaigns) {
            //  BalanceFragment();
           /* Intent i = new Intent(getApplication(), BalanceActivity.class);
            startActivity(i);
*/
        } else if (id == R.id.nav_accounts) {

            AccountsFragment();

        } else if (id == R.id.nav_billing) {

            //OrganisationUsers();
        }

        else if (id == R.id.nav_support) {

            /*Intent i = new Intent(getApplication(), Sync_Contacts_Activity.class);
            startActivity(i);*/
        }

        else if (id == R.id.nav_profile) {

          Intent i = new Intent(getApplication(), ProfileActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            // drawer.closeDrawer(GravityCompat.START);
            drawer.openDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.map_marker_icon) {
            Intent i = new Intent(getApplication(),MapsActivity.class);
            startActivity(i);
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }


}
