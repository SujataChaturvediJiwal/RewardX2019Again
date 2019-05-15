package com.kryptoblocks.rewardx2019.pojo;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchAllSubCategories {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<SearchAllSubCategoriesData> data = null;



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SearchAllSubCategoriesData> getData() {
        return data;
    }

    public void setData(List<SearchAllSubCategoriesData> data) {
        this.data = data;
    }
}
