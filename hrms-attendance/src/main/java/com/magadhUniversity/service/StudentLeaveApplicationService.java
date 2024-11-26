package com.magadhUniversity.service;

import com.magadhUniversity.model.StudentLeaveApplication;
import com.magadhUniversity.repository.StudentLeaveApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class StudentLeaveApplicationService {

    @Autowired
    private StudentLeaveApplicationRepository studentLeaveApplicationRepository;

    // Create a new leave application
    public StudentLeaveApplication createLeaveApplication(StudentLeaveApplication leaveApplication) throws Exception {
        long daysBetween = ChronoUnit.DAYS.between(leaveApplication.getStartDate(), leaveApplication.getEndDate());
        if (daysBetween > 3) {
            throw new Exception("You cannot apply for more than 3 consecutive days of leave.");
        }

        // Attendance validation
        if (leaveApplication.getAttendancePercentage() < 70.0) {
            throw new Exception("Cannot apply for leave with less than 70% attendance.");
        }

        return studentLeaveApplicationRepository.save(leaveApplication);
    }

    // Get leave applications by student ID
    public List<StudentLeaveApplication> getLeaveApplicationsByStudentId(String studentId) {
        return studentLeaveApplicationRepository.findByStudentId(studentId);
    }

    // Get all leave applications
    public List<StudentLeaveApplication> getAllLeaveApplications() {
        return studentLeaveApplicationRepository.findAll();
    }
    public StudentLeaveApplication updateLeaveApplicationStatus(Long id, String status) {
        // Find the leave application by ID
        StudentLeaveApplication leaveApplication = studentLeaveApplicationRepository.findById(id).orElse(null);

        if (leaveApplication != null) {
            leaveApplication.setStatus(status);  // Update status to Approved or Rejected
            return studentLeaveApplicationRepository.save(leaveApplication);  // Save changes
        }

        return null;  // Return null if leave application not found
    }
}
