<template>
    <div>
        <el-carousel :interval="4000" type="card" height="260px">
            <el-carousel-item v-for="(item,index) in images" :key="index">
                <el-image :src="item.url" alt="" style="width: 100%"/>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
    export default {
        name: "carousel",
        data(){
            return{
                images:[
                    {url:require('../static/img/carousel-1.jpg')},
                    {url:require('../static/img/carousel-2.jpg')},
                    {url:require('../static/img/carousel-3.jpg')}
                ]
            }
        }
    }
</script>

<style scoped>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

</style>