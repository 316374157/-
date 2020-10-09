<template>
        <div>
            <el-container>
                <el-header>
                    <MainNavHeader/>
                </el-header>
                <el-container>
                    <el-aside width="400px"  style="text-align: center;">
                        <MainTree/>
                    </el-aside>
                    <el-main>
                        <RouterView/>
                    </el-main>
                </el-container>
            </el-container>
        </div>
</template>

<script>
    import MainNavHeader from "../components/MainNavHeader";
    import MainTree from "../components/MainTree";
    export default {
        components:{
            MainNavHeader,
            MainTree,
        }
    };
</script>

<style>
    .el-header {
        background-color: #545c64;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-main {
        color: #333;
        text-align: center;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>