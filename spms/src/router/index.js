// 引入Vue3的API
import { createRouter, createWebHistory } from 'vue-router'
// 使用createRouter函数创建路由
const router = createRouter({
  // 创建H5历史模式
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',  // 该路由对应的路径
      name: 'Manager',  // 该路由的名称
      component: () => import('../views/Manager.vue'),  // 该路由对应的组件
      redirect: '/home',  // 重定向
      children: [    // 该路由的子路由
        {
          path: '/home',
          name: 'home',
          component: () => import('../views/manager/Home.vue')
        },
        {
          path: '/course',
          name: 'course',
          component: () => import('../views/manager/Course.vue')
        },
        {
          path: '/student',
          name: 'student',
          component: () => import('../views/manager/Student.vue')
        },
        {
          path: '/person',
          name: 'person',
          component: () => import('../views/manager/Person.vue')

        },
        {
          path: '/courselist',
          name: 'courselist',
          component: () => import('../views/manager/CourseList.vue')
        },
        {
          path: '/studentCourse',
          name: 'studentCourse',
          component: () => import('../views/manager/StudentCourse.vue')
        },
        {
          path: '/grade',
          name: 'grade',
          component: () => import('../views/manager/Grade.vue')

        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/login/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/register/Register.vue')
    }
  ]
})

export default router
