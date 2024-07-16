package com.example.retrofittest;

import android.icu.text.IDNA;

import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface Api {
    @GET("api/rand.music")
    Call<Data<Song>> getJsonData(@Query("sort") String sort,@Query("format") String format);

    @FormUrlEncoded
    @POST("api/collect.so")
    Call<IsCollect> postDataCall(@Field("url") String url);

}
