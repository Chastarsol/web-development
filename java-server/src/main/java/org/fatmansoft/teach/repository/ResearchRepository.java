package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Dormitory;
import org.fatmansoft.teach.models.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResearchRepository extends JpaRepository<Research, Integer>  {

    //根据id查询单个宿舍
    @Query(value = "from Research d where d.teacherName = ?1")
    List<Research> getListByTeacherName(String teacherName);
    @Query(value = "from Research d ")
    List<Research>  getAllList();
    //根据studentName返回他所参加的所有team
    @Query(value = "from Research d where  d.s1Name = :studentName or d.s2Name = :studentName or d.s3Name = :studentName or d.s4Name = :studentName")
    List<Research> getResearchListByStudentName(String studentName);
}