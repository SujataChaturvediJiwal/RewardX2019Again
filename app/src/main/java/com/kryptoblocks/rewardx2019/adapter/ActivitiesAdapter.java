package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.ActivitiesPojo;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivitiesData;

import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.MyViewHolder>{

    Context mContext;
    List<GetRecentActivitiesData> activitiesPojoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView number_tokens, company_name, activity_type, act_date;


        public MyViewHolder(View view) {
            super(view);

            number_tokens = view.findViewById(R.id.activity_token_value);
            company_name = view.findViewById(R.id.activity_company_name);
            activity_type = view.findViewById(R.id.activity_type);
            act_date = view.findViewById(R.id.activity_date);

        }
    }


    public ActivitiesAdapter(Context mContext, List<GetRecentActivitiesData> activitiesPojos) {
        this.mContext = mContext;
        this.activitiesPojoList = activitiesPojos;
    }
    @Override
    public ActivitiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_activities_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ActivitiesAdapter.MyViewHolder holder, int position) {

        GetRecentActivitiesData list = activitiesPojoList.get(position);

        holder.number_tokens.setText(String.valueOf(list.getRedeemPoints()));
        holder.company_name.setText(String.valueOf(list.getLoginName()));
        holder.activity_type.setText(String.valueOf(list.getRemarks()));
        holder.act_date.setText(String.valueOf(list.getRedeemDateTime()));

        }

    @Override
    public int getItemCount() {
        return activitiesPojoList.size();
    }


}
