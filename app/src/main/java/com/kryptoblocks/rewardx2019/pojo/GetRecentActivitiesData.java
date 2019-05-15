package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRecentActivitiesData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("user_uuid")
    @Expose
    private String userUuid;
    @SerializedName("login_name")
    @Expose
    private String loginName;
    @SerializedName("redeem_points")
    @Expose
    private String redeemPoints;
    @SerializedName("vendor_uuid")
    @Expose
    private String vendorUuid;
    @SerializedName("redeem_date_time")
    @Expose
    private String redeemDateTime;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("uuid")
    @Expose
    private String uuid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRedeemPoints() {
        return redeemPoints;
    }

    public void setRedeemPoints(String redeemPoints) {
        this.redeemPoints = redeemPoints;
    }

    public String getVendorUuid() {
        return vendorUuid;
    }

    public void setVendorUuid(String vendorUuid) {
        this.vendorUuid = vendorUuid;
    }

    public String getRedeemDateTime() {
        return redeemDateTime;
    }

    public void setRedeemDateTime(String redeemDateTime) {
        this.redeemDateTime = redeemDateTime;
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

}
