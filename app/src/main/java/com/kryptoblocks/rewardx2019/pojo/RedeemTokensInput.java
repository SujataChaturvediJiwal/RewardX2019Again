package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedeemTokensInput {
    @SerializedName("user_uuid")
    @Expose
    private String userUuid;
    @SerializedName("redeem_points")
    @Expose
    private String redeemPoints;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getRedeemPoints() {
        return redeemPoints;
    }

    public void setRedeemPoints(String redeemPoints) {
        this.redeemPoints = redeemPoints;
    }
}
