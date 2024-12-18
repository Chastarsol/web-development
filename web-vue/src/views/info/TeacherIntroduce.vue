<template>
    <div class="base_form">
      <div class="base_header">
        <div class="blue_column"></div>
        <div class="base_title">个人画像</div>
      </div>
      <div class="table_center" style="margin-top: 5px">
        <table class="content">
          <tr>
            <td>教师号</td>
            <td>
              {{ info.person.num }}
            </td>
            <td>姓名</td>
            <td>
              {{ info.person.name }}
            </td>
            <td>学院</td>
            <td>
              {{ info.person.dept }}
            </td>
            <td rowspan="3">
              <img :src="imgStr" alt="个人照片" width="200" />
            </td>
          </tr>
          <tr>
            <td>学位</td>
            <td>
              {{ info.degree }}
            </td>
            <td>职称</td>
            <td>
              {{ info.title }}
            </td>
            <td>证件号码</td>
            <td>
              {{ info.person.card }}
            </td>
          </tr>
          <tr>
            <td>性别</td>
            <td>
              {{ info.person.genderName }}
            </td>
            <td>出生日期</td>
            <td>
              {{ info.person.birthday }}
            </td>
            <td>邮箱</td>
            <td>
              {{ info.person.email }}
            </td>
          </tr>
          <tr>
            <td>电话</td>
            <td>
              {{ info.person.phone }}
            </td>
            <td>地址</td>
            <td colspan="3">
              {{ info.person.address }}
            </td>
          </tr>
        </table>
      </div>
    
    </div>
  </template>
  <script lang="ts">
  import { defineComponent } from "vue";
 
  import {type TeacherItem} from "~/models/general";
  
  import { getTeacherIntroduceData } from "~/services/infoServ";
  import { message } from "~/tools/messageBox";
  import { downloadPost, getAuthHeader } from "~/services/genServ";
  
  export default defineComponent({
    data: () => ({
      authHeader: getAuthHeader(),
      valid: false,
      uploadIsLoading: false,
      teacherId: null as number | null,
      imgStr: "",
      info: {} as TeacherItem,
      nameRules: [],
      emailRules: [],
    }),
    async created() {
      let res = await getTeacherIntroduceData(this.teacherId);
      this.info = res.data.info;
      this.teacherId = this.info.teacherId;
    },
    mounted() {},
  
    methods: {
    }
  });
  </script>
  <style scoped>
  #myChartBar {
    float: left;
  }
  #myChartLine {
    float: right;
  }
  #myChartPie {
    float: right;
  }
  </style>
  