package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerRegisterData {

    @SerializedName("salutation")
    @Expose
    private String salutation;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("facebook_id")
    @Expose
    private String facebookId;
    @SerializedName("google_id")
    @Expose
    private String googleId;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("profile_link")
    @Expose
    private String profileLink;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("has_completed_profile")
    @Expose
    private Boolean hasCompletedProfile;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("login_type")
    @Expose
    private String loginType;
    @SerializedName("login_name")
    @Expose
    private String loginName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("auth_token")
    @Expose
    private String authToken;

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getHasCompletedProfile() {
        return hasCompletedProfile;
    }

    public void setHasCompletedProfile(Boolean hasCompletedProfile) {
        this.hasCompletedProfile = hasCompletedProfile;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

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

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}