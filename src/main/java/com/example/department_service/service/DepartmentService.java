package com.example.department_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.department_service.dto.DepartmentRequest;
import com.example.department_service.dto.DepartmentResponse;
import com.example.department_service.entity.Department;
import com.example.department_service.exception.DuplicateResourceException;
import com.example.department_service.exception.ResourceNotFoundException;
import com.example.department_service.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    // Convert Entity into Response DTO
    private DepartmentResponse convertToResponse(Department department) {

        return new DepartmentResponse(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentCode(),
                department.getDescription()
        );
    }

    // CREATE Department
    public DepartmentResponse createDepartment(DepartmentRequest request) {

        if (repository.existsByDepartmentNameIgnoreCase(
                request.getDepartmentName())) {
            throw new DuplicateResourceException(
                    "Department name already exists");
        }

        if (repository.existsByDepartmentCodeIgnoreCase(
                request.getDepartmentCode())) {
            throw new DuplicateResourceException(
                    "Department code already exists");
        }

        Department department = new Department();

        department.setDepartmentName(request.getDepartmentName());
        department.setDepartmentCode(request.getDepartmentCode());
        department.setDescription(request.getDescription());

        Department saved = repository.save(department);

        return convertToResponse(saved);
    }

    // READ All Departments
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAllDepartments() {

        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // READ Department By ID
    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentById(Long id) {

        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with ID: " + id));

        return convertToResponse(department);
    }

    // UPDATE Department
    public DepartmentResponse updateDepartment(
            Long id, DepartmentRequest request) {

        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with ID: " + id));

        if (!department.getDepartmentName().equalsIgnoreCase(
                request.getDepartmentName())
                && repository.existsByDepartmentNameIgnoreCase(
                        request.getDepartmentName())) {

            throw new DuplicateResourceException(
                    "Department name already exists");
        }

        if (!department.getDepartmentCode().equalsIgnoreCase(
                request.getDepartmentCode())
                && repository.existsByDepartmentCodeIgnoreCase(
                        request.getDepartmentCode())) {

            throw new DuplicateResourceException(
                    "Department code already exists");
        }

        department.setDepartmentName(request.getDepartmentName());
        department.setDepartmentCode(request.getDepartmentCode());
        department.setDescription(request.getDescription());

        Department updated = repository.save(department);

        return convertToResponse(updated);
    }

    // DELETE Department
    public void deleteDepartment(Long id) {

        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with ID: " + id));

        repository.delete(department);
    }
 // PAGINATION
    @Transactional(readOnly = true)
    public Page<DepartmentResponse> getAllDepartments(Pageable pageable) {

        return repository.findAll(pageable)
                .map(this::convertToResponse);
    }
 // SEARCH DEPARTMENTS
    @Transactional(readOnly = true)
    public List<DepartmentResponse> searchDepartments(String keyword) {

        return repository
                .findByDepartmentNameContainingIgnoreCaseOrDepartmentCodeContainingIgnoreCase(
                        keyword, keyword)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }
}