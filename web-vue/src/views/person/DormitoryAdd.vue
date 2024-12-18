<template>
  <div class="base_form">
    <div class="base_header">
      <div class="blue_column"></div>
      <div class="base_title">添加宿舍</div>
    </div>

    <div class="form-div" style="margin-top: 5px">
      <table class="content">
        <tr>
          <td colspan="1" style="text-align: right">楼号</td>
          <td colspan="1">
            <input v-model="form.building" style="width: 97%" />
          </td>
        </tr>
        <tr>
          <td colspan="1" style="text-align: right">楼层</td>
          <td colspan="1"><input v-model="form.floor" style="width: 97%" /></td>
        </tr>
        <tr>
          <td colspan="1" style="text-align: right">年级</td>
          <td colspan="1"><input v-model="form.grade" style="width: 97%" /></td>
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
import { DormitoryItem } from "~/models/general";
import router from "~/router";
import { dormitoryEditSave } from "~/services/personServ";
import { getDormitoryList } from "~/services/personServ";

export default defineComponent({
  data() {
    return {
      dormitoryId: 0 as number,
      form: {} as DormitoryItem,
      datalist: [] as DormitoryItem[],
    };
  },
  async created() {
    this.initializeForm();
  },
  methods: {
    async initializeForm() {
      // 获取路由参数
      const res = this.$route.query.dormitoryId;
      if (res != null) {
        this.dormitoryId = parseInt(res.toString());
      }
      // 获取宿舍信息
      if (this.dormitoryId !== 0) {
        this.datalist = await getDormitoryList(this.dormitoryId);
        if (this.datalist.length > 0) {
          this.form = { ...this.datalist[0] }; // 复制第一个宿舍对象
        }
        console.log(this.form);
      }
    },
    // 提交表单
    async submit() {
      const res = await dormitoryEditSave(this.form);
      if (res.code == 0) {
        router.push({ path: "/dormitory-panel" });
      } else {
        alert(res.msg);
      }
    },
  },
});
</script>
