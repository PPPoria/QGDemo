package com.example.retrofittest;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private ImageView image;

    private Retrofit retrofit;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        try {
            getSong();
            //postIsCollect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void postIsCollect() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.uomg.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        Call<IsCollect> dataCall = api.postDataCall("https://www.bilibili.com/");

        dataCall.enqueue(new Callback<IsCollect>() {
            @Override
            public void onResponse(Call<IsCollect> call, Response<IsCollect> response) {
                IsCollect isCollect = response.body();
                if (isCollect == null) return;
                text.setText(isCollect.getMsg());
            }

            @Override
            public void onFailure(Call<IsCollect> call, Throwable throwable) {

            }
        });
    }

    private void getSong() throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.uomg.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        Call<Data<Song>> dataCall = api.getJsonData("热歌榜", "json");

        //同步请求
        //Response<Data<Song>> data = dataCall.execute();
        //异步请求
        dataCall.enqueue(new Callback<Data<Song>>() {
            @Override
            public void onResponse(Call<Data<Song>> call, Response<Data<Song>> response) {
                Toast.makeText(MainActivity.this, "get回调成功，异步执行", Toast.LENGTH_SHORT).show();
                Data<Song> body = response.body();
                if (body == null) return;
                Song song = body.getData();
                if (song == null) return;
                text.setText(song.getName());
                Glide.with(getApplicationContext())
                        .load(song.getPicurl())
                        .into(image);
            }

            @Override
            public void onFailure(Call<Data<Song>> call, Throwable throwable) {

            }
        });
    }

    private void initView() {
        text = findViewById(R.id.text);
        image = findViewById(R.id.image);
    }
}