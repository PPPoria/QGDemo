package com.example.glidetest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

public class MainActivity extends AppCompatActivity {

    private static ImageView image1;
    private static ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            initView();
            initGlide();

            return insets;
        });
    }

    private void initGlide(){
        //fitCenter()是默认的，在此不作演示
        RequestOptions options1 = new RequestOptions().centerCrop().error(android.R.drawable.ic_delete);
        RequestOptions options2 = new RequestOptions().circleCrop().placeholder(android.R.drawable.ic_popup_sync);

        //RequestBuilder构建
        RequestBuilder<Drawable> builder1 = Glide.with(this).load(R.drawable.image_test);
        RequestBuilder<Drawable> builder2 = Glide.with(this).load("https://p2.music.126.net/bLwn4NO-s93eU-ROZ1NbuA==/109951168018026306.jpg");

        //请求完成获取Target实例
        Target<Drawable> target1 = builder1.apply(options1).into(image1);
        Target<Drawable> target2 = builder2.apply(options2).into(image2);

        //同步清理内存
        Glide.get(this).clearMemory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步清理磁盘
                Glide.get(getApplicationContext()).clearDiskCache();
            }
        }).start();
    }

    private void initView() {
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
    }
}