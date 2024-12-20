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
        
        <el-table-column label="请假原因" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.reason }}
          </template>
        </el-table-column>
        <el-table-column label="老师姓名" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.teacherName }}
          </template>
        </el-table-column>
        <el-table-column label="请假状态" color="black" align="center">
          <template v-slot="scope">
            <span v-if="scope.row.status === 0">待处理</span>
            <span v-if="scope.row.status === 1">通过</span>
            <span v-if="scope.row.status === 2">未批准</span>
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
  import { studentGetLeaveRecord } from "~/services/personServ";

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
            if (userId!= null) {
                this.dataList = await studentGetLeaveRecord(userId);
            }
        }
    }
  });
</script>