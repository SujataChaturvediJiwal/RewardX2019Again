package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kryptoblocks.rewardx2019.CoffeeShopRewardsActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.GetAllIncentivesData;
import com.kryptoblocks.rewardx2019.pojo.GetRegsiteredVendorsData;

import java.util.List;

public class ParticularVendorRewardsAdapter extends RecyclerView.Adapter<ParticularVendorRewardsAdapter.MyViewHolder>{

    Context mContext;
    List<GetAllIncentivesData> getAllIncentivesDatatList;
    public static String single_reward_product_uuid;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView all_reward_amt, all_reward_name;
        ImageView all_reward_img;
        LinearLayout linear_layout_allRewards_ofVendor;


        public MyViewHolder(View view) {
            super(view);

            all_reward_amt = view.findViewById(R.id.reward_provider_reward_value_textView);
            all_reward_name = view.findViewById(R.id.reward_provider_name_textView);
            all_reward_img = view.findViewById(R.id.reward_provider_image);
            linear_layout_allRewards_ofVendor = view.findViewById(R.id.linear_layout_particularVendorRewards);
        }
    }


    public ParticularVendorRewardsAdapter(Context mContext, List<GetAllIncentivesData> getAllIncentivesData) {
        this.mContext = mContext;
        this.getAllIncentivesDatatList = getAllIncentivesData;
    }
    @Override
    public ParticularVendorRewardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_all_vendor_rewards, parent, false);
        return new ParticularVendorRewardsAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ParticularVendorRewardsAdapter.MyViewHolder holder, int position) {

        final GetAllIncentivesData list = getAllIncentivesDatatList.get(position);

        // holder.token_img.setText(String.valueOf(list.getTokens()));
        /*Glide.with(mContext)
                .load(list.getToken_image())
                .into(holder.token_img);*/

        holder.all_reward_amt.setText(String.valueOf(list.getTotalPoints()));
        holder.all_reward_name.setText(String.valueOf(list.getName()));
        Glide.with(mContext).load(list.getImageLink()).error(R.drawable.ic_launcher_foreground).into(holder.all_reward_img);
        //holder.act_date.setText(String.valueOf(list.getActivity_date()));
        holder.linear_layout_allRewards_ofVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                single_reward_product_uuid = list.getUuid();
                Intent i = new Intent(mContext, CoffeeShopRewardsActivity.class);
                mContext.startActivity(i);


            }
        });

        }

    @Override
    public int getItemCount() {
        return getAllIncentivesDatatList.size();
    }


}


