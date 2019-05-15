package com.kryptoblocks.rewardx2019.apiInterfaces;

import com.kryptoblocks.rewardx2019.pojo.Discover;
import com.kryptoblocks.rewardx2019.pojo.GetAllIncentives;
import com.kryptoblocks.rewardx2019.pojo.GetAllUserRewardPoints;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorOffers;
import com.kryptoblocks.rewardx2019.pojo.GetParticularRewardDetails;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivities;
import com.kryptoblocks.rewardx2019.pojo.GetUserRewardTokens;
import com.kryptoblocks.rewardx2019.pojo.GetVendorOfferById;
import com.kryptoblocks.rewardx2019.pojo.IsCustomerRegistered;
import com.kryptoblocks.rewardx2019.pojo.LoginCustomerOutput;
import com.kryptoblocks.rewardx2019.pojo.RedeemTokensInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerOutput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramOutput;
import com.kryptoblocks.rewardx2019.pojo.GetRegisteredVendors;
import com.kryptoblocks.rewardx2019.pojo.SearchAllSubCategories;
import com.kryptoblocks.rewardx2019.pojo.SearchMainCategories;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("registerCustomerByEmail/")
    Call<RegisterCustomerOutput> registerCustomer(@Body RegisterCustomerInput registerCustomerInput);

    //@POST("loginCustomerByEmailId?user_email=emailIdRegister&user_password=passwordRegister")
    @Headers("Content-Type:application/json")
    @POST("loginCustomerByEmailId/{user_email}/{user_password}")
    Call<LoginCustomerOutput> customerLogin(@Path("user_email") String user_email, @Path("user_password") String user_password);

    @GET("discoverVendors")
    Call<Discover> discoverVendors();

    @POST("registerCustomerWithVendor/")
    Call<RegisterToRewardsProgramOutput> registerToRewardsProgram(@Body RegisterToRewardsProgramInput registerToRewardsProgramInput);

    @GET("isCustomerRegisteredWithVendor/{user_uuid}/{vendor_uuid}")
    Call<IsCustomerRegistered> isCustomerRegistered(@Path("user_uuid") String user_uuid, @Path("vendor_uuid") String vendor_uuid);


    @GET("getRegisteredVendors/{user_uuid}")
    Call<GetRegisteredVendors> getRegisteredCustomers(@Path("user_uuid") String user_uuid);

    @GET("getAllActiveIncentives/{vendor_reward_uuid}/reward")
    Call<GetAllIncentives> getAllIncentives(@Path("vendor_reward_uuid") String vendor_reward_uuid);


    @GET("getSingleIncentive/{single_reward_product_uuid}")
    Call<GetParticularRewardDetails> getParticularIncentiveDetails(@Path("single_reward_product_uuid") String single_reward_product_uuid);

    @GET("getAllUserVendorRewardPoints/{user_uuid}")
    Call<GetAllUserRewardPoints> getAllUserRewardPoints(@Path("user_uuid") String user_uuid);

    @GET("getUserRewardPoints/{user_uuid}/{redeem_vendor_uuid}")
    Call<GetUserRewardTokens> getUserRewardPoints(@Path("user_uuid") String user_uuid, @Path("redeem_vendor_uuid") String redeem_vendor_uuid);

    @POST("redeemPoints/")
    Call<RegisterCustomerOutput> redeemPointsOffers(@Body RedeemTokensInput redeemOffersInput);

    @GET("getAllVendorOffers/{user_uuid}")
    Call<GetAllVendorOffers> getAllVendorsOffers(@Path("user_uuid") String user_uuid);

    @GET("getVendorOfferById/{offer_uuid}")
    Call<GetVendorOfferById> getVendorOfferByid(@Path("offer_uuid") String offer_uuid);

    @GET("GetRecentActivities/{user_uuid}")
    Call<GetRecentActivities> getRecentActivities(@Path("user_uuid") String user_uuid);

    @GET("getAllCategories")
    Call<SearchMainCategories> getSearchMainCategories();

    @GET("getAllSubCategories/{search_category_id")
    Call<SearchAllSubCategories> getSearchSubCategories(@Path("search_category_id") String search_category_id);
}

