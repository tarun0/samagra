package com.tarun.assignment.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {DbComment.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase{

    private static MyDatabase instance;

    public static MyDatabase getInstance (Context context) {
        if (instance == null) {
            synchronized (MyDatabase.class){
                instance = Room.databaseBuilder(context, MyDatabase.class, "Samagra.db")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return instance;
    }

    public abstract DaoComment commentModel();
}
