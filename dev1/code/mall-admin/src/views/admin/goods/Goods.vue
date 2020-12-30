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
                <el-aside width="200px">
                    <el-menu
                        default-active="5"
                        class="el-menu-vertical-demo"
                        :style="asideStyle"
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
                     <div v-if="showList">
                    <el-form :inline="true" :model="q" class="demo-form-inline">
                        <el-form-item label="商品名称">
                            <el-input v-model="q.goodsName" placeholder="商品名称"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="query">查询</el-button>
                            <el-button type="success" @click="add">新增</el-button>
                            <el-button type="danger" @click="del">删除</el-button>
                        </el-form-item>
                    </el-form>
                        <el-table
                        :data="goodsList"
                        style="width: 100%"
                        @selection-change="handleSelectionChange">
                        <el-table-column
                          type="selection"
                          width="55">
                        </el-table-column>
                        <el-table-column
                            prop="picUrl"
                            label="图片"
                            >
                            <template slot-scope="scope">
                                <el-image
                                    style="width: 80px; height: 80px"
                                    :src="scope.row.picUrl"></el-image>
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="goodsName"
                            label="商品名称"
                            >
                        </el-table-column>
                        <el-table-column
                            prop="category.categoryName"
                            label="分类">
                        </el-table-column>
                        <el-table-column
                            prop="price"
                            label="价格">
                        </el-table-column>
                        <el-table-column
                            prop="createTime"
                            label="创建时间">
                        </el-table-column>
                        </el-table>
                     </div>
                        <div v-if="!showList">
                        <el-form :model="goods" label-width="80px">
                            <el-form-item label="图片">
                                <el-upload
                                class="avatar-uploader"
                                action="http://127.0.0.1:10001/api/fileupload/upload"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload">
                                <img v-if="goods.picUrl" :src="goods.picUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                </el-upload>

                            </el-form-item>
                        <el-form-item label="商品名称">
                            <el-input v-model="goods.goodsName"></el-input>
                        </el-form-item>
                        <el-form-item label="编码">
                            <el-input v-model="goods.barcode"></el-input>
                        </el-form-item>
                        <el-form-item label="分类">
                            <el-select v-model="goods.categoryId" placeholder="请选择">
                                <el-option
                                v-for="item in categoryList"
                                :key="item.id"
                                :label="item.categoryName"
                                :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="价格">
                            <el-input v-model="goods.price"></el-input>
                        </el-form-item>
                        <el-form-item label="备注">
                            <el-input type="textarea" v-model="goods.describe"></el-input>
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
  name: "Goods",
  data() {
    return {
      goodsList: [],
      categoryList: [],
      q: {
        goodsName: ''
      },
      asideStyle: {
        height: '500px'
      },
      goods: {
          picUrl: ''
      },
      showList: true
    };
  },
  methods:{
      query(){
        var that = this;
        this.$axios({
            method: 'get',
            url: 'http://127.0.0.1:10001/goods/list',
            data: that.q
        }).then(function(res){
            if(res.data.code == 0){//成功
              that.goodsList = res.data.rows
            }else{
              
            }
        })
      },
      queryCategory(){
        var that = this;
        this.$axios.get('http://127.0.0.1:10001/category/listAll',
        {
            headers:{ token: localStorage.getItem("atoken") }
        }).then(function(res){
            if(res.data.code == 0){//成功
              that.categoryList = res.data.categoryList
            }else{
              
            }
        })
      },
      onSubmit(e){
        var that = this;
        var data = this.goods;
        this.$axios({
            method: 'post',
            url: 'http://127.0.0.1:10001/goods/save',
            data: data
        }).then(function(res){
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
        this.$axios({
            method: 'post',
            url: 'http://127.0.0.1:10001/goods/delete',
            data: that.ids
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
          this.goods.picUrl = e.url
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
        localStorage.removeItem("fuser");
        this.$router.push("admin_login");
      }
  },
  created(){
    var docHeight = document.documentElement.clientHeight;
    this.asideStyle.height = docHeight - 76 + "px";
    var user = localStorage.getItem("auser");
    if(user){
        this.user = JSON.parse(user);
    }else{
        this.$router.push("admin_login");
    }
    this.query();
    this.queryCategory();
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