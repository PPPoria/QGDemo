package com.example.mvvm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class SQLDataBase extends RoomDatabase {
    //提供BookDao实例
    public abstract BookDao bookDao();

    //================

    //单列模式
    public static SQLDataBase dataBase;

    public static SQLDataBase getDataBase(Context context){
        if(dataBase == null){
            synchronized (SQLDataBase.class){//因为是异步调用，所有需要有同步代码块
                if(dataBase == null)
                    dataBase = Room.databaseBuilder(context.getApplicationContext(), SQLDataBase.class, "Books.db").build();
            }
        }
        return dataBase;
    }
}
