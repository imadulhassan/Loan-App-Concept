package com.finaxemoney.network;

import com.finaxemoney.interfce.ApiNet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientt {

    public static Retrofit retrofit;
    public static final String BASE_URL = "https://api.cashfree.com/api/v2/";
    public static final String   CLIENT_ID= "53160f4e1ef0e24e6a9170b0806135";
    public static final String  SECRET= "0e9508028041e89b348162738c05046fca3710e9";

    public static final String  APPID= "53160f4e1ef0e24e6a9170b0806135";

    public static RetrofitClientt instance;

    private RetrofitClientt(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                //.addInterceptor(apiInterceptor)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()

                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();


         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
    }

    public static synchronized RetrofitClientt getInstance(){
        if (retrofit == null){
            instance = new RetrofitClientt();
            return  instance;
        }
        return instance;
    }
    public static ApiNet getApi(){
        return retrofit.create(ApiNet.class);
    }
}
