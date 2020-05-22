<template>
    <div class="container container-height">
        <div class="col-lg-12">
          <div class="row">
            <RestaurentNav/>
            <div class="form-group">
              <router-link class="btn btn-success" to="/restaurent-add-food">Add New Food</router-link>
            </div>
            <div class="">
              <table cellspacing="0" id="tech-companies-1" class="table table-small-font table-bordered table-striped">
                <thead>
                  <tr>
                    <th>Food Name</th>
                    <th>Unit Price</th>
                    <th>Image</th>
                    <th>Action</th> 
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(food, index) in foods" :key="index">
                    <td>{{food.name}}</td>
                    <td>${{food.price}}</td>
                    <td><img height="120" width="120" v-bind:alt="food.name" :src="food.imageUrl"></td>
                    <td width="120">
                      <!-- <a href="" class="btn btn-xs btn-primary">Edit</a> -->
                      <button v-on:click="deleteFood(food.id,$event)" class="btn btn-xs btn-danger">Delete</button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
    </div>
</template>
<script type="text/javascript">
  import RestaurentNav from './RestaurentNav';
export default {
  name: 'RestaurentProducts',
  props: {
    msg: String
  },
  components:{
    RestaurentNav
  },
  data(){
    var apiFoods = []
    return {
      foods:apiFoods,
      resId:null
    }
  },
  methods:{
  
    deleteFood:function(id,e)
    {

            this.helper.request({
                  method: 'post',
                  withData:'json',
                  url: this.api.getDeleteFoodApi()+'/'+id,
                  dataType:'json',


                  
                  success:()=>{
                    this.helper.showMessage('success','Sucessfully deleted food');
                    this.helper.request({
                              type: 'get',
                              withData:'json',
                              auth:false,
                              url: this.api.getRestaurentFoods()+'/'+this.resId,
                              dataType:'json',
                              complete:()=>{
                              },
                              success:(resp)=>{

                                this.foods = resp;
                                
                              }

                        })
                  },
                  error:()=>{
                    this.helper.showMessage('error','Sorry, Please try again!!!');
                      this.$router.push('/product-management');
                  }

            })
            e.preventDefault();
    }
  },
  mounted(){

    if(this.helper.getUserInfo().username == ''){
            this.helper.unsetUserInfo();
            this.$router.push('/login');
    }else{

        if(this.helper.getUserInfo().role == this.helper.userRole.user || this.helper.getUserInfo().role == this.helper.userRole.restaurant){

                        this.resId = this.helper.getUserInfo().id;
                        // var resId = 12;
                        this.helper.request({
                              type: 'get',
                              withData:'json',
                              auth:false,
                              url: this.api.getRestaurentFoods()+'/'+this.resId,
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