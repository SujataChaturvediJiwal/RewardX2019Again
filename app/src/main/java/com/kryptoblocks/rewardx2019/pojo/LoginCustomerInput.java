package com.kryptoblocks.rewardx2019.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginCustomerInput {
    @SerializedName("login_name")
    @Expose
    private String loginName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("login_method")
    @Expose
    private String loginMethod;



    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }
}
