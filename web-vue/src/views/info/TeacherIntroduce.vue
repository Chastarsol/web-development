<template >
  <div class="base_form" id="page-wrapper">
    <div class="base_header">
      <div class="blue_column"></div>
      <div class="base_title">个人信息</div>
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
          <el-row>
            <!--个人头像-->
            <el-card :body-style="{ padding: '0px' }"  class="avatar-card">
              <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
              <div style="padding: 14px;">
                <span>个人头像</span>
                <div class="bottom clearfix">
                </div>
              </div>
            </el-card>
          </el-row>
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
    <div id="chart-container" style="width: 100%; height: 300px; margin-top: 20px;"></div> <!-- 用于放置图表的容器 -->

  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import * as echarts from 'echarts'; // 引入ECharts
import {type TeacherItem} from "~/models/general";
import { getTeacherIntroduceData } from "~/services/infoServ";
import { message } from "~/tools/messageBox";
import { downloadPost, getAuthHeader } from "~/services/genServ";

export default defineComponent({
  data: () => ({
    authHeader: getAuthHeader(),
    teacherId: 1 as number | null,
    imgStr: "",
    info: {} as TeacherItem,
    nameRules: [],
    emailRules: [],
  }),
  async created() {
    try {
      let res = await getTeacherIntroduceData(this.teacherId);
      this.info = res.data; // 确认返回数据结构与这里匹配
    } catch (error) {
      message(this, "获取教师介绍数据失败" );
    }
  },
  mounted() {
    // 创建ECharts实例并配置图表选项
    const chart = echarts.init(document.getElementById('chart-container') as HTMLElement);
    const option = {
      xAxis: {
        type: 'category',
        data: ['2020年', '2021年', '2022年', '2023年'] // 假设的年份数据，需替换为实际数据
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          type: 'bar',
          data: [10, 15, 20, 25] // 假设的授课课程数量数据，需替换为实际数据
        }
      ]
    };
    chart.setOption(option); // 设置图表选项
  },
  methods: { }
});
</script>

<style>
#chart-container {
  border-radius: 6px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
#page-wrapper {
padding: 20px;
}

.content {
width: 100%;
border-collapse: collapse;
}

.content td {
padding: 8px 12px;
text-align: center;
}

.avatar-card {
float: right;
margin-left: 10px;
border: 1px solid #ccc;
border-radius: 5px;
box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
width: 100px;
height: auto;
overflow: hidden;
}

.image {
width: 100%;
display: block;
border-radius: 5px 5px 0 0;
}

.base_title {
font-size: 20px;
font-weight: bold;
color: #333;
}

.bottom {
margin-top: 13px;
line-height: 16px;
}

.blue_column {
width: 10px;
background-color: #007bff;
}

.time {
font-size: 13px;
color: #999;
}

.button {
padding: 0;
float: right;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
</style>