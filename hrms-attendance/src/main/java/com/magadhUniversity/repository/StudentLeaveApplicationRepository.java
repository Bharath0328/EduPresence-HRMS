package com.magadhUniversity.repository;

import com.magadhUniversity.model.StudentLeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentLeaveApplicationRepository extends JpaRepository<StudentLeaveApplication, Long> {

    List<StudentLeaveApplication> findByStudentId(String studentId);
}
