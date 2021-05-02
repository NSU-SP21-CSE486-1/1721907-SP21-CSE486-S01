package com.example.midassignment.Room.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.midassignment.Room.Models.Student;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    public void studentInsertion(Student student);


    @Query("Select * from Student")
    List<Student> getStudent();

}
