package com.magadhUniversity.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeName;  // Ensure employee name is correctly mapped

    @Column(nullable = false)
    private String department;  // Ensure department is correctly mapped

    @Column(nullable = false)
    private String leaveType;  // Ensure leave type is correctly mapped

    @Column(nullable = false)
    private LocalDate startDate;  // Ensure start date is correctly mapped

    @Column(nullable = false)
    private LocalDate endDate;  // Ensure end date is correctly mapped

    @Column(nullable = false)
    private String status = "Pending";  // Default value of status

    @Column(nullable = false)
    private String reason;  // Ensure reason is correctly mapped
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
