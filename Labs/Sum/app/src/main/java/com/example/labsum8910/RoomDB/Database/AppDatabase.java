package com.example.labsum8910.RoomDB.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.labsum8910.RoomDB.Dao.PersonDAO;
import com.example.labsum8910.RoomDB.Models.Person;

@Database(entities = {Person.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDAO personDAO();
}
