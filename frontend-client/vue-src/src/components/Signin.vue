<template>
<div class="container">
      <div class="row">
      <div class="col-sm-12 col-md-12 mrgb-30">
        <div class="heading-all mrgb-20">
                  <div class="clearfix">
                        <h1>{{label}}</h1>
                    </div>
                    <div class="hding-sept"></div>
                </div>
                <form @submit="checkForm" method="post" action="./">
                     
                      <div class="row">
                      <div class="col-sm-6 col-md-6">
                        <div class="clearfix mrgb-30">
                          <h3 class="mrgb-20">{{label2}}</h3> 
                            <p v-if="errors.length" class="text-danger">
                              <b>Please correct the following error(s):</b>
                              <ul>
                                <li v-for="error in errors"   :key="error.key">{{ error.value }}</li>
                              </ul>
                            </p> 
                            <div v-if="role == 'CUSTOMER' || role=='DRIVER'">                        
                            <label >First Name<Required/></label>
                            <input type="text" v-model="firstName" name="firstName" class="form-control mrgt-10 mrgb-10"/>
                            
                            <label >Last Name<Required/></label>
                            <input  type="text" v-model="lastName"  name="lastName" class="form-control mrgt-10 mrgb-10"/>
                            </div>
                            <div v-if="role=='RESTAURANT'"> 
                              <label >Restaurent Name<Required/></label>
                            <input type="text" v-model="firstName" name="firstName" class="form-control mrgt-10 mrgb-10"/>
                          </div>
                            <label>Email<Required/></label>
                            <input type="email" v-model="email" name="email" class="form-control mrgt-10 mrgb-10"/>
                            
                            <label>Phone Number<Required/></label>
                            <input type="text"  v-model="phoneNumber"  name="phoneNumber" class="form-control mrgt-10 mrgb-10"/>
                          
                          <hr/>
                          <label>Username<Required/></label>
                            <input type="text"  v-model="username"  name="username" class="form-control mrgt-10 mrgb-10"/>
                          <label>Password<Required/></label>
                          <input type="password"  v-model="password" name="password" class="form-control mrgt-10 mrgb-10"/>
                          
                          <label>Password Confirm<Required/></label>
                          <input type="password"   v-model="confirmPassword" name="confirmPassword" class="form-control mrgt-10 mrgb-10"/>
                          
                          <hr/>
                          <button class="btn-all pull-right">Register</button>
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
    name: 'Signup',
    components:{
      Required
    },
    props:{

    },
    watch:{
        $route (to){
          this.role = this.getRole(to);
          this.label = this.getLabel(this.role);
          this.label2 = this.getLabel2(this.role);

        }
    },
    data() {
        var role = this.getRole(this.$route)
        return {
          errors:[],
          firstName:null,
          lastName:null,
          email:null,
          username:null,
          phoneNumber:null,
          password:null,
          confirmPassword:null,
          role:role,
          label:this.getLabel(role),
          label2:this.getLabel2(role),
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
    getLabel:function(role){
      return ((role == 'CUSTOMER')?'Personal Account Registration':((role == 'DRIVER')?'Driver Account Registration':'Restaurant Account Registration'));
    },
    getLabel2:function(role){
      return ((role == 'CUSTOMER')?'Your Personal Information':((role == 'DRIVER')?'Your Personal Information':'Restaurant Information'));
    },
    checkForm: function (e) {
      console.log(this.role);
      this.errors = [];
      var i = 0;

      if (!this.firstName) {
        this.errors.push({
            key:i++,
            value:'First name is required.'
        });
      } 
if(!this.role=="RESTAURANT"){
      if (!this.lastName) {
        this.errors.push({
            key:i++,
            value:'Last name is required.'
        });
      }
    }
      if (!this.email) {
        this.errors.push({
            key:i++,
            value:'Email is required.'
        });
      }
      if (!this.username) {
        this.errors.push({
            key:i++,
            value:'Username is required.'
        });
      }
      if (!this.password) {
        this.errors.push({
            key:i++,
            value:'Password is required.'
        });
      }
      if (!this.confirmPassword) {
        this.errors.push({
            key:i++,
            value:'Confirm Password is required.'
        });
      }
      if (this.password != this.confirmPassword) {
        this.errors.push({
            key:i++,
            value:'Conform password does not match with password.'
        });
      }

      if (this.errors.length == 0) {
            //DRIVER, RESTAURANT, CUSTOMER, ADMIN
            var formData = new FormData();
            formData.append('firstName', this.firstName);
            console.log(this.role); 
            if(this.role=="RESTAURANT") formData.append('lastName', this.firstName);
            formData.append('lastName', this.lastName);
            formData.append('email', this.email);
            formData.append('phoneNumber', this.phoneNumber);
            formData.append('username', this.username);
            formData.append('password', this.password);
            formData.append('role', this.role);
            formData.append('confirmPassword', this.confirmPassword);

            this.helper.request({
                  method: 'post',
                  withData:'json',
                  auth:false,
                  url: this.api.getRegisterApi(),
                  dataType:'json',
                  data: formData,
                  success:()=>{
                    this.helper.showMessage('success','Login successfully. Please login.');
                    this.$router.push('/login');
                  },
                  error:()=>{
                      alert('request not completed.');
                  }

            })
      }

      
      e.preventDefault();
    },
    
}
}
</script>