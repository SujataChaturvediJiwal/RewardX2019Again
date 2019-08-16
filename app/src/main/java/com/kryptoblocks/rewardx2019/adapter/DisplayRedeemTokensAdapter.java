package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.RedeemDescriptionActivity;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetAllUserRewardPointsData;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendors;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorsData;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.mypreferenceLogin;

public class DisplayRedeemTokensAdapter extends RecyclerView.Adapter<DisplayRedeemTokensAdapter.MyViewHolder>{

    Context mContext;
    List<GetAllUserRewardPointsData> redeemTokenPojoList;
    public static String redeem_vendor_uuid;
    String redeem_points_per_user;
    String image_link, membership_uuid;
    ApiInterface apiInterface;
    public static String vendor_name;
    public  String vendor_id;
   // byte[] byteArray;
    public static String var_vendor_name;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView token_amt, token_name;
        public ImageView token_img;
        public LinearLayout linear_layout_redeem;


        public MyViewHolder(View view) {
            super(view);

            token_amt = view.findViewById(R.id.token_provider_token_value_textView);
            token_img = view.findViewById(R.id.token_provider_image);
            token_name = view.findViewById(R.id.token_provider_name_textView);
            linear_layout_redeem = view.findViewById(R.id.redeem_token_linear_layout);
        }
    }


    public DisplayRedeemTokensAdapter(Context mContext, List<GetAllUserRewardPointsData> activitiesPojos) {
        this.mContext = mContext;
        this.redeemTokenPojoList = activitiesPojos;
    }
    @Override
    public DisplayRedeemTokensAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_redeem_token_layout, parent, false);
        return new DisplayRedeemTokensAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final DisplayRedeemTokensAdapter.MyViewHolder holder, int position) {

        final GetAllUserRewardPointsData list = redeemTokenPojoList.get(position);

       // holder.token_img.setText(String.valueOf(list.getTokens()));
        /*Glide.with(mContext)
                .load(list.getToken_image())
                .into(holder.token_img);*/

        holder.token_name.setText(String.valueOf(list.getVendorName()));
        holder.token_amt.setText(String.valueOf(list.getTotalPoints()));
        Glide.with(mContext).load(list.getLogoLink()).error(R.drawable.ic_launcher_foreground).into(holder.token_img);



        holder.linear_layout_redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                redeem_vendor_uuid = list.getVendorUuid();

                redeem_points_per_user = String.valueOf(list.getTotalPoints());
                image_link = list.getLogoLink();
                membership_uuid = list.getMembershipUuid();

                var_vendor_name = list.getVendorName();

                SharedPreferences.Editor total_redeem_points_pref = mContext.getSharedPreferences(mypreferenceLogin, MODE_PRIVATE).edit();
                total_redeem_points_pref.putString("redeem_point_per_user", String.valueOf(redeem_points_per_user));
                total_redeem_points_pref.commit();

                SharedPreferences.Editor membership_id = mContext.getSharedPreferences(mypreferenceLogin, MODE_PRIVATE).edit();
                membership_id.putString("membership_id", membership_uuid);
                membership_id.commit();

                SharedPreferences.Editor img_link = mContext.getSharedPreferences(mypreferenceLogin, MODE_PRIVATE).edit();
                img_link.putString("image_link_logo", image_link);
                img_link.commit();

                System.out.println("Image link redeem------------"+image_link);


                /*try {
                    Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), StringToBitMap(list.getLogoLink()));
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[]  byteArray = stream.toByteArray();



                    Intent intent = new Intent(mContext,RedeemDescriptionActivity.class);
                    intent.putExtra("picture", byteArray);
                    mContext.startActivity(intent);

                    fetchVendorNames();


                }
                catch (Exception e)
                {
                    System.out.println("Exception------"+e);
                }*/



                /*SharedPreferences.Editor owner_name = mContext.getSharedPreferences(mypreferenceLogin, MODE_PRIVATE).edit();
                owner_name.putString("owner_vendor_name", vendor_name);
                owner_name.commit();*/

                /*SharedPreferences.Editor ima = mContext.getSharedPreferences(mypreferenceLogin, MODE_PRIVATE).edit();
                total_redeem_points_pref.putString("redeem_point_per_user", String.valueOf(redeem_points_per_user));
                total_redeem_points_pref.commit();
                */


                //fetchVendorNames();
                Intent i = new Intent(mContext, RedeemDescriptionActivity.class);
                mContext.startActivity(i);

            }
        });

        //holder.act_date.setText(String.valueOf(list.getActivity_date()));

    }

    @Override
    public int getItemCount() {
        return redeemTokenPojoList.size();
    }


    public void fetchVendorNames() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<GetAllVendors> call1 = apiInterface.getAllVendors();

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetAllVendors>() {


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<GetAllVendors> call, Response<GetAllVendors> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);
                    System.out.println("body----" + response.body().getData());


                    // vendorData= new ArrayList<>();
                    for(int i=0;i<response.body().getData().size();i++)
                    {
                       vendor_id = response.body().getData().get(i).getVendorUuid();
                       if(vendor_id.toString().equals(redeem_vendor_uuid))
                       {
                            vendor_name = response.body().getData().get(i).getOwnerName();
                           System.out.println("vendor_name----------" +i+"-----------" + vendor_name);



                       }
                       else
                       {
                           System.out.println("elsev part of display redeem----------"+i);
                       }
                    }


                    // startActivity(new Intent(getApplication(),ProfileActivity.class));

                    Log.i(TAG, "Success of add with  body, valid membership----" + response);
                   // Toast.makeText(mContext, "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else
                {
                    Log.i(TAG, "Not submitted----" + response);
                   // Toast.makeText(mContext, "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetAllVendors> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
               // Toast.makeText(mContext, "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }



}
