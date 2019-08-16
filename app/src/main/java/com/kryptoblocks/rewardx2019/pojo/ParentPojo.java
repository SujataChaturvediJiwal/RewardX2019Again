package com.kryptoblocks.rewardx2019.pojo;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ParentPojo extends ExpandableGroup<ChildPojo> {

    public ParentPojo(String title, List<ChildPojo> items)
    {
        super(title, items);
    }


}
