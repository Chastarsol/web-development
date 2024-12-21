<template>
    <div class="base_form">
      <div class="base_header">
        <div class="blue_column"></div>
        <div class="base_title">科研项目信息</div>
      </div>
      <div class="form-div" style="margin-top: 5px">
        <table class="content">
          <tr>
            <td colspan="1" style="text-align: right">科研标题</td>
            <td colspan="1"><input v-model="form.headline" style="width: 97%" /></td>
          </tr>
          <tr>
            <td colspan="1" style="text-align: right">科研内容</td>
            <td colspan="1"><input v-model="form.content" style="width: 97%" /></td>
          </tr>
          <tr>
            <td colspan="1" style="text-align: right">小组名称</td>
            <td colspan="1"><input v-model="form.teamName" style="width: 97%" /></td>
          </tr>
          <tr>
            <td colspan="1" style="text-align: right">老师姓名</td>
            <td colspan="1"><input v-model="form.teacherName" style="width: 97%" /></td>
          </tr>
          <tr>
            <td colspan="1" style="text-align: right">学生1姓名</td>
            <td colspan="1"><input v-model="form.s1Name" style="width: 97%" /></td>
          </tr>
          <tr>
            <td colspan="1" style="text-align: right">学生2姓名</td>
            <td colspan="1"><input v-model="form.s2Name" style="width: 97%" /></td>
          </tr>
          <tr>
            <td colspan="1" style="text-align: right">学生3姓名</td>
            <td colspan="1"><input v-model="form.s3Name" style="width: 97%" /></td>
          </tr>
          <tr>
            <td colspan="1" style="text-align: right">学生4姓名</td>
            <td colspan="1"><input v-model="form.s4Name" style="width: 97%" /></td>
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
  import { getDictionaryOptionItemList } from "~/services/systemServ";
  import { researchEditSave, getResearchInfoByResearchId } from "~/services/personServ";
  import { defineComponent } from "vue";
  import router from "~/router";
  import { ResearchItem } from "~/models/general";
  
  export default defineComponent({
    //数据
    data: () => ({
      researchId: 0 as number | null,
      form: {} as ResearchItem,
    }),
    //页面加载方法
    async created() {
      //获取路由参数,上一个页面传过来的id
      const res = this.$route.query.researchId;
      if (res != null) {
        this.researchId = parseInt(res.toString());
      }
      
      if (this.researchId != null) {
        this.form = await getResearchInfoByResearchId(this.researchId);
      }
    },
    methods: {
      //提交表单
      async submit() {
        const res = await researchEditSave(this.form);
        if (res.code == 0) {
          router.push({ path: "/research-panel-teacher" });
        } else {
          alert(res.msg);
        }
      },


    },
  });
  </script>
  