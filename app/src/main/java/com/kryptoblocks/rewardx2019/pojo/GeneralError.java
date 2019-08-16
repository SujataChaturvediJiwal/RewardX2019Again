package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralError {

        @SerializedName("error")
        @Expose
        private String error;

        public String getError() { return error; }

        public void setError(String error) { this.error = error; }
    }

