<template>
  <div class="container mt-5">
    <b-table striped hover :items="items"></b-table>
    <p>{{}}</p>
  </div>
</template>
  
<style>
table thead {
  background: black;
  color: white;
}
</style>
  
<script>
import axios from 'axios';

export default {
  data() {
    return {
      items: []
    }
  },
  mounted() {
    axios.get(`http://localhost:9090/hesap-hareketleri/get-all-transaction/` + this.$route.params.id, {
      headers: {
        'Authorization': this.$store.getters.getAccesToken
      }
    }).then(response =>
      this.items = response.data)
  }
}
</script>