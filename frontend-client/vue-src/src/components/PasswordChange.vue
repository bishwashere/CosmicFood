<template>
  <div class="container">
      <div class="row">
      <div class="col-sm-12 col-md-12 mrgb-30">
        <div class="heading-all mrgb-20">
                  <div class="clearfix">
                        <h1>Change Your Password</h1>
                    </div>
                    <div class="hding-sept"></div>
                </div>
                <form @submit="beforeSubmit" method="post" action="./">
                     <p v-if="errors.length" class="text-danger">
                        <b>Please correct the following error(s):</b>
                        <ul>
                          <li v-for="error in errors"   :key="error.key">{{ error.value }}</li>
                        </ul>
                      </p>
                      <div class="row">
                      <div class="col-sm-6 col-md-6">
                        <div class="clearfix mrgb-30">                            
                            <label for="oldPassword">Old Password<Required/></label>
                            <input type="password" v-model="oldPassword" name="oldPassword" class="form-control mrgt-10 mrgb-10"/>
                          
                            <label for="newPassword">New Password<Required/></label>
                            <input type="password" v-model="newPassword"  name="newPassword" class="form-control mrgt-10 mrgb-10"/>

                            <label for="conformNewPassword">Confirm New Password<Required/></label>
                            <input type="password" v-model="conformNewPassword"  name="conformNewPassword" class="form-control mrgt-10 mrgb-10"/>
                            
                          <hr/>
                          <button class="btn-all pull-right">Change</button>
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
  export default{
    name:'PasswordChange',
    components:{
      Required
    },
    data(){
      return {
          oldPassword:null,
          newPassword:null,
          conformNewPassword:null,
          errors:[]
      }
    },
    methods:{
      beforeSubmit:(e)=>{
        alert('To Do');
        e.preventDefault();
      }
    }
  }
</script>