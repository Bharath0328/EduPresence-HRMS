package com.magadhUniversity.controller;
import com.magadhUniversity.model.LeaveApplication;
import com.magadhUniversity.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/leaves")
public class EmployeeLeaveController {

    @Autowired
    private LeaveApplicationService leaveApplicationService;

    // Show form to create a leave application
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("leaveApplication", new LeaveApplication());
        return "create_leave";  // Show form to create a leave application
    }

    // Submit the leave application (POST)
    @PostMapping("/create")
    public String submitLeave(@ModelAttribute LeaveApplication leaveApplication, Model model) {
        try {
            // Log to verify form data is correctly received
            System.out.println("Submitted Leave Application: " + leaveApplication);

            // Save leave application to database
            leaveApplicationService.createLeaveApplication(leaveApplication);
            return "redirect:/admin/leaves";  // Redirect to admin page after submission
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());  // Show validation error message
            return "create_leave";  // Return to the form if there's an error
        }
    }

    // Employee: View leave status for employee
    @GetMapping("/status")
    public String viewLeaveStatus(@RequestParam("employeeName") String employeeName, Model model) {
        List<LeaveApplication> leaveApplications = leaveApplicationService.getLeaveApplicationsByEmployeeName(employeeName);
        model.addAttribute("leaveApplications", leaveApplications);
        return "view_leave_status";  // Show leave status for employee
    }

    // Admin: View all leave applications
    @GetMapping("/admin/leaves")
    public String listAllLeaves(Model model) {
        List<LeaveApplication> leaveApplications = leaveApplicationService.getAllLeaveApplications();
        model.addAttribute("leaveApplications", leaveApplications);
        return "list_leaves";  // Show admin leave application list
    }

    // Admin: Approve or reject a leave application
    @PostMapping("/admin/leaves/update/{id}")
    public String updateLeaveStatus(@PathVariable Long id, @RequestParam String status) {
        System.out.println("Attempting to update leave application with ID: " + id + " to status: " + status);
        LeaveApplication leaveApplication = leaveApplicationService.updateLeaveApplicationStatus(id, status);

        // Check if leave application is found and updated
        if (leaveApplication == null) {
            System.out.println("Leave Application not found with ID: " + id);
            return "redirect:/leaves/admin/leaves?error=Leave application not found";
        }

        // Redirect to the leave applications list after status update
        return "redirect:/leaves/admin/leaves";
    }
}
