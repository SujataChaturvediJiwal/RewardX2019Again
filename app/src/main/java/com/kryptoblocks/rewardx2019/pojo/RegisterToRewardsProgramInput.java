package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterToRewardsProgramInput {

    @SerializedName("user_uuid")
    @Expose
    private String userUuid;
    @SerializedName("vendor_uuid")
    @Expose
    private String vendorUuid;
    @SerializedName("membership_uuid")
    @Expose
    private String membershipUuid;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getVendorUuid() {
        return vendorUuid;
    }

    public void setVendorUuid(String vendorUuid) {
        this.vendorUuid = vendorUuid;
    }

    public String getMembershipUuid() {
        return membershipUuid;
    }

    public void setMembershipUuid(String membershipUuid) {
        this.membershipUuid = membershipUuid;
    }
}
