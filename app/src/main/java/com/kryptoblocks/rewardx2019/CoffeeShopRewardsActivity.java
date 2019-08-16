package com.kryptoblocks.rewardx2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.kryptoblocks.rewardx2019.adapter.ParticularVendorRewardsAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.fragments.RewardsProfileFragment;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetAllIncentives;
import com.kryptoblocks.rewardx2019.pojo.GetAllIncentivesData;
import com.kryptoblocks.rewardx2019.pojo.GetParticularRewardDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
import static com.kryptoblocks.rewardx2019.adapter.ParticularVendorRewardsAdapter.single_reward_product_uuid;
import static com.kryptoblocks.rewardx2019.adapter.RegisteredVendorRewardsAdapter.vendor_reward_uuid;

public class CoffeeShopRewardsActivity extends AppCompatActivity {

ApiInterface apiInterface;


    private void RewardsProfileFragment() {

        RewardsProfileFragment rewardsProfileFragment = new RewardsProfileFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.profile_fragment, rewardsProfileFragment);
        fragmentTransaction.commit();
    }

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;
    TextView text_place_order,single_reward_name, token_num, remaining_daysRewards, user_id_regsiteredVendorRewards ;
    ImageView back_arrow_rewards, small_coffee_image, barcode_image;
    ImageView img_reward_single;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesCoffeeShop;
    String user_id_coffee_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_shop_rewards);

        text_place_order = findViewById(R.id.order_place_textView);

        back_arrow_rewards = findViewById(R.id.back_arrow_coffee_rewards);

        single_reward_name = findViewById(R.id.single_reward_name);
        img_reward_single = findViewById(R.id.single_reward_image);
        token_num = findViewById(R.id.number_of_tokens);
        barcode_image = findViewById(R.id.barcode_descriptionOfRegisteredRewards);
        user_id_regsiteredVendorRewards = findViewById(R.id.user_id_in_vendorRegisteredRewards);

        sharedPreferencesCoffeeShop = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_coffee_shop = sharedPreferencesCoffeeShop.getString("user_unique_id","hi");

        Toast.makeText(CoffeeShopRewardsActivity.this, "User id:" + user_id_coffee_shop , Toast.LENGTH_SHORT).show();

        user_id_regsiteredVendorRewards.setText(user_id_coffee_shop);


       /* other_name_coffee = findViewById(R.id.other_coffee_name);
        small_coffee_image = findViewById(R.id.coffee_shop_image_small);
        remaining_daysRewards = findViewById(R.id.remaining_days_forRewards);*/


        text_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(), PayInStoreActivity.class);
                startActivity(i);
            }
        });

        back_arrow_rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(CoffeeShopRewardsActivity.this, AllVendorRewardsActivity.class );
              startActivity(i);
                Toast.makeText(CoffeeShopRewardsActivity.this, "hiiiiiii", Toast.LENGTH_SHORT).show();
            }
        });

        displayParticularRewardDetails();

        //barcode
        LinearLayout l = new LinearLayout(this);
        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        l.setOrientation(LinearLayout.VERTICAL);

        // setContentView(l);

        // barcode data  1bd6b950-664e-11e9-8dc2-5f9ee5638d6e

        // barcode image
        Bitmap bitmap = null;
        ImageView iv = new ImageView(this);

        try {

            bitmap = encodeAsBitmap(user_id_coffee_shop, BarcodeFormat.CODE_128, 1500, 400);
            barcode_image.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void displayParticularRewardDetails() {


        apiInterface =  ApiClient.getInstance().getClient2().create(ApiInterface.class);


        Call<GetParticularRewardDetails> call1 = apiInterface.getParticularIncentiveDetails(single_reward_product_uuid);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetParticularRewardDetails>() {


            @Override
            public void onResponse(Call<GetParticularRewardDetails> call, Response<GetParticularRewardDetails> response) {

                if (response.code() == 200) {

                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());


                   String name = response.body().getData().getName();
                   String points = String.valueOf(response.body().getData().getProductSpecificPoints());

                   single_reward_name.setText(name);
                    token_num.setText(points);
                    Glide.with(getApplication()).load(response.body().getData().getImageLink()).error(R.drawable.ic_launcher_foreground).into(img_reward_single);

                        Log.i(TAG, "  success to API." + response);
                        Toast.makeText(getApplicationContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();
                        }


                else  {
                    Log.i(TAG, "post not submitted to API." + response);
                    Toast.makeText(getApplicationContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetParticularRewardDetails> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    //for barcode generation
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
        }
