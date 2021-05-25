package com.example.nsucpcadmin;

public class Admin {

    String companyName;
    String deptName;

    public Admin(String companyName, String deptName) {
        this.companyName = companyName;
        this.deptName = deptName;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "companyName='" + companyName + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
