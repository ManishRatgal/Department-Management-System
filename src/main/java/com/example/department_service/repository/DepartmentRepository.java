package com.example.department_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.department_service.entity.Department;
import java.util.List;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {

    boolean existsByDepartmentNameIgnoreCase(String departmentName);

    boolean existsByDepartmentCodeIgnoreCase(String departmentCode);
    List<Department> findByDepartmentNameContainingIgnoreCaseOrDepartmentCodeContainingIgnoreCase(
            String departmentName,
            String departmentCode
    );
}