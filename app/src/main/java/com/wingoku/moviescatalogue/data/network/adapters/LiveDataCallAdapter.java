package com.wingoku.moviescatalogue.data.network.adapters;

import androidx.lifecycle.LiveData;

import com.wingoku.moviescatalogue.data.network.models.responses.ApiResponse;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<T> implements CallAdapter<T, LiveData<ApiResponse<T>>> {
    private Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<ApiResponse<T>> adapt(Call<T> call) {
        //converting Retrofit Call object to custom LiveData response object
        return new LiveData<ApiResponse<T>>() {
            @Override
            protected void onActive() {
                super.onActive();

                final ApiResponse apiResponse = new ApiResponse();
                //making retrofit call
                call.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(Call<T> call, Response<T> response) {
                        postValue(apiResponse.create(response));
                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
                        postValue(apiResponse.create(t));
                    }
                });
            }
        };
    }
}