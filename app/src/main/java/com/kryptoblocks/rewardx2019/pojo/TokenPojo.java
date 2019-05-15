package com.kryptoblocks.rewardx2019.pojo;

public class TokenPojo {

    String name, amt;

    public TokenPojo(String name, String amt) {
        this.name = name;
        this.amt = amt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

}
