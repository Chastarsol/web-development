package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Dormitory;
import org.fatmansoft.teach.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {

    @Query(value = "from Dormitory d where (?1 is null or ?1 = '' or d.door =?1)")
    List<Dormitory> findDormitoryListByNumName(String door);
    @Query(" FROM Student s WHERE s.dormitoryId = :dormitoryId")
    List<Student> findstudentListByDormitoryId(Integer dormitoryId);


}
