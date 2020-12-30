<template>
    <div>
        <el-container>
            <el-header>微服汇商城 wfuhui.com
              <span style="float: right">
              <el-link type="primary" :underline="false" v-if="user">欢迎，{{user.username}}</el-link>
              <el-link type="primary" :underline="false" href="javascript:logout()" style="margin-left: 20px">退出</el-link>
              </span>
            </el-header>
            <el-container>
                <el-aside width="200px" height="100%">
                    <el-menu
                        default-active="3"
                        class="el-menu-vertical-demo"
                        router>
                        <el-menu-item index="1" route="/admin_index">
                            <i class="el-icon-menu"></i>
                            <span slot="title">统计分析</span>
                        </el-menu-item>
                        <el-menu-item index="5" route="admin_goods">
                            <i class="el-icon-menu"></i>
                            <span slot="title">商品管理</span>
                        </el-menu-item>
                        <el-menu-item index="6" route="admin_order">
                            <i class="el-icon-menu"></i>
                            <span slot="title">订单管理</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <el-form :inline="true" :model="q" class="demo-form-inline">
                        <el-form-item>
                            <el-button type="warning" @click="add">新增</el-button>
                            <el-button type="danger" @click="del">删除</el-button>
                        </el-form-item>
                    </el-form>
                        <div v-if="showList">
                        <el-table
                        :data="advertList"
                        style="width: 100%">
                        <el-table-column
                            prop="picUrl"
                            label="图片"
                            >
                            <template slot-scope="scope">
                                <el-image
                                    style="width: 150px"
                                    :src="scope.row.picUrl"></el-image>
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="sort"
                            label="排序"
                            >
                        </el-table-column>
                        <el-table-column
                            prop="createTime"
                            label="创建时间"
                            >
                        </el-table-column>
                        </el-table>
                        </div>
                        <div v-if="!showList">
                        <el-form :model="advert" label-width="80px">
                            <el-form-item label="图片">
                                <el-upload
                                class="avatar-uploader"
                                action="http://127.0.0.1:10001/api/fileupload/upload"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload">
                                <img v-if="advert.picUrl" :src="advert.picUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                </el-upload>
                            </el-form-item>
                        <el-form-item label="排序">
                            <el-input v-model="advert.sort"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit">保存</el-button>
                            <el-button @click="cancel">取消</el-button>
                        </el-form-item>
                        </el-form>
                        </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
export default {
  name: "AdminAdvert",
  data() {
    return {
      advertList: [],
      q: {

      },
      showList: true,
      advert: {
          picUrl: ''
      }
    };
  },
  methods:{
      query(){
        var that = this;
        this.$axios({
            method: 'get',
            url: 'http://127.0.0.1:10001/advert/list',
            data: that.q
        }).then(function(res){
            if(res.data.code == 0){//成功
              that.advertList = res.data.rows
            }else{
              
            }
        })
      },
      onSubmit(e){
        var that = this;
        var data = this.advert;
        this.$axios.post('http://127.0.0.1:10001/advert/save', 
            data,
            {
                headers: {'token': localStorage.getItem("atoken")}
            }
        ).then(function(res){
            console.log(res)
            if(res.data.code == 0){//成功
              that.showList = true;
              that.query();
            }else{
              that.errorMsg = res.data.msg;
              that.$message.error(res.data.msg);
            }
        })
      },
      add(){
          this.showList = false
      },
      cancel(){
          this.showList = true
      },
      del(){
        if(this.ids.length == 0){
            that.$message.info("请选择数据");
            return;
        }
        var that = this;
        this.$axios.post('http://127.0.0.1:10001/advert/delete',
            that.ids,
            {
                headers: {'token': localStorage.getItem("atoken")}
            }).then(function(res){
            console.log(res)
            if(res.data.code == 0){//成功
              that.query();
            }else{
              that.errorMsg = res.data.msg;
              that.$message.error(res.data.msg);
            }
        })
      },
      handleAvatarSuccess(e){
          console.log(e)
          this.advert.picUrl = e.url
      },
      beforeAvatarUpload(e){

      },
      handleSelectionChange(e){
        var ids = [];
        for(var i = 0; i < e.length; i++){
            ids.push(e[i].id)
        }
        this.ids = ids;
      },
      logout(e){
        localStorage.removeItem("auser");
        this.$router.push("admin_login");
      }
  },
  created(){
    var user = localStorage.getItem("auser");
    if(user){
        this.user = JSON.parse(user);
    }else{
        this.$router.push("Login");
    }
    this.query()
  }
};
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}
</style>