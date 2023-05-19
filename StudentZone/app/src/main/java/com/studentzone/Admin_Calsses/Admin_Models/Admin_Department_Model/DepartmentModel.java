package com.studentzone.Admin_Calsses.Admin_Models.Admin_Department_Model;

public class DepartmentModel {

    String DepartmentName , DepartmentCode ;
    int DepartmentIcon;

    public DepartmentModel(String departmentName, String departmentCode, int departmentIcon) {
        DepartmentName = departmentName;
        DepartmentCode = departmentCode;
        DepartmentIcon = departmentIcon;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        DepartmentCode = departmentCode;
    }

    public int getDepartmentIcon() {
        return DepartmentIcon;
    }

    public void setDepartmentIcon(int departmentIcon) {
        DepartmentIcon = departmentIcon;
    }

}
