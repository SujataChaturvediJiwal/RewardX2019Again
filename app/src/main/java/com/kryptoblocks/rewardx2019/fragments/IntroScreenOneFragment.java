package com.kryptoblocks.rewardx2019.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kryptoblocks.rewardx2019.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroScreenOneFragment extends Fragment {


    TextView btn_next_one;

    private void IntroScreenTwoFragment() {

        IntroScreenTwoFragment introScreenTwoFragment = new IntroScreenTwoFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.first_launch_frame, introScreenTwoFragment);
        fragmentTransaction.commit();
    }

    public IntroScreenOneFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro_screen_one, container, false);

        btn_next_one = view.findViewById(R.id.next_intro_screen_one);
        btn_next_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntroScreenTwoFragment();
            }
        });

        return view;
    }

}
