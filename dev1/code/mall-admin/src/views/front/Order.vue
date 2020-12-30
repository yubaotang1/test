<template>
    <div id="Index">
        <el-container>
            <el-header><el-link :underline="false" href="/#/">微服汇wfuhui.com-商城</el-link>

              <span style="float: right">
              <el-link type="primary" :underline="false" href="/#/login" v-if="!user">登录</el-link>
              <el-link type="primary" :underline="false" href="/#/order" v-if="user">欢迎，{{user.loginName}}</el-link>
              <el-link type="primary" :underline="false" href="/#/cart" v-if="user" style="margin-left: 20px">购物车</el-link>
              <el-link type="primary" :underline="false" @click="logout" style="margin-left: 20px">退出</el-link>
              <el-link type="primary" :underline="false" href="/#/admin_index" style="margin-left: 20px">商家端</el-link>
              </span>
            </el-header>
            <el-container>
              <el-main>
                <el-table
                  :data="orderList"
                  style="width: 100%">
                  <el-table-column
                      prop="orderNumber"
                      label="订单编号">
                  </el-table-column>
                  <el-table-column
                      prop="totalAmount"
                      label="金额">
                  </el-table-column>
                  <el-table-column
                      prop="createTime"
                      label="创建时间">
                  </el-table-column>
                  </el-table>
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
      orderList: [],
      user: null,
      page: 1,
			pageSize: 8
    };
  },
  methods:{
    getOrder: function(){
      var that = this;
      this.$axios.get("http://127.0.0.1:10001" + "/api/order/list", {params: {}, headers:{ token: localStorage.getItem("ftoken") }}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code == 0){
            that.orderList = r.orderList;
          }
        }
      )
    },
    logout(e){
        localStorage.removeItem("fuser");
        this.$router.push("login");
      }
  },
  created(){
    var user = localStorage.getItem("fuser");
    if(user){
        this.user = JSON.parse(user);
    }else{
        
    }
    this.getOrder();
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