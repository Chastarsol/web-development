<template>
    <div class="base_form">
      <div class="base_header">
        <div class="blue_column"></div>
        <div class="base_title">请假记录</div>
      </div>

      <el-table :data="dataList" border style="width: 100%">
        <el-table-column label="序号" fixed="left" width="50" color="black">
          <template v-slot="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>

        <el-table-column label="学生姓名" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.studentName }}
          </template>
        </el-table-column>

        <el-table-column label="请假理由" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.reason }}
          </template>
        </el-table-column>
        
        <el-table-column label="操作" color="black" align="center" width="260">
          <template v-slot="scope">
            <el-button class="commButton" @click="agree(scope.row)">同意</el-button>
            <el-button class="commButton" @click="refuse(scope.row)">拒绝</el-button>
          </template>
      </el-table-column>
     
    </el-table>

    </div>
</template>

<script lang="ts">
  import { defineComponent } from "vue";
  import router from "~/router";
  import { LeaveItem } from "~/models/general";
  import { useAppStore } from "~/stores/app";
  import { teacherGetLeaveRecord } from "~/services/personServ";
  import { editLeaveItem } from "~/services/personServ"; 

  export default defineComponent({
    data() {
      return {
        userId : 0 as number,
        dataList: [] as LeaveItem[]
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
          if (userId != null) {
            this.dataList = await teacherGetLeaveRecord(userId);
          }
        },
        async agree(LeaveItem : LeaveItem) {
          LeaveItem.status = 1
          editLeaveItem(LeaveItem)
          this.queryAll(this.userId);
        },
        async refuse(LeaveItem : LeaveItem) {
          LeaveItem.status = 2
          editLeaveItem(LeaveItem)
          this.queryAll(this.userId);
        },
    }
  });
</script>