package com.example.epms.constant;

public class EmployeeConstant {

    public static final String EMP = "/employees";

    public static final String EMP_ALL = "/all";
    public static final String EMP_ADD = "/add";
    public static final String EMP_UPDATE = "/update/{id}";
    public static final String EMP_ADD_PRJS = "/add-projects/{id}";
    public static final String EMP_REMOVE_PRJS = "/remove-projects/{id}";
    public static final String EMP_DELETE = "/delete/{id}";

    private EmployeeConstant() {
    }
}
