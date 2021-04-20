package com.example.midassignment.Room.Databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.midassignment.Room.Dao.DAO;
import com.example.midassignment.Room.Models.Student;

@Database(entities = {Student.class} , version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract DAO dao();

}
