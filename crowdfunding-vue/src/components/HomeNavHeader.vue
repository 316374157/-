<template>
    <div>
        <el-row :gutter="100">
            <el-col :span="8">
                <el-menu
                        :default-active="activeIndex"
                        class="el-menu-demo"
                        mode="horizontal"
                        @select="handleSelect"
                        background-color="#545c64"
                        text-color="#fff"
                        router
                        active-text-color="#ffd04b">
                    <el-menu-item index="/userHome">众筹平台-{{this.$route.name}}</el-menu-item>
                </el-menu>
            </el-col>
            <el-col :span="4" :offset="7">
                <el-input  placeholder="搜索" style="float:right" prefix-icon="el-icon-search" ></el-input>
            </el-col>
            <el-col :span="3">
                <el-dropdown trigger="click" @command="handleCommand" >
                    <el-button type="success" icon="el-icon-user" style="float: right;margin-top:10px">{{user.username}}
                        <i class="el-icon-arrow-down el-icon--right"></i></el-button>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item icon="el-icon-s-tools">个人设置</el-dropdown-item>
                        <el-dropdown-item icon="el-icon-chat-dot-square" >消息</el-dropdown-item>
                        <el-dropdown-item icon="el-icon-switch-button" divided command="logout">退出系统</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-col>
            <el-col :span="1">
                <el-button type="danger" icon="el-icon-question" style="float: right;margin-top:10px">帮助</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<style>

</style>

<script>
export default {
    data() {
        return {
          activeIndex: '/userHome',
          user:{}
        };
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleCommand(command) {
            axios.get(`http://localhost:9000/auth/member/logout`).then(response =>{
                if (response.data.result === 'SUCCESS'){
                  sessionStorage.removeItem("user");
                  sessionStorage.removeItem("admin");
                  sessionStorage.clear();
                  if(this.$route.path !== "/"){
                    this.$router.push('/');
                  }else if(this.$route.path === "/"){
                    window.location.reload();
                  }
                }
            })
        },
    },
    created() {
      this.user = JSON.parse(sessionStorage.getItem("user"));
    }
};
</script>