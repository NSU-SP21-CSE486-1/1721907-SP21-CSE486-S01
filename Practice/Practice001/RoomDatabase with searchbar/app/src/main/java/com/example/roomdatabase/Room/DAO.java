package com.example.roomdatabase.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface DAO {

    @Insert
    public void studentInsertion(Student student);

    @Query("Select * from Student")
    List<Student> getStudent();

}
