package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kryptoblocks.rewardx2019.ExpandedSearchActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.pojo.SearchAllSubCategories;
import com.kryptoblocks.rewardx2019.pojo.SearchAllSubCategoriesData;
import com.kryptoblocks.rewardx2019.pojo.SearchMainCategoriesData;

import java.util.List;

public class SearchSubCategoriesAdapter extends RecyclerView.Adapter<SearchSubCategoriesAdapter.MyViewHolder>{

    Context mContext;
    List<SearchAllSubCategoriesData> subCategories;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView search_text;
        public LinearLayout image;


        public MyViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.main_category_search_layout_image);
            search_text = view.findViewById(R.id.main_category_search_text);
        }
    }


    public SearchSubCategoriesAdapter(Context mContext, List<SearchAllSubCategoriesData> searchAllSubCategories) {
        this.mContext = mContext;
        this.subCategories = searchAllSubCategories;
    }
    @Override
    public SearchSubCategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_search_for_categories, parent, false);
        return new SearchSubCategoriesAdapter.MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(SearchSubCategoriesAdapter.MyViewHolder holder, int position) {

        SearchAllSubCategoriesData list = subCategories.get(position);

        holder.search_text.setText(String.valueOf(list.getElementName()));
        //  Glide.with(mContext).load(list.getLogoLink()).error(R.drawable.ic_launcher_foreground).into(holder.img_discover);

    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }
}
