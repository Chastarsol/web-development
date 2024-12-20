package org.fatmansoft.teach.models;

//这个是请假管理的实体类

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "leave")

public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private Integer leaveId;
    private String reason;
    private Integer studentUserId;
    private Integer teacherUserId;
    private String teacherName;
    private String studentName;
    private Integer status;

}
