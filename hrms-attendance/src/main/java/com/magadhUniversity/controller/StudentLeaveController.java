package com.magadhUniversity.controller;

import com.magadhUniversity.model.StudentLeaveApplication;
import com.magadhUniversity.service.StudentLeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@Controller
@RequestMapping("/student-leaves")
public class StudentLeaveController {

    @Autowired
    private StudentLeaveApplicationService studentLeaveApplicationService;

    // Show form to create a leave application
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("studentLeaveApplication", new StudentLeaveApplication());
        return "create_student_leave";  // Return to leave form
    }

    // Submit the leave application
    @PostMapping("/create")
    public String submitLeave(@ModelAttribute StudentLeaveApplication leaveApplication, Model model) {
        try {
            studentLeaveApplicationService.createLeaveApplication(leaveApplication);
            return "redirect:/student-leaves/admin";  // Redirect to admin page
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "create_student_leave";  // Show error
        }
    }

    // Student: View leave status
    @GetMapping("/status")
    public String viewLeaveStatus(@RequestParam("studentId") String studentId, Model model) {
        List<StudentLeaveApplication> leaveApplications = studentLeaveApplicationService.getLeaveApplicationsByStudentId(studentId);
        model.addAttribute("leaveApplications", leaveApplications);
        return "view_student_leave_status";  // Return to leave status page
    }

    // Admin: View all leave applications
    @GetMapping("/admin")
    public String listAllLeaves(Model model) {
        List<StudentLeaveApplication> leaveApplications = studentLeaveApplicationService.getAllLeaveApplications();
        model.addAttribute("leaveApplications", leaveApplications);
        return "list_student_leaves";  // Return admin page
    }
    // Admin: Approve or reject a leave application
    @PostMapping("/admin/update/{id}")
    public String updateLeaveStatus(@PathVariable Long id, @RequestParam String status) {
        System.out.println("Admin is updating leave application ID: " + id + " to status: " + status);
        StudentLeaveApplication leaveApplication = studentLeaveApplicationService.updateLeaveApplicationStatus(id, status);

        if (leaveApplication == null) {
            // If leave application not found, redirect with error
            return "redirect:/student-leaves/admin?error=Leave application not found";
        }

        return "redirect:/student-leaves/admin";  // Redirect back to the admin page
    }
}
