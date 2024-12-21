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
@RequestMapping("/api/courseStudent")

public class CourseStudentController {

    @Autowired
    private CourseStudentRepository courseStudentRepository;

    @Autowired
    private PasswordEncoder encoder;  //密码服务自动注入

    @Autowired
    private PersonRepository personRepository;  //人员数据操作自动注入
    @Autowired
    private StudentService studentService;//学生数据操作自动注入
    @Autowired
    private UserRepository userRepository;  //学生数据操作自动注入
    @Autowired
    private UserTypeRepository userTypeRepository; //用户类型数据操作自动注入
    @Autowired
    private CourseRepository courseRepository;


    //老师查询自己的课被哪些同学所选
    @PostMapping("/getCourseTeacher")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public DataResponse getCourseTeacherSelf(@Valid @RequestBody DataRequest dataRequest) {
        Integer teacherUserId = dataRequest.getInteger("userId");
        List<CourseStudent> dataList = courseStudentRepository.findCourseListByTeacherId(teacherUserId);
        return CommonMethod.getReturnData(dataList);
    }
    //学生查询自己所选的课
    @PostMapping("/getCourseStudent")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public DataResponse getCourseStudentSelf(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentUserId = dataRequest.getInteger("userId");
        List<CourseStudent> dataList = courseStudentRepository.findCourseByStudentId(studentUserId );
        return CommonMethod.getReturnData(dataList);
    }
    //学生选课
    @PostMapping("/courseStudentAdd")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public DataResponse courseStudentEditSave(@Valid @RequestBody DataRequest dataRequest) {
        //获取前端传来的参数
        String courseName= dataRequest.getString("courseName");
        Integer studentUserId = dataRequest.getInteger("userId");
        //设置course-student表的主键
        Integer courseStudentId = 0;

        System.out.println("coursename is :" + courseName);
        System.out.println("userId is :" + studentUserId);

        //根据课程名字获取课程，进而获取课程id和老师姓名
        Course c =  courseRepository.getCourseIdByCourseName(courseName);
        Integer courseId= c.getCourseId();
        String teacherName= c.getTeacherName();
        //根据老师姓名获取老师，以及teacherUserId
        Person p1 = personRepository.findPersonByName(teacherName);
        Integer teacherUserId= p1.getPersonId();
        //根据studentUserId获取学生，以及学生姓名
        Person p2 = personRepository.findPersonById(studentUserId);
        String studentName=p2.getName() ;

        CourseStudent d = null;
        Optional<CourseStudent> op;
        if (courseStudentId != null) {
            op = courseStudentRepository.findById(courseStudentId);  //查询对应数据库中主键为id的值的实体对象
            if (op.isPresent()) {
                d = op.get();
            }
        }
        if (d == null) {
            // 如果选课记录不存在，则创建的选课记录
            d = new CourseStudent();
            if (courseStudentId == null) {
                d.setCourseStudentId(0);
            }
            d.setCourseId(courseId);
            d.setStudentUserId(studentUserId);
            d.setTeacherUserId(teacherUserId);
            d.setCourseName(courseName);
            d.setStudentName(studentName);
            d.setTeacherName(teacherName);
            courseStudentRepository.saveAndFlush(d);
        } else {
            // 如果选课信息已存在，更新已有选课信息
            d.setCourseId(courseId);
            d.setStudentUserId(studentUserId);
            d.setTeacherUserId(teacherUserId);
            d.setCourseName(courseName);
            d.setStudentName(studentName);
            d.setTeacherName(teacherName);
            courseStudentRepository.saveAndFlush(d);
        }

        System.out.println("CourseStudent is" + d);

        return CommonMethod.getReturnMessageOK();  //通知前端操作正常
    }

    //学生退选课
    @PostMapping("/courseStudentDelete")
    @PreAuthorize(" hasRole('ROLE_STUDENT')")
    public DataResponse couserStudentDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentUserId= dataRequest.getInteger("userId");
        String courseName = dataRequest.getString("courseName");
        CourseStudent cs = courseStudentRepository.findCourseStudentByStudentIdAndCourseName(studentUserId,courseName);
        Integer couserStudentId= cs.getCourseStudentId();
        CourseStudent d = null;
        Optional<CourseStudent> op;
        if (couserStudentId != null) {
            op = courseStudentRepository.findById(couserStudentId);   //查询获得实体对象
            if (op.isPresent()) {
                d = op.get();
            }
        }
        //删除该选课记录
        courseStudentRepository.delete(d);
        return CommonMethod.getReturnMessageOK();  //通知前端操作正常
    }

}











