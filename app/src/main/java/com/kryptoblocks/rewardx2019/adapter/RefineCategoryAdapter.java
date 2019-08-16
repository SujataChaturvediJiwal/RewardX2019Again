package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.ChildPojo;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivitiesData;
import com.kryptoblocks.rewardx2019.pojo.ParentPojo;
import com.kryptoblocks.rewardx2019.viewHolderClasses.ChilddViewHolder;
import com.kryptoblocks.rewardx2019.viewHolderClasses.PraentViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class RefineCategoryAdapter extends ExpandableRecyclerViewAdapter<PraentViewHolder,ChilddViewHolder> {

    Context mContext;
    ParentPojo parentPojo;
    public String activities_date, activities_converted_date;

    public RefineCategoryAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public PraentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_category_search_parent, parent, false);
        return new PraentViewHolder(itemView);
    }

    @Override
    public ChilddViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_category_search_child, parent, false);
        return new ChilddViewHolder(itemView);
    }

    @Override
    public void onBindChildViewHolder(ChilddViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final ChildPojo artist = (ChildPojo) (group).getItems().get(childIndex);
        // holder.bind(artist);
        artist.setChild_name(artist.getChild_name());
        //holder.bind(artist);
    }


    @Override
    public void onBindGroupViewHolder(PraentViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setGenreTitle(group);
    }


}
