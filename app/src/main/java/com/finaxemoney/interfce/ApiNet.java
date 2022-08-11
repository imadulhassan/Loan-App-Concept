package com.finaxemoney.interfce;


import com.finaxemoney.model.TokenResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiNet {
//
    @Headers({"x-client-id:53160f4e1ef0e24e6a9170b0806135","x-client-secret:0e9508028041e89b348162738c05046fca3710e9"})
    @POST("cftoken/order")
    Call<TokenResponse> getToken(@Body JsonObject object);



}

