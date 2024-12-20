import { generalRequest } from "~/services/genServ";
import {
  type DataResponse,
  type StudentItem,
  type FamilyMemberItem,
  type TeacherItem,
  type LeaveItem,
  type DormitoryItem,
} from "~/models/general";
//获取学生列表分页数据后台数据请求方法
export async function getStudentPageData(
  numName: String | null,
  currentPage: number
): Promise<DataResponse> {
  const res = await generalRequest("/api/student/getStudentPageData", {
    numName: numName,
    currentPage: currentPage,
  });
  return res as DataResponse;
}
//删除学生后台数据请求方法
export async function studentDelete(studentId: number): Promise<DataResponse> {
  const res = await generalRequest("/api/student/studentDelete", {
    studentId: studentId,
  });
  return res as DataResponse;
}
//获取学生基本信息后台数据请求方法
export async function getStudentInfo(studentId: number): Promise<StudentItem> {
  const res = await generalRequest("/api/student/getStudentInfo", {
    studentId: studentId,
  });
  return res.data as StudentItem;
}
//保存学生基本信息后台数据请求方法
export async function studentEditSave(
  studentId: number | null,
  form: StudentItem
): Promise<DataResponse> {
  const res = await generalRequest("/api/student/studentEditSave", {
    studentId: studentId,
    form: form,
  });
  return res as DataResponse;
}
//获取学生家庭成员列表数据后台数据请求方法
export async function getFamilyMemberList(
  studentId: number
): Promise<FamilyMemberItem[]> {
  const res = await generalRequest("/api/student/getFamilyMemberList", {
    studentId: studentId,
  });
  return res.data as FamilyMemberItem[];
}
//保存学生家庭成员信息后台数据请求方法
export async function familyMemberSave(
  form: FamilyMemberItem
): Promise<DataResponse> {
  const res = await generalRequest("/api/student/familyMemberSave", {
    form: form,
  });
  return res as DataResponse;
}
//删除学生家庭成员信息后台数据请求方法
export async function familyMemberDelete(
  memberId: number
): Promise<DataResponse> {
  const res = await generalRequest("/api/student/familyMemberDelete", {
    memberId: memberId,
  });
  return res as DataResponse;
}
//获取教师列表数据后台数据请求方法
export async function getTeacherList(numName: String | null): Promise<[]> {
  const res = await generalRequest("/api/teacher/getTeacherList", {
    numName: numName,
  });
  return res as [];
}
//获取教师基本信息后台数据请求方法
export async function getTeacherInfo(
  teacherId: number | null
): Promise<TeacherItem> {
  const res = await generalRequest("/api/teacher/getTeacherInfo", {
    teacherId: teacherId,
  });
  return res as TeacherItem;
}
//保存教师基本信息后台数据请求方法
export async function teacherEditSave(
  teracherId: number,
  form: TeacherItem
): Promise<DataResponse> {
  const res = await generalRequest("/api/teacher/teacherEditSave", {
    teacherId: teracherId,
    form: form,
  });
  return res as DataResponse;
}
//删除教师后台数据请求方法
export async function teacherDelete(teacherId: number): Promise<DataResponse> {
  const res = await generalRequest("/api/teacher/teacherDelete", {
    teacherId: teacherId,
  });
  return res as DataResponse;
}


//以下是自己实现的方法

//根据宿舍id获取宿舍列表数据后台数据请求方法
export async function getDormitoryList(dormitoryId: number | null): Promise<[]> {
  const res = await generalRequest("/api/dormitory/getDormitoryList", {
    dormitoryId: dormitoryId,
  });
  return res as [];
}

//删除宿舍后台数据请求方法
export async function dormitoryDelete(dormitoryId: number): Promise<DataResponse> {
  const res = await generalRequest("/api/dormitory/dormitoryDelete", {
    dormitoryId: dormitoryId,
  });
  return res as DataResponse;
}

//获取宿舍基本信息后台数据请求方法
export async function getDormitoryInfo(dormitoryId: number | null): Promise<[]> {
  const res = await generalRequest("/api/dormitory/getDormitoryInfo", {dormitoryId: dormitoryId,});
  return res.data as [];
}

//保存宿舍基本信息后台数据请求方法
export async function dormitoryEditSave(form: DormitoryItem): Promise<DataResponse> {
  const res = await generalRequest("/api/dormitory/dormitoryEditSave", {
    form: form,
  });
  return res as DataResponse;
}

//新增请假记录
export async function postLeaveRequest(form: LeaveItem): Promise<DataResponse>  {
  const res = await generalRequest("/api/student/postLeaveRequest", {
    form: form,
  });
  return res as DataResponse;
}
//查询当前登录学生的请假记录
export async function studentGetLeaveRecord(userId: number): Promise<[]>  {
  const res = await generalRequest("/api/student/studentGetLeaveRecord", {
    userId: userId,
  });
  return res.data as [];
}


//老师同意或拒绝某一请假记录
export async function editLeaveItem(form: LeaveItem): Promise<DataResponse>  {
  const res = await generalRequest("/api/student/editLeaveItem", {
    form: form,
  });
  return res as DataResponse;
}

//老师查询学生的请假记录
export async function teacherGetLeaveRecord(userId: number): Promise<[]>  {
  const res = await generalRequest("/api/student/teacherGetLeaveRecord", {
    userId: userId,
  });
  return res.data as [];
}

//管理员查询学生的请假记录
export async function adminGetLeaveRecord(userId: number): Promise<[]>  {
  const res = await generalRequest("/api/student/adminGetLeaveRecord", {
    userId: userId ,
  });
  return res.data as [];
}