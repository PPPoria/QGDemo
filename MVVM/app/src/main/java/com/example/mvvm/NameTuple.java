package com.example.mvvm;

import androidx.room.ColumnInfo;

public class NameTuple {

    //键的名字要对应上，否则不能映射
    @ColumnInfo(name = "newName")
    public String newName;

    @ColumnInfo(name = "oldName")
    public String oldName;
}
