package org.fatmansoft.teach.controllers;

import org.apache.poi.ss.formula.functions.T;
import org.fatmansoft.teach.models.*;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.repository.*;
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

    @PostMapping("/getDormitoryList")
    @PreAuthorize("hasRole('ADMIN')")
    public String  getDormitoryList(@Valid @RequestBody DataRequest dataRequest) {
        String door = dataRequest.getString("door");
        if(door == null)
            door ="";
        List<Dormitory> sList = dormitoryRepository.findDormitoryListByNumName(door);
        return JsonConvertUtil.getDataListJson(sList);
    }
    //根据宿舍Id返回整个宿舍学生
    @PostMapping("/getStudentListByDormitoryId")
    @PreAuthorize("hasRole('ADMIN')")
    public String getDormitoryStudentInfo(@Valid @RequestBody DataRequest dataRequest) {
        Integer dormitoryId = dataRequest.getInteger("dormitoryId");
        if (dormitoryId== null) {
            dormitoryId = 0;
        }
        List<Student> sList = dormitoryRepository.findstudentListByDormitoryId(dormitoryId);
        return JsonConvertUtil.getDataObjectJson(sList);

    }
}
