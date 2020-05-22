<template>
<div class="container">
      <div class="row">
      <div class="col-sm-12 col-md-12 mrgb-30">
        <div class="heading-all mrgb-20">
                  <div class="clearfix">
                        <h1>Your Information</h1>
                    </div>
                    <div class="hding-sept"></div>
                </div>
                
              <div class="panel-body">
<!--                 <div class="col-md-3"></div>
 -->               <div class="col-md-3">
                  <img src="/dummy.jpg"></div>
                  <div class="col-md-3">
                 <table class="table table-striped">
                  <tbody>
                    <tr class="table-primary"><td>Username</td><td>{{account.username}}</td></tr>
                    <tr class="table-primary"><td>First Name</td><td>{{account.firstName}}</td></tr>
                    <tr class="table-primary"><td>Last Name</td><td>{{account.lastName}}</td></tr>
                    <tr class="table-primary"><td>Email</td><td>{{account.email}}</td></tr>
                    <tr class="table-primary"><td>Address</td><td>{{account.address}}</td></tr>
                    
                    <tr class="table-primary"><td>Phone Number</td><td>{{account.phoneNumber}}</td></tr>
                    <tr class="table-primary"><td></td><td><router-link to="/edit-account">Edit Info</router-link></td></tr>

                    </tbody> 
                   </table>
                  
                   </div>
           </div>
        </div>
       </div>
     </div>
</template>
<script type="text/javascript">

export default {
    name: 'Account',
    props: {
    msg: String
  },
  data(){

    return {
    
      account:{}
    }
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
                                
                                this.account= resp;
                              }

                        })


       

    }
        
    
  }

}

</script>