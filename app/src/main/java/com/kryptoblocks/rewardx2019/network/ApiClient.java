package com.kryptoblocks.rewardx2019.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

   /* public static final String url = "http://192.168.163.2:3000/api/v1/customers/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        *//*OkHttpClient.Builder okhttp = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        okhttp.addInterceptor(httpLoggingInterceptor);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
*//*
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
*/

    private final static String BASE_URL = "http://192.168.163.2:3000/api/v1/customers/";
    private final static String BASE_URL2 = "http://192.168.163.2:3000/api/v1/incentives/";

    public static ApiClient apiClient;
    private Retrofit retrofit = null;
    private Retrofit retrofit2=null;

    public static ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }

//private static Retrofit storeRetrofit = null;

    public Retrofit getClient() {
        return getClient(null);
    }

    public Retrofit getClient2() {
        return getClient2(null);
    }


    private Retrofit getClient(final Context context) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();

                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit;
    }
    private Retrofit getClient2(final Context context) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit;
    }
}
