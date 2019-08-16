package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllVendorsData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("host_url")
    @Expose
    private String hostUrl;
    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("owner_uuid")
    @Expose
    private String ownerUuid;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("vendor_uuid")
    @Expose
    private String vendorUuid;
    @SerializedName("logo_link")
    @Expose
    private String logoLink;

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

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorUuid() {
        return vendorUuid;
    }

    public void setVendorUuid(String vendorUuid) {
        this.vendorUuid = vendorUuid;
    }

    public String getLogoLink() { return logoLink; }

    public void setLogoLink(String logoLink) { this.logoLink = logoLink; }


}
