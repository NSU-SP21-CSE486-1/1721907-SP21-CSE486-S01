package com.example.midassignment.Firebase.Models;

import android.location.Address;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    int serialId;
    String fullName;
    int studentId;
    String school;
    String dept;
    String date;
    String nid;
    int phoneNumber;
    String presentCountry,presentDistrict,presentPostOffice,presentPoliceStation,presentPostalCode,presentHouse,presentRoad;
    String permanentCountry,permanentDistrict,permanentPostOffice,permanentPoliceStation,permanentPostalCode,permanentHouse,permanentRoad;

    public Student(String fullName, int studentId, String school, String dept, String date, String nid, int phoneNumber, String presentCountry, String presentDistrict, String presentPostOffice, String presentPoliceStation, String presentPostalCode, String presentHouse, String presentRoad, String permanentCountry, String permanentDistrict, String permanentPostOffice, String permanentPoliceStation, String permanentPostalCode, String permanentHouse, String permanentRoad) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.school = school;
        this.dept = dept;
        this.date = date;
        this.nid = nid;
        this.phoneNumber = phoneNumber;
        this.presentCountry = presentCountry;
        this.presentDistrict = presentDistrict;
        this.presentPostOffice = presentPostOffice;
        this.presentPoliceStation = presentPoliceStation;
        this.presentPostalCode = presentPostalCode;
        this.presentHouse = presentHouse;
        this.presentRoad = presentRoad;
        this.permanentCountry = permanentCountry;
        this.permanentDistrict = permanentDistrict;
        this.permanentPostOffice = permanentPostOffice;
        this.permanentPoliceStation = permanentPoliceStation;
        this.permanentPostalCode = permanentPostalCode;
        this.permanentHouse = permanentHouse;
        this.permanentRoad = permanentRoad;
    }

    public Student() {
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
                ", phoneNumber=" + phoneNumber +
                ", presentCountry='" + presentCountry + '\'' +
                ", presentDistrict='" + presentDistrict + '\'' +
                ", presentPostOffice='" + presentPostOffice + '\'' +
                ", presentPoliceStation='" + presentPoliceStation + '\'' +
                ", presentPostalCode='" + presentPostalCode + '\'' +
                ", presentHouse='" + presentHouse + '\'' +
                ", presentRoad='" + presentRoad + '\'' +
                ", permanentCountry='" + permanentCountry + '\'' +
                ", permanentDistrict='" + permanentDistrict + '\'' +
                ", permanentPostOffice='" + permanentPostOffice + '\'' +
                ", permanentPoliceStation='" + permanentPoliceStation + '\'' +
                ", permanentPostalCode='" + permanentPostalCode + '\'' +
                ", permanentHouse='" + permanentHouse + '\'' +
                ", permanentRoad='" + permanentRoad + '\'' +
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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPresentCountry() {
        return presentCountry;
    }

    public void setPresentCountry(String presentCountry) {
        this.presentCountry = presentCountry;
    }

    public String getPresentDistrict() {
        return presentDistrict;
    }

    public void setPresentDistrict(String presentDistrict) {
        this.presentDistrict = presentDistrict;
    }

    public String getPresentPostOffice() {
        return presentPostOffice;
    }

    public void setPresentPostOffice(String presentPostOffice) {
        this.presentPostOffice = presentPostOffice;
    }

    public String getPresentPoliceStation() {
        return presentPoliceStation;
    }

    public void setPresentPoliceStation(String presentPoliceStation) {
        this.presentPoliceStation = presentPoliceStation;
    }

    public String getPresentPostalCode() {
        return presentPostalCode;
    }

    public void setPresentPostalCode(String presentPostalCode) {
        this.presentPostalCode = presentPostalCode;
    }

    public String getPresentHouse() {
        return presentHouse;
    }

    public void setPresentHouse(String presentHouse) {
        this.presentHouse = presentHouse;
    }

    public String getPresentRoad() {
        return presentRoad;
    }

    public void setPresentRoad(String presentRoad) {
        this.presentRoad = presentRoad;
    }

    public String getPermanentCountry() {
        return permanentCountry;
    }

    public void setPermanentCountry(String permanentCountry) {
        this.permanentCountry = permanentCountry;
    }

    public String getPermanentDistrict() {
        return permanentDistrict;
    }

    public void setPermanentDistrict(String permanentDistrict) {
        this.permanentDistrict = permanentDistrict;
    }

    public String getPermanentPostOffice() {
        return permanentPostOffice;
    }

    public void setPermanentPostOffice(String permanentPostOffice) {
        this.permanentPostOffice = permanentPostOffice;
    }

    public String getPermanentPoliceStation() {
        return permanentPoliceStation;
    }

    public void setPermanentPoliceStation(String permanentPoliceStation) {
        this.permanentPoliceStation = permanentPoliceStation;
    }

    public String getPermanentPostalCode() {
        return permanentPostalCode;
    }

    public void setPermanentPostalCode(String permanentPostalCode) {
        this.permanentPostalCode = permanentPostalCode;
    }

    public String getPermanentHouse() {
        return permanentHouse;
    }

    public void setPermanentHouse(String permanentHouse) {
        this.permanentHouse = permanentHouse;
    }

    public String getPermanentRoad() {
        return permanentRoad;
    }

    public void setPermanentRoad(String permanentRoad) {
        this.permanentRoad = permanentRoad;
    }
}