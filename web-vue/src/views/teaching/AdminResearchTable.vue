<template>
  <div class="base_form">
    <div class="base_header">
      <div class="blue_column"></div>
      <div class="base_title">科研管理</div>
    </div>
    <!--页面上方的操作-->
    <div class="base_query_oneLine">
      <div class="query_left">
        <el-button class="commButton" @click="addItem()">添加</el-button>
      </div>
    </div>

    <div class="table-content">
      <el-table
        :data="dataList"
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
        <el-table-column label="序号" fixed="left" width="50" color="black">
          <template v-slot="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column
          label="科研标题"
          color="black"
          align="center"
          width="100"
        >
          <template v-slot="scope">
            {{ scope.row.headLine }}
          </template>
        </el-table-column>
        <el-table-column
          label="科研内容"
          color="black"
          align="center"
          width="600"
        >
          <template v-slot="scope">
            {{ scope.row.content }}
          </template>
        </el-table-column>
        <el-table-column
          label="小组名称"
          color="black"
          align="center"
          width="100"
        >
          <template v-slot="scope">
            {{ scope.row.teamName }}
          </template>
        </el-table-column>
        <el-table-column
          label="老师姓名"
          color="black"
          align="center"
          width="100"
        >
          <template v-slot="scope">
            {{ scope.row.teacherName }}
          </template>
        </el-table-column>
        <el-table-column
          label="组员姓名"
          color="black"
          align="center"
          width="100"
        >
          <template v-slot="scope">
            {{ scope.row.s1Name }}
          </template>
        </el-table-column>
        <el-table-column
          label="组员姓名"
          color="black"
          align="center"
          width="100"
        >
          <template v-slot="scope">
            {{ scope.row.s2Name }}
          </template>
        </el-table-column>
        <el-table-column
          label="组员姓名"
          color="black"
          align="center"
          width="100"
        >
          <template v-slot="scope">
            {{ scope.row.s3Name }}
          </template>
        </el-table-column>
        <el-table-column label="组员姓名" color="black" align="center" width="100">
          <template v-slot="scope">
            {{ scope.row.s4Name }}
          </template>
        </el-table-column>
        <el-table-column label="操作" color="black" align="center" width="230">
          <template v-slot="scope">
            <el-button
              class="commButton"
              @click="editItem(scope.row.researchId)"
              >编辑</el-button
            >
            <el-button
              class="commButton"
              @click="deleteItem(scope.row.researchId)"
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
import router from "~/router";
import { ResearchItem } from "~/models/general";
import { useAppStore } from "~/stores/app";
import { adminGetResearch, researchDelete } from "~/services/personServ";

import { message, messageConform } from "~/tools/messageBox";

export default defineComponent({
  data() {
    return {
      userId: 0 as number,
      dataList: [] as ResearchItem[],
    };
  },

  created() {
    //当前登录的老师的userId，然后根据该id查询科研项目
    //this.userId = useAppStore().userInfo.id;
    this.queryAll(this.userId);
  },

  methods: {
    async queryAll(userId: number | null) {
      this.dataList = await adminGetResearch(this.userId);
    },
    // 添加科研项目,进入科研项目信息页面
    addItem() {
      router.push({ path: "/researchInfo" });
    },
    // 编辑科研项目,进入科研项目信息页面
    editItem(researchId: number) {
      router.push({
        path: "/researchInfo",
        // 传递参数
        query: { researchId: researchId },
      });
    },
    // 删除科研项目
    async deleteItem(researchId: number) {
      const result = await messageConform("确认删除该科研项目吗?");
      if (!result) {
        return;
      }
      const res = await researchDelete(researchId);
      if (res.code == 0) {
        message(this, "删除成功");
      } else {
        message(this, res.msg);
      }
      this.queryAll(this.userId);
    },
  },
});
</script>
