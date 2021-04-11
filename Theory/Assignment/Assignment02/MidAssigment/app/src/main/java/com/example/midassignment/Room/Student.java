package com.example.midassignment.Room;

import android.location.Address;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Student {

    @PrimaryKey (autoGenerate = true)
    int serialId;
    String fullName;
    String studentId;
    String school;
    String dept;
    String date;
    String nid;
    String phoneNumber;

    public Student(String fullName, String studentId, String school, String dept, String date, String nid, String phoneNumber) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.school = school;
        this.dept = dept;
        this.date = date;
        this.nid = nid;
        this.phoneNumber = phoneNumber;
    }



    @Override
    public String toString() {
        return "Student{" +
                "serialId=" + serialId +
                ", fullName='" + fullName + '\'' +
                ", studentId=" + studentId +
                ", school='" + school + '\'' +
                ", dept='" + dept + '\'' +
                ", date='" + date + '\'' +
                ", nid='" + nid + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public int getSerialId() {
        return serialId;
    }

    public void setSerialId(int serialId) {
        this.serialId = serialId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}