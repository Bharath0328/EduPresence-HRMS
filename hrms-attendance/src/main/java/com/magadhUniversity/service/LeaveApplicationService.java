package com.magadhUniversity.service;

import com.magadhUniversity.model.LeaveApplication;
import com.magadhUniversity.repository.LeaveApplicationRepository;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveApplicationService {

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    // Create Leave Application
    public LeaveApplication createLeaveApplication(LeaveApplication leaveApplication) throws Exception {
        long daysBetween = ChronoUnit.DAYS.between(leaveApplication.getStartDate(), leaveApplication.getEndDate());

        // Validate if the leave duration exceeds 3 consecutive days
        if (daysBetween > 3) {
            throw new Exception("You cannot apply for more than 3 consecutive days of leave.");
        }

        // Save leave application to the database
        return leaveApplicationRepository.save(leaveApplication);
    }

    // Get Leave Applications by Employee Name
    public List<LeaveApplication> getLeaveApplicationsByEmployeeName(String employeeName) {
        return leaveApplicationRepository.findByEmployeeName(employeeName);
    }

    // Get All Leave Applications
    public List<LeaveApplication> getAllLeaveApplications() {
        return leaveApplicationRepository.findAll();
    }

    // Get Leave Applications by Date Range
    public List<LeaveApplication> getLeaveApplicationsByDateRange(LocalDate startDate, LocalDate endDate) {
        return leaveApplicationRepository.findByStartDateBetween(startDate, endDate);
    }


    // Update Leave Application Status (Approved / Rejected)
    public LeaveApplication updateLeaveApplicationStatus(Long id, String status) {
        // Find the leave application by ID
        LeaveApplication leaveApplication = leaveApplicationRepository.findById(id).orElse(null);

        if (leaveApplication != null) {
            // Update status
            leaveApplication.setStatus(status);
            // Save the updated leave application back to the database
            return leaveApplicationRepository.save(leaveApplication);
        }
        return null;  // Return null if the leave application is not found
    }
}
