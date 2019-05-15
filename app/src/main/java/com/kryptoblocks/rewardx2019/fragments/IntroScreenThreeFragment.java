package com.kryptoblocks.rewardx2019.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.SocialLoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroScreenThreeFragment extends Fragment {



    TextView btn_next_three;

    public IntroScreenThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro_screen_three, container, false);

        btn_next_three = view.findViewById(R.id.next_intro_screen_three);
        btn_next_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SocialLoginActivity.class);
                startActivity(i);
            }
        });


        return view;
    }

}
