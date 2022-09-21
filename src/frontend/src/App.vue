<template>
<nav class="navbar navbar-expand-lg navbar-light bg-light" v-if="this.$store.getters.isAuthenticated">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="/">Home <span class="sr-only"></span></a>
      <router-link :to="'/hesap/'+result.sub" class="nav-item nav-link">Hesaplar</router-link>
      <button class="btn btn-outline-danger logout" @click="logout">Çıkış</button>
    </div>
  </div>
</nav>
<router-view></router-view>
</template>
<script>
  import store from './store';
  import jwt_decode from "jwt-decode";
  export default {
    data() {
      return {
      loginData: store.getters.isAuthenticated,
      tokenData:store.getters.getAccesToken,
      result:[]
    }
    },
    methods: {
    logout() {
      this.$store.commit('logout');
      this.$router.push("LoginPage")
    }
    },
    mounted(){
      var token =this.tokenData.toString();
     this.result = jwt_decode(token);
      console.log(this.result);
    }
  }
</script>
<style>
    .navbar{
        margin-left: 16px !important;
    }
    .logout{
      position: absolute;
      right: 15px;
    }
</style>
