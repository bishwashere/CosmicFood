<template>
    <div class="container container-height">
        <div class="row">
          <div class="col-sm-4" v-for="(food, index) in foods" :key="index">
            
              <div class="grid-item">
                        <div class="p-item mrgb-30">
                          <div class="">
                                  <router-link :to="{path: '/foods/' + food.id}" >
                                  <!-- <img alt="3" src="/dummy-food.jpg"> -->
                                  <img height="300" width="100%" alt="3" :src="food.imageUrl">
                                </router-link>
                                </div>
                            <div class="p-item-info">
                                <div class="price-tag">
                                  
                                    <h3 class="font-24 active-text">${{food.price}}</h3>
                                  
                                    <!-- <span class="cut-price">$690.00</span> -->
                                </div>
                                <router-link :to="{path: '/foods/' + food.id}" >
                                <h2>{{food.name}}</h2>
                                </router-link>
                                <button class="btn-all" v-on:click="addToCart(food.id,1,food)" >Add to Cart</button>
                            </div>
                        </div>
                    </div>
          </div>
        </div>
    </div>
</template>
<script type="text/javascript">
export default {
  name: 'RestaurentProducts',
  props: {
    msg: String
  },
  data(){
    var apiFoods = []
    return {
      foods:apiFoods
    }
  },
  methods:{
    addToCart:function(id,quantity,food){
      food.quantity = quantity;
      this.helper.addToCart(id,food);
    }
  },
  mounted(){

    if(this.helper.getUserInfo().username == ''){
            this.helper.unsetUserInfo();
            this.$router.push('/login');
    }else{

        if(this.helper.getUserInfo().role == this.helper.userRole.user || this.helper.getUserInfo().role == this.helper.userRole.restaurant){
                        var resId = this.$route.params.id;
                        //var resId = 12;
                        this.helper.request({
                              type: 'get',
                              withData:'json',
                              auth:false,
                              url: this.api.getRestaurentFoods()+'/'+resId,
                              dataType:'json',
                              complete:()=>{
                              },
                              success:(resp)=>{

                                this.foods = resp;
                              }

                        })


        }else{
           this.helper.unsetUserInfo();
           this.$router.push('/login');
        }

    }
        
    
  }
}
</script>