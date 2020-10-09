<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="4" :offset="6">
                <el-card :body-style="{ padding: '0px' }" style="height: 400px">
                    <img src="../static/img/p1.jpg" class="image">
                    <div style="padding: 14px;">
                        <h2 style="text-align: center">智能高清监控机器人</h2>
                        <div class="bottom clearfix">
                            <p>可爱的造型，摄像安防远程互联的全能设计，让你随时随地守护您的家人，陪伴你的生活。</p>
                            <el-button size="medium" style="float: right">项目详情 &raquo;</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="4">
                <el-card :body-style="{ padding: '0px' }" style="height: 400px">
                    <img src="../static/img/p2.jpg" class="image">
                    <div style="padding: 14px;">
                        <h2 style="text-align: center">NEOKA智能手环</h2>
                        <div class="bottom clearfix">
                            <p>要运动更要安全，这款、名为“蝶舞”的NEOKA-V9100智能运动手环为“安全运动而生”。</p>
                            <el-button size="medium" style="float: right">项目详情 &raquo;</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="4">
                <el-card :body-style="{ padding: '0px' }" style="height: 400px">
                    <img src="../static/img/p3.jpg" class="image">
                    <div style="padding: 14px;">
                        <h2 style="text-align: center">驱蚊扣</h2>
                        <div class="bottom clearfix">
                            <p>随处使用的驱蚊纽扣，解决夏季蚊虫问题。</p>
                            <el-button size="medium" style="float: right">项目详情 &raquo;</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "card",
        data() {
            return {
                currentDate: new Date()
            };
        }
    }
</script>

<style scoped>
    .bottom {
        margin-top: 13px;
        line-height: 22px;
    }

    .image {
        width: 140px;
        display: block;
        height: 140px;
        margin: auto;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }
</style>