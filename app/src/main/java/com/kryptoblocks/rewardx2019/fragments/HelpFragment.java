package com.kryptoblocks.rewardx2019.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryptoblocks.rewardx2019.MainActivity;
import com.kryptoblocks.rewardx2019.R;

import static com.kryptoblocks.rewardx2019.MainActivity.tab_home;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {


    public HelpFragment() {
        // Required empty public constructor
    }

    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        tab_home.setVisibility(View.GONE);
        getActivity().setTitle("Help");
       /* collapsingToolbarLayout = view.findViewById(R.id.collapsingToolbarLayout1);
        collapsingToolbarLayout.setTitle("Title collapsing");
        collapsingToolbarLayout.setContentScrimColor(Color.BLUE);*/

      //  setTitle();
        return view;
    }

}
