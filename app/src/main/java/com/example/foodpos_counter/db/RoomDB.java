package com.example.foodpos_counter.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.TypeConverters;

import com.example.foodpos_counter.db.Entity.CartTable;
import com.example.foodpos_counter.db.Entity.DataConverter;

@Database(entities = {CartTable.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class RoomDB extends androidx.room.RoomDatabase{

    public abstract DAO dao();
    public static volatile RoomDB INSTANCE;


    static RoomDB getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (RoomDB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,"add_cart")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}

