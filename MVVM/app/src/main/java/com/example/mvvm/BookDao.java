package com.example.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {

    //返回所有的Book实例，并用List储存
    @Query("SELECT * FROM books")
    List<Book> loadAll();

    //返回单种数据，可以使用LiveData接收
    @Query("SELECT newName FROM  books")
    LiveData<String> loadNewNames();

    //返回多种数据，并且映射到NameTuple上
    @Query("SELECT newName, oldName FROM  books")
    List<NameTuple> loadAllNames();

    //有参数地查询，传递到注释中的参数可以使用冒号加同形参名表示
    @Query("SELECT * FROM books WHERE id > :minId")
    List<Book> loadALLBiggerThan(int minId);

    @Query("SELECT * FROM books WHERE id BETWEEN :minId AND :maxId")
    List<Book> loadALLBetween(int minId, int maxId);

    @Query("SELECT * FROM books WHERE newName IN (:names)"
            + "OR oldName IN (:names)")
    List<Book> loadALLFromNames(List<String> names);

    //----------------

    //多参数插入，设定冲突模式为“覆盖”
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Book... book);

    //如果只有一个参数，那么会返回一个rawId
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Book book);

    //----------------

    //会寻找主键Primary一致的行，如果没有则不删除
    @Delete
    int delete(Book book);

    //----------------

    //会寻找主键Primary一致的行，如果没有则不更新
    @Update
    int update(Book... book);
}
