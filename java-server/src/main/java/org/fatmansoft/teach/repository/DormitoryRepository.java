package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Dormitory;
import org.fatmansoft.teach.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {

    //根据id查询单个宿舍
    @Query(value = "from Dormitory d where d.dormitoryId = ?1")
    List<Dormitory> findDormitoryListByDormitoryId(Integer dormitoryId);

    //查询所有宿舍
    @Query(value = "from Dormitory d where ?1 = null")
    List<Dormitory> findAllDormitoryList(Integer dormitoryId);


    @Query(" FROM Student s WHERE s.dormitoryId = :dormitoryId")
    List<Student> findstudentListByDormitoryId(Integer dormitoryId);

    @Query(nativeQuery = true, value = "DELETE FROM dormitory WHERE dormitory_id = :dormitoryId")
    void deleteDormitoryByDormitoryId(Integer dormitoryId);

}
