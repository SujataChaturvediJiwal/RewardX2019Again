package com.kryptoblocks.rewardx2019.apiInterfaces;

import com.kryptoblocks.rewardx2019.pojo.Discover;
import com.kryptoblocks.rewardx2019.pojo.GetAllIncentives;
import com.kryptoblocks.rewardx2019.pojo.GetAllUserRewardPoints;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorOffers;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendors;
import com.kryptoblocks.rewardx2019.pojo.GetParticularRewardDetails;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivities;
import com.kryptoblocks.rewardx2019.pojo.GetUserRewardTokens;
import com.kryptoblocks.rewardx2019.pojo.GetVendorOfferById;
import com.kryptoblocks.rewardx2019.pojo.IsCustomerMembershipValid;
import com.kryptoblocks.rewardx2019.pojo.IsCustomerRegistered;
import com.kryptoblocks.rewardx2019.pojo.LoginCustomerInput;
import com.kryptoblocks.rewardx2019.pojo.LoginCustomerOutput;
import com.kryptoblocks.rewardx2019.pojo.RedeemTokensInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerOutput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramOutput;
import com.kryptoblocks.rewardx2019.pojo.GetRegisteredVendors;
import com.kryptoblocks.rewardx2019.pojo.SearchAllSubCategories;
import com.kryptoblocks.rewardx2019.pojo.SearchMainCategories;
import com.kryptoblocks.rewardx2019.pojo.TotalCustomerRewardPoints;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("registerCustomer/")
    Call<RegisterCustomerOutput> registerCustomer(@Body RegisterCustomerInput registerCustomerInput);

    //@POST("loginCustomerByEmailId?user_email=emailIdRegister&user_password=passwordRegister")
   /* @Headers("Content-Type:application/json")
    @POST("loginCustomerByEmailId/{user_email}/{user_password}")
    Call<LoginCustomerOutput> customerLogin(@Path("user_email") String user_email, @Path("user_password") String user_password);
*/
    /*@GET("discoverNearbyStoresOfAllVendors/{user_longitude}/{user_latitude}")
    Call<Discover> discoverVendors(@Path("user_longitude") String user_longitude, @Path("user_latitude") String user_latitude);*/

    /*@POST("registerCustomerWithVendor/")
    Call<RegisterToRewardsProgramOutput> registerToRewardsProgram(@Body RegisterToRewardsProgramInput registerToRewardsProgramInput);
*/
    @GET("isCustomerRegisteredWithVendor/{user_uuid}/{vendor_uuid}")
    Call<IsCustomerRegistered> isCustomerRegistered(@Path("user_uuid") String user_uuid, @Path("vendor_uuid") String vendor_uuid);


    @GET("getVendorsRegisteredWithCustomer/{user_uuid}")
    Call<GetRegisteredVendors> getRegisteredCustomers(@Path("user_uuid") String user_uuid);

    @GET("getAllActiveIncentives/{vendor_reward_uuid}/reward")
    Call<GetAllIncentives> getAllIncentives(@Path("vendor_reward_uuid") String vendor_reward_uuid);


    @GET("getSingleIncentive/{single_reward_product_uuid}")
    Call<GetParticularRewardDetails> getParticularIncentiveDetails(@Path("single_reward_product_uuid") String single_reward_product_uuid);

  /*  @GET("getAllUserVendorRewardPoints/{user_uuid}")
    Call<GetAllUserRewardPoints> getAllUserRewardPoints(@Path("user_uuid") String user_uuid);*/

    @GET("getUserRewardPoints/{user_uuid}/{redeem_vendor_uuid}")
    Call<GetUserRewardTokens> getUserRewardPoints(@Path("user_uuid") String user_uuid, @Path("redeem_vendor_uuid") String redeem_vendor_uuid);

   /* @POST("redeemPoints/")
    Call<RegisterCustomerOutput> redeemPointsOffers(@Body RedeemTokensInput redeemOffersInput);*/

    @GET("getAllVendorOffers/{user_uuid}")
    Call<GetAllVendorOffers> getAllVendorsOffers(@Path("user_uuid") String user_uuid);

    @GET("getVendorOfferById/{offer_uuid}/{vendor_id}")
    Call<GetVendorOfferById> getVendorOfferByid(@Path("offer_uuid") String offer_uuid,@Path("vendor_id") String vendor_id);

    @GET("GetRecentActivities/{user_uuid}")
    Call<GetRecentActivities> getRecentActivities(@Path("user_uuid") String user_uuid);

    @GET("getAllCategories")
    Call<SearchMainCategories> getSearchMainCategories();

    @GET("getAllSubCategories/{search_category_id}")
    Call<SearchAllSubCategories> getSearchSubCategories(@Path("search_category_id") String search_category_id);

    ////1 july 2019

    @POST("loginCustomer")
    Call<LoginCustomerOutput> customerLogin(@Body LoginCustomerInput loginCustomerInput);

    @GET("isCustomerMembershipValid/{membership_number}/{local_vendor_id_var}")
    Call<IsCustomerMembershipValid> isCustomerMembershipValid(@Path("membership_number") String membership_number,
                                                              @Path("local_vendor_id_var") String local_vendor_id_var);
    @POST("registerCustomerWithVendor")
    Call<RegisterToRewardsProgramOutput> registerCustomerWithVendor(@Body RegisterToRewardsProgramInput registerToRewardsProgramInput);

    @GET("discoverNearbyRewards/{user_longitude}/{user_latitude}/{filter_distance}")
    Call<Discover> discoverVendorsbyDistance(@Path("user_longitude") String user_longitude,
                                   @Path("user_latitude") String user_latitude,
                                   @Path("filter_distance") String filter_distance);

    @GET("getTotalCustomerRewardPoints/{user_uuid}")
    Call<TotalCustomerRewardPoints> getTotalCustomerRewardPoints(@Path("user_uuid") String user_uuid);

    @GET("getCustomerRewardPointDetailsForAllVendors/{user_uuid}")
    Call<GetAllUserRewardPoints> getAllUserRewardPoints(@Path("user_uuid") String user_uuid);

    @GET("GetAllVendors")
    Call<GetAllVendors> getAllVendors();
}

