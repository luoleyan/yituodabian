<template>
    <!-- 管理员更新学生信息 -->
    <div>
        <!-- 支持模糊查询学生信息 -->
        <div class="card" style="margin-bottom: 10px;">
            <el-input v-model="data.name" style="width: 260px; margin-right: 10px;" placeholder="请输入学生姓名查询"
                :prefix-icon="Search" />
            <el-input v-model="data.username" style="width: 260px; margin-right: 10px;" placeholder="请输入学号查询"
                :prefix-icon="Search" />
            <el-input v-model="data.phone" style="width: 260px; margin-right: 10px;" placeholder="请输入手机号查询"
                :prefix-icon="Search" />
            <el-input v-model="data.email" style="width: 260px;" placeholder="请输入邮箱查询" :prefix-icon="Search" />
            <!-- 清空搜索框 -->
            <el-button type="primary" @click="load" style="margin-left: 10px;">查询</el-button>
            <el-button type="info" @click="reset">重置</el-button>
        </div>
        <div class="card" style="margin-bottom: 10px;">
            <div style="margin-bottom: 10px;">
                <el-button type="primary" @click="handleAdd">新增</el-button>
            </div>
            <!-- 展示现有的所有学生信息，一条数据为一行，一行是一个数组 -->
            <div>
                <el-table :data="data.tableData" style="width: 100%; background: rgb(36,36,36); color: #66ccff;">
                    <el-table-column prop="id" label="ID" width="70" />
                    <el-table-column prop="username" label="学号" />
                    <el-table-column prop="name" label="姓名" />
                    <el-table-column prop="phone" label="手机号" />
                    <el-table-column prop="email" label="邮箱" />
                    <el-table-column prop="sex" label="性别" />
                    <el-table-column prop="birth" label="生日" />
                    <el-table-column prop="avatar" label="头像" >
                        <template #default="scope">
                            <el-image v-if="scope.row.avatar" style="width: 42px; height: 42px; border-radius: 5px" :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]"></el-image>
                        </template>
                    </el-table-column>
                    <!-- 每一行的末尾有两个按钮，支持管理员进行编辑和删除学生信息 -->
                    <el-table-column label="操作">
                        <template #default="scope">
                            <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
                            <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <!-- 学生信息的页脚，显示当前页号和总页数 -->
        <div class="card">
            <el-pagination v-model:page-size="data.pageSize" v-model:current-page="data.pageNum"
                @current-change="handlePageChange" background layout="prev, pager, next" :total="data.total" />
        </div>

        <el-dialog v-model="data.formVisible" distroy-on-close width="42%" style="background: rgb(36,36,36);">
            <template #header="{ close, titleId }">
                <span :id="titleId" class="dlgTitleClass"
                    style="margin: 10px 10px; font-size: large; color: #fff; font-weight: bolder;">课程信息</span>
            </template>
            <el-form :model="data.form" :rules="rules" ref="formRef" label-width="100px" label-position="right"
                style="padding-right: 42px;">
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
                <el-form-item label="学生头像">
                    <el-upload action="http://localhost:8088/files/upload" list-type="picture" :on-success="handleImgUploadSuccess">
                        <el-button style="background: rgb(121, 121, 121); color: aliceblue;" >上传头像</el-button>
                    </el-upload>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="data.formVisible = false" style="background: rgb(121, 121, 121); color: aliceblue;">取
                        消</el-button>
                    <el-button type="primary" @click="save">
                        保 存
                    </el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import request from '@/utils/request.js';
import { Search } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus';
import { reactive, ref } from 'vue'
const data = reactive({
    name: '',
    username: '',
    phone: '',
    email: '',
    tableData: [],
    total: 0,
    pageNum: 1,
    pageSize: 5,
    formVisible: false,
    // 这些变量用于绑定修改和新增学生信息时输入的表单元素
    form: {
        id: '',
        name: '',
        sex: '',
        birth: '',
        email: '',
        phone: '',
        username: '',
        password: ''
    },
})


// load()函数用于获取数据并展示
const load = () => {
    request.get('/student/selectPage', {
        params: {
            pageNum: data.pageNum,
            pageSize: data.pageSize,
            name: data.name.trim(),
            no: data.username.trim(),
            teachers: data.phone.trim(),
            email: data.email.trim(),
        }
    }).then(res => {
        // console.log(res);
        // 将查询出的数据作为一行数据展示在页面上
        data.tableData = res.data?.list || []
        // 更新当前总页数
        data.total = res.data?.total || 0
    })
}
    
// 加载页面时调用load()函数展示数据
load()

// 在切换学生信息页号时根据页号更新展示的信息
const handlePageChange = (pageNum) => {
    load()
}

// 清空输入框
const reset = () => {
    data.name = '',
        data.username = '',
        data.phone = '',
        data.email = '',
        load()
}

const rules = reactive({
    username: [
        {
            required: true, massage: '请输入学号', trigger: 'blur'
        }
    ],
    password: [
        {
            required: true, massage: '请输入密码', trigger: 'blur'
        }
    ],
})

const formRef = ref()

// 每点击新增时，将绑定数据重新设置为一个空对象，并显示新增/修改弹窗表单
const handleAdd = () => {
    data.form = {}
    data.formVisible = true
}

// 保存编辑/新增信息
const save = () => {
    formRef.value.validate((valid) => {
        if (valid) {
            request.request({
                url: data.form.id ? '/student/update' : '/student/add',
                method: data.form.id ? 'put' : 'post',
                data: data.form
            }).then(res => {
                if (res.code === '200') {
                    load()
                    data.formVisible = false
                    ElMessage.success('保存成功')
                } else {
                    ElMessage.error(res.msg)
                }
            })
        }
    })
}

// 点击编辑时，操作当前行数据，并显示弹窗表单
const handleEdit = (row) => {
    data.form = JSON.parse(JSON.stringify(row))
    data.formVisible = true
}


// 点击删除时，传递当前id到后台进行操作
const handleDelete = (id) => {
    ElMessageBox.confirm('删除后数据无法恢复，您确认删除该条数据吗？', '删除确认', {
        type: 'warning',
    }).then(res => {
        // axios发送DELETE请求，把id作为路径参数传递
        request.delete('/student/delete/' + id).then(res => {
            // 请求响应成功时，提示删除成功，重新调用load()函数更新页面
            if (res.code === '200') {
                ElMessage.success('删除成功')
                load()

            } else {
                // 否则提示错误信息
                ElMessage.error(res.msg)
            }
        })
    }).catch(res => {  // 用户点击取消时
        ElMessage({
            type: 'info',
            message: '已取消',
        })
    })
}

// 学生头像上传成功时，将请求回的图片展示在头像区域
const handleImgUploadSuccess = (res)=>{
    // console.log(res);
    data.form.avatar = res.data
    
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
</style>
