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
import com.kryptoblocks.rewardx2019.OfferDescriptionActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.ActivitiesPojo;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorOffersData;
import com.kryptoblocks.rewardx2019.pojo.OffersPojo;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder>{

    Context mContext;
    List<GetAllVendorOffersData> offersPojoList;
    public static String offer_id, vendor_id;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView offer_description, offer_shop_name, offer_discount, offer_token;
        public ImageView img_offer;
        public LinearLayout layout_for_offers;



        public MyViewHolder(View view) {
            super(view);

            offer_description = view.findViewById(R.id.offer_title_name);
            offer_shop_name = view.findViewById(R.id.offer_shop_name);
            offer_discount = view.findViewById(R.id.offer_discount_value);
            offer_token = view.findViewById(R.id.offer_token_value);
            img_offer = view.findViewById(R.id.offer_image);
            layout_for_offers = view.findViewById(R.id.linear_layout_offers);

        }
    }

    public OffersAdapter(Context mContext, List<GetAllVendorOffersData> offersPojos) {
        this.mContext = mContext;
        this.offersPojoList = offersPojos;
    }
    @Override
    public OffersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_offer_layout, parent, false);
        return new OffersAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(OffersAdapter.MyViewHolder holder, int position) {

        final GetAllVendorOffersData list = offersPojoList.get(position);

        holder.offer_description.setText(String.valueOf(list.getCategoryValue()));
        holder.offer_shop_name.setText(String.valueOf(list.getOfferName()));
        holder.offer_discount.setText(String.valueOf(list.getOfferDescription()));
        holder.offer_token.setText(String.valueOf(list.getRewardPoints()));
        Glide.with(mContext).load(list.getOfferImageLink()).error(R.drawable.ic_launcher_foreground).into(holder.img_offer);

        holder.layout_for_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                offer_id = list.getUuid();
                vendor_id = list.getVendorUuid();
                Intent i = new Intent(mContext, OfferDescriptionActivity.class);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return offersPojoList.size();
    }

}
