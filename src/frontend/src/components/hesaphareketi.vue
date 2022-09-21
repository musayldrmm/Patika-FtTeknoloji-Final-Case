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
import moment from 'moment';
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
    }).then(response =>{
      for(let i = 0; i < response.data.length; i++) {
      response.data[i].islemtarihi=moment(response.data[i].islemtarihi).format("YYYY-MM-DD");
      }
      this.items = response.data})
  }
}
</script>