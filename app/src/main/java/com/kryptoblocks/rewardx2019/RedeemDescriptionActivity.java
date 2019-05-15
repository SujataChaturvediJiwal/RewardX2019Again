package com.kryptoblocks.rewardx2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetParticularRewardDetails;
import com.kryptoblocks.rewardx2019.pojo.GetUserRewardTokens;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
import static com.kryptoblocks.rewardx2019.adapter.DisplayRedeemTokensAdapter.redeem_vendor_uuid;
import static com.kryptoblocks.rewardx2019.adapter.ParticularVendorRewardsAdapter.single_reward_product_uuid;

public class RedeemDescriptionActivity extends AppCompatActivity {


    TextView redeem_vendor_name, amt;
    ImageView img;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_description);

        redeem_vendor_name = findViewById(R.id.redeem_details_vendor_name);
        amt = findViewById(R.id.redeem_details_num_tokens);
        img = findViewById(R.id.redeem_details_image);

        displayParticularRewardDetails();
    }


    public void displayParticularRewardDetails() {

        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);


        Call<GetUserRewardTokens> call1 = apiInterface.getUserRewardPoints(user_uuid,redeem_vendor_uuid);

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
                    Toast.makeText(getApplicationContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();
                }


                else  {
                    Log.i(TAG, "post not submitted to API." + response);
                    Toast.makeText(getApplicationContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetUserRewardTokens> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

}
