package com.magadhUniversity.repository;

import com.magadhUniversity.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {

 // Find leave applications by employee name
 List<LeaveApplication> findByEmployeeName(String employeeName);

 // Find leave applications by date range
 List<LeaveApplication> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}



