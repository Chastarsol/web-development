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
            <td colspan="1"><input v-model="form.building" style="width: 97%" /></td>
          </tr>
          <tr>
            <td colspan="1" style="text-align: right">楼层</td>
            <td colspan="1"><input v-model="form.floor" style="width: 97%" /></td>
          </tr>
          <tr>
            <td colspan="1" style="text-align: right">门牌号</td>
            <td colspan="1"><input v-model="form.dormitoryId" style="width: 97%" /></td>
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
    import { DormitoryItem} from "~/models/general";
    import router from "~/router";
    import { dormitoryEditSave } from "~/services/personServ";
  
  
    export default defineComponent({
      data () {
        return {
          dormitoryId: 0 as number,
          form: {} as DormitoryItem,
        };
      }, 
      methods: {
        // 提交表单
        async submit() {
          const res = await dormitoryEditSave(this.dormitoryId, this.form);
  
          if (res.code == 0) {
            router.push({ path: "/teacher-panel" });
          } else {
            alert(res.msg);
          }
       },
  
      },
    },
  )
  </script>
  
  