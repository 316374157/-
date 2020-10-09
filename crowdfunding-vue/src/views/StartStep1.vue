<template>
<div>
  <ProjectStep ref="child"/>
  <div style="text-align: left">
    <div>
      <el-row>
        <el-col>
          <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
            <h2>
              项目及发起人信息
            </h2>
          </blockquote>
        </el-col>
        <el-col :offset="1">
            <h3>
              项目信息
            </h3>
          <el-divider></el-divider>
          <el-col :span="13">
            <el-form ref="form" :model="form" label-width="100px" >
              <el-form-item label="分类信息">
                <el-checkbox-group v-model="form.typeIdList">
                  <el-checkbox :label="1">科技</el-checkbox>
                  <el-checkbox :label="2" >设计</el-checkbox>
                  <el-checkbox :label="3" >农业</el-checkbox>
                  <el-checkbox :label="4" >工艺</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="标签">
                <el-checkbox-group v-model="form.tagIdList">
                  <el-checkbox-button v-for="(item,index) in form.tags" :label="item.id" :key="index">{{item.title}}</el-checkbox-button>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="项目名称">
                <el-input v-model="form.projectName"></el-input>
              </el-form-item>
              <el-form-item label="一句话简介">
                <el-input type="textarea" v-model="form.projectDescription"></el-input>
              </el-form-item>
              <el-form-item label="筹集金额">
                <el-input v-model="form.money"></el-input>
                <label>筹资金额不能低于100元,不能高于1000000000元</label>
              </el-form-item>
              <el-form-item label="筹集天数">
                <el-input v-model="form.day"></el-input>
                <label>一般10-90天，建议30天</label>
              </el-form-item>
              <el-form-item label="项目头图">
                <el-upload
                    action="http://localhost:9000/project/upload/one/image"
                    list-type="picture-card"
                    :on-remove="headerRemove"
                    :multiple="false"
                    :limit="1"
                    :file-list="headerPicture"
                    :on-success="onHeaderSuccess"
                >
                  <i class="el-icon-plus"></i>
                </el-upload>
                <label class="control-label">图片上无文字,支持jpg、jpeg、png、gif格式，大小不超过2M，建议尺寸：740*457px</label>
              </el-form-item>
              <el-form-item label="项目详情">
                <el-upload
                    action="http://localhost:9000/project/upload/one/image"
                    list-type="picture-card"
                    :on-remove="detailRemove"
                    :file-list="detailPicture"
                    :on-success="onDetailSuccess"
                    :multiple="true"
                >
                  <i class="el-icon-plus"></i>
                </el-upload>
                <label class="control-label">支持jpg、jpeg、png、gif格式，大小不超过2M，建议尺寸：宽740px</label>
              </el-form-item>
            </el-form>
          </el-col>
        </el-col>
        <el-col :offset="1">
            <h3>
              发起人信息
            </h3>
          <el-divider></el-divider>
          <el-col :span="13">
            <el-form ref="form" :model="form.memberLaunchInfoVO" label-width="100px" >
              <el-form-item label="自我介绍">
                <el-input v-model="form.memberLaunchInfoVO.descriptionSimple"></el-input>
              </el-form-item>
              <el-form-item label="详细介绍">
                <el-input type="textarea" v-model="form.memberLaunchInfoVO.descriptionDetail"></el-input>
              </el-form-item>
              <el-form-item label="联系电话">
                <el-input v-model="form.memberLaunchInfoVO.phoneNum"></el-input>
              </el-form-item>
              <el-form-item label="客服电话">
                <el-input v-model="form.memberLaunchInfoVO.serviceNum"></el-input>
              </el-form-item>
            </el-form>
          </el-col>
        </el-col>
      </el-row>
    </div>
  </div>
  <el-button type="warning" @click="next">下一步</el-button>
</div>
</template>

<script>
import ProjectStep from "@/components/ProjectStep";
export default {
  name: "startStep1",
  components:{
    ProjectStep
  },
  methods:{
    next(){
      sessionStorage.setItem("project-step1",JSON.stringify(this.form))
      axios.post("http://localhost:9000/project/save/project/basicinfo",this.form).then(response =>{
        if(response.data.result === "SUCCESS"){
          this.$refs.child.next();
          this.$router.push("/startStep2")
        }else {
          this.showFiledMsg(response.data.msg)
        }
      })

    },
    detailRemove(file, fileList) {
      this.form.detailPicturePathList.splice(this.form.detailPicturePathList.indexOf(file.response.data[0]),1)
      sessionStorage.setItem("detailPicture",JSON.stringify(fileList))
    },
    headerRemove(file, fileList) {
      this.form.headerPicturePath = '';
      sessionStorage.setItem("headerPicture",JSON.stringify(fileList))
    },
    onHeaderSuccess(response,file,fileList){
      this.form.headerPicturePath = response.data;
      sessionStorage.setItem("headerPicture",JSON.stringify(fileList))
    },
    onDetailSuccess(response,file,fileList){
      this.form.detailPicturePathList.push(response.data);
      sessionStorage.setItem("detailPicture",JSON.stringify(fileList))
    },
    showFiledMsg(msg){
      this.$message({
        showClose: true,
        message: msg,
        type: 'error'
      });
    }
  },
  data() {
    return {
      headerPicture:[],
      detailPicture:[],
      form: {
        //分类id集合
        typeIdList:[],
        //标签id集合
        tagIdList:[],
        // 项目名称
        projectName:'',
        // 项目描述
        projectDescription:'',
        // 计划筹集的金额
        money:0,
        // 筹集资金的天数
        day:0,
        // 头图的路径
        headerPicturePath:'',
        // 详情图片的路径
        detailPicturePathList:[],
        // 发起人信息
        memberLaunchInfoVO:{
          // 简单介绍
          descriptionSimple:'',
          // 详细介绍
          descriptionDetail:'',
          // 联系电话
          phoneNum:'',
          // 客服电话
          serviceNum:''
        },
        tags:[
          {id:1,title:'大屏'},
          {id:2,title:'美颜'},
          {id:3,title:'续航'},
          {id:4,title:'高度解析'},
          {id:5,title:'高清'},
          {id:6,title:'i7'},
          {id:7,title:'固态'},
          {id:8,title:'32G'}
        ]
      }
    }
  },
  created() {
    if(JSON.parse(sessionStorage.getItem("headerPicture")) !== [] ){
      this.headerPicture = JSON.parse(sessionStorage.getItem("headerPicture"))
    }
    if(JSON.parse(sessionStorage.getItem("detailPicture")) !== [] ){
      this.detailPicture = JSON.parse(sessionStorage.getItem("detailPicture"))
    }
    if(JSON.parse(sessionStorage.getItem("project-step1")) !== null){
      this.form = JSON.parse(sessionStorage.getItem("project-step1"))
    }
  }
}
</script>

<style scoped>

</style>