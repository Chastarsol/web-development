package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Dormitory;
import org.fatmansoft.teach.models.Leave;
import org.fatmansoft.teach.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    //查询所有请假条例
    @Query(value = "from Leave d where ?1 = null")
    List<Leave> getAllLeaveList();
    //根据id查询单个条例
    @Query(value = "from Leave d where d.studentUserId = :studentId ")
    List<Leave> findLeaveListById(Integer studentId);
    @Query(value = "from Leave d where d.status= 0 and d.teacherUserId=:teacherId")
    List<Leave> getLeaveListOnlyZero(Integer teacherId);
    @Query(value = "from Leave d where d.status= 0 ")
    List<Leave> getAllLeaveListOnlyZero();
    @Query(value = "from Leave  ")
    List<Leave> getLeaveListAll();
}