package com.example.mvp.Presenter;

import com.example.mvp.Api;
import com.example.mvp.Data;
import com.example.mvp.Model.Urls;
import com.example.mvp.View.ShowActivityInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPresenter implements ShowPresenterInterface {

    public static ShowPresenter instance = new ShowPresenter();
    private ShowActivityInterface activity;

    public static ShowPresenterInterface getInstance() {
        return instance;
    }

    @Override
    public void bindActivity(ShowActivityInterface sa) {
        this.activity = sa;
    }

    @Override
    public void setImageFromInternet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Urls().getRandImageRequestUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Data> dataCall = api.getRandImage("七了个三", "json");

        dataCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();
                if(data == null){
                    activity.showImageByUrl(null);
                    return;
                }
                activity.showImageByUrl(data.getImgurl());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable throwable) {

            }
        });
    }
}
