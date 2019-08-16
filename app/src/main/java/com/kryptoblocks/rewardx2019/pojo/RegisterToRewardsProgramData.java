package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterToRewardsProgramData {
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("owner_uuid")
    @Expose
    private String ownerUuid;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("user_uuid")
    @Expose
    private String userUuid;
    @SerializedName("vendor_uuid")
    @Expose
    private String vendorUuid;
    @SerializedName("membership_uuid")
    @Expose
    private String membershipUuid;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;
    @SerializedName("error")
    @Expose
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }


}
