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
            <div style="margin-bottom: 10px;">
                <el-button type="primary" @click="handleAdd">新增</el-button>
            </div>
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
                            <el-button size="small" type="primary"
                                @click="handleEdit( scope.row)">编辑</el-button>
                            <el-button size="small" type="danger"
                                @click="handleDelete( scope.row.id)">删除</el-button>
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
                    style="margin: 10px 10px; font-size: large; color: #fff; font-weight: bolder;">课程信息</span>
            </template>
            <el-form :model="data.form" label-width="100px" label-position="right" style="padding-right: 42px;">
                <el-form-item label="课程名称">
                    <el-input v-model="data.form.name" autocomplete="off" />
                </el-form-item>
                <el-form-item label="课程编号">
                    <el-input v-model="data.form.no" autocomplete="off" />
                </el-form-item>
                <el-form-item label="课程描述">
                    <el-input v-model="data.form.description" autocomplete="off" />
                </el-form-item>
                <el-form-item label="课时">
                    <el-input v-model="data.form.times" autocomplete="off" />
                </el-form-item>
                <el-form-item label="任课老师">
                    <el-input v-model="data.form.teachers" autocomplete="off" />
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="data.formVisible = false" style="background: rgb(121, 121, 121); color: aliceblue;">取 消</el-button>
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
    teachers: '',
    tableData: [],
    total: 0,
    pageNum: 1,
    pageSize: 5,
    formVisible: false,
    form: {
        id: '',
        name: '',
        no: '',
        description: '',
        times: '',
        teachers: ''
    },
})

const load = () => {
    // 页面加载时将已有课程信息查询并展示在页面上，通过GET请求实现
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

// 调用load()函数，在页面加载时展示信息
load()

// 在切换页面时，调用load()函数重新加载页面
const handlePageChange = (pageNum) => {
    load()
}


// reset()函数将输入框信息重置为空
const reset = () => {
    data.name = ''
    data.no = ''
    data.teachers = ''
    load()
}

// 处理添加操作，把数据表单置为空，并让编辑课程信息的弹窗显示在页面上
const handleAdd = () => {
    data.form = {}
    data.formVisible = true
}

// save()函数
const save = () => {
    // 根据当前是否输入了id来判断，输入了id发送更新信息请求，否则发送添加信息请求
    request.request({
        url: data.form.id ? '/course/update' : '/course/add',
        method: data.form.id ? 'put' : 'post',
        data: data.form
    }).then(res =>{
        // 请求成功时，重新调用load()函数，把更新后的后台数据展示在页面上
        if(res.code === '200'){
            load()
            // 并将弹窗元素重新隐藏
            data.formVisible = false
            ElMessage.success('保存成功')
        }else{
            // 否则提示错误信息
            ElMessage.error(res.msg)
        }
    })
}

// 处理编辑函数，把当前操作的那一行数据进行处理
const handleEdit = (row) => {
    data.form = JSON.parse(JSON.stringify(row))
    data.formVisible = true
}

// 处理删除的函数，传入当前课程的id，根据id删除特定课程
const handleDelete = (id) => {
    ElMessageBox.confirm('删除后数据无法恢复，您确认删除该条数据吗？', '删除确认', {
        type: 'warning',
    }).then(res =>{
        request.delete('/course/delete/' + id).then(res =>{
        if(res.code === '200'){
            // 若请求成功，提示删除成功，并再次调用load()函数重新加载页面数据
            ElMessage.success('删除成功')
            load()
            
        }else{
            // 否则，提示后台返回的错误信息
            ElMessage.error(res.msg)
        }
    })
        // 如果用户取消了当前操作
    }).catch(res =>{
        ElMessage({
        type: 'info',
        message: '已取消',
      })
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
