package com.kryptoblocks.rewardx2019.pojo;

public class ActivitiesPojo {

    String tokens;

    public ActivitiesPojo(String tokens, String company_name, String activity_type, String activity_date) {
        this.tokens = tokens;
        this.company_name = company_name;
        this.activity_type = activity_type;
        this.activity_date = activity_date;
    }

    String company_name;
    String activity_type;
    String activity_date;

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getActivity_date() {
        return activity_date;
    }

    public void setActivity_date(String activity_date) {
        this.activity_date = activity_date;
    }

}
