<template>
    <div>
      <div style="height: 60px; background-color: rgb(45,45,46); display: flex; align-items: center; border-bottom: 1px solid #ddd">
        <div style="flex: 1">
          <div style="padding-left: 20px; display: flex; align-items: center">
            <img src="@/assets/imgs/mainpage.png" alt="" style="width: 40px">
            <div style="font-weight: bold; font-size: 24px; margin-left: 5px; color: rgb(255,255,255);">学生成绩管理系统</div>
          </div>
        </div>
        <div style="width: fit-content; padding-right: 10px; display: flex; align-items: center;">
          <img :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"  alt="" style="width: 40px; height: 40px; border-radius: 21px; ">
          <span style="margin-left: 5px; color:rgb(255,255,255);">{{user.name}}</span>
        </div>
      </div>
  
      <div style="display: flex">
        <div style="width: 200px; border-right: 1px solid rgb(#CECECE); min-height: calc(100vh - 60px); background-color: rgb(31,33,31);">
          <el-menu
              router
              style="border: none"
              :default-active="$route.path"
              :default-openeds="['/home', '3']"
          >
            <el-menu-item index="/home" style="background-color: rgb(24,24,24);">
              <el-icon color="#CECECE"><HomeFilled /></el-icon>
              <span style="color: #CECECE;">系统首页</span>
            </el-menu-item>
            <el-sub-menu index="2" style="background-color: rgb(24,24,24);">
              <template #title>
                <el-icon color="#CECECE"><Memo /></el-icon>
                <span style="color: #CECECE;">课程管理</span>
              </template>
              <el-menu-item index="/course" style="background-color: rgb(24,24,24);">
                <el-icon color="#CECECE" ><Document /></el-icon>
                <span style="color: #CECECE;">课程信息</span>
              </el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="3" v-if="user.role === 'ADMIN' " style="background-color: rgb(24,24,24);">
              <template #title>
                <el-icon color="#CECECE"><User /></el-icon>
                <span style="color: #CECECE;">用户管理</span>
              </template>
              <el-menu-item index="/student" style="background-color: rgb(24,24,24);">
                <el-icon color="#CECECE" ><UserFilled /></el-icon>
                <span style="color: #CECECE;">学生信息</span>
              </el-menu-item>
            </el-sub-menu>
            <el-menu-item index="/person" v-if="user.role === 'STUDENT' " style="background-color: rgb(24,24,24);">
              <el-icon color="#CECECE"><User /></el-icon>
              <span style="color: #CECECE;">个人资料</span>
            </el-menu-item>
            <el-menu-item index="login" @click="logout" style="background-color: rgb(24,24,24);">
              <el-icon color="#CECECE"><SwitchButton /></el-icon>
              <span style="color: #CECECE;">退出系统</span>
            </el-menu-item>
          </el-menu>
        </div>
  
        <div style="flex: 1; width: 0; background-color: rgb(34,31,33); padding: 10px">
          <router-view />
        </div>
      </div>
  
    </div>
  </template>
  
  <script setup>
  import { useRoute } from 'vue-router'
  const $route = useRoute()
  console.log($route.path)
  
  const logout = () => {
    localStorage.removeItem('admin')
  }
  const user = JSON.parse(localStorage.getItem('admin') || '{}')
  </script>
  
  <style scoped>
  /* .el-sub-menu__title:hover{
    background-color: rgb(55,55,61) !important;
  }  */
  .el-menu-item.is-active {
    background-color: rgb(55,55,61) !important;
  }
  .el-menu-item:hover {
    background-color: rgb(55,55,61) !important;
    color: #fff;
  }
  :deep(th)  {
    color: #333;
  }
  </style>