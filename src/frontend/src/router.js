import {createWebHistory, createRouter} from"vue-router" 
import login from "./components/login.vue"  
import hesap from "./components/hesaplar.vue"
import hesaphareketi from "./components/hesaphareketi.vue"  
import Home from "./pages/index/index.vue" 
import store from "./store"
import alimsatim from "./components/alimsatim.vue"
const router = createRouter({
    routes:[
{path:"/login", component:login,name:"LoginPage"},
{path:"/", component:Home,name:"HomePage"},
{path:"/hesap/:id",component:hesap,name:"HesapPage"},
{path:"/hesaphareketi/:id",component:hesaphareketi,name:"hesaphareketi"},
{path:"/alim-satim/:hesapadi/:id/:customerid",component:alimsatim,name:"alim-satim"}],
    history:createWebHistory()
});



router.beforeEach(async (to, from) => {
    const isAuthenticated = store.getters.isAuthenticated;
    console.log(isAuthenticated);
    if ((!isAuthenticated) && to.name !== "LoginPage") {
      return { name: "LoginPage" };
    } else if ((isAuthenticated) &&( to.name === "LoginPage") ) {
      return { name: "HesapPage" };
    } else {
      return true;
    }
});

export default router;