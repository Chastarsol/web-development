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
@Table(name = "dormitory")
public class Dormitory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dormitory_id")
    private Integer dormitoryId;

    private String door;

    private String building;

    private String floor;

    private String grade;



}
