package com.kryptoblocks.rewardx2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.end_time_reward_date;
import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.flagRegistered;
import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.img;
import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.offer_name;
import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.points;
import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.summary;
import static com.kryptoblocks.rewardx2019.fragments.RedeemFragment.mypreferenceLogin;

public class DiscoverDescriptionActivity extends AppCompatActivity {


    ImageView img_discover_desc, back_arrow_discover_desc;
    TextView text_points, text_offer_name, text_vendor_name, text_summary, expiry_date;
    SharedPreferences sharedPreferencesDicoverDesc;
    public static DiscoverDescriptionActivity discoverDescriptionActivity;
    String vendor_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_description);

        discoverDescriptionActivity = new DiscoverDescriptionActivity();

        text_offer_name = findViewById(R.id.offerName_discover_desc);
        text_points = findViewById(R.id.rewards_discover_desc);
        text_vendor_name = findViewById(R.id.vendor_name_discover_desc);
        img_discover_desc = findViewById(R.id.img_discover_desc);
        back_arrow_discover_desc = findViewById(R.id.discover_desc_back_arrow);
        text_summary = findViewById(R.id.summary_discover_desc);
        expiry_date = findViewById(R.id.expiry_date_reward_tv);

        back_arrow_discover_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        text_points.setText(points );
        text_offer_name.setText(offer_name );
        text_summary.setText(summary);

        sharedPreferencesDicoverDesc =getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        vendor_name = sharedPreferencesDicoverDesc.getString("vendor_discover_name","hi");
        text_vendor_name.setText(vendor_name);
        expiry_date.setText(end_time_reward_date);
        //text_vendor_name.setText();

        Glide.with(getApplication()).load(img).error(R.drawable.ic_launcher_foreground).into(img_discover_desc);

        //code here
        if(!(flagRegistered==1)) {
            System.out.println("flagRegistered in activity-----" + flagRegistered);
            Intent i = new Intent(this, TranslucentDiscoverActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        //finish();

    }

}
