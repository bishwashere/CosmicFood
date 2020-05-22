<template>
    <div>
        <Header/>
        <section class="main-body"><router-view/></section>
        <Footer/>
        
    </div>
</template>
<script>
import Header from './Header';
import Footer from './Footer';


import './../../assets/bootstrap-3.3.7-dist/css/bootstrap.min.css'
import './../../assets/src/css/menu.css'
import './../../assets/src/css/style.css'
import './../../assets/src/css/font-awesome.min.css'

export default {
    name: 'Layout',
    components:{
        Footer,
        Header,
    },
    props: {
        msg: String
    },
     
}
</script>