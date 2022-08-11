package com.finaxemoney.network;







import com.finaxemoney.interfce.ResponseListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitController<T> implements Callback<T> {

    ResponseListener<T> responseListener;
    public RetrofitController(Call<T> call, ResponseListener<T> listener){
        responseListener = listener;
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        responseListener.onResponse(call,response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
