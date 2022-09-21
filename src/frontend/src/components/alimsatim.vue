<template>
    <div class="setting">
        <img src="@/assets/money.png" alt="#" class="img-money">
        <h1>Alim-Satim ekrani</h1>
        <h3> Choose the currency and the amounts to get the exchange rate </h3>
        <div class="container">
            <div class="currency">

                <select name="" id="currency-one" v-model="currencyOne">
                    <option v-bind:value="currencyOne">
                        {{currencyOne}}</option>
                </select>
                <input type="number" id="amount-one" placeholder="0" v-model="amountOne" @change="fetchData()">

            </div>

            <div class="currency">
                <div class="currency-to">

                    <select name="" id="currency-two" v-model="currencyTwo">
                        <option v-for="hesapbilgisi2 in hesapbilgisi" :key="hesapbilgisi2"
                            v-bind:value="hesapbilgisi2.tipid[0].hesapAdi">{{hesapbilgisi2.tipid[0].hesapAdi}}</option>
                    </select>
                    <input type="number" id="amount-two" v-model="amountTwo" placeholder="0">
                    <div class="swap-rate-container">
                        <button @click="soldData(currencyTwo),postDataBuy(), buyData(currencyOne)" class="btn albuton" id="swap">Al<i
                                class="fas fa-sync"></i></button>
                        <button class="btn satbuton" id="swap" @click="soldData(currencyTwo), postDataSell(), buyData(currencyOne)">Sat<i
                                class="fas fa-sync"></i></button>

                        <div class="rate" id="rate"> Alim satim fiyati : 1 {{currencyOne}}= {{rate}} {{currencyTwo}}
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="row">
            <div v-for="hesapbilgisi in hesapbilgisi" :key="hesapbilgisi" class="card"
                style="width: 13rem; margin-left:90px;">
                <img src="@/assets/final.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h2 class="card-title">Hesap Tipi: {{hesapbilgisi.tipid[0].hesapAdi}}</h2>
                    <p class="card-text">Hesap sahibi {{hesapbilgisi.customerid.name}}</p>
                    <p class="card-text">Varlik miktari: {{hesapbilgisi.miktar}}</p>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import axios from 'axios';
import moment from 'moment'
export default {
    data() {
        return {
            hesapbilgisi: [],
            Data: [],
            currencyOne: "USD",
            currencyTwo: "EUR",
            rate: 0,
            amountOne: 0,
            amountTwo: 0,
            selected: "",
            accountDataOne:[],
            accountDataTwo: []
        }
    },
    mounted() {
        axios.get("http://localhost:9090/hesap/get-all/" + this.$route.params.customerid, {
            headers: {
                'Authorization': this.$store.getters.getAccesToken
            }
        }).then(response => {
            this.hesapbilgisi = response.data
            console.log(this.hesapbilgisi);
            this.currencyOne = this.$route.params.hesapadi
        }
        )
        // this.fetchData();
    },
    methods: {
        fetchData() {
            var myHeaders = new Headers();
            myHeaders.append("apikey", "AQ7dz9E5KHIVIFSDx90Jr1dIoNx3W2hw");

            var requestOptions = {
                method: 'GET',
                redirect: 'follow',
                headers: myHeaders
            };

            fetch("https://api.apilayer.com/exchangerates_data/latest?symbols=USD%2CXAU%2CTRY%2CEUR&base=" + this.$route.params.hesapadi, requestOptions)
                .then(response => response.text())
                .then(result => {
                    this.Data = JSON.parse(result)
                    this.rate = this.Data.rates[this.currencyTwo]
                    this.amountTwo = this.amountOne * this.rate.toFixed(2)
                }
                )
                .catch(error => console.log('error', error));
        },
        filtermethod() {
            return this.hesapbilgisi.filter(item => {
                return item
            })
        },
        postDataBuy() {
            var datetime = new Date();
            datetime = moment(datetime).format("YYYY-MM-DD");
            const buyData = {
                alimaciklamasi: this.amountOne + "@" + this.rate,
                satimaciklamasi: this.amountTwo + "@" + this.rate,
                alimmiktari: this.amountOne.toFixed(2),
                satimmiktari: -1 * this.amountTwo.toFixed(2),
                islemtarihi: datetime,
                alimhesabi: {
                    id: Number(this.$route.params.id)
                }
                ,
                satimhesabi: {
                    id: this.accountDataTwo.id
                }
            }
            axios.post("http://localhost:9090/exchange/buy-sold", buyData, {
                headers: {
                    'Authorization': this.$store.getters.getAccesToken
                }
            })

        },
        postDataSell() {
            var datetime = new Date();
            datetime = moment(datetime).format("YYYY-MM-DD");
            const buyData = {
                alimaciklamasi: this.amountOne + "@" + this.rate,
                satimaciklamasi: this.amountTwo + "@" + this.rate,
                alimmiktari: this.amountOne.toFixed(2),
                satimmiktari: -1 * this.amountTwo.toFixed(2),
                islemtarihi: datetime,
                alimhesabi: {
                    id: this.accountDataTwo.id
                }
                ,
                satimhesabi: {
                    id: Number(this.$route.params.id)
                }
            }
            if (this.accountDataOne.miktar > 0) {
                axios.post("http://localhost:9090/exchange/buy-sold", buyData, {
                    headers: {
                        'Authorization': this.$store.getters.getAccesToken
                    }
                })
            }
            else {
                alert("satiş miktari yok")
            }



        },

        soldData(param) {
            for (let i = 0; i < this.hesapbilgisi.length; i++) {
                let hesapAdi = this.hesapbilgisi[i].tipid[0].hesapAdi
                if (param === hesapAdi) {
                    this.accountDataTwo = this.hesapbilgisi[i];
                }
            }
              
        },
        buyData(param) {
            for (let i = 0; i < this.hesapbilgisi.length; i++) {
                let hesapAdi = this.hesapbilgisi[i].tipid[0].hesapAdi
                if (param === hesapAdi) {
                    this.accountDataOne = this.hesapbilgisi[i];
                }
            }
              console.log(this.accountDataOne.miktar)
        }
    }
}
</script>


<style>
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

body {
    background: #f4f4f4;
}

.setting {
    /*height: 100vh;*/
    /* overflow: hidden; */
    font-family: 'Roboto', sans-serif;
    background: #f4f4f4;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 0;
    padding: 20px;
}

h1 {
    color: #5fbaa7;
    padding: 10px 0;
    font-size: 3rem;
}

h3 {
    text-align: center;
    font-size: 1.2rem;
}

.albuton {
    position: absolute;
    left: 58%;
    top: 56%;
    transform: translate(-50%, -50%);
    color: #fff;
    background-color: #5fbaa7;
    cursor: pointer;
    border-radius: 5px;
    font-size: 16px;
    padding: 6px 46px;

}

.satbuton {
    position: absolute;
    left: 65%;
    top: 56%;
    transform: translate(-50%, -50%);
    color: #fff;
    background-color: #5fbaa7;
    cursor: pointer;
    border-radius: 5px;
    font-size: 16px;
    padding: 6px 46px;
}


.btn:focus {
    outline: none;
}

.img-money {
    width: 150px;
}

.currency {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 40px 0;
}

.currency select {
    padding: 10px 20px 10px 10px;
    border: 1px solid #dedede;
    font-size: 18px;
    outline: none;
    border: 1px solid #dedede;
    position: relative;
}

.currency input {
    border: 0;
    /* background: transparent; */
    font-size: 30px;
    text-align: right;
    outline: none;
    border: 1px solid #dedede;
}

.swap-rate-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.rate {
    left: 616px;
    top: 461px;
    position: absolute;
    color: #5fbaa7;
    font-size: 18px;
    padding: 0 10px;
    font-weight: bold;
}

.last-update {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    font-size: 1rem;
}

@media (max-device-width: 600px) {
    body {
        display: block;
        text-align: center;
        margin: 0;
        padding: 1rem;
    }

    .swap-rate-container {
        justify-content: center;
    }

    .img-money {
        width: 100px;
        height: 100px;
    }

    h1 {
        font-size: 1.5rem;
    }

    h3 {
        font-size: 1.2rem;
    }

    .currency input {
        max-width: 200px;
        font-size: 24px;
        padding: 5px;
    }

    .currency select {
        font-size: 18px;
        padding: 10px 10px;
    }
}

@media (max-device-width: 400px) {
    body {
        display: block;
        text-align: center;
        margin: 0;
        padding: 1rem;
    }

    .swap-rate-container {
        justify-content: center;
    }

    .img-money {
        width: 100px;
        height: 100px;
    }

    h1 {
        font-size: 1.5rem;
    }

    h3 {
        font-size: 1.2rem;
    }

    .currency input {
        max-width: 200px;
        font-size: 18px;
        padding: 5px;
    }

    .currency select {
        font-size: 16px;
        padding: 5px;
    }

    .currency {
        flex-wrap: wrap;
    }
}
</style>