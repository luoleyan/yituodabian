<template>
    <!-- 注册页面 -->
    <div class="background">
        <div>
            <div style="width: 350px;" class="wrapper">
                <h2>注 册</h2>
                <el-form :model="data.account">
                    <el-form-item>
                        <el-input style="width: 100%;" prefix-icon="User" v-model="data.student.username"
                            placeholder="请输入账户" />
                    </el-form-item>
                    <el-form-item>
                        <el-input show-password style="width: 100%;" prefix-icon="Lock" v-model="data.student.password"
                            placeholder="请输入密码" />
                    </el-form-item>
                    <el-form-item>
                        <el-button style="width: 100%;" type="primary" @click="register">注 册</el-button>
                    </el-form-item>
                </el-form>
                <!-- 点击跳转至登录页面 -->
                <div class="register-link">
                    <p>已有账号？<a href="/login">即刻登录！</a></p>
                </div>
            </div>
        </div>
    </div>
</template>
    
<script setup>
import { reactive, ref } from 'vue';
import request from '@/utils/request.js';
import { ElMessage } from 'element-plus';
import router from '@/router';


const data = reactive(
    {
        student: {

        }
    }
)

const register = () => {
    // console.log(data.account);
    if (data.student.username == '' || data.student.password == '') {
        alert('账号或密码不能为空')
        router.push('/register')
    }else {
        request.post('/register', data.student).then(res => {
            // console.log(res)
            // 请求成功时，将注册时提交的信息放入本地存储空间
            if (res.code === '200') {
                alert('注册成功');
                console.log(res.data);
                localStorage.setItem("admin", JSON.stringify(res.data));
                // 并跳转至登录页面
                router.push('/login')
            }
            else {
                console.log(res.data);
                alert(res.msg);

            }
        })
    }
}


</script>
    
<style scoped >
.background {
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    min-width: 100vh;
    min-height: 100vh;
    background: url('../source/illust_102638087_20221116_163857.jpg') no-repeat;
    background-size: cover;
}

.wrapper {
    margin-top: 5%;
    margin-left: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    /* aligns the text to the center of the page */
    /* center the main element in the page */
    align-items: center;
    /* aligns the element with its bottom of the page */
    /* to the bottom of the page */
    position: relative;
    width: 400px;
    height: 400px;
    /* background-color: transparent; */
    background-color: rgba(247, 244, 229, .72);
    border: 1px solid rgba(191, 200, 207, .9);
    border-right: 1px solid rgba(191, 200, 207, .9);
    border-bottom: 0;
    border-radius: 20px;
    backdrop-filter: blur(24px);
}

.wrapper h2 {
    font-size: 2em;
    color: rgb(125, 163, 195);
}

.login-box {
    background-color: transparent;
    box-shadow: 0 0 20px 10px rgba(191, 200, 207, .9);
    padding: 30px;
    border-radius: 20px;
}


:deep .el-input__wrapper {
  background: transparent;
  line-height: 50px;
  border-radius: 55px;
  box-shadow: 0 0  5px rgba(191, 200, 207, .9);
}
:deep .el-input__inner { 
  font-size: 0.9375rem;
  color: #66ccff;
  font-weight: 600;

}

.wrapper button {
    width: 320px;
    height: 45px;
    border: none;
    background-color: rgb(125, 216, 222);
    border-radius: 40px;
    cursor: pointer;
    font-size: 1em;
    font-weight: 500;
    color: white;
    /* change the color of the text */
    /* this will make the text display as blink */
    transition: .5s;
}

.wrapper button:hover {
    box-shadow: 0 0 15px #000;
}

.register-link {
    font-size: 0.9em;
    margin: 0 0 0 42%;
}

.register-link a {
    display: inline-block;
    color: white;
    font-weight: 600;
    transition: .5;
    /* this will make the text blink */
    /* this will make the text display as blink*/
    height: 25px;
}

.register-link a:hover {
    border-bottom: 1px solid rgba(255, 255, 255, .3);
    font-size: 1.1em;
}
</style>
    
