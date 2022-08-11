package com.finaxemoney.interfce;

import retrofit2.Call;
import retrofit2.Response;

public interface ResponseListener<T>{
    void onResponse(Call<T> call, Response<T> response);
    void onFailure(Call<T> call, Throwable t);
}

