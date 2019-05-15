package com.kryptoblocks.rewardx2019.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryptoblocks.rewardx2019.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterMenuOptionsFragment extends Fragment {


    public FilterMenuOptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_options, container, false);

        return view;
    }

}
