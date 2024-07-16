package com.example.mvp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.mvp.Presenter.ShowPresenter;
import com.example.mvp.Presenter.ShowPresenterInterface;
import com.example.mvp.View.ShowActivityInterface;

public class MainActivity extends AppCompatActivity implements ShowActivityInterface {
    private static final String TAG = "MainActivity";

    private ImageView imageView;
    private Button button;
    private ShowPresenterInterface presenter;


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
        initListener();
        initShowPresenter();

        presenter.setImageFromInternet();
    }

    @Override
    public void initShowPresenter() {
        presenter = ShowPresenter.getInstance();
        presenter.bindActivity(this);
    }

    @Override
    public void initView() {
        imageView = findViewById(R.id.rand_image);
        button = findViewById(R.id.change_button);
    }

    @Override
    public void initListener() {
        button.setOnClickListener(v -> presenter.setImageFromInternet());
    }

    @Override
    public void showImageByUrl(String url) {
        Glide.with(this)
                .load(url)
                //.load("https://gw1.alicdn.com/tfscom/tuitui/TB2BWasd9MmBKNjSZTEXXasKpXa_!!0-rate.jpg")
                .error(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into(imageView);
    }

}