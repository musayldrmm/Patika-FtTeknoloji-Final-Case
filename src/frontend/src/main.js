import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.css'
import router from './router'
import BootstrapVue3 from 'bootstrap-vue-3'
import store from './store'

const app =createApp(App)
app.use(BootstrapVue3)
app.use(router);
app.use(store)
app.mount('#app');
