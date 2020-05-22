<template>
    <div class="container container-height">
        <div class="row">
    <div class="row">
                  <div class="col-sm-4 col-md-4">
                        <div class="zoomwrap mrgb-30">
                            <ul id="glasscase" class="gc-start">
                              <li><img :src="food.imageUrl" alt="single-shop-02" /></li>
                              
                            </ul>
                         </div> 
                     </div> 
                     
                     <div class="col-sm-8 col-md-8">
                      
                        <div class="mrgb-10 clearfix">
                            <h2>{{food.name}}</h2>
                            <!-- <span class="light-gray">(XIAM 4W 16 )</span> -->
                            
                            <div class="rating clearfix">
                              
                                <ul class="pull-left">
                                  <li><i class="fa fa-star"></i></li>
                                    <li><i class="fa fa-star"></i></li>
                                    <li><i class="fa fa-star"></i></li>
                                    <li><i class="fa fa-star"></i></li>
                                    <li><i class="fa fa-star"></i></li>
                                </ul>
                                
                                <!-- <a href="#" class="write-link">
                                  Write a review
                                </a> -->
                                
                            </div>
                            
                        </div>
                        <hr/>
                        
                        <div class="mrgb-10 clearfix">
                            <!-- <span class="det-pricecut pull-left">Rs 24,000</span> -->
                            <span class="font-24 active-text regprice pull-left">${{food.price}}</span>
                            <!-- <span class="offtag pull-left">10% OFF</span> -->
                        </div>
                        
                        <hr/>
                        
                        <div class="mrgb-10 clearfix">
                          <div class="det-qty-wrap pull-left">
                                <label>Quantity</label> 
                                <div class="det-qty pull-left">
                                    <input class="qtyfield qtyfield0" type="text" v-model="quantity">
                                    <button item_no="0" class="qtyminus" v-on:click="quantity -= 1"><i class="fa fa-minus"></i></button>
                                    <button item_no="0" class="qtyplus" v-on:click="quantity += 1"><i class="fa fa-plus"></i></button>
                                 </div>
                                <span class="stocktag instock pull-left">In Stock</span>
                                <span class="remain-item">20 Items Left</span>
                            </div>
                            
                        </div>
                        
                        <hr/>
                        
                        
                        
                        <hr/>
                        
                        <div class="mrgb-10">
                          <button class="btn-cart" v-on:click="addToCart(food.id,quantity,food)"><i class="fa fa-shopping-basket"></i>Add to Cart</button>
                        </div>
                        <hr/>
                        
                        <div class="mrgb-30 mrgt-20 clearfix">
                          <span class='st_sharethis_large' displayText='ShareThis'></span>
                            <span class='st_facebook_large' displayText='Facebook'></span>
                            <span class='st_twitter_large' displayText='Tweet'></span>
                            <span class='st_googleplus_large' displayText='Google +'></span>
                            <span class='st_linkedin_large' displayText='LinkedIn'></span>
                            <span class='st_pinterest_large' displayText='Pinterest'></span>
                        </div>
                        
                        
                     </div>              
                </div>

                 <div id="content" class="mrgb-30">
                    <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                      <li class="active"><a href="#1" data-toggle="tab">Description</a></li>
                     
                      <li><a href="#3" data-toggle="tab">Reviews</a></li>
                    </ul>
                    
                    <div id="my-tab-content" class="tab-content">
                      <div class="tab-pane clearfix active" id="1">
                        <h3 class="mrgb-10">Product Detail</h3>
                        <p>Pellentesque habitant morbi tristique senectus et netus et malesuada
                          fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultric
                          eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas 
                          semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.
                         </p>
                         
                         
                      </div>
                      
                      
                      
                      <div class="tab-pane clearfix" id="3">
                        <h3 class="mrgb-10">Write a review</h3>
                        <form action="./" method="post">
                          
                          <div class="mrgb-10">
                            <label for="rName">Full Name</label>
                            <input type="text" id="rName" class="form-control" style="max-width:40%; min-width:220px;" />
                          </div>
                          
                          <div class="mrgb-10"> 
                            <label for="rCment">Write Your Review</label>
                            <textarea id="rName" class="form-control" style="height:180px;"></textarea>
                          </div>
                          
                          <div class="mrgb-10 clearfix"> 
                            <label>Rating </label>
                            
                            <div class="mrgb-20">
                            <fieldset class="rating_rvw">
                                <input type="radio" id="star5" name="rating" value="5" />
                                <label class = "full" for="star5" title="Awesome"></label>
                                
                                <input type="radio" id="star4" name="rating" value="4" />
                                <label class = "full" for="star4" title="Pretty good"></label>
                                
                                <input type="radio" id="star3" name="rating" value="3" />
                                <label class = "full" for="star3" title="Meh"></label>
                                
                                <input type="radio" id="star2" name="rating" value="2" />
                                <label class = "full" for="star2" title="Kinda bad "></label>
                                
                                <input type="radio" id="star1" name="rating" value="1" />
                                <label class = "full" for="star1" title="Sucks big time"></label>
                            </fieldset>
                            </div>
                            
                          </div>
                          
                          <div class="mrgb-10">
                            <label for="rChcode">Character code</label>
                            <input type="text" id="rChcode" class="form-control" style="max-width:40%; min-width:220px;" />
                          </div>
                          
                          <hr/>
                          
                          
                            <button type="submit" class="btn-all pull-right">Submit</button>
                         
                        </form>
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
    return {
      food:{
        name:null,
        price:0
        
      },
      quantity:1
    }
  },
  methods:{
    addToCart:function(id,quantity,food){
      console.log('id',id);
      console.log('food',food);
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
                        var id = this.$route.params.id;
                        this.helper.request({
                              type: 'get',
                              withData:'json',
                              auth:false,
                              url: this.api.getFoodDetailApi()+'/'+id,
                              dataType:'json',
                              complete:()=>{
                              },
                              success:(resp)=>{

                                this.food = resp;
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