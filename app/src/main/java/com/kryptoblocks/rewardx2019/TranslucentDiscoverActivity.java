package com.kryptoblocks.rewardx2019;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kryptoblocks.rewardx2019.fragments.AccountsFragment;
import com.kryptoblocks.rewardx2019.fragments.DiscoverFragment;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.kryptoblocks.rewardx2019.DiscoverDescriptionActivity.discoverDescriptionActivity;

public class TranslucentDiscoverActivity extends AppCompatActivity {

    TextView textView_back, join;
    ImageView img_close;
    DiscoverFragment discoverFragment;
    public static int flag_join;

    public void DiscoverFragment() {

        if (discoverFragment != null) {
            discoverFragment = new DiscoverFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, discoverFragment);
            //fragmentTransaction.addToBackstack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent_discover);

        img_close = findViewById(R.id.translucent_back);
        join = findViewById(R.id.join_now_discover);

        flag_join = 0;

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_join = 1;
                Intent i = new Intent(getApplication(), AddProgramActivity.class);
                startActivity(i);
                System.out.println("flag join value-----"+flag_join);
            }
        });

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                discoverDescriptionActivity.finish();
             Intent i = new Intent(TranslucentDiscoverActivity.this, MainActivity.class);
             startActivity(i);
              DiscoverFragment();
                //onBackPressed();
                //finish();
                //onBackPressed();
            }
        });

       // onBackPressed();
        }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(TranslucentDiscoverActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        new DiscoverFragment();
        finish();
    }
}
