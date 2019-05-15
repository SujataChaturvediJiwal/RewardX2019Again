package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kryptoblocks.rewardx2019.ExpandedSearchActivity;
import com.kryptoblocks.rewardx2019.MainActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivitiesData;
import com.kryptoblocks.rewardx2019.pojo.SearchAllSubCategories;
import com.kryptoblocks.rewardx2019.pojo.SearchMainCategories;
import com.kryptoblocks.rewardx2019.pojo.SearchMainCategoriesData;

import java.util.ArrayList;
import java.util.List;

import static com.kryptoblocks.rewardx2019.ExpandedSearchActivity.selected_item_clicked;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SearchMainCategoriesAdapter extends RecyclerView.Adapter<SearchMainCategoriesAdapter.MyViewHolder>{

    Context mContext;
    List<SearchMainCategoriesData> searchMainCategoriesDataList;
    public static String search_category_id;
    ApiInterface apiInterface;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  search_text;
        public LinearLayout image;


        public MyViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.main_category_search_layout_image);
            search_text = view.findViewById(R.id.main_category_search_text);
            }
    }


    public SearchMainCategoriesAdapter(Context mContext, List<SearchMainCategoriesData> searchMainCategoriesData) {
        this.mContext = mContext;
        this.searchMainCategoriesDataList = searchMainCategoriesData;
    }
    @Override
    public SearchMainCategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_search_for_categories, parent, false);
        return new SearchMainCategoriesAdapter.MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(SearchMainCategoriesAdapter.MyViewHolder holder, int position) {

      final  SearchMainCategoriesData list = searchMainCategoriesDataList.get(position);

        holder.search_text.setText(String.valueOf(list.getElementName()));
      //  Glide.with(mContext).load(list.getLogoLink()).error(R.drawable.ic_launcher_foreground).into(holder.img_discover);



        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                search_category_id = list.getParentElementUuid();
                System.out.println("Id----------------------"+search_category_id);

                  selected_item_clicked = true;

                Intent i = new Intent(mContext, ExpandedSearchActivity.class);
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return searchMainCategoriesDataList.size();
    }

}
