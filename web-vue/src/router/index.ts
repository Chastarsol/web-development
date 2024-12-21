// Composables
import { createRouter, createWebHistory } from "vue-router";
//路由表
const routes = [
  //当路由为空时，重定向到登录页面
  {
    path: "/",
    redirect: "/Login",
  },
  //登录页面
  {
    path: "/Login",
    name: "Login",
    component: () => import("~/Login.vue"),
  },
  //主页面
  {
    path: "/MainPage",
    name: "MainPage",
    component: () => import("~/views/MainPage.vue"),
  },
  {
    path: "/SystemIntroduce",
    name: "SystemIntroduce",
    component: () => import("~/views/info/SystemIntroduce.vue"),
  },
  {
    path: "/Password",
    name: "Password",
    component: () => import("~/views/info/Password.vue"),
  },
  {
    path: "/StudentIntroduce",
    name: "StudentIntroduce",
    component: () => import("~/views/info/StudentIntroduce.vue"),
  },
  {
    path: "/TeacherIntroduce",
    name: "TeacherIntroduce",
    component: () => import("~/views/info/TeacherIntroduce.vue"),
  },
  {
    path: "/menu-manage-panel",
    name: "MenuManage",
    component: () => import("~/views/system/MenuManage.vue"),
  },
  {
    path: "/dictionary-manage-panel",
    name: "DictionaryManage",
    component: () => import("~/views/system/DictionaryManage.vue"),
  },

  {
    path: "/student-panel",
    name: "StudentTable",
    component: () => import("~/views/person/StudentTable.vue"),
  },
  {
    path: "/StudentInfo",
    name: "StudentInfo",
    component: () => import("~/views/person/StudentInfo.vue"),
  },
  {
    path: "/FamilyMember",
    name: "FamilyMember",
    component: () => import("~/views/person/FamilyMember.vue"),
  },
  {
    path: "/teacher-panel",
    name: "teacherTable",
    component: () => import("~/views/person/TeacherTable.vue"),
  },
  {
    path: "/TeacherInfo",
    name: "TeacherInfo",
    component: () => import("~/views/person/TeacherInfo.vue"),
  },

  {
    path: "/course-panel",
    name: "CourseTable",
    component: () => import("~/views/teaching/CourseTable.vue"),
  },
  {
    path: "/score-table-panel",
    name: "ScoreTable",
    component: () => import("~/views/teaching/ScoreTable.vue"),
  },
  //从这里开始，是除了提供的代码框架外，自己开发的功能

  //教材管理
  {
    path: "/textbook-panel",
    name: "TextbookTable",
    component: () => import("~/views/teaching/TextbookTable.vue"),
  },
  //宿舍管理
  {
    path: "/dormitory-panel",
    name: "DormitoryTable",
    component: () => import("~/views/person/DormitoryTable.vue"),
  },
  //宿舍信息
  {
    path: "/DormitoryInfo",
    name: "DormitoryInfo",
    component: () => import("~/views/person/DormitoryInfo.vue"),
  },
  //宿舍增加
  {
    path: "/DormitoryAdd",
    name: "DormitoryAdd",
    component: () => import("~/views/person/DormitoryAdd.vue"),
  },
  //竞赛管理
  {
    path: "/competition-panel",
    name: "CompetitionTable",
    component: () => import("~/views/teaching/CompetitionTable.vue"),
  },
  //学生发送请假请求页面
  {
    path: "/leave",
    name: "StudentLeave",
    component: () => import("~/views/studentServe/StudentLeave.vue"),
  },
  //学生查看请假记录
  {
    path: "/leaveRecord",
    name: "StudentLeaveRecord",
    component: () => import("~/views/studentServe/StudentLeaveRecord.vue"),
  },
  //老师：请假管理
  {
    path: "/leave-manage-panel-teacher",
    name: "TeacherLeaveManage",
    component: () => import("~/views/studentServe/TeacherLeaveManage.vue"),
  },
  //管理员：请假管理
  {
    path: "/leave-manage-panel-admin",
    name: "AdminLeaveManage",
    component: () => import("~/views/studentServe/AdminLeaveManage.vue"),
  },
  //老师：科研管理
  {
    path: "/research-panel-teacher",
    name: "TeacherResearchTable",
    component: () => import("~/views/teaching/TeacherResearchTable.vue"),
  },
  //科研信息
  {
    path: "/researchInfo",
    name: "ResearchInfo",
    component: () => import("~/views/teaching/ResearchInfo.vue"),
  },
  //学生：科研管理
  {
    path: "/research-panel-student",
    name: "StudentResearchTable",
    component: () => import("~/views/studentServe/StudentResearchTable.vue"),
  },
  //管理员：科研管理
  {
    path: "/researchPanelAdmin",
    name: "AdminResearchTable",
    component: () => import("~/views/teaching/AdminResearchTable.vue"),
  },
  //学生选课
  {
    path: "/course-student",
    name: "CourseStudent",
    component: () => import("~/views/studentServe/CourseStudent.vue"),
  },
  //查看学生选课记录
  {
    path: "/CourseStudentInfo",
    name: "CourseStudentInfo",
    component: () => import("~/views/studentServe/CourseStudentInfo.vue"),
  },
  //老师查看教授课程
  {
    path: "/course-teacher",
    name: "CourseTeacher",
    component: () => import("~/views/studentServe/CourseTeacher.vue"),
  },
];
//路由创建
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
//路由导出
export default router;
