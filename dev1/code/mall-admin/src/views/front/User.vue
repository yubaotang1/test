<template>
    <div id="Index">
        <el-container>
            <el-header><el-link :underline="false" href="/#/">微服汇wfuhui.com-商城</el-link>

              <span style="float: right">
              <el-link type="primary" :underline="false" href="/#/login" v-if="!user">登录</el-link>
              <el-link type="primary" :underline="false" href="/#/order" v-if="user">欢迎，{{user.loginName}}</el-link>
              <el-link type="primary" :underline="false" href="/#/cart" v-if="user" style="margin-left: 20px">购物车</el-link>
              <el-link type="primary" :underline="false" href="/#/admin_index" style="margin-left: 20px">商家端</el-link>
              </span>
            </el-header>
            <el-container>
              <el-main>
                
              </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
export default {
  name: "Index",
  data() {
    return {
      categories: [],
      goodses: [],
      user: null,
      page: 1,
			pageSize: 8
    };
  },
  methods:{
    getUser: function(){
      $.getJSON("http://127.0.0.1:10001" + "/api/member/info", {}, function(r){
          if(r.code == 0){
            that.categories = r.categoryList;
          }
        })
    },
      
  },
  created(){
    var user = localStorage.getItem("auser");
    if(user){
        this.user = JSON.parse(user);
    }else{
        
    }
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