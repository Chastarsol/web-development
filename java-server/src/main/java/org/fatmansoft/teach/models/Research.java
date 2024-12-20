package org.fatmansoft.teach.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "research")
public class Research {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "research_id")
    private Integer researchId;
    private String teamName;
    private String teacherName;
    private String s1Name;
    private String s2Name;
    private String s3Name;
    private String s4Name;
    private String headLine;
    private String content;

}
