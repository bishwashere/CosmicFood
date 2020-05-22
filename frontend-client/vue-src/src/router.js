import Vue from 'vue'
import Router from 'vue-router'
import Landing from './components/Landing.vue'
import Login from './components/Login.vue'
import Signin from './components/Signin.vue'
import ForgotPassword from './components/ForgotPassword.vue'
import Account from './components/Account.vue'
import PasswordChange from './components/PasswordChange.vue'
import Cms from './components/Cms.vue'
import RestaurentProducts from './components/RestaurentProducts.vue'
import FoodDetails from './components/FoodDetails.vue'
import EditAccount from './components/edit-account.vue'
import CartDetails from './components/CartDetails.vue'
import RestaurentOrderList from './components/RestaurentOrderList.vue'
import CantactUs from './components/contact-us.vue'
import ManageRestaurentFood from './components/ManageRestaurentFood.vue'
import RestaurentFoodForm from './components/RestaurentFoodForm.vue'
import UserOrderHistory from './components/UserOrderHistory.vue'
import DriverDashboard from './components/DriverDashboard.vue'

Vue.use(Router)

export default new Router({
	mode: 'history',
	routes:[
		{
			path:'/',
			name:'landing',
			component:Landing
		},
		{
			path:'/login',
			name:'Login',
			component:Login
		},
		{
			path:'/signin/:usertype',
			name:'Signin',
			component:Signin
		},

		{
			path:'/forgot-password',
			name:'ForgotPassword',
			component:ForgotPassword
		},
		{
			path:'/profile',
			name:'Account',
			component:Account
		},
		{
			path:'/passwordchange',
			name:'PasswordChange',
			component:PasswordChange
		},
		{
			path:'/cms/:slug',
			name:'Cms',
			component:Cms
		},
		{
			path:'/restautent-foods/:id',
			name:'RestaurentProducts',
			component:RestaurentProducts
		},
		{
			path:'/foods/:id',
			
			name:'FoodDetails',
			component:FoodDetails
		},
		{
			path:'/edit-account',
			name:'EditAccount',
			component:EditAccount
		},
		{  
			path:'/cart-details',
			name:'CartDetails',
			component:CartDetails
		},
		{
			path:'/order/:type',
			name:'RestaurentOrderList',
			component:RestaurentOrderList
		},
		{  
			path:'/contact-us',
			name:'CantactUs',
			component:CantactUs
		},
		{  
			path:'/product-management',
			name:'ManageRestaurentFood',
			component:ManageRestaurentFood
		},
		{  
			path:'/restaurent-add-food',
			name:'RestaurentFoodForm',
			component:RestaurentFoodForm
		},
		{  
			path:'/restaurent-update-food/:foodId',
			name:'RestaurentFoodForm',
			component:RestaurentFoodForm
		},
		{  
			path:'/order-history',
			name:'UserOrderHistory',
			component:UserOrderHistory
		},
		{  
			path:'/driver-dashboard',
			name:'DriverDashboard',
			component:DriverDashboard
		}
	],
	duplicateNavigationPolicy: 'reload'
}) 