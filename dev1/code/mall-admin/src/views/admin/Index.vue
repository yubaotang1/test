<template>
    <div>
        <el-container>
            <el-header>微服汇商城 wfuhui.com

              <span style="float: right">
              <el-link type="primary" :underline="false" v-if="user">欢迎，{{user.username}}</el-link>
              <el-link type="primary" :underline="false" style="margin-left: 20px" @click="logout()">退出</el-link>
              </span>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu
                        default-active="1"
                        class="el-menu-vertical-demo"
                        :style="asideStyle"
                        router>
                        <el-menu-item index="1" route="admin_index">
                            <i class="el-icon-menu"></i>
                            <span slot="title">统计分析</span>
                        </el-menu-item>
                        
                        <el-menu-item index="3" route="admin_goods">
                            <i class="el-icon-menu"></i>
                            <span slot="title">商品管理</span>
                        </el-menu-item>
                        <el-menu-item index="4" route="admin_order">
                            <i class="el-icon-menu"></i>
                            <span slot="title">订单管理</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                  <h1>欢迎使用！</h1>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
export default {
  name: "AdminIndex",
  data() {
    return {
      user: null,
      asideStyle: {
        height: '500px'
      }
    };
  },
  methods:{
    logout: function(){
      localStorage.removeItem("auser");
      app.user = null;
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