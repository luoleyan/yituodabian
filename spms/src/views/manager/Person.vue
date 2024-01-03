<template>
    <div class="card" style="width: 64%; padding: 42px;">
            <el-form :model="data.form"  ref="formRef" label-width="100px" label-position="right"
                style="padding-right: 42px;">
                <el-form-item label="学生头像">
                    <el-upload class="avatar-uploader" action="http://localhost:8088/files/upload" :show-file-list="false" :on-success="handleImgUploadSuccess">
                        <img v-if="data.form.avatar" :src="data.form.avatar" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                    </el-upload>
                </el-form-item>
                <el-form-item label="学号" prop="username" >
                    <el-input v-model="data.form.username" autocomplete="off" disabled />
                </el-form-item>
                <el-form-item label="学生密码" prop="password">
                    <el-input show-password v-model="data.form.password" autocomplete="off" />
                </el-form-item>
                <el-form-item label="学生姓名">
                    <el-input v-model="data.form.name" autocomplete="off" />
                </el-form-item>
                <el-form-item label="学生手机号">
                    <el-input v-model="data.form.phone" autocomplete="off" />
                </el-form-item>
                <el-form-item label="学生邮箱">
                    <el-input v-model="data.form.email" autocomplete="off" />
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="data.form.sex">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item style="width: 100%;" label="生日">
                    <el-date-picker format="YYYY-MM-DD" value-format="YYYY-MM-DD" v-model="data.form.birth">
                    </el-date-picker>
                </el-form-item>
                <el-form-item >
                <el-button type="primary" @click="update">保 存</el-button>
                </el-form-item>
                </el-form>
    </div>
</template>

<script setup>
    import router from '@/router';
import request from '@/utils/request';
import { reactive } from 'vue';
import { Plus } from '@element-plus/icons-vue'
    const data = reactive({
        form:JSON.parse(localStorage.getItem('admin') || '{}') 
    })

    const handleImgUploadSuccess = (res)=>{
    // console.log(res);
    data.form.avatar = res.data
    
}

const update = () =>{
    request.put('/student/update',data.form).then(res =>{
    if (res.code === '200') {
                    ElMessage.success('保存成功')
                    router.push('/login')
                } else {
                    ElMessage.error(res.msg)
                }
            
})
}
</script>

<style scoped>
:deep(.el-input__inner::placeholder) {
    color: #fff;
}

:deep .el-input__wrapper {
    background: rgb(36, 36, 36);

}

:deep .el-input__inner {
    background: rgb(36, 36, 36);
    color: #86909C;
}

:deep(.el-table__header) {
    background-color: rgb(36, 36, 36);
}

:deep(.el-table tr) {
    background-color: rgb(36, 36, 36);
}

:deep(.el-table th.el-table__cell) {
    background-color: rgb(36, 36, 36);
    color: #fff;
}

:deep(.el-pagination.is-background .el-pager li:not(.is-disabled)) {
    background-color: rgb(130, 130, 130) !important;
    color: #fff;
}

:deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
    background-color: #fff !important;
    color: #66ccff;
}



:deep .el-pagination .btn-next,
:deep .el-pagination .btn-prev {
    background: rgb(130, 130, 130) !important;
    background-color: rgb(130, 130, 130) !important;
}

:deep .el-form-item__label {
    color: #fff;
}

.avatar-uploader .avatar {
  width: 128px;
  height: 128px;
  display: block;
}


.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 128;
  height: 128;
  text-align: center;
}
</style>