<template>
	<footer>
            <div class="foot-top">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4 col-md-3 mrgb-30">
                            <h3>Information</h3>
                            <ul>
                                <li>
                                    <router-link to="/cms/about">About</router-link>
                                </li>
                                <li>
                                    <router-link to="/cms/delivary-info">Delivery Information</router-link>
                                </li>
                                <li>
                                    <router-link to="/cms/privacy-policy">Privacy and Policy</router-link>
                                </li> 
                                <li>
                                    <router-link to="/contact-us">Contact Us</router-link>
                                </li>
                                <!-- <li><a href="#">FAQ’s</a></li>
                                <li><a href="#">Blog</a></li> -->
                            </ul>
                        </div>                      
                        <div class="col-sm-4 col-md-3 mrgb-30">
                            <h3>Customer Service</h3>
                            <ul>
                                <li><a href="#">Returns</a></li>
                                <li><a href="#">Site Map</a></li>
                                <li><a href="#">Order History</a></li> 
                                <li><a href="#">Other Services</a></li>
                                <li><a href="#">Sitemap</a></li>
                                <li><a href="#">Topography</a></li>
                            </ul>
                        </div>                      
                        <div class="col-sm-4 col-md-3 mrgb-30">
                            <h3>My Account</h3>
                            <ul>
                                <li><router-link to="/profile">My Account</router-link></li>
                                <li><router-link to="/login">Signin</router-link></li>
                                <li><router-link to="">Signin</router-link></li> 
                                <li><router-link to="">Cart</router-link></li>
                                <li><router-link to="">Checkout</router-link></li>
                                <li><router-link to="">Wishlist</router-link></li>
                            </ul>
                        </div>                      
                        <div class="col-sm-12 col-md-3 mrgb-30">
                            <h3>Contact</h3>
                            <address class="mrgb-20">
                                <div class="foot-continfo mrgb-20">
                                    <div class="icon-cont">
                                        <i class="fa fa-paper-plane"></i>
                                    </div>
                                    <div class="add-info">
                                        ur business address is 1063 Free ype and scrambled it to make
                                    </div>    
                                </div>
                                <div class="foot-continfo mrgb-20">
                                    <div class="icon-cont">
                                        <i class="fa fa-phone-square"></i>
                                    </div>
                                    <div class="add-info">
                                        + 977- 010000000
                                    </div>   
                                </div>
                                <div class="foot-continfo mrgb-20">
                                    <div class="icon-cont">
                                        <i class="fa fa-envelope"></i>
                                    </div>    
                                    <div class="add-info">
                                        support@snapb.com
                                    </div>    
                                </div>
                            </address>                          
                            <ul class="social-link">
                                <li><a href="#" target="_blank"><img src="/social-images/fb.png" alt="fb"></a></li>
                                <li><a href="#" target="_blank"><img src="/social-images/youtube.png" alt="youtube"></a></li>
                                <li><a href="#" target="_blank"><img src="/social-images/gplus.png" alt="gplus"></a></li>
                                <li><a href="#" target="_blank"><img src="/social-images/instagram.png" alt="instagram"></a></li>
                            </ul>
                        </div>
                        
                    </div>
                </div>
            </div><!-- footer top end -->           
            <div class="foot-btm">
                <div class="container">
                    <p>© 2019 LockDownFood.com | All Right Reserved.</p>
                </div>
            </div>
            <div class="global-message">
                <h3 class="success-heading">Success</h3>
                <h3 class="error-heading">Error</h3>
                <p class="message-box"></p>
            </div>
            
        </footer><!-- footer end -->
</template>
<script type="text/javascript">
	export default{
		name:'Footer',
	}
</script>