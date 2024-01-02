<template>
    <div>
        <div class="card" style="margin-bottom: 10px;">
            <el-input v-model="data.name" style="width: 260px; margin-right: 10px;" placeholder="请输入课程名称查询"
                :prefix-icon="Search" />
            <el-input v-model="data.no" style="width: 260px; margin-right: 10px;" placeholder="请输入课程编号"
                :prefix-icon="Search" />
            <el-input v-model="data.teachers" style="width: 260px;" placeholder="请输入任课老师查询" :prefix-icon="Search" />
            <el-button type="primary" @click="load" style="margin-left: 10px;">查询</el-button>
            <el-button type="info" @click="reset">重置</el-button>
        </div>
        <div class="card" style="margin-bottom: 10px;">
            <div>
                <el-table :data="data.tableData" style="width: 100%; background: rgb(36,36,36); color: #66ccff;">
                    <el-table-column prop="id" label="ID" width="70" />
                    <el-table-column prop="name" label="课程名称" />
                    <el-table-column prop="no" label="课程编号" />
                    <el-table-column prop="description" label="课程描述" />
                    <el-table-column prop="times" label="课时" />
                    <el-table-column prop="teachers" label="任课老师" />
                    <el-table-column label="操作">
                        <template #default="scope">
                           <el-button type="primary" size="small"  @click="selectCourse(scope.row)">选课</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>

        <div class="card">
            <el-pagination v-model:page-size="data.pageSize" v-model:current-page="data.pageNum"
                @current-change="handlePageChange" background layout="prev, pager, next" :total="data.total" />
        </div>

        
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
    teachers: '',
    tableData: [],
    total: 0,
    pageNum: 1,
    pageSize: 5,
    formVisible: false,
    student: JSON.parse(localStorage.getItem('admin') || '{}')
})

const load = () => {
    request.get('/course/selectPage', {
        params: {
            pageNum: data.pageNum,
            pageSize: data.pageSize,
            name: data.name.trim(),
            no: data.no.trim(),
            teachers: data.teachers.trim()
        }
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
    data.name = ''
    data.no = ''
    data.teachers = ''
    load()
}

const selectCourse = (row) =>{
    request.post("/studentCourse/add", {studentId: data.student.id, name: row.name, no: row.no, courseId: row.id}).then(res =>{
        if(res.code === '200'){
            ElMessage.success('选课成功')
            load()
        }else{
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