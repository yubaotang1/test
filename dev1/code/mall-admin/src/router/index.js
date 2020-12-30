import Vue from 'vue'
import Router from 'vue-router'

import Index from '../views/Index'
import Login from '../views/Login'
import Detail from '../views/front/Detail'
import Cart from '../views/front/Cart'
import User from '../views/front/User'
import Order from '../views/front/Order'

import AdminIndex from '../views/admin/Index'
import AdminLogin from '../views/admin/Login'
import AdminMember from '../views/admin/member/Member'
import AdminGoods from '../views/admin/goods/Goods'
import AdminOrder from '../views/admin/order/Order'
import AdminAdvert from '../views/admin/advert/Advert'
import AdminCategory from '../views/admin/category/Category'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Index',
            component: Index
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        },
        {
            path: '/cart',
            name: 'Cart',
            component: Cart
        },
        {
            path: '/detail',
            name: 'Detail',
            component: Detail
        },
        {
            path: '/user',
            name: 'User',
            component: User
        },
        {
            path: '/order',
            name: 'Order',
            component: Order
        },
        {
            path: '/admin_index',
            name: 'AdminIndex',
            component: AdminIndex
        },
        {
            path: '/admin_login',
            name: 'AdminLogin',
            component: AdminLogin
        },
        {
            path: '/admin_member',
            name: 'AdminMember',
            component: AdminMember
        },
        {
            path: '/admin_goods',
            name: 'AdminGoods',
            component: AdminGoods
        },
        {
            path: '/admin_order',
            name: 'AdminOrder',
            component: AdminOrder
        },
        {
            path: '/admin_category',
            name: 'AdminCategory',
            component: AdminCategory
        },
        {
            path: '/admin_advert',
            name: 'AdminAdvert',
            component: AdminAdvert
        },
    ]
})