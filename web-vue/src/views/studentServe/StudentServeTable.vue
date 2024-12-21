<template>
    <div class="base_form">
      <div class="base_header">
        <div class="blue_column"></div>
        <div class="base_title">学生服务</div>
      </div>
      <button class="leave-button" @click="goToLeavePage">请假</button>
      <button class="leave-button" @click="goToLeaveRecordPage(userId)">查看请假记录</button>
      <button class="leave-button" @click="goToResearchPage(userId)">查看科研项目</button>
    </div>
</template>
  
  <script lang="ts">
  import { defineComponent } from "vue";
  import router from "~/router";
  import { LeaveItem } from "~/models/general";
  import { useAppStore } from "~/stores/app";
  
  export default defineComponent({
    data() {
      return {
        userId : 0 as number,
        dataList: [] as LeaveItem[],

      };
    },
    methods: {
      goToLeavePage() {
        router.push({
        path: "/leave",
      });
      },
      goToLeaveRecordPage(userId : number) {
        router.push({
          path: "/leaveRecord",
          query: {userId : userId}
        },

        )
      },
      goToResearchPage(userId : number) {
        router.push({
          path: "/research-panel-student",
          query: {userId : userId}
        },
      )
      }


    },
    created() {
      this.userId = useAppStore().userInfo.id;
    }
  });
  </script>
  
  <style scoped>
  .leave-button {
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    cursor: pointer;
    margin-top: 20px;
    transition: all 0.3s ease;
  }
  
  .leave-button:hover {
    background-color: #0056b3;
    transform: scale(1.05);
  }
  </style>