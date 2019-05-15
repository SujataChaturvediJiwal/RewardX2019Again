package com.kryptoblocks.rewardx2019.pojo;

import android.media.Image;

public class DiscoverTabForOffers {

    String text1, text2;
    String offer_image;


    public DiscoverTabForOffers(String text1, String text2, String offer_image) {
        this.text1 = text1;
        this.text2 = text2;
        this.offer_image = offer_image;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getOffer_image() {
        return offer_image;
    }

    public void setOffer_image(String offer_image) {
        this.offer_image = offer_image;
    }

}
