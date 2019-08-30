package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRecentActivitiesData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("redeem_date_time")
    @Expose
    private String redeemDateTime;
    @SerializedName("owner_uuid")
    @Expose
    private String ownerUuid;
    @SerializedName("membership_uuid")
    @Expose
    private String membershipUuid;
    @SerializedName("redeem_points")
    @Expose
    private String redeemPoints;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("is_processable")
    @Expose
    private Boolean isProcessable;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("reward_points")
    @Expose
    private Integer rewardPoints;
    @SerializedName("date_time_allocated")
    @Expose
    private String dateTimeAllocated;
    @SerializedName("vendor_uuid")
    @Expose
    private String vendorUuid;
    @SerializedName("incentive_uuid")
    @Expose
    private String incentiveUuid;
    @SerializedName("incentive_name")
    @Expose
    private String incentiveName;


    @SerializedName("customerPOS_uuid")
    @Expose
    private String customerPOSUuid;
    @SerializedName("incentive_name")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    public String getMembershipUuid() {
        return membershipUuid;
    }

    public void setMembershipUuid(String membershipUuid) {
        this.membershipUuid = membershipUuid;
    }

    public String getRedeemPoints() {
        return redeemPoints;
    }

    public void setRedeemPoints(String redeemPoints) {
        this.redeemPoints = redeemPoints;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Boolean getIsProcessable() {
        return isProcessable;
    }

    public void setIsProcessable(Boolean isProcessable) {
        this.isProcessable = isProcessable;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getDateTimeAllocated() {
        return dateTimeAllocated;
    }

    public void setDateTimeAllocated(String dateTimeAllocated) {
        this.dateTimeAllocated = dateTimeAllocated;
    }

    public String getVendorUuid() {
        return vendorUuid;
    }

    public void setVendorUuid(String vendorUuid) {
        this.vendorUuid = vendorUuid;
    }

    public String getIncentiveUuid() {
        return incentiveUuid;
    }

    public void setIncentiveUuid(String incentiveUuid) {
        this.incentiveUuid = incentiveUuid;
    }

    public String getIncentiveName() {
        return incentiveName;
    }

    public void setIncentiveName(String incentiveName) {
        this.incentiveName = incentiveName;
    }

    public String getCustomerPOSUuid() { return customerPOSUuid; }

    public void setCustomerPOSUuid(String customerPOSUuid) { this.customerPOSUuid = customerPOSUuid; }

}
