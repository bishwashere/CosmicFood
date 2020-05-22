<template>
<div class="container">
      <div class="row">
      <div class="col-sm-12 col-md-12 mrgb-30">
                <div class="heading-all mrgb-20">
                  <div class="clearfix">
                        <h1>Register</h1>
                    </div>
                    <div class="hding-sept"></div>
                </div>
                <form method="post" action="./">
                    <div class="row">
                      <div class="col-sm-6 col-md-6">
                        <div class="clearfix mrgb-30">
                          <h3 class="mrgb-20">Forgot Password</h3>                            
                            <label for="email">Email</label>
                            <input type="text" name="email" class="form-control mrgt-10 mrgb-10"/>
                          <hr/>
                          <button class="btn-all pull-right">Submit</button>
                        </div>
                      </div>
                    </div><!-- row end -->   
                </form>
           </div>
        </div>
       </div>
</template>
<script type="text/javascript">
export default {
    name: 'ForgotPassword',
    props: {
        msg: String
    }
}
</script>