package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kryptoblocks.rewardx2019.ExpandedSearchActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.ActivitiesPojo;
import com.kryptoblocks.rewardx2019.pojo.DiscoverData;
import com.kryptoblocks.rewardx2019.pojo.DiscoverTabForOffers;

import java.util.List;

public class DiscoverTabForOffersAdapter extends RecyclerView.Adapter<DiscoverTabForOffersAdapter.MyViewHolder>{

    Context mContext;
    List<DiscoverData> discoverTabForOffersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text1, text2;
        public LinearLayout linearLayout_image;


        public MyViewHolder(View view) {
            super(view);

            text1 = view.findViewById(R.id.text1_forDiscoverOffers);
            text2 = view.findViewById(R.id.text2_forDiscoverOffers);
            linearLayout_image = view.findViewById(R.id.image_layout_forDiscoverOffers);
        }
    }


    public DiscoverTabForOffersAdapter(Context mContext, List<DiscoverData> discoverTabForOffers) {
        this.mContext = mContext;
        this.discoverTabForOffersList = discoverTabForOffers;
    }
    @Override
    public DiscoverTabForOffersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_discover_top_brands, parent, false);
        return new DiscoverTabForOffersAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final DiscoverTabForOffersAdapter.MyViewHolder holder, int position) {

        DiscoverData list = discoverTabForOffersList.get(position);

       holder.text1.setText(String.valueOf(list.getCompanyCategoryCode()));
        holder.text2.setText(String.valueOf(list.getVendorName()));
      //  Glide.with(mContext).load(list.getOffer_image()).error(R.drawable.ic_launcher_foreground).into(holder.image);
        Glide.with(mContext).load(list.getLogoLink()).asBitmap().into(new SimpleTarget<Bitmap>(80, 80) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(mContext.getResources(), resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    holder.linearLayout_image.setBackground(drawable);
                }
            }
        });

        holder.linearLayout_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ExpandedSearchActivity.class);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return discoverTabForOffersList.size();
    }

}
