package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendors;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorsData;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivitiesData;

import java.util.List;

public class AllVendorsListAdapter extends RecyclerView.Adapter<AllVendorsListAdapter.MyViewHolder> {

    Context mContext;
    List<GetAllVendorsData> getAllVendorsData;
    public String activities_date, activities_converted_date;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView vendor_names;
        public ImageView img;


        public MyViewHolder(View view) {
            super(view);
            vendor_names = view.findViewById(R.id.vendor_names_list);
            img = view.findViewById(R.id.vendor_image);
            }
    }


    public AllVendorsListAdapter(Context mContext, List<GetAllVendorsData> vendorsData) {
        this.mContext = mContext;
        this.getAllVendorsData = vendorsData;
    }
    @Override
    public AllVendorsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_vendor_names, parent, false);
        return new AllVendorsListAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(AllVendorsListAdapter.MyViewHolder holder, int position) {
        GetAllVendorsData list = getAllVendorsData.get(position);

        holder.vendor_names.setText(list.getVendorName());
        Glide.with(mContext).load(list.getLogoLink()).error(R.drawable.ic_launcher_foreground).into(holder.img);
        System.out.println("vendor name activities adapter------"+list+"------------"+list.getVendorName());

        System.out.println("Size of activities adapter------"+getAllVendorsData.size());

    }

    @Override
    public int getItemCount() {
        return getAllVendorsData.size();
    }

}
