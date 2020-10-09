<template>
    <div>
        <el-row>
            <el-col :span="6">
                <div class="demo-basic--circle">
                    <div class="block"><el-avatar :size="200" src=""></el-avatar>
                        <h3 style="">Label</h3>
                        <span>Something else</span>
                    </div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="demo-basic--circle">
                    <div class="block"><el-avatar :size="200" src="" ></el-avatar>
                        <h3>Label</h3>
                        <span>Something else</span>
                    </div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="block"><el-avatar :size="200" src="" ></el-avatar>
                    <h3>Label</h3>
                    <span>Something else</span>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="block"><el-avatar :size="200" src="" ></el-avatar>
                    <h3>Label</h3>
                    <span>Something else</span>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "MainView",
        data () {
            return {

            }
        }
    }
</script>

<style scoped>

</style>