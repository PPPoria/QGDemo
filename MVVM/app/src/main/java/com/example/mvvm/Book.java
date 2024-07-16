package com.example.mvvm;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Books")
public class Book {
    @PrimaryKey
    public int id;//设置主键（必须）

    @ColumnInfo(name = "newName")
    public String newName;//设置键

    @ColumnInfo(name = "oldName")
    public String oldName;

    @Ignore
    Bitmap picture;//忽视，不会存储在DataBase

    @Embedded
    public Writer writer;//表示绑定，但是会占用计算资源，一般不推荐使用

    //构造方法，且必须有一个空构造
    public Book(int id, String newName, String oldName, Bitmap picture, Writer writer) {
        this.id = id;
        this.newName = newName;
        this.oldName = oldName;
        this.picture = picture;
        this.writer = writer;
    }

    public Book(){}
}
