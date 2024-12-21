package org.fatmansoft.teach.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.formula.functions.T;
import org.fatmansoft.teach.models.*;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.repository.*;
import org.fatmansoft.teach.service.ResearchService;
import org.fatmansoft.teach.service.StudentService;
import org.fatmansoft.teach.util.ComDataUtil;
import org.fatmansoft.teach.util.CommonMethod;
import org.fatmansoft.teach.util.DateTimeTool;
import org.fatmansoft.teach.util.JsonConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/research")

public class ResearchController {
    @Autowired
    private PasswordEncoder encoder;  //密码服务自动注入
    @Autowired
    private ResearchRepository researchRepository;
    @Autowired
    private PersonRepository personRepository;  //人员数据操作自动注入
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;//学生数据操作自动注入
    @Autowired
    private UserRepository userRepository;  //学生数据操作自动注入
    @Autowired
    private UserTypeRepository userTypeRepository; //用户类型数据操作自动注入
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ResearchService researchService;


    //新增科研项目
    @PostMapping("/researchEditSave")
    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
    public DataResponse researchEditSave(@Valid @RequestBody DataRequest dataRequest) {
        Map form = dataRequest.getMap("form"); //参数获取Map对象
        Integer researchId = CommonMethod.getInteger(form, "researchId");
        String teamName = CommonMethod.getString(form, "teamName");
        String teacherName = CommonMethod.getString(form, "teacherName");
        String s1Name = CommonMethod.getString(form, "s1Name");
        String s2Name = CommonMethod.getString(form, "s2Name");
        String s3Name = CommonMethod.getString(form, "s3Name");
        String s4Name = CommonMethod.getString(form, "s4Name");
        String headline = CommonMethod.getString(form, "headline");
        String content = CommonMethod.getString(form, "content");
        Research d = null;
        Optional<Research> op;
        if (researchId != null) {
            op = researchRepository.findById(researchId);  //查询对应数据库中主键为id的值的实体对象
            if (op.isPresent()) {
                d = op.get();
            }
        }
        // 如果team不存在，则创建新team
        if (d == null) {
            d = new Research();
            if (researchId == null) {
                d.setResearchId(0);
            }
            d.setContent(content);
            d.setHeadline(headline);
            d.setTeamName(teamName);
            d.setTeacherName(teacherName);
            d.setS1Name(s1Name);
            d.setS2Name(s2Name);
            d.setS3Name(s3Name);
            d.setS4Name(s4Name);
            researchRepository.saveAndFlush(d);
        } else {
            // 如果team存在，更新已有team信息
            d.setContent(content);
            d.setHeadline(headline);
            d.setTeamName(teamName);
            d.setTeacherName(teacherName);
            d.setS1Name(s1Name);
            d.setS2Name(s2Name);
            d.setS3Name(s3Name);
            d.setS4Name(s4Name);
            // 更新其他属性
            researchRepository.saveAndFlush(d);
        }



        return CommonMethod.getReturnMessageOK();  //通知前端操作正常
    }
    //根据userId返回该老师所管理的team
    @PostMapping("/teacherGetResearch")
    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
    public DataResponse getResearchInfo(@Valid @RequestBody DataRequest dataRequest) {
        Integer userId= dataRequest.getInteger("userId");
        String teacherName=personRepository.findPersonById(userId).getName();
        List<Research> List = researchRepository.getListByTeacherName(teacherName);
        return CommonMethod.getReturnData(List);
    }
    //删除team
    @PostMapping("/researchDelete")
    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
    public DataResponse teamDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer researchId= dataRequest.getInteger("researchId");
        Research d = null;
        Optional<Research> op;
        if (researchId != null) {
            op = researchRepository.findById(researchId);   //查询获得实体对象
            if (op.isPresent()) {
                d = op.get();
            }
        }
        //删除该team
        researchRepository.delete(d);
        return CommonMethod.getReturnMessageOK();  //通知前端操作正常
    }

    @PostMapping("/getResearchInfoByResearchId")
    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
    public DataResponse getResearchInfoByResearchId(@Valid @RequestBody DataRequest dataRequest) {
        Integer researchId = dataRequest.getInteger("researchId");

        Research s = null;
        Optional<Research> op;
        if (researchId != null) {
            op = researchRepository.findById(researchId); //根据科研项目主键从数据库查询科研项目的信息
            if (op.isPresent()) {
                s = op.get();
            }
        }
        return CommonMethod.getReturnData(researchService.getMapFromResearch(s)); //这里回传包含科研项目信息的Map对象
    }

    //返回所有的team（管理员）
    @PostMapping("/adminGetResearch")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse getAllResearchInfo(@Valid @RequestBody DataRequest dataRequest) {
        List<Research> List = researchRepository.getAllList();
        return CommonMethod.getReturnData(List);
    }
    //根据UseId返回该学生所有参加的team（学生）
    @PostMapping("/studentGetResearch")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public DataResponse getResearchByStudentId(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId= dataRequest.getInteger("userId");
        String studentName=personRepository.findPersonById(studentId).getName();
        List<Research> List= researchRepository.getResearchListByStudentName(studentName);
        return CommonMethod.getReturnData(List);
    }
}