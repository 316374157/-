import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "../views/Index";
import AdminLogin from "../views/AdminLogin";
import Main from "../views/Main";
import MainView from "../views/MainView";
import User from "../views/User";
import Role from "../views/Role";
import Permission from "../views/Permission";
import Auth from "../views/Auth";
import UserRegister from "../views/UserRegister";
import UserLogin from "../views/UserLogin";
import UserHome from "@/views/UserHome";
import PreHome from "@/views/PreHome";
import Asset from "@/views/Asset";
import MyCrowdfunding from "@/views/MyCrowdfunding";
import CreateCrowd from "@/views/CreateCrowd";
import Start from "@/views/Start";
import StartStep1 from "@/views/StartStep1";
import StartStep2 from "@/views/StartStep2";
import StartStep3 from "@/views/StartStep3";
import StartStep4 from "@/views/StartStep4";
import DetailProject from "@/views/DetailProject";
import PayStep1 from "@/views/PayStep1";
import PayStep2 from "@/views/PayStep2";
import PayStep3 from "@/views/PayStep3";
import PayStep4 from "@/views/PayStep4";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: '首页',
        component: Index,
        children: [
            {
                path: "/detailProject",
                name: "项目详情",
                component: DetailProject,
            },
            {
                path: '/payStep1',
                name: '支付-第一步',
                component: PayStep1,
                meta: {
                    requireAuth: true,
                    level:1
                }
            },
            {
                path: '/payStep2',
                name: '支付-第二步',
                component: PayStep2,
                meta: {
                    requireAuth: true,
                    level:1
                }
            },
            {
                path: '/payStep3',
                name: '支付-第三步',
                component: PayStep3,
                meta: {
                    requireAuth: true,
                    level:1
                }
            },
            {
                path: '/payStep4',
                name: '支付-第四步',
                component: PayStep4,
                meta: {
                    requireAuth: true,
                    level:1
                }
            }
        ]
    },
    {
        path: '/userRegister',
        name: '用户注册',
        component: UserRegister
    },
    {
        path: '/userLogin',
        name: '用户登录',
        component: UserLogin
    },
    {
        path: '/preHome',
        component: PreHome,
        redirect: '/userHome',
        meta: {
            requireAuth: true,
            level:1
        },
        children: [
            {
                path: '/userHome',
                name: '会员中心',
                component: UserHome,
                redirect: '/asset',
                meta: {
                    requireAuth: true,
                    level:1
                },
                children: [
                    {
                        path: '/asset',
                        name: '资产总览',
                        component: Asset,
                        meta: {
                            requireAuth: true,
                            level:1
                        }
                    },
                    {
                        path: '/myCrowdfunding',
                        name: '我的众筹',
                        component: MyCrowdfunding,
                        meta: {
                            requireAuth: true,
                            level:1
                        }
                    }
                ]
            },
            {
                path: '/createCrowd',
                name: '发起众筹',
                component: CreateCrowd,
                meta: {
                    requireAuth: true,
                    level:1
                },
                children: [
                    {
                        path: '/myCrowdfunding',
                        name: '我的众筹',
                        component: MyCrowdfunding,
                        meta: {
                            requireAuth: true,
                            level:1
                        }
                    },
                    {
                        path: '/start',
                        name: '协议',
                        component: Start,
                        meta: {
                            requireAuth: true,
                            level:1
                        }
                    },
                    {
                        path: '/startStep1',
                        name: '创建项目-第一步',
                        component: StartStep1,
                        meta: {
                            requireAuth: true,
                            level:1
                        }
                    },
                    {
                        path: '/startStep2',
                        name: '创建项目-第二步',
                        component: StartStep2,
                        meta: {
                            requireAuth: true,
                            level:1
                        }
                    },
                    {
                        path: '/startStep3',
                        name: '创建项目-第三步',
                        component: StartStep3,
                        meta: {
                            requireAuth: true,
                            level:1
                        }
                    },
                    {
                        path: '/startStep4',
                        name: '创建项目-第四步',
                        component: StartStep4,
                        meta: {
                            requireAuth: true,
                            level:1
                        }
                    }
                ]
            }
        ]
    },
    {
        path: '/adminLogin',
        name: '管理员登录',
        component: AdminLogin
    },
    {
        path: '/main',
        name: '后台主页',
        component: Main,
        redirect: '/mainView',
        meta: {
            requireAuth: true,
            level:2
        },
        children: [
            {
                path: '/mainView',
                name: '后台主页视图',
                component: MainView,
                meta: {
                    requireAuth: true,
                    level:2
                },
            },
            {
                path: '/user',
                name: '用户维护',
                component: User,
                meta: {
                    requireAuth: true,
                    level:2
                },
            },
            {
                path: '/role',
                name: '角色维护',
                component: Role,
                meta: {
                    requireAuth: true,
                    level:2
                },
            },
            {
                path: '/permission',
                name: '菜单维护',
                component: Permission,
                meta: {
                    requireAuth: true,
                    level:2
                },
            },
            {
                path: '/auth',
                name: '权限维护',
                component: Auth,
                meta: {
                    requireAuth: true,
                    level:2
                },
            }
        ]
    }

];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});


export default router
