<template>
    <div class="container container-height">
        <div class="col-lg-12">
          <div class="row">
            <RestaurentNav/>
            <div class="form-group">
              <router-link class="btn btn-primary" to="/product-management">Back</router-link>
            </div>
            <div class="">
              <form @submit="doFoodSubmit">
                <p v-if="errors.length" class="text-danger">
                              <b>Please correct the following error(s):</b>
                              <ul>
                                <li v-for="error in errors"   :key="error.key">{{ error.value }}</li>
                              </ul>
                            </p>
                <div class="form-group">
                  <label>Food Name<Required/></label>
                  <input type="text" class="form-control" name="name" v-model="name" >
                </div>
                <div class="form-group">
                  <label>Unit Price<Required/></label>
                  <input type="number" class="form-control" name="price" v-model="price" >
                </div>
                <div class="form-group">
                  <label>Image<Required/></label>
                  <input type="text" class="form-control" name="imageUrl" v-model="imageUrl">
                </div>
                <div class="form-group">
                  <label>Description</label>
                  <textarea name="description" class="form-control" v-model="description" rows=4></textarea>
                </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-success">Save</button>
                </div>
              </form>
            </div>
          </div>
        </div>
    </div>
</template>
<script type="text/javascript">
  import RestaurentNav from './RestaurentNav';
  import Required from './Required'
export default {
  name: 'RestaurentProducts',
  props: {
    msg: String
  },
  components:{
    RestaurentNav,
    Required
  },
  data(){

    return {
      name:null,
      price:null,
      imageUrl:null,
      description:null,
      errors:[]
    }
  },
  methods:{
    doFoodSubmit:function(e){

      this.errors = [];
              var i = 0;
              if (!this.name) {
                this.errors.push({
                    key:i++,
                    value:'Food Name field is required.'
                });
              }
              if (!this.price) {
                this.errors.push({
                    key:i++,
                    value:'Price field is required.'
                });
              }
              if (!this.imageUrl) {
                this.errors.push({
                    key:i++,
                    value:'Image Url field is required.'
                });
              }

              if (this.errors.length == 0) {
                  console.log('this.$route.params.foodId',this.$route.params.foodId)
                    var formData = new FormData();
                        formData.append('name', this.name);
                        formData.append('price', this.price);
                        formData.append('imageUrl', this.imageUrl);
                        formData.append('restaurantId', this.helper.getUserInfo().id);

                        this.helper.request({
                              type: 'post',
                              withData:'json',
                              url: this.api.getRestaurentAddFoodApi(),
                              dataType:'json',
                              auth:false,
                              data: formData,
                              complete:()=>{
                                this.helper.showMessage('danger','Invalid Username or Password');
                              },
                              success:()=>{

                                this.helper.showMessage('success','Product has been added successfully.');
                                this.name = null;
                                this.price = null;
                                this.imageUrl = null;
                                this.description = null;

                              }

                        })
              }

      e.preventDefault();
    }
  },
  mounted(){

        
    
  }
}
</script>