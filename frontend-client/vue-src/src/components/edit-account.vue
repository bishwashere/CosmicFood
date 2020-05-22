<template>
<div class="container">
      <div class="row">
      <div class="col-sm-12 col-md-12 mrgb-30">
        <div class="heading-all mrgb-20">
                  <div class="clearfix">
                        <h1>Edit Account Info</h1>
                    </div>
                    <div class="hding-sept"></div>
                </div>
                <form @submit="checkForm" method="post" action="./">
                     
                      <div class="row">
                      <div class="col-sm-6 col-md-6">
                        <div class="clearfix mrgb-30">
                          
                            <p v-if="errors.length" class="text-danger">
                              <b>Please correct the following error(s):</b>
                              <ul>
                                <li v-for="error in errors"   :key="error.key">{{ error.value }}</li>
                              </ul>
                            </p>                         
                            <label >First Name<Required/></label>
                            <input type="text" v-model="profile.firstName" name="firstName" class="form-control mrgt-10 mrgb-10"  />
                          
                            <label>Last Name<Required/></label>
                            <input type="text" v-model="profile.lastName"  name="lastName" class="form-control mrgt-10 mrgb-10"/>
                            
                            <label>Email<Required/></label>
                            <input type="email" v-model="profile.email" name="email" class="form-control mrgt-10 mrgb-10"/>
                            
                            <label>Phone Number<Required/></label>
                            <input type="text"  v-model="profile.phoneNumber"  name="phoneNumber" class="form-control mrgt-10 mrgb-10"/>
                          
                          <hr/>
                          <button class="btn-all pull-right">Update</button>
                        </div>
                      </div>
                    </div><!-- row end -->   
                </form>
           </div>
        </div>
       </div>
</template>
<script type="text/javascript">
  import Required from './Required'
export default {
    //name: 'EditAccount',
    components:{
      Required
    },
    props:{

    },
    data() {
        // var role = this.getRole(this.$route)
        return {
          errors:[],
          profile:{},
        }
    },
    methods:{
    getRole:function(route){
      var utype = route.params.usertype;
      var userRole = this.helper.userRole.user;
        if(utype == 'restaurent'){
            userRole = this.helper.userRole.restaurant;
        }else if(utype == 'driver'){
            userRole = this.helper.userRole.driver;
        }
        return userRole;
    },
   
    checkForm: function (e) {

      console.log(this.role);
      this.errors = [];
      var i = 0;

      if (!this.profile.firstName) {
        this.errors.push({
            key:i++,
            value:'First name is required.'
        });
      }
      if (!this.profile.lastName) {
        this.errors.push({
            key:i++,
            value:'Last name is required.'
        });
      }
      if (!this.profile.email) {
        this.errors.push({
            key:i++,
            value:'Email is required.'
        });
      }
      if (this.errors.length == 0) {
            //DRIVER, RESTAURANT, CUSTOMER, ADMIN
            var formData = new FormData();
            formData.append('firstName', this.profile.firstName);
            formData.append('lastName', this.profile.lastName);
            formData.append('email', this.profile.email);
            formData.append('phoneNumber', this.profile.phoneNumber);
            // formData.append('userID', 2);

          

            this.helper.request({
                  method: 'post',
                  withData:'json',
                  url: this.api.getEditAccountApi()+'/'+this.profile.username,
                  dataType:'json',
                  data: formData,
                  success:()=>{
                    this.helper.showMessage('success','Sucessfully changed account info!!!');
                    this.$router.push('/profile');
                  },
                  error:()=>{
                    this.helper.showMessage('error','Sorry, Please try again!!!');
                      this.$router.push('/profile');
                  }

            })
      }

      
      e.preventDefault();
    },
    

  
},
mounted(){
    if(this.helper.getUserInfo().username == ''){
            this.helper.unsetUserInfo();
            this.$router.push('/login');
    }else{


                        this.helper.request({
                              type: 'get',
                              withData:'json',
                              url: this.api.getUserInfoByUsernameApi()+'/'+this.helper.getUserInfo().username,
                              dataType:'json',
                              complete:()=>{
                              },
                              success:(resp)=>{
                                this.profile= resp;
                                
                              }

                        })


       

    }
        
    
  }
}


</script>