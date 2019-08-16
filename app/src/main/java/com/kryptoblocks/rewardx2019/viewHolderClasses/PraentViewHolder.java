package com.kryptoblocks.rewardx2019.viewHolderClasses;

import android.view.View;
import android.widget.TextView;

import com.kryptoblocks.rewardx2019.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class PraentViewHolder extends GroupViewHolder {

    private TextView ItemTitle;

    public PraentViewHolder(View itemView) {
       super(itemView);
        ItemTitle = itemView.findViewById(R.id.parent_item_name);
    }

    public void setGenreTitle(ExpandableGroup group) {

        ItemTitle.setText(group.getTitle());
    }
}
