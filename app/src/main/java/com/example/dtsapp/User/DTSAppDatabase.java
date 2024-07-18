package com.example.dtsapp.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {User.class}, version = 1, exportSchema = false)

public abstract class DTSAppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
