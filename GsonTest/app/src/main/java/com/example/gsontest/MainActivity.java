package com.example.gsontest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Type type;

    private TextView to;
    private TextView from;

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
        intiView();
        String jsonString = getJsonString();

        to.setText(jsonString);
        from.setText(getEachFromJson(jsonString));
    }

    private void intiView() {
        to = findViewById(R.id.to);
        from = findViewById(R.id.from);
    }

    private String getEachFromJson(String jsoonString) {
        StringBuilder sb = new StringBuilder();
        Gson gson = new GsonBuilder().serializeNulls().create();

        int num1 = gson.fromJson("1", int.class);
        String string = gson.fromJson("\"string\"", String.class);
        Integer num2 = gson.fromJson("2", Integer.class);
        int[] arr = gson.fromJson("[3,4]", int[].class);
        Student<Job> student = gson.fromJson("{\"e\":{},\"name\":\"张三\"}", type);
        Job job = student.getE();

        sb.append(num1)
                .append(string)
                .append(num2)
                .append(arr[1])
                .append(student.getName())
                .append(job.getSomeText());

        Log.d(TAG, "getEachFromJson: student--" + student);
        System.out.println(student);
        Log.d(TAG, "getEachFromJson: job--" + job);
        System.out.println(job);

        return sb.toString();
    }

    private String getJsonString() {
        StringBuilder sb = new StringBuilder();
        Gson gson = new GsonBuilder().serializeNulls().create();

        Student<Job> student = new Student<>();
        student.setE(new Job());
        type = new TypeToken<Student<Job>>() {
        }.getType();

        sb.append(gson.toJson(1))
                .append(gson.toJson("string"))
                .append(gson.toJson(new Integer(2)))
                .append(gson.toJson(new int[]{3, 4}))
                .append(gson.toJson(student, type));

        return sb.toString();
    }
}