package com.kryptoblocks.rewardx2019;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.fragments.OffersFragment;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetParticularRewardDetails;
import com.kryptoblocks.rewardx2019.pojo.GetVendorOfferById;
import com.kryptoblocks.rewardx2019.pojo.GetVendorOfferByIdData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.kryptoblocks.rewardx2019.adapter.OffersAdapter.offer_id;
import static com.kryptoblocks.rewardx2019.adapter.OffersAdapter.vendor_id;
import static com.kryptoblocks.rewardx2019.adapter.ParticularVendorRewardsAdapter.single_reward_product_uuid;

public class OfferDescriptionActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    ConstraintLayout image_offer_desc;
    TextView token_value_offer_desc, merchant_name_offer_desc,percentage_amt_offer_desc, end_date_offer_desc;
    ImageView img_back_arrow_offer_description;
    String offer_desc_date, date_offers_desc;

    private void OffersFragment() {

        OffersFragment offersFragment = new OffersFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, offersFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_description);

        image_offer_desc = findViewById(R.id.offer_description_image);
        token_value_offer_desc = findViewById(R.id.offer_description_tokenNum);
        merchant_name_offer_desc = findViewById(R.id.offer_description_merchantName);
        percentage_amt_offer_desc = findViewById(R.id.offer_description_offerPercentage);
        img_back_arrow_offer_description = findViewById(R.id.offer_description_back_arrow);
        end_date_offer_desc = findViewById(R.id.offer_description_endDate);

        img_back_arrow_offer_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        displayParticularOfferDetails();
    }

    public void displayParticularOfferDetails() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);


        Call<GetVendorOfferById> call1 = apiInterface.getVendorOfferByid(offer_id,vendor_id);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetVendorOfferById>() {


            @Override
            public void onResponse(Call<GetVendorOfferById> call, Response<GetVendorOfferById> response) {

                if (response.code() == 200) {

                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body-------------" + response.body().getData());


                    String name = response.body().getData().getOfferName();
                    String points = String.valueOf(response.body().getData().getRewardPoints());
                    String offer_perc = response.body().getData().getOfferDescription();

                    date_offers_desc = response.body().getData().getEndTime();
                    merchant_name_offer_desc.setText(name);
                    token_value_offer_desc.setText(points);
                    percentage_amt_offer_desc.setText(offer_perc);

                    dateConverter();

                    end_date_offer_desc.setText(offer_desc_date);

                    //Glide.with(getApplication()).load(response.body().getData().getOfferImageLink()).error(R.drawable.ic_launcher_foreground).into(image_offer_desc);

                    Glide.with(getApplicationContext()).load(response.body().getData().getOfferImageLink()).asBitmap().into(new SimpleTarget<Bitmap>(80, 80) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Drawable drawable = new BitmapDrawable(getApplicationContext().getResources(), resource);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                image_offer_desc.setBackground(drawable);
                            }
                        }
                    });

                    Log.i(TAG, "  success to API." + response);
                    //Toast.makeText(getApplicationContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();
                }


                else  {
                    Log.i(TAG, "post not submitted to API." + response);
                    //Toast.makeText(getApplicationContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetVendorOfferById> call, Throwable t) {
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

    void dateConverter() {
        Toast.makeText(getApplicationContext(), "date function called", Toast.LENGTH_SHORT).show();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date d = sdf.parse(date_offers_desc);
            // Toast.makeText(mContext, "date-========="+d, Toast.LENGTH_SHORT).show();
            sdf = new SimpleDateFormat("dd MMM yyyy");
            offer_desc_date = sdf.format(d);
            System.out.println("date-----"+sdf.format(d));
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }
    }
}
