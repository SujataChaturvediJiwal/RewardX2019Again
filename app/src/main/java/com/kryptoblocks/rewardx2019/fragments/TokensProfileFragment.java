package com.kryptoblocks.rewardx2019.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kryptoblocks.rewardx2019.CashOutActivity;
import com.kryptoblocks.rewardx2019.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TokensProfileFragment extends Fragment {


    public TokensProfileFragment() {
        // Required empty public constructor
    }

    TextView cashOut_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tokens_profile, container, false);


        cashOut_text = view.findViewById(R.id.cash_out_textView);

        cashOut_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CashOutActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

}
