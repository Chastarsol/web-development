package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Course;
import org.fatmansoft.teach.models.CourseStudent;
import org.fatmansoft.teach.models.Dormitory;
import org.fatmansoft.teach.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Integer> {
    @Query(value = "from CourseStudent c where c.teacherUserId=:teacherId ")
    List<CourseStudent>  findCourseListByTeacherId(Integer teacherId);

    @Query(value = "from CourseStudent c where c.studentUserId=:studentId")
    List<CourseStudent>  findCourseByStudentId(Integer studentId);

    @Query(value = "from CourseStudent c where c.studentUserId=:studentUserId and c.courseName=:courseName")
    CourseStudent  findCourseStudentByStudentIdAndCourseName(Integer studentUserId, String courseName);
}

