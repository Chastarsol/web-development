package org.fatmansoft.teach.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "course_student")
public class CourseStudent {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "course_student_id")
 private Integer courseStudentId;
 private Integer courseId ;
 private Integer studentUserId ;
 private Integer teacherUserId ;
 private String courseName ;
 private String studentName ;
 private String teacherName ;
}

