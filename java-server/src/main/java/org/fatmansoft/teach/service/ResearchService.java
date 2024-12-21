package org.fatmansoft.teach.service;

import org.fatmansoft.teach.models.Person;
import org.fatmansoft.teach.models.Research;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.util.ComDataUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResearchService {
    public Map getMapFromResearch(Research s) {
        Map m = new HashMap();

        m.put("researchId",s.getResearchId());
        m.put("teamName",s.getTeamName());
        m.put("teacherName", s.getTeacherName());
        m.put("s1Name", s.getS1Name());
        m.put("s2Name",s.getS2Name());
        m.put("s3Name",s.getS3Name());
        m.put("s4Name",s.getS4Name());
        m.put("headline",s.getHeadline());
        m.put("content",s.getContent());
        return m;
    }

}
