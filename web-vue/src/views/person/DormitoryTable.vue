<template>
  <div class="base_form">
    <div class="base_header">
      <div class="blue_column"></div>
      <div class="base_title">宿舍管理</div>
    </div>

    <div class="base_query_oneLine">
      <div class="query_left">
        <el-button class="commButton" @click="addItem()">添加</el-button>
      </div>
      <div class="query_right">
        <el-input v-model="door">门牌号</el-input>
        <el-button class="commButton" @click="doQuery()">查询</el-button>
      </div>
    </div>



    <div class="table-content">
      <el-table 
        :data="dataList" 
        stripe 
        :header-cell-style="{
          color: '#2E2E2E',
          fontSize: '10px',
          fontWeight: '400',
          background: 'rgb(242,242,242)',
        }"
        :row-style="{ height: '10px' }"
        :cell-style="{ padding: '2px' }"
        style="width: 100%"
        >

        <el-table-column label="序号" width="180">
          <template v-slot="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>

        <el-table-column label="楼号" width="180">
          <template v-slot="scope">
            {{ scope.row.building }}
          </template>
        </el-table-column>

        <el-table-column label="楼层" width="180">
          <template v-slot="scope">
            {{ scope.row.floor }}
          </template>
        </el-table-column>

        <el-table-column label="门牌号" width="180">
          <template v-slot="scope">
            {{ scope.row.door }}
          </template>
        </el-table-column>

        <el-table-column label="年级" width="180">
          <template v-slot="scope">
            {{ scope.row.grade }}
          </template>
        </el-table-column>

        <!--表格最右侧的操作按钮-->
        <el-table-column label="操作" color="black" align="center" width="230">
          <template v-slot="scope">
            <el-button class="commButton" @click="editItem(scope.row.dormitoryId)"
              >基本信息</el-button
            >
            <el-button class="commButton" @click="editItem(scope.row.dormitoryId)"
              >编辑</el-button
            >
            <el-button class="commButton" @click="deleteItem(scope.$index)"
              >删除</el-button
            >
          </template>
        </el-table-column>


      </el-table>
    </div>

    
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { getDormitoryList } from "~/services/personServ";
import { dormitoryDelete } from "~/services/personServ";

import { type DormitoryItem } from "~/models/general";
import { message, messageConform } from "~/tools/messageBox";
import router from "~/router";

export default defineComponent({
  data() {
    return {
      door: "",
      dataList: [] as DormitoryItem[],

    };
  },

  
  methods: {
    async doQuery() {
      this.dataList = await getDormitoryList(this.door);
    },
    //添加宿舍，跳转到宿舍信息界面
    addItem() {
      router.push({ path: "/DormitoryInfo" });
    },
    //编辑宿舍,跳转到宿舍信息页面
    editItem(dormitoryId: number) {
      router.push({
        path: "/DormitoryInfo",
        query: { dormitoryId: dormitoryId },
      });
    },
    //删除宿舍
    async deleteItem(index: number) {
      const result = await messageConform("确认删除宿舍吗?");
      if (!result) {
        return;
      }
      console.log(this.dataList[index]);
      const res = await dormitoryDelete(this.dataList[index].dormitoryId);
      if (res.code == 0) {
        message(this, "删除成功");
        this.dataList.splice(index, 1);
      } else {
        message(this, res.msg);
      }
    },
  },

  created() {
    this.doQuery();
  },
});
</script>
