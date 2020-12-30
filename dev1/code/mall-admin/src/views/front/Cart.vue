<template>
    <div id="Index">
        <el-container>
            <el-header><el-link :underline="false" href="/#/">微服汇wfuhui.com-商城</el-link>

              <span style="float: right">
              <el-link type="primary" :underline="false" href="/#/login">登录</el-link>
              <el-link type="primary" :underline="false" href="/#/admin_index" style="margin-left: 20px">商家端</el-link>
              </span>
            </el-header>
            <el-container>
              <el-main>
                <el-table
                  :data="goodsList"
                  style="width: 100%">
                  <el-table-column
                      prop="picUrl"
                      label="图片">
                      <template slot-scope="scope">
                          <el-image
                              style="width: 80px; height: 80px"
                              :src="scope.row.picUrl"></el-image>
                      </template>
                  </el-table-column>
                  <el-table-column
                      prop="goodsName"
                      label="姓名">
                  </el-table-column>
                  <el-table-column
                      prop="price"
                      label="价格">
                  </el-table-column>
                  <el-table-column
                      prop="buyNum"
                      label="数量">
                   </el-table-column>
                  </el-table>
                  <div style="text-align: right; margin-top: 20px;">
                  <el-button type="danger" @click="buy">立即购买</el-button>
                  </div>
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
      goodsList: [],
      user: null,
      totalAmount: 0
    };
  },
  methods:{
    buy: function(){
      var orderGoodsList = [];
      for(var i = 0; i < this.goodsList.length; i++){
        var orderGoods = {
            goodsId: this.goodsList[i].id,
            goodsName: this.goodsList[i].goodsName,
            price: this.goodsList[i].price,
            picUrl: this.goodsList[i].picUrl,
            buyNum: 1
          }
        orderGoodsList.push(orderGoods);
      }
      var data = {
        totalAmount: this.totalAmount,
        orderGoodsList: orderGoodsList
      }

      this.$axios.post("http://127.0.0.1:10001" + "/api/order/create", data, {headers: {token: localStorage.getItem("ftoken")}}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code === 0){
              alert("下单成功");
              that.$router.push("order");
            }else{
              alert(r.msg);
            }
        }
      )
    }
      
  },
  created(){
    var user = localStorage.getItem("auser");
    if(user){
        this.user = JSON.parse(user);
    }else{
        
    }
    // 获取购物车数据
    var goodsList = localStorage.getItem('shopCart');
    if (goodsList) {
      this.goodsList = JSON.parse(goodsList);
      var totalAmount = 0;
      for(var i = 0; i < this.goodsList.length; i++){
        totalAmount += this.goodsList[i].price;
      }
      this.totalAmount = totalAmount;
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