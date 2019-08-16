package com.kryptoblocks.rewardx2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.kryptoblocks.rewardx2019.adapter.DisplayRedeemTokensAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetAllUserRewardPoints;
import com.kryptoblocks.rewardx2019.pojo.GetParticularRewardDetails;
import com.kryptoblocks.rewardx2019.pojo.GetUserRewardTokens;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
import static com.kryptoblocks.rewardx2019.adapter.DisplayRedeemTokensAdapter.redeem_vendor_uuid;
import static com.kryptoblocks.rewardx2019.adapter.DisplayRedeemTokensAdapter.var_vendor_name;
import static com.kryptoblocks.rewardx2019.adapter.DisplayRedeemTokensAdapter.vendor_name;
import static com.kryptoblocks.rewardx2019.adapter.ParticularVendorRewardsAdapter.single_reward_product_uuid;

public class RedeemDescriptionActivity extends AppCompatActivity {


    TextView redeem_vendor_name, amt, user_id_redeem_desc;
    ImageView img, back_arrow_redeem_desc, barcode_redeem_desc ;
    ApiInterface apiInterface;

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesRedeemDesc;
    String user_id_RedeemDesc;

    //--------------
    JSONArray user;
    JSONObject hay;
    // Profile JSON url
    private static final String PROFILE_URL = "http://192.168.163.2:3000/api/customers/getCustomerRewardPointDetailsForAllVendors/al0un56wjyr0dh6e";
    private ProgressDialog pDialog;
    // ALL JSON node names
    private static final String POINTS = "points";
    // private static final String TAG_ID = "id";
    private static final String VENDOR_NAME = "vendorname";
    private static final String IMAGE = "image";
    String ppoints, membership_uuid, vendor_names, link;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_description);

        redeem_vendor_name = findViewById(R.id.redeem_details_vendor_name);
        amt = findViewById(R.id.redeem_details_num_tokens);
        img = findViewById(R.id.redeem_details_image);
        back_arrow_redeem_desc = findViewById(R.id.redeem_desc_back_arrow);
        barcode_redeem_desc = findViewById(R.id.barcode_redeem_description);
        user_id_redeem_desc = findViewById(R.id.user_id_redeem_description);

        sharedPreferencesRedeemDesc =getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        membership_uuid = sharedPreferencesRedeemDesc.getString("membership_id","hi");

        System.out.println("membership uuid-------"+membership_uuid);


        sharedPreferencesRedeemDesc = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_RedeemDesc = sharedPreferencesRedeemDesc.getString("user_unique_id","hi");
        //Toast.makeText(RedeemDescriptionActivity.this, "User id:" + user_id_RedeemDesc , Toast.LENGTH_SHORT).show();
        user_id_redeem_desc.setText(membership_uuid);

        sharedPreferencesRedeemDesc =getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        ppoints = sharedPreferencesRedeemDesc.getString("redeem_point_per_user","hi");
        amt.setText(ppoints);


        sharedPreferencesRedeemDesc =getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        link = sharedPreferencesRedeemDesc.getString("image_link_logo","hi");
        Glide.with(getApplication()).load(link).error(R.drawable.ic_launcher_foreground).into(img);
        //img.set(link);

        /*sharedPreferencesRedeemDesc =getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        vendor_name = sharedPreferencesRedeemDesc.getString("owner_vendor_name","hi");
        redeem_vendor_name.setText(vendor_name);*/

        //displaying image
        /*try {
            Bundle extras = getIntent().getExtras();
            byte[] byteArray = extras.getByteArray("picture");

            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            img.setImageBitmap(bmp);
        }
        catch (Exception e)
        {
            System.out.println("Exception ion activity------------"+e);
        }*/

        redeem_vendor_name.setText(var_vendor_name);

        //barcode---------------------------------------------------------
        LinearLayout lin_layout = new LinearLayout(this);
        lin_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        lin_layout.setOrientation(LinearLayout.VERTICAL);
        // barcode image
        Bitmap bitmap = null;
        try {
            bitmap = encodeAsBitmap(membership_uuid, BarcodeFormat.CODE_128, 1500, 400);
            barcode_redeem_desc.setImageBitmap(bitmap);
        }
        catch (WriterException e) {
            e.printStackTrace(); }

        back_arrow_redeem_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
       // displayParticularRewardDetails();
    }


    public void displayParticularRewardDetails() {

        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);


        Call<GetUserRewardTokens> call1 = apiInterface.getUserRewardPoints(user_id_RedeemDesc,redeem_vendor_uuid);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetUserRewardTokens>() {


            @Override
            public void onResponse(Call<GetUserRewardTokens> call, Response<GetUserRewardTokens> response) {

                if (response.code() == 200) {

                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());


                    String name = response.body().getData().getVendorName();
                    String points = String.valueOf(response.body().getData().getTotalPoints());

                    redeem_vendor_name.setText(name);
                    amt.setText(points);
                    Glide.with(getApplication()).load(response.body().getData().getLogoLink()).error(R.drawable.ic_launcher_foreground).into(img);

                    Log.i(TAG, "  success to API." + response);
                    //Toast.makeText(getApplicationContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();
                }


                else  {
                    Log.i(TAG, "post not submitted to API." + response);
                   // Toast.makeText(getApplicationContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetUserRewardTokens> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            super.onBackPressed(); //replaced
        }
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

  /*  class LoadProfile extends AsyncTask<String, String, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RedeemDescriptionActivity.this);
            pDialog.setMessage("Loading profile ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        *//**
         * getting Profile JSON
         * *//*
        protected String doInBackground(String... args) {
            // Building Parameters
            String json = null;
            try {
                List<GetAllUserRewardPoints> params = new ArrayList<GetAllUserRewardPoints>();
               // params.add(new BasicNameValuePair("username", "admin"));

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(PROFILE_URL);
                httppost.setEntity(new UrlEncodedFormEntity(params));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity resEntity = response.getEntity();
                json = EntityUtils.toString(resEntity);

                Log.i("Profile JSON: ", json.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            try
            {
                hay = new JSONObject(json);
                JSONArray user = hay.getJSONArray("user");
                JSONObject jb= user.getJSONObject(0);
                String firstname = jb.getString("firstname");
                String middlename = jb.getString("middlename");
                String lastname = jb.getString("lastname");

                // displaying all data in textview

                txtFname.setText("Firstname: " + firstname);
                txtMname.setText("Middle Name: " + middlename);
                txtLname.setText("Last Name " + lastname);
            }catch(Exception e)
            {
                e.printStackTrace();
            }

        }

    }*/

}
