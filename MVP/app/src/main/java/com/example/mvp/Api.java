package com.example.mvp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @GET("rand.img3")
    Call<Data> getRandImage(@Query("sort") String sort, @Query("format") String format);
}
