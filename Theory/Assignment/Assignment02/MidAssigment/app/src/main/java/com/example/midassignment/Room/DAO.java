package com.example.midassignment.Room;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DAO {

    @Insert
    public void studentInsertion(Student student);



}
