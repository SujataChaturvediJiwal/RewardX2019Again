package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRegsiteredVendorsData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("working_days")
    @Expose
    private DiscoverWorkingDays workingDays;
    @SerializedName("profile_link")
    @Expose
    private String profileLink;
    @SerializedName("cover_link")
    @Expose
    private String coverLink;
    @SerializedName("logo_link")
    @Expose
    private String logoLink;
    @SerializedName("company_category_uuid")
    @Expose
    private String companyCategoryUuid;
    @SerializedName("company_category_code")
    @Expose
    private String companyCategoryCode;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DiscoverWorkingDays getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(DiscoverWorkingDays workingDays) {
        this.workingDays = workingDays;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    public String getCompanyCategoryUuid() {
        return companyCategoryUuid;
    }

    public void setCompanyCategoryUuid(String companyCategoryUuid) {
        this.companyCategoryUuid = companyCategoryUuid;
    }

    public String getCompanyCategoryCode() {
        return companyCategoryCode;
    }

    public void setCompanyCategoryCode(String companyCategoryCode) {
        this.companyCategoryCode = companyCategoryCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
