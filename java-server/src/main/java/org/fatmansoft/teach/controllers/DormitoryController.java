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
@RequestMapping("/api/dormitory")

public class DormitoryController {

    @Autowired
    private DormitoryRepository dormitoryRepository;

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


    //根据宿舍id查询宿舍，id若为null则查询所有宿舍
    @PostMapping("/getDormitoryList")
    @PreAuthorize("hasRole('ADMIN')")
    public String  getDormitoryList(@Valid @RequestBody DataRequest dataRequest) {
        Integer dormitoryId = dataRequest.getInteger("dormitoryId");

        //如果传入的id为null，则查询所有宿舍
        List<Dormitory> sList;
        if(dormitoryId == null) {
            sList = dormitoryRepository.findAllDormitoryList(dormitoryId);
        } else {
            //否则根据该id查询宿舍
            sList = dormitoryRepository.findDormitoryListByDormitoryId(dormitoryId);
        }
        return JsonConvertUtil.getDataListJson(sList);
    }


    //根据宿舍Id返回整个宿舍学生
    @PostMapping("/getDormitoryInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse getDormitoryStudentInfo(@Valid @RequestBody DataRequest dataRequest) {
        Integer dormitoryId = dataRequest.getInteger("dormitoryId");
       // System.out.println("接收到的宿舍Id: " + dormitoryId);
        if (dormitoryId== null) {
            dormitoryId = 0;
        }
        List<Student> list = dormitoryRepository.findstudentListByDormitoryId(dormitoryId);

        List<Map> dataList=new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Student s = (Student) list.get(i);

            Map m = studentService.getMapFromStudent(s);
            if (m.get("gender").equals("1")) {
                m.put("gender", "男");
            } else {
                m.put("gender", "女");
            }
            m.remove("introduce");
            dataList.add(m);
        }
        return CommonMethod.getReturnData(dataList);

    }

    @PostMapping("/dormitoryDelete")
    @PreAuthorize(" hasRole('ADMIN')")//删除宿舍
    public DataResponse dormitoryDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer dormitoryId = dataRequest.getInteger("dormitoryId");  //获取宿舍id值、
        Dormitory d = null;
        Optional<Dormitory> op;
        if (dormitoryId != null) {
            op = dormitoryRepository.findById(dormitoryId);   //查询获得实体对象
            if (op.isPresent()) {
                d = op.get();
            }
        }
        //删除该宿舍内的学生
        if (d != null) {
            List<Student> list = dormitoryRepository.findstudentListByDormitoryId(dormitoryId);
            for (int i = 0; i < list.size(); i++) {
                Student s = list.get(i);

                Optional<User> uOp = userRepository.findByPersonPersonId(s.getPerson().getPersonId()); //查询对应该学生的账户
                if (uOp.isPresent()) {
                    userRepository.delete(uOp.get()); //删除对应该学生的账户
                }

                Person p = s.getPerson();
                studentRepository.delete(s);
                personRepository.delete(p);
            }
        }
        //删除该宿舍
        dormitoryRepository.deleteDormitoryByDormitoryId(dormitoryId);
        return CommonMethod.getReturnMessageOK();  //通知前端操作正常
    }

}
