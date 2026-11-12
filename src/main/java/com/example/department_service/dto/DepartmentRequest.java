package com.example.department_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DepartmentRequest {

    @NotBlank(message = "Department name is required")
    @Size(min = 3, max = 100,
          message = "Department name must be between 3 and 100 characters")
    private String departmentName;

    @NotBlank(message = "Department code is required")
    @Size(min = 3, max = 20,
          message = "Department code must be between 3 and 20 characters")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$",
             message = "Department code can contain only letters, numbers, _ and -")
    private String departmentCode;

    @Size(max = 255,
          message = "Description cannot exceed 255 characters")
    private String description;

    public DepartmentRequest() {
    }

    public DepartmentRequest(String departmentName,
                             String departmentCode,
                             String description) {
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.description = description;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}