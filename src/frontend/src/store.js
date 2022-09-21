import { createStore } from "vuex";

// export default createStore({
//     state: {},
//     mutations: {},
//     actions: {},
//     getters: {},
//     modules: { generalPage },
// });

import createPersistedState from "vuex-persistedstate"
import SecureLS from "secure-ls";

const ls = new SecureLS({
    isCompression: false,
  });
  
export default createStore({
    state: {
        loginData: null,
        verifyUser: null,
        access_token: "",
    },
    getters: {
        isAuthenticated: state => {
            return state.loginData !== null
        },
        getUser(state){
            return state.loginData
        },
       
        getAccesToken(state){
            return state.access_token
        }
       
    },
    mutations: {
    //   setAuth: (state,params) => {
    //     state.loginData = params
        
    //   },
    setAuth: function(state,params){
        state.loginData = params
    },
    logout(state){
        state.loginData = null
        state.verifyUser = null
        state.access_token=null
    },
    setAccessToken(state,params){
        state.access_token=params
    }
        
    },
    actions: {
        

    },
    modules: {  },
    plugins: [createPersistedState({
        storage: {
            getItem: (key) => ls.get(key),
            setItem: (key, value) => ls.set(key, value),
            removeItem: (key) => ls.remove(key),
        },
    }), ]
})