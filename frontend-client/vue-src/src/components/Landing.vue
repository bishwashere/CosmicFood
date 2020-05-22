<template>
    <div class="container container-height">
        <div class="row">
          <div class="col-sm-4" v-for="(restaurent, index) in restaurents" :key="index">
                <router-link :to="{path: '/restautent-foods/' + restaurent.id}" >
                <div class="grid-item">
                              <div class="p-item mrgb-30">
                                <div class="">
                                        <img alt="3" src="/dummy-res.jpg">
                                      </div>
                                  <div class="p-item-info">
                                      <div class="price-tag">
                                        <h3 class="font-24 active-text">{{restaurent.firstName}}</h3>
                                      </div>
                                  </div>
                              </div>
                          </div>
                        </router-link>
                </div>
        </div>
    </div>
</template>
<script type="text/javascript">
export default {
  name: 'Landing',
  props: {
    msg: String
  },
  data(){
    
    return {
      restaurents:[]
    }
  },
  mounted(){
    if(this.helper.getUserInfo().username == ''){
            this.helper.unsetUserInfo();
            this.$router.push('/login');
    }else{
      
        if(this.helper.getUserInfo().role == this.helper.userRole.user){

                        this.helper.request({
                              type: 'get',
                              withData:'json',
                              url: this.api.getAllRestaurentApi(),
                              dataType:'json',
                              complete:()=>{
                              },
                              success:(resp)=>{
                                this.restaurents = resp;
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