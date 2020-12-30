<template>
    <div>
        <el-container>
            <el-header>微服汇商城 wfuhui.com
              <span style="float: right">
              <el-link type="primary" :underline="false" v-if="user">欢迎，{{user.username}}</el-link>
              <el-link type="primary" :underline="false" href="javascript:logout()" style="margin-left: 20px">退出</el-link>
              </span></el-header>
            <el-container>
                <el-aside width="200px" height="100%">
                    <el-menu
                        default-active="6"
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
                    <div>
                    <el-form :inline="true" :model="q" class="demo-form-inline">
                        <el-form-item label="订单编号">
                            <el-input v-model="q.nickname" placeholder="订单编号"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="query">查询</el-button>
                        </el-form-item>
                    </el-form>
                        <el-table
                        :data="orderList"
                        style="width: 100%">
                        <el-table-column
                            prop="orderNumber"
                            label="订单编号">
                        </el-table-column>
                        <el-table-column
                            prop="member.realName"
                            label="姓名">
                        </el-table-column>
                        <el-table-column
                            prop="totalAmount"
                            label="价格">
                        </el-table-column>
                        <el-table-column
                            prop="createTime"
                            label="创建时间">
                        </el-table-column>
                        </el-table>
                    </div>
                    <div></div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
export default {
  name: "Order",
  data() {
    return {
      q:{
          orderNumber: ''
      },
      orderList: []
    };
  },
  methods:{
      query(){
        var that = this;
        this.$axios({
            method: 'get',
            url: 'http://127.0.0.1:10001/order/list',
            data: {}
        }).then(function(res){
            console.log(res)
            if(res.data.code == 0){//成功
              that.orderList = res.data.rows
            }else{
              
            }
        })
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
        this.$router.push("admin_login");
    }
    this.query();
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