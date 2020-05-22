<template>
    <div class="container container-height">
        <div class="col-lg-12">
        <div class="row">
          <RestaurentNav/>
          <table cellspacing="0" id="tech-companies-1" class="table table-small-font table-bordered table-striped">
                        <thead>
                          <tr>
                            <th>Order Items</th>
                            <th>Order Date</th>
                            <th>Customer name</th>
                            <th>Price</th>
                            <th>Action</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="(order,index) in orders" :key="index">
                            <td>{{getItemNames(order)}}</td>
                            <td>09/23/2019</td>
                            <td>{{getUserName(order)}}</td>
                            <td>USD {{getTotalPrice(order)}}</td>
                            <td v-if="order.orderState == 'ACCEPTED'">
                              <span class="text-success">Accepted</span>
                              &nbsp;<a href="" v-on:click="rejectOrder(order,$event)" class="btn btn-xs btn-danger">Reject</a>
                            </td>
                            <td v-if="order.orderState == 'PENDING'">
                              <a href="" v-on:click="acceptOrder(order,$event)" class="btn btn-xs btn-success">Accept</a>
                              &nbsp;<a href="" v-on:click="rejectOrder(order,$event)" class="btn btn-xs btn-danger">Reject</a>
                            </td>
                          </tr>
                        </tbody>
                      </table>
        </div>
        </div>
    </div>
</template>
<script type="text/javascript">
  import RestaurentNav from './RestaurentNav';
export default {
  name: 'RestaurentOrderList',
  props: {
    msg: String
  },
  components:{
    RestaurentNav
  },
  data(){
    return {
      orders:[],
      foods:[],
      users:[],
      orderType:this.$route.params.type
    }
  },
  watch:{
        $route (to){
          this.orderType = to.params.type;
          this.loadData();
        }
  },
  methods:{
    loadData(){
              if(this.helper.getUserInfo().username == ''){
                      this.helper.unsetUserInfo();
                      this.$router.push('/login');
              }else{
                
                  if(this.helper.getUserInfo().role == this.helper.userRole.restaurant){
                        // var resId = 3;
                        var apiUrl = '';
                        var resId = this.helper.getUserInfo().id;
                        if(this.orderType == 'new'){
                          apiUrl = this.api.getRestaurentPendingOrderApi()+'/'+resId;
                        }else if(this.orderType == 'active'){
                          apiUrl = this.api.getRestaurentActiveOrderApi()+'/'+resId;
                        }else{
                          apiUrl = this.api.getRestaurentRejectedOrderApi()+'/'+resId;
                        }
                        this.helper.request({
                              type: 'get',
                              withData:'json',
                              auth:false,
                              url: apiUrl,
                              dataType:'json',
                              complete:()=>{
                              },
                              success:(resp)=>{
                                this.orders = [];
                                this.getAllFoods(resp);
                              }

                        })


                  }else{
                     this.helper.unsetUserInfo();
                     this.$router.push('/login');
                  }
                  
              }
    },
    getAllFoods(newOrders){

        var foodIds = [];
        var userIds = [];
        var foodCnt = 0;
        var userCnt = 0;
        if(newOrders.length > 0){
          newOrders.map((order)=>{
            userIds[userCnt++] = order.customerId;
            if(order.foods){
                if(order.foods.length > 0){
                    order.foods.map((food)=>{
                      foodIds[foodCnt++] = food.foodId;
                    })
                }
            }                          
          });
        }
        this.helper.request({
            type: 'post',
            withData:'json',
            auth:false,
            withNotFormData:true,
            url: this.api.getAllFoodsByIdsApi(),
            data:foodIds,
            dataType:'json',
            complete:()=>{
            },
            success:(resp)=>{
              this.foods = resp;
              this.getAllUsers(userIds,newOrders)              
            }
        })

    },
    getAllUsers(userIds,orders){

        this.helper.request({
            type: 'post',
            withData:'json',
            auth:false,
            withNotFormData:true,
            url: this.api.getAllUsersByIdsApi(),
            data:userIds,
            dataType:'json',
            complete:()=>{
            },
            success:(resp2)=>{
              this.users = resp2;
              this.orders = orders;              
            }
        })
        
    },
    getItemNames(order){
        var foodNames = [];
        var cnt = 0;
        if(order.foods){
            if(order.foods.length > 0){
                order.foods.map((ofood)=>{
                  this.foods.map((food)=>{
                      if(food.id == ofood.foodId){
                        foodNames[cnt++] = food.name;
                      }
                  })
                  
                })
            }
        }
        return foodNames.join(', ');
    },
    getUserName(order){
      var customerName = '';
        if(order.customerId){
          this.users.map((user)=>{
              if(user.id == order.customerId){
                customerName = user.firstName;
              }
          })
        }
      return customerName;
    },
    getTotalPrice(order){
        var total_price = 0;
        if(order.foods){
            if(order.foods.length > 0){
                order.foods.map((ofood)=>{
                  this.foods.map((food)=>{
                      if(food.id == ofood.foodId){
                        total_price += food.price*ofood.quantity;
                      }
                  })
                  
                })
            }
        }
        return total_price;
    },
    acceptOrder(order,e){
            

            this.helper.request({
                type: 'get',
                withData:'json',
                auth:false,
                url: this.api.getRestaurentOrderAcceptApi()+'/'+order.id,
                dataType:'json',
                complete:()=>{
                },
                success:()=>{
                  this.helper.showMessage('success','You accepted the order.');

                  window.$(e.target).text('Accepted');
                  window.$(e.target).css({
                    'background': 'rgba(255, 255, 255, 0)',
                    'border': 'rgba(255, 255, 255, 0)',
                    'color': '#5cb85c'
                  });


                  setTimeout(()=>{ 
                      this.loadData();
                  }, 900);
                  
                  
                }
            })
            e.preventDefault();
    },
    rejectOrder(order,e){
            this.helper.request({
                type: 'get',
                withData:'json',
                auth:false,
                url: this.api.getRestaurentOrderRejectApi()+'/'+order.id,
                dataType:'json',
                complete:()=>{
                },
                success:()=>{
                  console.log('e.target',e.target);
                  this.helper.showMessage('success','You rejected the order.');
                  window.$(e.target).text('Rejected');
                  window.$(e.target).css({
                    'background': 'rgba(255, 255, 255, 0)',
                    'border': 'rgba(255, 255, 255, 0)',
                    'color': '#b85c5c'
                  });
                  setTimeout(()=>{ 
                      this.loadData();
                  }, 900);
                  
                }
            })
            e.preventDefault();
    }
  },
  mounted(){
    this.loadData();
  }
}
</script>