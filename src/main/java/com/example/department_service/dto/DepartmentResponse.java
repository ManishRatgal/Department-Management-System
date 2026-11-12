package com.example.department_service.dto;

public class DepartmentResponse {

    private Long id;
    private String departmentName;
    private String departmentCode;
    private String description;

    public DepartmentResponse() {
    }

    public DepartmentResponse(Long id, String departmentName,
                              String departmentCode,
                              String description) {

        this.id = id;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public String getDescription() {
        return description;
    }
}