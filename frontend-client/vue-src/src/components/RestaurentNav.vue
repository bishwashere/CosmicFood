<template>
	<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="newOrder">
            <router-link to="/order/new">New Orders</router-link>
          </li>
        <li>
            <router-link to="/order/active">Active orders</router-link>
        </li>
        <!-- <li>
          <router-link to="/order/rejected">Rejected orders</router-link>
        </li> -->
      </ul>
      <ul class="nav navbar-nav pull-right">
          <li>
            <router-link to="/product-management">Manage products</router-link>
          </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</template>
<script type="text/javascript">
  export default {
    data(){

      return {
        newOrder:((this.$route.params.type == 'new')?'active':''),
        activeOrder:((this.$route.params.type == 'active')?'active':''),
        rejectedOrder:((this.$route.params.type == 'rejected')?'active':'')
      }
    }
  }
</script>