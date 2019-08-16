package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscoverData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("image_link")
    @Expose
    private String imageLink;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("product_url")
    @Expose
    private String productUrl;
    @SerializedName("batch_count")
    @Expose
    private Integer batchCount;
    @SerializedName("product_specific_points")
    @Expose
    private Integer productSpecificPoints;
    @SerializedName("price_category_points")
    @Expose
    private Integer priceCategoryPoints;
    @SerializedName("total_points")
    @Expose
    private Integer totalPoints;
    @SerializedName("product_category_uuid")
    @Expose
    private String productCategoryUuid;
    @SerializedName("product_sub_category_uuid")
    @Expose
    private String productSubCategoryUuid;
    @SerializedName("product_upi_number")
    @Expose
    private String productUpiNumber;
    @SerializedName("product_upi_brand")
    @Expose
    private String productUpiBrand;
    @SerializedName("product_upi_mpn")
    @Expose
    private String productUpiMpn;
    @SerializedName("product_uuid")
    @Expose
    private String productUuid;
    @SerializedName("units_of_purchase")
    @Expose
    private String unitsOfPurchase;
    @SerializedName("points_category")
    @Expose
    private Integer pointsCategory;
    @SerializedName("points_sub_category")
    @Expose
    private Integer pointsSubCategory;
    @SerializedName("points_upi_number")
    @Expose
    private Integer pointsUpiNumber;
    @SerializedName("points_upi_brand")
    @Expose
    private Integer pointsUpiBrand;
    @SerializedName("points_upi_mpn")
    @Expose
    private Integer pointsUpiMpn;
    @SerializedName("points_product_name")
    @Expose
    private Integer pointsProductName;
    @SerializedName("units_of_purchase_value")
    @Expose
    private Integer unitsOfPurchaseValue;
    @SerializedName("points_units_of_purchase")
    @Expose
    private Integer pointsUnitsOfPurchase;
    @SerializedName("points_uop_variance")
    @Expose
    private Integer pointsUopVariance;
    @SerializedName("points_price_of_purchase_category")
    @Expose
    private Integer pointsPriceOfPurchaseCategory;
    @SerializedName("customer_value")
    @Expose
    private String customerValue;
    @SerializedName("points_customer_value")
    @Expose
    private Integer pointsCustomerValue;
    @SerializedName("points_cltv_status")
    @Expose
    private Integer pointsCltvStatus;
    @SerializedName("points_cltv_period")
    @Expose
    private Integer pointsCltvPeriod;
    @SerializedName("current_rule")
    @Expose
    private String currentRule;
    @SerializedName("vendor_uuid")
    @Expose
    private String vendorUuid;
    @SerializedName("price_of_purchase_category")
    @Expose
    private String priceOfPurchaseCategory;
    @SerializedName("cltv_status")
    @Expose
    private String cltvStatus;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user_uuid")
    @Expose
    private String userUuid;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("is_batch")
    @Expose
    private Boolean isBatch;
    @SerializedName("is_deleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("discount_value")
    @Expose
    private Integer discountValue;
    @SerializedName("discount_type")
    @Expose
    private String discountType;
    @SerializedName("is_verified")
    @Expose
    private Boolean isVerified;
    @SerializedName("uop_variance")
    @Expose
    private String uopVariance;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Integer getBatchCount() {
        return batchCount;
    }

    public void setBatchCount(Integer batchCount) {
        this.batchCount = batchCount;
    }

    public Integer getProductSpecificPoints() {
        return productSpecificPoints;
    }

    public void setProductSpecificPoints(Integer productSpecificPoints) {
        this.productSpecificPoints = productSpecificPoints;
    }

    public Integer getPriceCategoryPoints() {
        return priceCategoryPoints;
    }

    public void setPriceCategoryPoints(Integer priceCategoryPoints) {
        this.priceCategoryPoints = priceCategoryPoints;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getProductCategoryUuid() {
        return productCategoryUuid;
    }

    public void setProductCategoryUuid(String productCategoryUuid) {
        this.productCategoryUuid = productCategoryUuid;
    }

    public String getProductSubCategoryUuid() {
        return productSubCategoryUuid;
    }

    public void setProductSubCategoryUuid(String productSubCategoryUuid) {
        this.productSubCategoryUuid = productSubCategoryUuid;
    }

    public String getProductUpiNumber() {
        return productUpiNumber;
    }

    public void setProductUpiNumber(String productUpiNumber) {
        this.productUpiNumber = productUpiNumber;
    }

    public String getProductUpiBrand() {
        return productUpiBrand;
    }

    public void setProductUpiBrand(String productUpiBrand) {
        this.productUpiBrand = productUpiBrand;
    }

    public String getProductUpiMpn() {
        return productUpiMpn;
    }

    public void setProductUpiMpn(String productUpiMpn) {
        this.productUpiMpn = productUpiMpn;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }

    public String getUnitsOfPurchase() {
        return unitsOfPurchase;
    }

    public void setUnitsOfPurchase(String unitsOfPurchase) {
        this.unitsOfPurchase = unitsOfPurchase;
    }

    public Integer getPointsCategory() {
        return pointsCategory;
    }

    public void setPointsCategory(Integer pointsCategory) {
        this.pointsCategory = pointsCategory;
    }

    public Integer getPointsSubCategory() {
        return pointsSubCategory;
    }

    public void setPointsSubCategory(Integer pointsSubCategory) {
        this.pointsSubCategory = pointsSubCategory;
    }

    public Integer getPointsUpiNumber() {
        return pointsUpiNumber;
    }

    public void setPointsUpiNumber(Integer pointsUpiNumber) {
        this.pointsUpiNumber = pointsUpiNumber;
    }

    public Integer getPointsUpiBrand() {
        return pointsUpiBrand;
    }

    public void setPointsUpiBrand(Integer pointsUpiBrand) {
        this.pointsUpiBrand = pointsUpiBrand;
    }

    public Integer getPointsUpiMpn() {
        return pointsUpiMpn;
    }

    public void setPointsUpiMpn(Integer pointsUpiMpn) {
        this.pointsUpiMpn = pointsUpiMpn;
    }

    public Integer getPointsProductName() {
        return pointsProductName;
    }

    public void setPointsProductName(Integer pointsProductName) {
        this.pointsProductName = pointsProductName;
    }

    public Integer getUnitsOfPurchaseValue() {
        return unitsOfPurchaseValue;
    }

    public void setUnitsOfPurchaseValue(Integer unitsOfPurchaseValue) {
        this.unitsOfPurchaseValue = unitsOfPurchaseValue;
    }

    public Integer getPointsUnitsOfPurchase() {
        return pointsUnitsOfPurchase;
    }

    public void setPointsUnitsOfPurchase(Integer pointsUnitsOfPurchase) {
        this.pointsUnitsOfPurchase = pointsUnitsOfPurchase;
    }

    public Integer getPointsUopVariance() {
        return pointsUopVariance;
    }

    public void setPointsUopVariance(Integer pointsUopVariance) {
        this.pointsUopVariance = pointsUopVariance;
    }

    public Integer getPointsPriceOfPurchaseCategory() {
        return pointsPriceOfPurchaseCategory;
    }

    public void setPointsPriceOfPurchaseCategory(Integer pointsPriceOfPurchaseCategory) {
        this.pointsPriceOfPurchaseCategory = pointsPriceOfPurchaseCategory;
    }

    public String getCustomerValue() {
        return customerValue;
    }

    public void setCustomerValue(String customerValue) {
        this.customerValue = customerValue;
    }

    public Integer getPointsCustomerValue() {
        return pointsCustomerValue;
    }

    public void setPointsCustomerValue(Integer pointsCustomerValue) {
        this.pointsCustomerValue = pointsCustomerValue;
    }

    public Integer getPointsCltvStatus() {
        return pointsCltvStatus;
    }

    public void setPointsCltvStatus(Integer pointsCltvStatus) {
        this.pointsCltvStatus = pointsCltvStatus;
    }

    public Integer getPointsCltvPeriod() {
        return pointsCltvPeriod;
    }

    public void setPointsCltvPeriod(Integer pointsCltvPeriod) {
        this.pointsCltvPeriod = pointsCltvPeriod;
    }

    public String getCurrentRule() {
        return currentRule;
    }

    public void setCurrentRule(String currentRule) {
        this.currentRule = currentRule;
    }

    public String getVendorUuid() {
        return vendorUuid;
    }

    public void setVendorUuid(String vendorUuid) {
        this.vendorUuid = vendorUuid;
    }

    public String getPriceOfPurchaseCategory() {
        return priceOfPurchaseCategory;
    }

    public void setPriceOfPurchaseCategory(String priceOfPurchaseCategory) {
        this.priceOfPurchaseCategory = priceOfPurchaseCategory;
    }

    public String getCltvStatus() {
        return cltvStatus;
    }

    public void setCltvStatus(String cltvStatus) {
        this.cltvStatus = cltvStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Boolean getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(Boolean isBatch) {
        this.isBatch = isBatch;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getUopVariance() {
        return uopVariance;
    }

    public void setUopVariance(String uopVariance) {
        this.uopVariance = uopVariance;
    }

}
