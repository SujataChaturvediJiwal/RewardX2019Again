package com.kryptoblocks.rewardx2019.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.adapter.DiscoverTabForOffersAdapter;
import com.kryptoblocks.rewardx2019.pojo.ActivitiesPojo;
import com.kryptoblocks.rewardx2019.pojo.DiscoverTabForOffers;

import java.util.List;

import static com.kryptoblocks.rewardx2019.MainActivity.tab_home;
import static com.kryptoblocks.rewardx2019.MainActivity.toolbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountsFragment extends Fragment {



    public AccountsFragment() {
        // Required empty public constructor
    }

RadioButton cardpayment_radioBtn, netBanking_radioBtn;
    LinearLayout addCard_layout, netBanking_layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);

        tab_home.setVisibility(View.GONE);
       // toolbar.setVisibility(View.GONE);



        cardpayment_radioBtn = view.findViewById(R.id.radio_button_cardPayment);
        addCard_layout = view.findViewById(R.id.layout_on_radioButton_click_addCard);

        netBanking_radioBtn = view.findViewById(R.id.radio_button_netbanking);
        netBanking_layout = view.findViewById(R.id.layout_on_radioButton_netbanking);

        cardpayment_radioBtn.setChecked(false);
        netBanking_radioBtn.setChecked(false);
        addCard_layout.setVisibility(View.GONE);
        netBanking_layout.setVisibility(View.GONE);

        cardpayment_radioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addCard_layout.setVisibility(View.VISIBLE);
                netBanking_layout.setVisibility(View.GONE);
                netBanking_radioBtn.setChecked(false);

                }
        });

        netBanking_radioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                netBanking_layout.setVisibility(View.VISIBLE);
                addCard_layout.setVisibility(View.GONE);
                cardpayment_radioBtn.setChecked(false);

                }
        });

       /* if (cardpayment_radioBtn.isChecked()) {
            if (netBanking_layout.getVisibility() == View.VISIBLE) {
                addCard_layout.setVisibility(View.VISIBLE);
                netBanking_layout.setVisibility(View.GONE);
                netBanking_radioBtn.setChecked(false);
            }
            if (netBanking_radioBtn.isChecked()) {
                netBanking_layout.setVisibility(View.VISIBLE);
                cardpayment_radioBtn.setChecked(false);
            }

        }*/

            return view;
        }


    }
