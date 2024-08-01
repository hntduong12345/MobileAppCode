package com.example.myapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.dao.PersonDAO;
import com.example.myapplication.model.Person;

@Database(entities = {Person.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDAO personDAO();
}
