package com.example.mvvm;

import androidx.room.ColumnInfo;

public class Writer {
    @ColumnInfo(name = "writerName")
    public String writerName;
    @ColumnInfo(name = "writerAge")
    public int age;

    public Writer(String writerName, int age) {
        this.writerName = writerName;
        this.age = age;
    }
}
