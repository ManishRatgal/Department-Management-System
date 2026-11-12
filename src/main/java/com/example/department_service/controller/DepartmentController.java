package com.example.department_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.department_service.dto.DepartmentRequest;
import com.example.department_service.dto.DepartmentResponse;
import com.example.department_service.service.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/departments")

@Tag(
    name = "Department Management",
    description = "APIs for creating, updating, deleting, retrieving and searching departments"
)

public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // CREATE DEPARTMENT
    @Operation(
        summary = "Create Department",
        description = "Creates a new department with unique name and code",
        responses = {
            @ApiResponse(responseCode = "201", description = "Department created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "409", description = "Department already exists")
        }
    )
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(
            @Valid @RequestBody DepartmentRequest request) {

        DepartmentResponse response =
                departmentService.createDepartment(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET ALL DEPARTMENTS
    @Operation(
        summary = "Get All Departments",
        description = "Retrieves all available departments",
        responses = {
            @ApiResponse(responseCode = "200", description = "Departments retrieved successfully")
        }
    )
    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {

        return ResponseEntity.ok(
                departmentService.getAllDepartments());
    }

    // GET DEPARTMENT BY ID
    @Operation(
        summary = "Get Department By ID",
        description = "Retrieves department details using department ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Department found"),
            @ApiResponse(responseCode = "404", description = "Department not found")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id));
    }

    // UPDATE DEPARTMENT
    @Operation(
        summary = "Update Department",
        description = "Updates an existing department using its ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Department updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Department not found"),
            @ApiResponse(responseCode = "409", description = "Duplicate department name or code")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest request) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, request));
    }

    // DELETE DEPARTMENT
    @Operation(
        summary = "Delete Department",
        description = "Deletes a department using its ID",
        responses = {
            @ApiResponse(responseCode = "204", description = "Department deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Department not found")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }

    // PAGINATION AND SORTING
    @Operation(
        summary = "Get Departments With Pagination",
        description = "Retrieves departments with pagination and sorting",
        responses = {
            @ApiResponse(responseCode = "200", description = "Paginated departments retrieved successfully")
        }
    )
    @GetMapping("/page")
    public ResponseEntity<Page<DepartmentResponse>> getDepartmentsWithPagination(
            @PageableDefault(size = 5, sort = "departmentName")
            Pageable pageable) {

        return ResponseEntity.ok(
                departmentService.getAllDepartments(pageable));
    }

    // SEARCH DEPARTMENTS
    @Operation(
        summary = "Search Departments",
        description = "Searches departments by department name or department code",
        responses = {
            @ApiResponse(responseCode = "200", description = "Search completed successfully")
        }
    )
    @GetMapping("/search")
    public ResponseEntity<List<DepartmentResponse>> searchDepartments(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                departmentService.searchDepartments(keyword));
    }
}