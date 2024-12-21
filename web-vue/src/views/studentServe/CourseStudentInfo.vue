<template>
    <div class="base_form">
      <div class="base_header">
        <div class="blue_column"></div>
        <div class="base_title">选课结果</div>
      </div>

      <el-table :data="dataList" border style="width: 100%">
        <el-table-column label="序号" fixed="left" width="50" color="black">
          <template v-slot="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        
        <el-table-column label="课程名称" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.courseName }}
          </template>
        </el-table-column>
        <el-table-column label="老师姓名" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.teacherName }}
          </template>
        </el-table-column>


    </el-table>

    </div>
</template>

<script lang="ts">
  import { defineComponent } from "vue";
  
  import { CourseStudentItem, LeaveItem } from "~/models/general";
  import { useAppStore } from "~/stores/app";
  import { getCourseStudent } from "~/services/personServ";

  export default defineComponent({
    data() {
      return {
        userId : 0 as number,
        dataList: [] as CourseStudentItem[]
      };
    },
    created() {
      const res = this.$route.query.userId;
      if (res!= null) {
        this.userId = parseInt(res.toString());
      }
      this.queryAll(this.userId);
    },
    methods : {
        async queryAll(userId : number) {
            if (userId!= null) {
                this.dataList = await getCourseStudent(userId);
            }
        }
    }
  });
</script>