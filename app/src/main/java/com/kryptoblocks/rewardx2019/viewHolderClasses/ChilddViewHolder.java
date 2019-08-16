package com.kryptoblocks.rewardx2019.viewHolderClasses;

import android.view.View;
import android.widget.TextView;


import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.ChildPojo;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ChilddViewHolder extends ChildViewHolder {

    private TextView childName;

    public ChilddViewHolder(View itemView) {
        super(itemView);
        childName = itemView.findViewById(R.id.parent_item_name);
    }

    public void bind(ChildPojo childPojo) {
        childName.setText(childPojo.getChild_name());
    }

    public void setChild_name(String child_name) {
       childName.setText(child_name);
    }
}


