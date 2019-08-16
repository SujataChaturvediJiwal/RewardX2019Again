package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kryptoblocks.rewardx2019.AllVendorRewardsActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.GetRegsiteredVendorsData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegisteredVendorRewardsAdapter extends RecyclerView.Adapter<RegisteredVendorRewardsAdapter.MyViewHolder>{

    Context mContext;
    List<GetRegsiteredVendorsData> rewardsPojoList;
    String reward_date;
    public static String vendor_reward_uuid,vendorRewards_name;

    public String converted_date;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView reward_merch_name, reward_date, membership_id;
        public LinearLayout layout_rewards;
        public ImageView reward_img;
        public CheckBox checkbox_profile_rewards;


        public MyViewHolder(View view) {
            super(view);

            reward_merch_name = view.findViewById(R.id.reward_profile_merchant_name);
            reward_date = view.findViewById(R.id.reward_profile_date);
           // reward_img = view.findViewById(R.id.img_reward_profile);
            checkbox_profile_rewards = view.findViewById(R.id.checkbox_profile_rewards);
            layout_rewards = view.findViewById(R.id.linearLayout_rewards);
            membership_id = view.findViewById(R.id.reward_id);

            }
    }


    public RegisteredVendorRewardsAdapter(Context mContext, List<GetRegsiteredVendorsData> rewardsPojos) {
        this.mContext = mContext;
        this.rewardsPojoList = rewardsPojos;
    }
    @Override
    public RegisteredVendorRewardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        /*switch (viewType) {
            case Model.AIR_TYPE:
                View itemViewAir = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.profile_reward_single_view, parent, false);
                return new AirlineViewHolder(itemViewAir);
            case Model.SHOP_TYPE:
                View itemViewShop = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profile_reward_single_view, parent, false);
            return new CoffeeShopViewHolder(itemViewShop);

        }*/

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_reward_single_view, parent, false);
        return new RegisteredVendorRewardsAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RegisteredVendorRewardsAdapter.MyViewHolder holder, int position) {



       final GetRegsiteredVendorsData list = rewardsPojoList.get(position);

        reward_date = list.getRegistrationDate();
       dateConverter();

       // String merc = list.getReward_merchant_name();

        holder.reward_merch_name.setText(String.valueOf(list.getOwnerName()));
        holder.membership_id.setText(list.getMembershipUuid());
        holder.reward_date.setText(converted_date);
       // Glide.with(mContext).load(list.getLogoLink()).error(R.drawable.ic_launcher_foreground).into(holder.reward_img);
        if(holder.checkbox_profile_rewards.isChecked())
        {
            holder.checkbox_profile_rewards.setBackgroundColor(mContext.getResources().getColor(R.color.colorLightBlue));
        }
        holder.layout_rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* vendor_reward_uuid = list.getUuid();
                System.out.println("Value of vendor reward uuid-----------"+vendor_reward_uuid);
                vendorRewards_name =list.getOwnerName();
                Intent i = new Intent(mContext, AllVendorRewardsActivity.class);
                mContext.startActivity(i);*/
            }
        });

       /* if(merc == "Red Airline") {
            holder.layout_rewards.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, AirlineRedeemActivity.class);
                    mContext.startActivity(i);
                }
            });
        }
        else if (merc == "Slate Gym")
        {
            holder.layout_rewards.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, CoffeeShopRewardsActivity.class);
                    mContext.startActivity(i);
                }
            });
        }*/

        /*switch (holder.getItemViewType()) {
            case 0:
                AirlineViewHolder viewHolder0 = (AirlineViewHolder) holder;
                ...
                break;

            case 2:
                CoffeeShopViewHolder viewHolder2 = (CoffeeShopViewHolder) holder;
                ...
                break;
        }*/

    }
/*
    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2 * 2;
    }*/
    @Override
    public int getItemCount() {
        return rewardsPojoList.size();
    }



   /* private class AirlineViewHolder extends MyViewHolder {
        public AirlineViewHolder(View airLineView) {
            super(airLineView);
        }
    }

    private class CoffeeShopViewHolder extends MyViewHolder {
        public CoffeeShopViewHolder(View coffeeShopView) {
            super(coffeeShopView);
        }

    }*/

    void dateConverter() {
       // Toast.makeText(mContext, "date function called", Toast.LENGTH_SHORT).show();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date d = sdf.parse(reward_date);
           // Toast.makeText(mContext, "date-========="+d, Toast.LENGTH_SHORT).show();
            sdf = new SimpleDateFormat("dd MMM yyyy");
            converted_date = sdf.format(d);
            System.out.println("date-----"+sdf.format(d));
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }
    }


    }


