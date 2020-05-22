<template>
  <header>
            <div class="hd-top">
                <div class="container">
                    <div class="hdtop-left pull-left">
                        <p>Welcome <a href="#">Guest !</a></p>
                        <p><i class="fa fa-phone"></i>Call to order 099-412035 / 9809496968</p>
                    </div>
                    <ul class="pull-right top-right-new">
                       <div class="btn-group">
                          <li v-if="checkLogin() == false">
                             <router-link to="/login">Login</router-link>
                          </li>
                          <li v-if="checkLogin() == false">
                             <a href ="" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                             Register
                             </a>
                             <div class="dropdown-menu">
                                <router-link class="dropdown-item" to="/signin/user">
                                    <span class="wrap-word"><i class="fa fa-user-plus" aria-hidden="true"></i> Register As User</span>
                                </router-link>
                                <div class="dropdown-divider"></div>
                                <router-link class="dropdown-item" to="/signin/restaurent">
                                    <span class="wrap-word"><i class="fa fa-cutlery" aria-hidden="true"></i> Register As Restaurent</span>
                                </router-link> 
                                <div class="dropdown-divider"></div>
                                <router-link class="dropdown-item" to="/signin/driver">
                                    <span class="wrap-word"><i class="fa fa-car" aria-hidden="true"></i> Register As Driver</span>
                                </router-link>
                             </div>
                          </li>
                          <li v-if="checkLogin()">
                             <a href ="" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                             Hello {{getUserName()}} ({{helper.getUserInfo().role}})
                             </a>
                             <div class="dropdown-menu">
                                <router-link class="dropdown-item" to="/profile">
                                    <span class="wrap-word"><i class="fa fa-user" aria-hidden="true"></i> Your Profile</span>
                                </router-link>
                                <div class="dropdown-divider"></div>
                                <router-link v-if="helper.userRole.user == helper.getUserInfo().role" class="dropdown-item" to="/order-history">
                                    <span class="wrap-word"><i class="fa fa-list" aria-hidden="true"></i> Your Order History</span>
                                </router-link>
                                <div class="dropdown-divider"></div>
                                <router-link class="dropdown-item" to="/passwordchange">
                                    <span class="wrap-word"><i class="fa fa-lock" aria-hidden="true"></i> Change your password</span>
                                </router-link>
                                <a href="" v-on:click="doLogout()" class="dropdown-item">
                                    <span class="wrap-word"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</span>
                                </a> 
                             </div>
                          </li>
                       </div>
                    </ul>

                </div><!-- container end -->
            </div>
            <div class="hd-bottom" style="margin-bottom: 15px">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-8 col-sm-3 col-sm-3">
                            <div class="logo">

                                <router-link :to="getHomeLink()"><img src="/logo.png" alt="logo"></router-link>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="hd-search">
                                <form method="post" action="#">
                                    <input type="text" placeholder="Search what you are looking for?">
                                    <button type="submit"><i class="fa fa-search"></i></button>
                                </form>
                            </div>
                        </div>
                        <div class="col-sm-3 col-md-2">

                          <router-link v-if="getCustomerRole()" to="/cart-details" class="hd-cart pull-right">

                                <span class="text">CART</span>
                                <span class="cart-count">{{countCart}}</span>

                          </router-link>
                        </div>
                    </div><!-- row end -->
                </div>
            </div>
            <!-- <div class="container">
                <div class="row">
                    <div class="col-sm-12 col-md-1 mrgb-30"></div>
                    <div class="col-sm-12 col-md-10 mrgb-30"></div>
                    <div class="wsmenucontent overlapblackbg"></div>
                    <div class="wsmenuexpandermain slideRight">
                        <a id="navToggle" class="animated-arrow slideLeft"><span></span></a>
                    </div>
                </div>
            </div> -->
        </header>
</template>
<script type="text/javascript">
  export default{
    data(){
      var getCart = [];
      if(this.checkLogin()){
          getCart = this.helper.getCart();
      }
      return {
        countCart:getCart.length
      }
    },
    methods:{
      doLogout:function(){
        this.helper.unsetUserInfo();
        this.helper.showMessage('success','Logged out successfully.');
        setTimeout(function(){ 
                                      window.location.href = "/login";
        }, 1000);
      },
      checkLogin:function(){
        if(this.helper.getUserInfo().userToken == ''){
          return false;
        }else{
          return true;
        }
      },
      getUserName:function(){
          if(this.helper.getUserInfo().userToken == ''){
            this.helper.showMessage('success','Session time out.');
              setTimeout(function(){ 
                window.location.href = "/login";
              }, 1000);
          }else{
            return this.helper.getUserInfo().username;
          }
      },
      getCustomerRole:function(){
          if(this.helper.getUserInfo().userToken == ''){
            return true;
          }else{
            if(this.helper.getUserInfo().role == this.helper.userRole.user){
              return true;
            }
          }
          return false;
      },
      getHomeLink:function(){
        if(this.helper.getUserInfo().userToken == ''){
            return '/login';
        }else if(this.helper.getUserInfo().role == this.helper.userRole.user){
          return '/';
        }else if(this.helper.getUserInfo().role == this.helper.userRole.restaurant){
          return '/order/new';
        }else{
          return '/driver-dashboard';
        }
      }
    }
  }
</script>