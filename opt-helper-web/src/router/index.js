import {createRouter, createWebHistory} from 'vue-router'
import Layout from '@/layout'

export const routes = [
  {
    path: '/',
    component: Layout,
    name: '主页',
    meta: {
      title: '主页'
    }
    // component: () => import('@/views/home/Index')
  },
  {
    path: '/one',
    component: Layout,
    name: '一级菜单',
    meta: {
      title: '菜单一'
    },
    // component: () => import('@/views/home/index')
    children: [
      {
        path: '/one1',
        name: '二级菜单1',
        meta: {
          title: '菜单2-1'
        },
        component: () => import('@/views/home/Index')
      },
      {
        path: '/one2',
        name: '二级菜单2',
        meta: {
          title: '菜单2-1'
        },
        component: () => import('@/views/home/Index')
      },
      {
        path: '/one3',
        name: '二级菜单3',
        meta: {
          title: '菜单2-3'
        },
        component: () => import('@/views/home/Index')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
