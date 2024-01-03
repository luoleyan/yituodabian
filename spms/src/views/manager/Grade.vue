<template>
    <div>
        <div class="card" style="margin-bottom: 10px;">
            <el-input v-model="data.courseName" style="width: 260px; margin-right: 10px;" placeholder="请输入课程名称查询"
                :prefix-icon="Search" />
            <el-input v-model="data.studentName" style="width: 260px; margin-right: 10px;" placeholder="请输入学生名称"
                :prefix-icon="Search" />
            <el-button type="primary" @click="load" style="margin-left: 10px;">查询</el-button>
            <el-button type="info" @click="reset">重置</el-button>
        </div>
        <div class="card" style="margin-bottom: 10px;">
            <div>
                <el-table :data="data.tableData" style="width: 100%; background: rgb(36,36,36); color: #66ccff;">
                    <el-table-column prop="id" label="ID" width="70" />
                    <el-table-column prop="courseName" label="课程名称" />
                    <el-table-column prop="studentName" label="学生姓名" />
                    <el-table-column prop="score" label="学科成绩" />
                    <el-table-column prop="comment" label="教师评语" />
                    <el-table-column prop="feedback" label="学生反馈" />
                    <el-table-column label="操作">
                        <template #default="scope">
                            <el-button type="info" size="small" @click="handleEdit(scope.row)"  v-if="data.user.role === 'ADMIN'">编辑</el-button>
                            <el-button type="danger" size="small" @click="del(scope.row.id)"  v-if="data.user.role === 'ADMIN'">删除</el-button>
                            <el-button type="info" size="small" @click="handleEdit(scope.row)"
                                v-if="data.user.role === 'STUDENT'">反馈</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>

        <div class="card">
            <el-pagination v-model:page-size="data.pageSize" v-model:current-page="data.pageNum"
                @current-change="handlePageChange" background layout="prev, pager, next" :total="data.total" />
        </div>

        <el-dialog v-model="data.formVisible" width="42%" style="background: rgb(36,36,36);">
            <template #header="{ close, titleId }">
                <span :id="titleId" class="dlgTitleClass"
                    style="margin: 10px 10px; font-size: large; color: #fff; font-weight: bolder;">成绩信息</span>
            </template>
            <el-form :model="data.form" label-width="100px" label-position="right" style="padding-right: 42px;">
                <el-form-item label="学生成绩" v-if="data.user.role === 'ADMIN'">
                    <el-input  v-model="data.form.score" autocomplete="off" />
                </el-form-item>
                <el-form-item label="教师评语" v-if="data.user.role === 'ADMIN'">
                    <el-input type="textarea" v-model="data.form.comment" autocomplete="off" />
                </el-form-item>
                <el-form-item label="课程学生反馈" v-if="data.user.role === 'STUDENT'">
                    <el-input type="textarea" v-model="data.form.feedback" autocomplete="off" />
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
    courseName: '',
    studentName: '',
    tableData: [],
    total: 0,
    pageNum: 1,
    pageSize: 5,
    formVisible: false,
    user: JSON.parse(localStorage.getItem('admin') || '{}'),
    form: {

    },
})


const load = () => {
    let params = {
        pageNum: data.pageNum,
        pageSize: data.pageSize,
        courseName: data.courseName.trim(),
        studentName: data.studentName.trim(),
    }
    if (data.user.role === "STUDENT") {
        params.studentId = data.user.id
    }
    request.get('/grade/selectPage', {
        
        params: params
    }).then(res => {
        // console.log(res);
        data.tableData = res.data?.list || []
        data.total = res.data?.total || 0
    })
}

load()

const handlePageChange = (pageNum) => {
    load()
}

const reset = () => {
    data.courseName = ''
    data.studentName = ''
    load()
}

const del = (id) => {
    ElMessageBox.confirm('删除后数据无法恢复，您确认删除该条数据吗？', '删除确认', {
        type: 'warning',
    }).then(res => {
        request.delete('/grade/delete/' + id).then(res => {
            if (res.code === '200') {
                ElMessage.success('删除成功')
                load()

            } else {
                ElMessage.error(res.msg)
            }
        })
    }).catch(res => {
        ElMessage({
            type: 'info',
            message: '已取消',
        })
    })
}

const handleEdit = (row) => {
    data.form = JSON.parse(JSON.stringify(row))
    // console.log(data.form);
    data.formVisible = true
}

const save = () => {
    request.put('/grade/update', data.form).then(res => {
        if (res.code === '200') {
            load()
            data.formVisible = false
            ElMessage.success('保存成功')
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
</style>