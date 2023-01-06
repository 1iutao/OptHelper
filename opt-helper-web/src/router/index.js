import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/components/Login";
import Home from "@/components/Home";
import Food from "@/components/Food";
import Drink from "@/components/Drink";
Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    redirect: "/home"
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/home",
    component: Home,
    redirect: "/food",
    children: [
      {
        path: "/food",
        component: Food,
      },
      {
        path: "/drink",
        component: Drink,
      }
    ]
  },
]

const router = new VueRouter({
  routes
})

export default router
