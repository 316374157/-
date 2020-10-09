<template>
    <div>
        <el-menu
                :default-active="defaultActive"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                router
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b">
            <el-menu-item index="/mainView">
                <i class="el-icon-s-platform"></i>
                <span slot="title" style="font-size: 23px">控制面板</span>
            </el-menu-item>
            <el-submenu index="1">
                <template slot="title">
                    <i class="el-icon-s-fold"></i>
                    <span slot="title" style="font-size: 23px">权限管理</span>
                    <el-badge type="info" :value="3" style="float: right"/>
                </template>
                <el-menu-item index="/user"><i class="el-icon-user-solid"></i>用户维护</el-menu-item>
                <el-menu-item index="/role"><i class="el-icon-s-custom"></i>角色维护</el-menu-item>
                <el-menu-item index="/permission"><i class="el-icon-shopping-bag-2"></i>菜单维护</el-menu-item>
                <el-menu-item index="/auth"><i class="el-icon-s-cooperation"></i>权限维护</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">
                    <i class="el-icon-check"></i>
                    <span slot="title" style="font-size: 23px">业务审核</span>
                    <el-badge type="info" :value="3" style="float: right"/>
                </template>
                <el-menu-item index=""><i class="el-icon-document-checked"></i>实名认证审核</el-menu-item>
                <el-menu-item index=""><i class="el-icon-document-checked"></i>广告审核</el-menu-item>
                <el-menu-item index=""><i class="el-icon-document-checked"></i>项目审核</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">
                    <i class="el-icon-menu"></i>
                    <span slot="title" style="font-size: 23px">业务管理</span>
                    <el-badge type="info" :value="7" style="float: right"/>
                </template>
                <el-menu-item index=""><i class="el-icon-picture"></i>资质维护</el-menu-item>
                <el-menu-item index=""><i class="el-icon-s-data"></i>分类管理</el-menu-item>
                <el-menu-item index=""><i class="el-icon-edit"></i>流程管理</el-menu-item>
                <el-menu-item index=""><i class="el-icon-s-shop"></i>广告管理</el-menu-item>
                <el-menu-item index=""><i class="el-icon-s-comment"></i>消息模板</el-menu-item>
                <el-menu-item index=""><i class="el-icon-notebook-2"></i>项目分类</el-menu-item>
                <el-menu-item index=""><i class="el-icon-price-tag"></i>项目标签</el-menu-item>
            </el-submenu>
            <el-menu-item index="4">
                <i class="el-icon-document"></i>
                <span slot="title" style="font-size: 23px">参数管理</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<style>

</style>

<script>
    export default {
        data(){
            return{
                defaultActive: this.$route.path
            };
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>