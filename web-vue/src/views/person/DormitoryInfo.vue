<template>
  <div class="base_form">
    <div class="base_header">
      <div class="blue_column"></div>
      <div class="base_title">宿舍信息</div>
    </div>

    <el-table :data="dataList" border style="width: 100%">
        <el-table-column label="序号" fixed="left" width="50" color="black">
          <template v-slot="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column label="学号" color="black" align="center" width="100">
          <template v-slot="scope">
            {{ scope.row.num }}
          </template>
        </el-table-column>
        <el-table-column label="姓名" color="black" align="center" width="100">
          <template v-slot="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="院系" color="black" align="center" width="100">
          <template v-slot="scope">
            {{ scope.row.dept }}
          </template>
        </el-table-column>
        <el-table-column label="专业" color="black" align="center" width="100">
          <template v-slot="scope">
            {{ scope.row.major }}
          </template>
        </el-table-column>
        <el-table-column label="班级" color="black" align="center" width="100">
          <template v-slot="scope">
            {{ scope.row.className }}
          </template>
        </el-table-column>
        <el-table-column label="证件号码" color="black" align="center" width="100">
          <template v-slot="scope">
            {{ scope.row.card }}
          </template>
        </el-table-column>
        <el-table-column label="性别" color="black" align="center" width="100">
          <template v-slot="scope">
            {{ scope.row.genderName }}
          </template>
        </el-table-column>
        <el-table-column label="出生日期" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.birthday }}
          </template>
        </el-table-column>
        <el-table-column label="邮箱" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.email }}
          </template>
        </el-table-column>
        <el-table-column label="电话" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.phone }}
          </template>
        </el-table-column>
        <el-table-column label="地址" color="black" align="center">
          <template v-slot="scope">
            {{ scope.row.address }}
          </template>
        </el-table-column>




      <el-table-column label="操作" color="black" align="center" width="260">
          <template v-slot="scope">
            <el-button class="commButton" @click="editItem(scope.row.studentId)">编辑</el-button>

            <el-button class="commButton" @click="deleteItem(scope.row.studentId)">删除</el-button>
            
          </template>
      </el-table-column>

    </el-table>

  </div>
</template>

<script lang="ts">
  import { defineComponent } from "vue";
  import { type StudentItem } from "~/models/general";
  import { getDormitoryInfo } from "~/services/personServ";
  export default defineComponent({
    data () {
      return {
        dormitoryId: 0 as number,
        dataList: [] as StudentItem[],

      };
    },
    // 页面加载函数
    async created() {
        // 获取路由参数
        const res = this.$route.query.dormitoryId;
        if (res != null) {
         this.dormitoryId = parseInt(res.toString());
        }
        // 获取信息
        if (this.dormitoryId != null) {
          this.dataList = await getDormitoryInfo(this.dormitoryId);
     }
  },
    

    methods: {
      async doQuery() {
        this.dataList = await getDormitoryInfo(this.dormitoryId)
      }
    },

  })
</script>

