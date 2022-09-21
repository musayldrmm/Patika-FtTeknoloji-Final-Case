<template>
  <div class="row">
    <div v-for="hesapbilgisi in hesapbilgisi" :key="hesapbilgisi" class="card"
      style="width: 21rem; margin-left:90px; margin-top:50px;">
      <img src="@/assets/final.png" class="card-img-top" alt="...">
      <div class="card-body">
        <h2 class="card-title">Hesap Tipi: {{hesapbilgisi.tipid[0].hesapAdi}}</h2>
        <p class="card-text">Hesap sahibi {{hesapbilgisi.customerid.name}}</p>
        <p class="card-text">Varlik miktari: {{hesapbilgisi.miktar}}</p>
        <router-link :to="'/hesaphareketi/'+hesapbilgisi.id" class="btn btn-primary">Hesap hareketleri</router-link>
        <router-link
          :to="'/alim-satim/'+hesapbilgisi.tipid[0].hesapAdi+'/'+hesapbilgisi.id+'/'+hesapbilgisi.customerid.id"
          class="btn btn-primary buton">Alim satim</router-link>
      </div>
    </div>

  </div>
</template>

<style>
.buton {
  margin-left: 10px !important;
}
</style>

<script>
import axios from 'axios';
export default {

  data() {
    return {
      hesapbilgisi: []
    }
  },
  mounted() {
    axios.get("http://localhost:9090/hesap/get-all/" + this.$route.params.id, {
      headers: {
        'Authorization': this.$store.getters.getAccesToken
      }}).then(response => {
        this.hesapbilgisi = response.data
        console.log(response.data);
      }
      )

  }
}

</script>
    