package com.kryptoblocks.rewardx2019.pojo;

public class OffersPojo {

    String discount_offer_percentage;
    String offer_shop_name;
    String offer_shop_offer;
    String offer_token;

    public OffersPojo(String discount_offer_percentage, String offer_shop_name, String offer_shop_offer, String offer_token) {
        this.discount_offer_percentage = discount_offer_percentage;
        this.offer_shop_name = offer_shop_name;
        this.offer_shop_offer = offer_shop_offer;
        this.offer_token = offer_token;
    }

    public String getDiscount_offer_percentage() {
        return discount_offer_percentage;
    }

    public void setDiscount_offer_percentage(String discount_offer_percentage) {
        this.discount_offer_percentage = discount_offer_percentage;
    }

    public String getOffer_shop_name() {
        return offer_shop_name;
    }

    public void setOffer_shop_name(String offer_shop_name) {
        this.offer_shop_name = offer_shop_name;
    }

    public String getOffer_shop_offer() {
        return offer_shop_offer;
    }

    public void setOffer_shop_offer(String offer_shop_offer) {
        this.offer_shop_offer = offer_shop_offer;
    }

    public String getOffer_token() {
        return offer_token;
    }

    public void setOffer_token(String offer_token) {
        this.offer_token = offer_token;
    }

}
