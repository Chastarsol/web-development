package org.fatmansoft.teach.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.formula.functions.T;
import org.fatmansoft.teach.models.*;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.repository.*;
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
@RequestMapping("/api/student")

public class LeaveController {
    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private PasswordEncoder encoder;  //密码服务自动注入

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

    //根据userId返回请假条例
    @PostMapping("/studentGetLeaveRecord")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public DataResponse  getLeaveList(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentUserId = dataRequest.getInteger("userId");
        //Integer teacherUserId = dataRequest.getInteger("teacherId");

        //如果传入的id为null，则查询所有请假记录
        List<Leave> sList;
        if(studentUserId== null) {
            sList =leaveRepository.getAllLeaveList();
        } else {
            //否则根据该id查询条例
            sList = leaveRepository.findLeaveListById(studentUserId);
        }
        return CommonMethod.getReturnData(sList);
    }

    //根据UserID和teacherName新增学生请假
    @PostMapping("/postLeaveRequest")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public DataResponse leaveEditSaveBystudent(@Valid @RequestBody DataRequest dataRequest) {
        Map form = dataRequest.getMap("form"); //参数获取Map对象
        Integer leaveId= CommonMethod.getInteger(form, "leaveId");
        if (leaveId == null) {
            leaveId = 0;
        }
        String teacherName= CommonMethod.getString(form, "teacherName");
        Integer studentUserId= CommonMethod.getInteger(form, "studentId");

        String studentName = personRepository.findPersonById(studentUserId).getName();

        String reason= CommonMethod.getString(form, "reason");
        Person person =personRepository.findPersonByName(teacherName);
        Integer teacherUserId=person.getPersonId();


        //创建新的请假请求
        Leave d = new Leave();
        d.setLeaveId(leaveId);
        d.setStudentUserId(studentUserId);
        d.setReason(reason);
        d.setTeacherUserId(teacherUserId);
        d.setStatus(0);
        d.setTeacherName(teacherName);
        d.setStudentName(studentName);

        System.out.println("this is d " + d);
        leaveRepository.saveAndFlush(d);



        return CommonMethod.getReturnMessageOK();   // 将dormitoryId返回前端
    }

    @PostMapping("/teacherGetLeaveRecord")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public DataResponse  getLeaveListByTeacher(@Valid @RequestBody DataRequest dataRequest) {
        Integer teacherUserId = dataRequest.getInteger("teacherUserId");
        //如果传入的id为null，则查询所有请假记录
        List<Leave> sList;
        if(teacherUserId== null) {
            sList =leaveRepository.getAllLeaveListOnlyZero();
        } else {
            //否则根据该老师id查询条例
            sList =leaveRepository.getLeaveListOnlyZero(teacherUserId );
        }
        return CommonMethod.getReturnData(sList);
    }
    //老师进行编辑请假请求
    @PostMapping("/editLeaveItem")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public DataResponse  editLeave(@Valid @RequestBody DataRequest dataRequest) {
        Map form = dataRequest.getMap("form"); //参数获取Map对象
        Integer leaveId= CommonMethod.getInteger(form, "leaveId");
        String teacherName= CommonMethod.getString(form, "teacherName");
        Integer studentUserId= CommonMethod.getInteger(form, "studentUserId");

        String studentName = personRepository.findPersonById(studentUserId).getName();


        System.out.println("student name is : " + studentName);

        String reason= CommonMethod.getString(form, "reason");
        Integer status= CommonMethod.getInteger(form, "status");
        Integer teacherUserId= CommonMethod.getInteger(form, "teacherUserId");
        Leave d = new Leave();
        d.setLeaveId(leaveId);
        d.setStudentUserId(studentUserId);
        d.setReason(reason);
        d.setTeacherUserId(teacherUserId);
        d.setStatus(status);
        d.setTeacherName(teacherName);
        d.setStudentName(studentName);
        leaveRepository.saveAndFlush(d);
        return CommonMethod.getReturnMessageOK();
    }

    //返回所有请假信息
    @PostMapping("/adminGetLeaveRecord")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse  getLeaveListAll(@Valid @RequestBody DataRequest dataRequest) {
        List<Leave> List=leaveRepository.getLeaveListAll();
        return CommonMethod.getReturnData(List);
    }
}