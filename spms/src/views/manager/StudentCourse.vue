<template>
    <!-- 选课记录页面 -->
    <div>
        <!-- 支持模糊查询的搜索框 -->
        <div class="card" style="margin-bottom: 10px;">
            <el-input v-model="data.name" style="width: 260px; margin-right: 10px;" placeholder="请输入课程名称查询"
                :prefix-icon="Search" />
            <el-input v-model="data.no" style="width: 260px; margin-right: 10px;" placeholder="请输入课程编号"
                :prefix-icon="Search" />
            <el-button type="primary" @click="load" style="margin-left: 10px;">查询</el-button>
            <el-button type="info" @click="reset">重置</el-button>
        </div>
        <div class="card" style="margin-bottom: 10px;">
            <div>
                <!-- 展示表中信息 -->
                <el-table :data="data.tableData" style="width: 100%; background: rgb(36,36,36); color: #66ccff;">
                    <el-table-column prop="id" label="ID" width="70" />
                    <el-table-column prop="name" label="课程名称" />
                    <el-table-column prop="no" label="课程编号" />
                    <el-table-column prop="studentName" label="学生姓名" />
                    <el-table-column label="操作">
                        <!-- 管理员可以删除此条信息、为学生的这门课程打分 -->
                        <template #default="scope">
                            <el-button type="danger" size="small" @click="del(scope.row.id)">删除</el-button>
                            <el-button type="info" size="small" @click="addGrade(scope.row)"
                                v-if="data.user.role === 'ADMIN'">打分</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>

        <div class="card">
            <el-pagination v-model:page-size="data.pageSize" v-model:current-page="data.pageNum"
                @current-change="handlePageChange" background layout="prev, pager, next" :total="data.total" />
        </div>

        <!-- 点击打分按钮后，出现的弹窗表单，用于提交管理员输入的信息 -->
        <el-dialog v-model="data.formVisible" width="42%" style="background: rgb(36,36,36);">
            <template #header="{ close, titleId }">
                <span :id="titleId" class="dlgTitleClass"
                    style="margin: 10px 10px; font-size: large; color: #fff; font-weight: bolder;">成绩信息</span>
            </template>
            <el-form :model="data.gradeForm" label-width="100px" label-position="right" style="padding-right: 42px;">
                <el-form-item label="课程名称">
                    <el-input v-model="data.gradeForm.name" disabled autocomplete="off" />
                </el-form-item>
                <el-form-item label="课程分数">
                    <el-input v-model="data.gradeForm.score" autocomplete="off" />
                </el-form-item>
                <el-form-item label="课程评语">
                    <el-input type="textarea" v-model="data.gradeForm.comment" autocomplete="off" />
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
import { reactive } from 'vue'
const data = reactive({
    name: '',
    no: '',
    tableData: [],
    total: 0,
    pageNum: 1,
    pageSize: 5,
    // 控制弹窗表单是否显示
    formVisible: false,
    // 从本地存储空间中取出登录信息
    user: JSON.parse(localStorage.getItem('admin') || '{}'),
    gradeForm: {

    },
})

// load()函数，请求后端数据并展示在页面
const load = () => {
    let params = {
        pageNum: data.pageNum,
        pageSize: data.pageSize,
        name: data.name.trim(),
        no: data.no.trim(),
    }
    if (data.user.role === "STUDENT") {
        params.studentId = data.user.id
    }
    request.get('/studentCourse/selectPage', {
        params: params
    }).then(res => {
        // console.log(res);
        // 将一条数据作为一行展示在页面
        data.tableData = res.data?.list || []
        // 获取总页数
        data.total = res.data?.total || 0
    })
}

// 加载页面时调用load()函数展示数据
load()

// 换页时根据页号重新加载数据
const handlePageChange = (pageNum) => {
    load()
}

// 清空搜索框
const reset = () => {
    data.name = ''
    data.no = ''
    load()
}

// 删除功能
const del = (id) => {
    ElMessageBox.confirm('删除后数据无法恢复，您确认删除该条数据吗？', '删除确认', {
        type: 'warning',
    }).then(res => {
        // 根据Id删除此条数据
        request.delete('/studentCourse/delete/' + id).then(res => {
            if (res.code === '200') {
                ElMessage.success('删除成功')
                // 请求成功，重新加载页面
                load()

            } else {
                // 否则提示错误信息
                ElMessage.error(res.msg)
            }
        })
    }).catch(res => {  // 当用户点击取消时
        ElMessage({
            type: 'info',
            message: '已取消',
        })
    })
}

// 点击打分后，将弹窗表单显示
const addGrade = (row) => {
    data.formVisible = true
    // 设置对象的属性
    data.gradeForm.name = row.name
    data.gradeForm.courseId = row.courseId
    data.gradeForm.studentId = row.studentId
    data.gradeForm.courseName = row.name
    data.gradeForm.studentName = row.studentName
    // console.log(data.gradeForm);
}

// 点击保存后
const save = () => {
    request.post('/grade/add', data.gradeForm).then(res => {
        if (res.code === '200') {
            // 若请求成功，重新加载数据并将弹窗表单隐藏
            load()
            data.formVisible = false
            ElMessage.success('保存成功')
            // 把弹窗表单中绑定的响应式对象置空
            data.gradeForm = '' 
        } else {
            // 否则提示错误信息
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
</style>
