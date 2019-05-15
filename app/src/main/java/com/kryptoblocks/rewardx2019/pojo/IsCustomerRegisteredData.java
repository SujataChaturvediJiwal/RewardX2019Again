package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsCustomerRegisteredData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("customer_uuid")
    @Expose
    private String customerUuid;
    @SerializedName("vendor_uuid")
    @Expose
    private String vendorUuid;
    @SerializedName("registered_via")
    @Expose
    private String registeredVia;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerUuid() {
        return customerUuid;
    }

    public void setCustomerUuid(String customerUuid) {
        this.customerUuid = customerUuid;
    }

    public String getVendorUuid() {
        return vendorUuid;
    }

    public void setVendorUuid(String vendorUuid) {
        this.vendorUuid = vendorUuid;
    }

    public String getRegisteredVia() {
        return registeredVia;
    }

    public void setRegisteredVia(String registeredVia) {
        this.registeredVia = registeredVia;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

}
