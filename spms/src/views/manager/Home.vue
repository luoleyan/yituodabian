<template>
  <div>
    <div class="card" style="line-height: 30px;">
        <div style="color: aliceblue;">欢迎您, <span style="color: #66ccff;"> {{ user.name }} </span> 祝您今天过得开心</div>
    </div>
  </div>
</template>

<script setup>
const user = JSON.parse(localStorage.getItem('admin') || '{}')
</script>