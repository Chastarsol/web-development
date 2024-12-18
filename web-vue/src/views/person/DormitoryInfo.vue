<template>
  <div class="base_form">
    <div class="base_header">
      <div class="blue_column"></div>
      <div class="base_title">宿舍信息</div>
    </div>

    <el-table :data="studentDataList" border style="width: 100%">
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
  import { DormitoryItem, type StudentItem } from "~/models/general";
  import { getDormitoryInfo } from "~/services/personServ";
  import { message, messageConform } from "~/tools/messageBox";
  import { getStudentPageData, studentDelete } from "~/services/personServ";
  import router from "~/router";
  import { dormitoryEditSave } from "~/services/personServ";


  export default defineComponent({
    data () {
      return {
        dormitoryId: 0 as number,
        studentDataList: [] as StudentItem[],
        form: {} as DormitoryItem,
      };
    },
    // 页面加载函数
    created() {
        this.doQuery()
    },

    methods: {
      async doQuery() {
        // 获取路由参数
        const res = this.$route.query.dormitoryId;
        console.log('获取的宿舍ID:', res);
        if (res != null) {
         this.dormitoryId = parseInt(res.toString())
        }

        // 获取信息
        if (this.dormitoryId != null) {
          this.studentDataList = await getDormitoryInfo(this.dormitoryId)
          console.log('获取的数据列表:', this.studentDataList);
        }
      },
      // 提交表单
      async submit() {
        const res = await dormitoryEditSave(this.form);

        if (res.code == 0) {
          router.push({ path: "/teacher-panel" });
        } else {
          alert(res.msg);
        }
     },
      //编辑学生信息
      async editItem(studentId : number) {
        router.push({
        path: "/StudentInfo",
        // 传递参数
        query: { studentId: studentId },
      });
      },
      //删除学生信息
      async deleteItem(studentId :number) {
        const result = await messageConform("确认删除学生吗?");
        if (!result) {
          return;
        }
        const res = await studentDelete(studentId);
        if (res.code == 0) {
          message(this, "删除成功");
        } else {
          message(this, res.msg);
        }
      },

    },
  },
)
</script>

