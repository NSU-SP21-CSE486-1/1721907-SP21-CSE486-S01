package com.example.midassignment.Room;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DAO<contact, student> {

    @Insert
    public void studentInsertion (student);
    @Insert
    public void contactInsertion (contact);

}
