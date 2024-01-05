<template>
    <!-- 用户登录 -->
    <div class="background">
        <div>
            <div style="width: 350px;" class="wrapper">
                <h2>登 录</h2>
                <el-form :model="data.account">
                    <el-form-item>
                        <el-input style="width: 100%;" prefix-icon="User" v-model="data.account.username"
                            placeholder="请输入账户" />
                    </el-form-item>
                    <el-form-item>
                        <el-input show-password style="width: 100%;" prefix-icon="Lock" v-model="data.account.password"
                            placeholder="请输入密码" />
                    </el-form-item>
                    <!-- 选择身份，根据身份的不同页面会有不同的展示 -->
                    <el-form-item prop="role">
                        <el-select style="width: 100%;" v-model="data.account.role" placeholder="请选择角色">
                            <el-option label="管理员" value="ADMIN"></el-option>
                            <el-option label="学生" value="STUDENT"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button style="width: 100%;" type="primary" @click="login">登 录</el-button>
                    </el-form-item>
                </el-form>
                <div class="remember-forgot">
                    <label><input type="checkbox">记住密码</label>
                    <a href="#">忘记密码?</a>
                </div>
                <!-- 点击跳转至注册页面 -->
                <div class="register-link">
                    <p>还没账号？<a href="/register">即刻注册！</a></p>
                </div>
            </div>
        </div>
    </div>
</template>
    
<script setup>
import { reactive, ref } from 'vue';
import request from '@/utils/request.js';
import router from '@/router';


const data = reactive(
    {
        account: {}
    }
)

const login = () => {
    // console.log(data.account);
    // 判断当前输入用户名和密码是否为空
    if (data.account.username == '' || data.account.password == '') {
        alert('账号或密码不能为空')
        router.push('/login')
    } else {
        // 若账号密码不为空，利用axios发送POST请求，将当前输入的用户名和密码作为数据传递
        request.post('/login', data.account).then(res => {
            console.log(res)
            // 请求成功时，提示登录成功
            if (res.code === '200') {
                alert('登录成功');
                console.log(res.data);
                // 将user信息存入本地存储空间
                localStorage.setItem("admin", JSON.stringify(res.data));
                router.push('/home')
            }
            else {
                // 否则，打印错误信息，并弹窗提示
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
    background:linear-gradient(#0000006f,#0000006f) ,url(../source/illust_97285941_20220915_160606.jpg) no-repeat;
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
.remember-forgot {
    display: flex;
    justify-content: space-between;
    margin: -15px 0 15px -8px;
    color: #fff;
    font-size: 0.9em;
    /* set a right value that will never be reached */
    width: 320px;
    transition: .5;
}

.remember-forgot a {
    display: inline-block;
    height: 25px;
    color: #fff;
    transition: .5;
}

.remember-forgot a:hover {
    color: rgba(255, 255, 255, .7);
    /* change the color of the text */
    /* this will make the text blink */
    /* this will make the text display as blink*/
    transition: .5;
    border-bottom: 1px solid #fff;
}

.remember-forgot label input {
    accent-color: rgb(174, 216, 221);
    /* set the accent color */
    /* to something that will blink */
    margin-right: 3px;
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
    
