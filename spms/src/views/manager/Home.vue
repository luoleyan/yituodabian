<template>
  <div>
    <div class="card" style="line-height: 30px;">
        <div style="color: aliceblue;">欢迎您, <span style="color: #66ccff;"> {{ user.name }} </span> 祝您今天过得开心</div>
    </div>
  </div>
</template>

<script setup>
  // 主页路由，从本地存储空间中取出管理员信息，用于展示
const user = JSON.parse(localStorage.getItem('admin') || '{}')
</script>
