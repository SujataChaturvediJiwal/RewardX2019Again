package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllUserRewardPointsData {
    @SerializedName("user_uuid")
    @Expose
    private String userUuid;
    @SerializedName("vendor_uuid")
    @Expose
    private String vendorUuid;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("total_points")
    @Expose
    private Integer totalPoints;
    @SerializedName("logo_link")
    @Expose
    private String logoLink;

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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }


}
