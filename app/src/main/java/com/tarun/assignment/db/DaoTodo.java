package com.tarun.assignment.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;


@Dao
public interface DaoTodo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTodo(DbComment comment);
}
