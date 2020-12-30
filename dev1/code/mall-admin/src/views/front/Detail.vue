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
                <el-row :gutter="20">
                <el-col :span="6">
                  <div class="grid-content bg-purple">
                    <el-image
                      style="width: 250px; height: 250px"
                      :src="goods.picUrl"></el-image>
                  </div>
                </el-col>
                <el-col :span="18">
                  <div class="grid-content bg-purple">
                    <div>
                      <p>{{goods.goodsName}}</p>  
                      <p>价格：{{goods.price}}</p>  
                    </div>
                    <div>
                      <el-button type="warning" @click="addShopCart">加入购物车</el-button>
                      <el-button type="danger" @click="buy">立即购买</el-button>
                    </div>
                  </div>
                </el-col>
              </el-row>
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
      goods: {},
      user: null,
      page: 1,
			pageSize: 8
    };
  },
  methods:{
    getGoods: function(id){
      var that = this;
      this.$axios.get("http://127.0.0.1:10001" + "/api/goods/detail", {params: {id: id}}).then(
        function(res){
          var r = res.data;
          console.log(r)
          if(r.code == 0){
            that.goods = r.goods;
          }
        }
      )
    },
    addShopCart: function (e) {
      // 加入购物车
      var goodsList = localStorage.getItem("shopCart");
      var goods = this.goods;
      var buyNum = 1;
      if (goodsList) {
        goodsList = JSON.parse(goodsList);
        for (var i = 0; i < goodsList.length; i++) {
          if (goodsList[i].id == goods.id) {
            buyNum = goodsList[i].buyNum + 1;
            break;
          }
        }
        var temp = {
          id: goods.id,
          goodsName: goods.goodsName,
          picUrl: goods.picUrl,
          price: goods.price,
          buyNum: buyNum
        }
        goodsList.push(temp);
      } else {
        goodsList = [{
          id: goods.id,
          goodsName: goods.goodsName,
          picUrl: goods.picUrl,
          price: goods.price,
          buyNum: 1
        }];
      }
      localStorage.setItem("shopCart", JSON.stringify(goodsList));
      alert("加入购物车成功");
    },
    buy: function(){
      var that = this;
      var data = {
        totalAmount: that.goods.price,
        orderGoodsList: [{
          goodsId: that.goods.id,
          goodsName: that.goods.goodsName,
          price: that.goods.price,
          picUrl: that.goods.picUrl,
          buyNum: 1
        }]
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
    var user = localStorage.getItem("fuser");
    if(user){
        this.user = JSON.parse(user);
    }else{
        
    }

    var id = this.$route.query.id;
    this.getGoods(id)
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