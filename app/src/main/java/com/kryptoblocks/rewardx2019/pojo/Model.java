package com.kryptoblocks.rewardx2019.pojo;

public class Model {

    public static final int AIR_TYPE=0;
    public static final int SHOP_TYPE=1;
    public static final int AUDIO_TYPE=2;

    public int type;
    public int data;
   // public String text;

    public Model(int type, int data)
    {
        this.type=type;
        this.data=data;
       // this.text=text;
    }
}
