<template>
    <div class="base_form">
      <div class="base_header">
        <div class="blue_column"></div>
        <div class="base_title">选课系统</div>
      </div>

      <div class="query_right">
        <button style="margin-left: 5px" class="commButton" @click="courseStudentRecord(userId)">查看选课结果</button>
      </div>
  
  
      <div class="table-content">
        <el-table 
          :data="courseList" 
          stripe 
          :header-cell-style="{
            color: '#2E2E2E',
            fontSize: '10px',
            fontWeight: '400',
            background: 'rgb(242,242,242)',
          }"
          :row-style="{ height: '10px' }"
          :cell-style="{ padding: '2px' }"
          style="width: 100%"
          >
  
          <el-table-column label="序号" width="180">
            <template v-slot="scope">
              {{ scope.$index + 1 }}
            </template>
          </el-table-column>
  
          <el-table-column label="课程名称" width="180">
            <template v-slot="scope">
              {{ scope.row.name }}
            </template>
          </el-table-column>
  
          <el-table-column label="课程学分" width="180">
            <template v-slot="scope">
              {{ scope.row.credit }}
            </template>
          </el-table-column>
  
          <!-- <el-table-column label="前置课程" width="180">
            <template v-slot="scope">
              {{ scope.row.preCourse }}
            </template>
          </el-table-column> -->
  

          <!--表格最右侧的操作按钮-->
          <el-table-column label="操作" color="black" align="center" width="230">
            <template v-slot="scope">
        
              <el-button class="commButton" @click="addItem(scope.row.name)"
                >选课</el-button
              >
              <el-button class="commButton" @click="deleteItem(scope.row.name)"
                >退选</el-button
              >
            </template>
          </el-table-column>
  
  
        </el-table>
      </div>
  
      
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent } from "vue";
  import { getDormitoryList } from "~/services/personServ";
  import { dormitoryDelete } from "~/services/personServ";
  
  import { CourseStudentItem, CourseItem } from "~/models/general";
  import { message, messageConform } from "~/tools/messageBox";
  import router from "~/router";
  import { getCourseListStudent, courseStudentAdd, courseStudentDelete } from "~/services/personServ";
  import { useAppStore } from "~/stores/app";

  export default defineComponent({
    data() {
      return {
        userId : 0 as number,
        courseList: [] as CourseItem[],
        courseStudentItem : {} as CourseStudentItem,
      };
    },
  
    
    methods: {
      //查询所有课程信息,显示在主页上
      async queryAll() {
        this.courseList = await getCourseListStudent(this.userId);
      },
  
      //新增选课
      async addItem(courseName : string) {
        message(this, "选课成功");
        await courseStudentAdd(courseName, this.userId);
      },
      //删除选课
      async deleteItem(courseName: string) {
        const result = await messageConform("确认退选课程吗?");
        if (!result) {
          return;
        }
        const res = await courseStudentDelete(courseName, this.userId);
        if (res.code == 0) {
          message(this, "退选成功");
        } else {
          message(this, res.msg);
        }
        this.queryAll();
      },

      //查看选课记录
      async courseStudentRecord(userId : number) {
        router.push({
        path: "/CourseStudentInfo",
        // 传递参数
        query: { userId: userId },
      });
      }

    },
    created() {
      this.userId = useAppStore().userInfo.id;
      this.queryAll();
    },
  });
  </script>
  