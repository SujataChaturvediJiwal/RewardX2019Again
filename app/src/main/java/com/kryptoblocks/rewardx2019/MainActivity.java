package com.kryptoblocks.rewardx2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.fragments.AccountsFragment;
import com.kryptoblocks.rewardx2019.fragments.ActivitiesFragment;
import com.kryptoblocks.rewardx2019.fragments.DiscoverFragment;
import com.kryptoblocks.rewardx2019.fragments.HelpFragment;
import com.kryptoblocks.rewardx2019.fragments.IntroScreenOneFragment;
import com.kryptoblocks.rewardx2019.fragments.OffersFragment;
import com.kryptoblocks.rewardx2019.fragments.ProfileFragment;
import com.kryptoblocks.rewardx2019.fragments.RedeemFragment;
import com.squareup.leakcanary.LeakCanary;

import static com.kryptoblocks.rewardx2019.ProfileActivity.isLocationEnabled;
import static com.kryptoblocks.rewardx2019.ProfileActivity.mypreference;
import static com.kryptoblocks.rewardx2019.ProfileActivity.mypreferenceLogin;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_fullName;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LocationListener {




    public void ActivitiesFragment() {

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

    public  void DiscoverFragment() {

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
    private void HelpFragment() {

        HelpFragment helpFragment = new HelpFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, helpFragment);
        fragmentTransaction.commit();
    }
    private void ProfileFragment() {

        ProfileFragment profileFragment = new ProfileFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, profileFragment);
        fragmentTransaction.commit();
    }
    public static TabLayout tab_home;
    public static Toolbar toolbar;
    TextView text_search_view;
    ImageView search_image_view;
    public String latitude;
    public String longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;
    CollapsingToolbarLayout collapsingToolbarLayout;

    SharedPreferences sharedPreferencesMainAct;
    SharedPreferences preferences;
    public static final String mypreferenceMain = "mypref";
    SharedPreferences sharedPreferencesLocation;
     public static ActionBarDrawerToggle toggle;
    public static NavigationView navigationView;
     String main_act_user_id, main_act_user_pwd;
    TextView nav_user_name;
    ImageView nav_user_image;
    String user_name_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         toolbar = findViewById(R.id.toolbar);

        //collapsing toolbar
    /* collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout1);
     collapsingToolbarLayout.setTitle("Title collapsing");
     collapsingToolbarLayout.setContentScrimColor(Color.GREEN);*/

        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);



         toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nav_user_name = hView.findViewById(R.id.name_textView_navHeader);
       // TextView nav_user = hView.findViewById(R.id.nav_name);

        sharedPreferencesMainAct = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_name_nav = sharedPreferencesMainAct.getString("user_full_name","hi");

       nav_user_name.setText(user_name_nav);
       System.out.println("Full name of user------------"+user_name_nav);

        //OffersFragment();

        // IntroScreenOneFragment();

        DiscoverFragment();

       // text_search_view = findViewById(R.id.search_textView);
        search_image_view = findViewById(R.id.search_image_main);
        tab_home = findViewById(R.id.tabLayout_main);

       // tab_home.addTab(tab_home.newTab().setText("Coupons"));
        tab_home.addTab(tab_home.newTab().setText("Discover"));
        tab_home.addTab(tab_home.newTab().setText("Redeem"));


        tab_home.getTabAt(0).select();

        tab_home.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                  /*  case 0:
                        fragment = new OffersFragment();
                        break;*/
                    case 0:
                        fragment = new DiscoverFragment();

                        break;
                    case 1:
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

        preferences = getSharedPreferences(mypreferenceMain, Context.MODE_PRIVATE);
        /*if(preferences.getBoolean("logged",true))
        {
            Intent i = new Intent(getApplication(),ProfileActivity.class);
            startActivity(i);
        }*/

        //for location--------------------------------------

        sharedPreferencesLocation = getSharedPreferences(mypreferenceMain, Context.MODE_PRIVATE);

        if (ContextCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }
        getLocation();

        //retrieving data of shared preferences
        String lat = sharedPreferencesLocation.getString("shared_lati","hi");
        String longi = sharedPreferencesLocation.getString("shared_longi","bye");
       // Toast.makeText(MainActivity.this, "Shared latitude:" + lat + " Shared longitude:" + longi, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {

            Intent i = new Intent(getApplication(), MainActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_activities) {

            ActivitiesFragment();

            /*Intent i = new Intent(getApplication(), Share_RateActivity.class);
            startActivity(i);*/
            //PersonalProfileFragment();
            //Toast.makeText(this, "fragemnttttt personal", Toast.LENGTH_SHORT).show();



        } else if (id == R.id.nav_help) {
            HelpFragment();
            //Toast.makeText(this, "fragemnttttt personal", Toast.LENGTH_SHORT).show();
           // CouponFragment();
        }
        /*else if (id == R.id.nav_compaigns) {
            //  BalanceFragment();
           *//* Intent i = new Intent(getApplication(), BalanceActivity.class);
            startActivity(i);
*//*
        } else if (id == R.id.nav_accounts) {

            AccountsFragment();

        } else if (id == R.id.nav_billing) {

            //OrganisationUsers();
        }*/

        else if (id == R.id.nav_logOut) {
            preferences = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

           // editor.clear();
           // editor.commit();
            Intent i = new Intent(getApplication(), SocialLoginActivity.class);
            startActivity(i);
            editor.remove("user_id");
            editor.remove("user_pswd");
            editor.remove("user_uuid");
            editor.clear();
            editor.commit();
            //preferences.edit().putBoolean("logged",true).apply();

           /* sharedPreferencesMainAct = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
            //retrieving data of shared preferences
            SharedPreferences.Editor editor = sharedPreferencesMainAct.edit();
           *//* main_act_user_id = sharedPreferencesMainAct.getString("user_id","hi");
            main_act_user_pwd = sharedPreferencesMainAct.getString("user_pswd","bye");*//*
            editor.clear();
            editor.commit();*/

        }

        else if (id == R.id.nav_profile) {

           // toggle.setDrawerIndicatorEnabled(false);
           // toolbar.setVisibility(View.GONE);

            ProfileFragment();
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

    protected void getLocation() {
        if (isLocationEnabled(MainActivity.this)) {
            locationManager = (LocationManager)  this.getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();
            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

            //You can still do this if you like, you might get lucky:
            if (ContextCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

            }

            Location location = locationManager.getLastKnownLocation(bestProvider);

            if (location != null) {
                Log.e("TAG", "GPS is on");
                latitude = String.valueOf(location.getLatitude());
                longitude = String.valueOf(location.getLongitude());

                //using sharedPreferences
                SharedPreferences.Editor editor = sharedPreferencesLocation.edit();
                editor.putString("shared_lati", latitude);
                editor.putString("shared_longi", longitude);
                editor.commit();

                System.out.println("Clicked-----");
                //tv_value.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
                System.out.print("Location-------------" +latitude+ " " + longitude);
               // Toast.makeText(MainActivity.this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
                // searchNearestPlace(voice2text);
            }
            else{
                //This is what you need:
                locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
            }
        }
        else
        {
            //prompt user to enable location....
            //.................
        }
    }
    @Override
    public void onLocationChanged(Location location) {
       /* txtLat = (TextView) findViewById(R.id.textview1);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());*/
        System.out.print("Location-------------"+location.getLatitude()+ " " + location.getLongitude());
        //Log.d("Loc----------"+location.getLatitude()+location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}
