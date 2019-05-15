package com.kryptoblocks.rewardx2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.adapter.ActivitiesAdapter;
import com.kryptoblocks.rewardx2019.adapter.SearchMainCategoriesAdapter;
import com.kryptoblocks.rewardx2019.adapter.SearchSubCategoriesAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivities;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivitiesData;
import com.kryptoblocks.rewardx2019.pojo.SearchAllSubCategories;
import com.kryptoblocks.rewardx2019.pojo.SearchMainCategories;
import com.kryptoblocks.rewardx2019.pojo.SearchMainCategoriesData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
import static com.kryptoblocks.rewardx2019.adapter.SearchMainCategoriesAdapter.search_category_id;

public class ExpandedSearchActivity extends AppCompatActivity {

    TextView cancel_text;
    RecyclerView recycle_search_main;
    ApiInterface apiInterface;
    List<SearchMainCategories> mainCategories;
    SearchMainCategoriesAdapter searchMainCategoriesAdapter;
    LinearLayout linearLayout_image;
    String id ="ELEM_PROD_CAT_AIRLINES";

    public static boolean category_main_called = false;
    public static boolean selected_item_clicked = false;

    List<SearchAllSubCategories> subCategories;
    SearchSubCategoriesAdapter searchSubCategoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_search);

        cancel_text = findViewById(R.id.cancel);

      //  overridePendingTransition(R.anim.slide_to_bottom, R.anim.slide_to_top);
        recycle_search_main = findViewById(R.id.search_main_category_recyclerView);
        linearLayout_image = findViewById(R.id.main_category_search_layout_image);

        cancel_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(), MainActivity.class);
                startActivity(i);
            }
        });

        if(!category_main_called) {

            searchMainCategory();
        }

        if(selected_item_clicked) {

            searchSubCategory();
        }
            // searchSubCategory();
    }

     /*linearLayout_image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplication(), MainActivity.class);
            startActivity(i);
        }
    });
  */
    public void searchMainCategory() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<SearchMainCategories> call1 = apiInterface.getSearchMainCategories();

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<SearchMainCategories>() {


            @Override
            public void onResponse(Call<SearchMainCategories> call, Response<SearchMainCategories> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {

                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());

                    mainCategories = new ArrayList<>();
                    searchMainCategoriesAdapter = new SearchMainCategoriesAdapter(getApplicationContext(), response.body().getData());
                    /*RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);*/
                    RecyclerView.LayoutManager subLayoutManager = new GridLayoutManager(getApplicationContext(),4);
                    recycle_search_main.setLayoutManager(subLayoutManager);
                    recycle_search_main.setLayoutManager(subLayoutManager);
                    // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
                    recycle_search_main.setItemAnimator(new DefaultItemAnimator());
                    recycle_search_main.setAdapter(searchMainCategoriesAdapter);

                    Log.i(TAG, "  success to API." + response);
                    Toast.makeText(getApplicationContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else
                {
                    Log.i(TAG, "post not submitted to API." + response);
                    Toast.makeText(getApplicationContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchMainCategories> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void searchSubCategory() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<SearchAllSubCategories> call1 = apiInterface.getSearchSubCategories(id);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<SearchAllSubCategories>() {


            @Override
            public void onResponse(Call<SearchAllSubCategories> call, Response<SearchAllSubCategories> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {

                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());

                    category_main_called = true;

                    mainCategories = new ArrayList<>();
                    searchSubCategoriesAdapter = new SearchSubCategoriesAdapter(getApplicationContext(), response.body().getData());
                    /*RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);*/
                    RecyclerView.LayoutManager subLayoutManager = new GridLayoutManager(getApplicationContext(),4);
                    recycle_search_main.setLayoutManager(subLayoutManager);
                    recycle_search_main.setLayoutManager(subLayoutManager);
                    // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
                    recycle_search_main.setItemAnimator(new DefaultItemAnimator());
                    recycle_search_main.setAdapter(searchSubCategoriesAdapter);

                    Log.i(TAG, "  success to API." + response);
                    Toast.makeText(getApplicationContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else
                {
                    Log.i(TAG, "post not submitted to API." + response);
                    Toast.makeText(getApplicationContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchAllSubCategories> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}
