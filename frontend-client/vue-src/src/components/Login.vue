<template>
    <div>
        <div class="container container-height">
              <div class="row" >
                <div class="col-sm-12 col-md-12 mrgb-30">
                    <div class="heading-all mrgb-20">
                          <div class="clearfix">
                                <h1>Login</h1>
                            </div>
                            <div class="hding-sept"></div>
                        </div>
                        <div class="row">
                        <div class="col-sm-6 col-md-6">
                          <div class="clearfix mrgb-30">
                            <h3 class="mrgb-10">Login</h3>
                            <p v-if="errors.length" class="text-danger">
                              <b>Please correct the following error(s):</b>
                              <ul>
                                <li v-for="error in errors"   :key="error.key">{{ error.value }}</li>
                              </ul>
                            </p>
                            <form method="post" @submit="doSignup" action="./">
                              <span >Login Id or email address</span>
                              <input type="text" v-model="username"  name="username" class="form-control mrgt-10 mrgb-10"/>
                              <span class="mrgb-20">Password</span>
                              <input type="password" v-model="password"  name="password" class="form-control mrgt-10 mrgb-10" />
                              <span>
                                <router-link class="font-14 active-text" to="/forgot-password">Forget Password</router-link>
                            </span>
                              <div class="btn-wrap">
                                <input type="submit" name="btnsubmit" value="login" class="btn-all mrgt-10 mrgb-10 pull-right"/>
                              </div>
                            </form>
                          </div><!--right closed-->
                      </div>
                   </div><!-- row end -->  
               </div>
          </div>
          <router-view/>
        </div>
    </div>
</template>
<script>
    export default {
        name:'Login',
        data(){

           return {
              errors:[],
              username:null,
              password:null
            }
        },
        methods:{

          doSignup:function(e){
              this.errors = [];
              var i = 0;
              if (!this.username) {
                this.errors.push({
                    key:i++,
                    value:'Username is required'
                });
              }
              if (!this.password) {
                this.errors.push({
                    key:i++,
                    value:'Password is required'
                });
              }
              if (this.errors.length == 0) {

                    var formData = new FormData();
                        formData.append('username', this.username);
                        formData.append('password', this.password);

                        this.helper.request({
                              type: 'post',
                              withData:'json',
                              url: this.api.getLoginApi(),
                              dataType:'json',
                              auth:false,
                              data: formData,
                              complete:()=>{
                                this.helper.showMessage('danger','Invalid Username or Password');
                              },
                              success:(resp)=>{
                                this.helper.request({
                                type: 'get',
                                withData:'json',
                                url: this.api.getUserInfoByUsernameApi()+'/'+this.username,
                                dataType:'json',
                                complete:()=>{
                                  this.helper.showMessage('danger','Error found');
                                },
                                success:(resp2)=>{

                                  this.helper.showMessage('success','Login successfully.');
                                  resp2.username = this.username;
                                  resp2.userToken = resp.jwt;
                                  this.helper.setUserInfo(resp2);

                                  setTimeout(()=>{ 
                                      if(resp2.role == this.helper.userRole.restaurant){

                                        //this.$router.push('/order/new');
                                        window.location.href = "/order/new";
                                      }else if(resp2.role == this.helper.userRole.driver){
                                        //this.$router.push('/driver-dashboard');
                                        window.location.href = '/driver-dashboard';
                                      }else{
                                        window.location.href = "/";
                                      }
                                      
                                   }, 5000);
                                  
                                }

                        })

                              }

                        })
              }
              e.preventDefault();
          }
        }
    }
</script>