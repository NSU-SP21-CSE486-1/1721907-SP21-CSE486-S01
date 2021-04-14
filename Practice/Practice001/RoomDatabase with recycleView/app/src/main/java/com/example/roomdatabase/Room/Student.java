package com.example.roomdatabase.Room;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey (autoGenerate = true)
    int sId;
    String sfirstName;
    String slastName;
    int sclass;

    public Student(String sfirstName, String slastName, int sclass) {
        this.sfirstName = sfirstName;
        this.slastName = slastName;
        this.sclass = sclass;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getSfirstName() {
        return sfirstName;
    }

    public void setSfirstName(String sfirstName) {
        this.sfirstName = sfirstName;
    }

    public String getSlastName() {
        return slastName;
    }

    public void setSlastName(String slastName) {
        this.slastName = slastName;
    }

    public int getSclass() {
        return sclass;
    }

    public void setSclass(int sclass) {
        this.sclass = sclass;
    }


}
