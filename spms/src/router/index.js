import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Manager',
      component: () => import('../views/Manager.vue'),
      redirect: '/home',
      children: [
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
