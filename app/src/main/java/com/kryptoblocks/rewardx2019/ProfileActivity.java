package com.kryptoblocks.rewardx2019;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.kryptoblocks.rewardx2019.fragments.ActivitiesFragment;
import com.kryptoblocks.rewardx2019.fragments.RewardsProfileFragment;
import com.kryptoblocks.rewardx2019.fragments.TokensProfileFragment;
import com.kryptoblocks.rewardx2019.utilityy.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import static android.location.LocationManager.GPS_PROVIDER;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_fullName;
//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;

public class ProfileActivity extends AppCompatActivity implements LocationListener{

    TextView text_rotate, see_activities;

    TabLayout tabLayoutProfile;

    public String latitude;
    public String longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;
    public static final String mypreference = "mypref";
    SharedPreferences sharedPreferences;

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesProfile;
    String user_id_profile;
    String user_full_name_profile;

    TextView user_id_textView, user_name;
    ImageView user_bardcode_img, image_profile;
    //LinearLayout image_profile;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    LinearLayout linear_layout_profile;
    FrameLayout profile_content;


    private void RewardsProfileFragment() {

        RewardsProfileFragment rewardsProfileFragment = new RewardsProfileFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.profile_fragment, rewardsProfileFragment);
        fragmentTransaction.commit();
        }

  /* private void ActivitiesFragment() {

        *//*ActivitiesFragment activitiesFragment = new ActivitiesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.profile_content_frame, activitiesFragment);
        fragmentTransaction.commit();*//*

       Fragment mFragment = null;
       mFragment = new ActivitiesFragment();
       FragmentManager fragmentManager = getSupportFragmentManager();
       fragmentManager.beginTransaction().replace(R.id.profile_content_frame, mFragment).commit();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

       /* text_rotate = findViewById(R.id.rotate_text_profile);

        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate);
        ranim.setFillAfter(true); //For the textview to remain at the same place after the rotation
        text_rotate.setAnimation(ranim);*/


       //LINK https://stackoverflow.com/questions/3156781/how-to-show-android-checkbox-at-right-side/3156813


        /*CheckBox cb = (CheckBox)((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.check_right_checkbox,null);
        rootView.addView(cb);*/

        see_activities = findViewById(R.id.see_your_activities_text);

        user_id_textView = findViewById(R.id.unique_id_userProfile);
        user_name = findViewById(R.id.user_name_profile);
        user_bardcode_img = findViewById(R.id.bar_code_userProfile);
        image_profile = findViewById(R.id.profile_image);
        linear_layout_profile = findViewById(R.id.linear_layout_profile_page);
        profile_content = findViewById(R.id.profile_content_frame);

        sharedPreferencesProfile = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_full_name_profile = sharedPreferencesProfile.getString("user_full_name","hi");

        user_name.setText(user_full_name_profile);

        System.out.println("User full name *************"+user_full_name_profile);
        System.out.println("User full name i profile page*************"+user_name.getText().toString());

        sharedPreferencesProfile = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_profile = sharedPreferencesProfile.getString("user_unique_id","hi");

        Toast.makeText(ProfileActivity.this, "User id:" + user_id_profile , Toast.LENGTH_SHORT).show();

        user_id_textView.setText(user_id_profile);


        tabLayoutProfile = findViewById(R.id.tabLayout_activity_profile);

        tabLayoutProfile.addTab(tabLayoutProfile.newTab().setText("Rewards"));
        tabLayoutProfile.addTab(tabLayoutProfile.newTab().setText("Points"));


        tabLayoutProfile.getTabAt(0).select();

        RewardsProfileFragment();

        tabLayoutProfile.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new RewardsProfileFragment();
                        break;
                    case 1:
                        fragment = new TokensProfileFragment();
                        break;

                }


                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.profile_fragment, fragment);
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

        see_activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /*Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);*/
               /* linear_layout_profile.setVisibility(View.GONE);
                profile_content.setVisibility(View.VISIBLE);*/
                    ActivitiesFragment newFragment = new ActivitiesFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.attach(new ActivitiesFragment()).commit();

             //ActivitiesFragment();


            }
        });


       /* sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if (ContextCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }
        getLocation();

        //retrieving data of shared preferences
        String lat = sharedPreferences.getString("shared_lati","hi");
        String longi = sharedPreferences.getString("shared_longi","bye");
        Toast.makeText(ProfileActivity.this, "Shared latitude:" + lat + " Shared longitude:" + longi, Toast.LENGTH_SHORT).show();*/

  //barcode---------------------------------------------------------
        LinearLayout lin_layout = new LinearLayout(this);
        lin_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        lin_layout.setOrientation(LinearLayout.VERTICAL);
        // barcode image
        Bitmap bitmap = null;
        try {
            bitmap = encodeAsBitmap(user_id_profile, BarcodeFormat.CODE_128, 1500, 400);
            user_bardcode_img.setImageBitmap(bitmap);
            }
            catch (WriterException e) {
            e.printStackTrace(); }

        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
            }

    public static boolean isLocationEnabled(Context context)
    {
        //...............
        return true;
    }

    protected void getLocation() {
        if (isLocationEnabled(ProfileActivity.this)) {
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
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("shared_lati", latitude);
                editor.putString("shared_longi", longitude);
                editor.commit();

                System.out.println("Clicked-----");
                //tv_value.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
                System.out.print("Location-------------" +latitude+ " " + longitude);
                Toast.makeText(ProfileActivity.this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
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

    //for barcode------------------------
    Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

    //---------------------------------------

    //for profile image
    private void selectImage() {

        final CharSequence[] items = {"Take Photo", "Choose from Library"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(ProfileActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();


                    } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } /*else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }*/
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        byte[] byteArray = bytes .toByteArray();

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        image_profile.setImageBitmap(thumbnail);
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
       /* Data pic = new Data();
        pic.setImage(encoded);
        new UploadProfilePictureAsync().execute(pic);*/
        System.out.println("Cameraaaaaaaaaaaaaaaaaaaa     " +encoded);

    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), data.getData());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        image_profile.setImageBitmap(bm);
        System.out.println("galleryyyyyyyyyyyyyyyyyyyyyyyyyyyyy  " +bm);
        // Data pic = new Data();

    }
    ////////////////////////////////////////////////////
}
