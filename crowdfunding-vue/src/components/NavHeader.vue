<template>
    <div>
        <el-menu
                :default-active="activeIndex"
                class="el-menu-demo"
                router
                mode="horizontal"
                @select="handleSelect"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b">
            <el-menu-item index="/">尚筹网-创意产品众筹平台</el-menu-item>
            <el-menu-item style="float:right" index="/adminLogin">管理员入口</el-menu-item>
            <el-menu-item style="float:right"><el-divider direction="vertical"></el-divider></el-menu-item>
            <el-menu-item style="float:right" index="/userRegister">注册</el-menu-item>
            <el-menu-item style="float:right" index="/userLogin">登录</el-menu-item>
        </el-menu>
    </div>
</template>

<style>
</style>

<script>
    export default {
        data() {
            return {
                activeIndex: '/',
            };
        },
        created() {
            this.activeIndex = this.$route.path;
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    };
</script>