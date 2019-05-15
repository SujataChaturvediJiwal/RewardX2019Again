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
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.RedeemDescriptionActivity;
import com.kryptoblocks.rewardx2019.pojo.GetAllUserRewardPointsData;

import java.util.List;

public class DisplayRedeemTokensAdapter extends RecyclerView.Adapter<DisplayRedeemTokensAdapter.MyViewHolder>{

    Context mContext;
    List<GetAllUserRewardPointsData> redeemTokenPojoList;
    public static String redeem_vendor_uuid;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView token_amt, token_name;
        ImageView token_img;
        public LinearLayout linear_layout_redeem;


        public MyViewHolder(View view) {
            super(view);

            token_amt = view.findViewById(R.id.token_provider_token_value_textView);
            token_img = view.findViewById(R.id.token_provider_image);
            token_name = view.findViewById(R.id.token_provider_name_textView);
            linear_layout_redeem = view.findViewById(R.id.redeem_token_linear_layout);
        }
    }


    public DisplayRedeemTokensAdapter(Context mContext, List<GetAllUserRewardPointsData> activitiesPojos) {
        this.mContext = mContext;
        this.redeemTokenPojoList = activitiesPojos;
    }
    @Override
    public DisplayRedeemTokensAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_redeem_token_layout, parent, false);
        return new DisplayRedeemTokensAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final DisplayRedeemTokensAdapter.MyViewHolder holder, int position) {

        final GetAllUserRewardPointsData list = redeemTokenPojoList.get(position);

       // holder.token_img.setText(String.valueOf(list.getTokens()));
        /*Glide.with(mContext)
                .load(list.getToken_image())
                .into(holder.token_img);*/

        holder.token_name.setText(String.valueOf(list.getVendorName()));
        holder.token_amt.setText(String.valueOf(list.getTotalPoints()));
        Glide.with(mContext).load(list.getLogoLink()).error(R.drawable.ic_launcher_foreground).into(holder.token_img);

        holder.linear_layout_redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                redeem_vendor_uuid = list.getVendorUuid();
                Intent i = new Intent(mContext, RedeemDescriptionActivity.class);
                mContext.startActivity(i);
            }
        });

        //holder.act_date.setText(String.valueOf(list.getActivity_date()));

    }

    @Override
    public int getItemCount() {
        return redeemTokenPojoList.size();
    }


}
