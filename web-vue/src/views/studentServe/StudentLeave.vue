<template>
    <div class="base_form">
      <div class="base_header">
        <div class="blue_column"></div>
        <div class="base_title">请假服务</div>
      </div>



      <div class="form-div" style="margin-top: 5px">
      <table class="content">
        <tr>
          <td colspan="1" style="text-align: right">请假原因</td>
          <td colspan="1">
            <textarea v-model="form.reason" style="width: 97%; height: 100px;"></textarea>
          </td>
        </tr>
        <tr>
          <td colspan="1" style="text-align: right">你的姓名</td>
          <td colspan="1"><input v-model="form.studentName" style="width: 97%" /></td>
        </tr>
        <tr>
          <td colspan="1" style="text-align: right">请假教师姓名</td>
          <td colspan="1"><input v-model="form.teacherName" style="width: 97%" /></td>
        </tr>

        <tr>
          <td colspan="2">
            <button class="commButton" @click="submit">提交</button>
          </td>
        </tr>
      </table>
    </div>

    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent } from "vue";
  import router from "~/router";
  import { LeaveItem } from "~/models/general";
  import { postLeaveRequest } from "~/services/personServ"; 
  import { useAppStore } from "~/stores/app";
  
  export default defineComponent({
    data() {
      return {
        userId: 0 as number,
        form: {} as LeaveItem,
        //datalist: [] as LeaveItem[]
      }
    },

    methods: {
      async submit() {
        const res = await postLeaveRequest(this.form);
        if (res.code == 0) {
          router.push({ path: "/student-serve-panel" });
        } else {
          alert(res.msg);
        }
      }
    },
   
    created() {
        this.userId = useAppStore().userInfo.id;
        this.form.studentId = this.userId
    }
  });
  </script>
  
  <style scoped>
 .commButton {
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    cursor: pointer;
    margin-top: 20px;
    transition: all 0.3s ease;
  }
  
 .commButton:hover {
    background-color: #0056b3;
    transform: scale(1.05);
  }
  
 .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input,
  textarea {
    width: 97%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 3px;
  }
  
  table.content {
    width: 100%;
    border-collapse: collapse;
  }
  
  table.content tr td {
    padding: 8px;
  }
  </style>