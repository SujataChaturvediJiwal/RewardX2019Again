package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchAllSubCategoriesData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("company_category_code")
    @Expose
    private String companyCategoryCode;
    @SerializedName("element_type")
    @Expose
    private String elementType;
    @SerializedName("element_name")
    @Expose
    private String elementName;
    @SerializedName("element_value")
    @Expose
    private String elementValue;
    @SerializedName("parent_element_uuid")
    @Expose
    private String parentElementUuid;
    @SerializedName("__v")
    @Expose
    private Integer v;

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

    public String getCompanyCategoryCode() {
        return companyCategoryCode;
    }

    public void setCompanyCategoryCode(String companyCategoryCode) {
        this.companyCategoryCode = companyCategoryCode;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementValue() {
        return elementValue;
    }

    public void setElementValue(String elementValue) {
        this.elementValue = elementValue;
    }

    public String getParentElementUuid() {
        return parentElementUuid;
    }

    public void setParentElementUuid(String parentElementUuid) {
        this.parentElementUuid = parentElementUuid;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
