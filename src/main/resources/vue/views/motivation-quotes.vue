<template id="motivation-quotes">
  <app-layout>
    <div>
      <h3>Motivation Quotes</h3>
      <ul>
        <li v-for="quotes in motivation-quotes" :key="tip.id">
          <a class="quote-link">{{quotes.tips}}</a>
        </li>
      </ul>
      <div class="col" align="centre">
        <button rel="tooltip" title="Refresh"
                class="btn btn-info btn-simple btn-link"
                @click="fetchRandomQuote">
          <i class="fa fa-refresh" aria-hidden="true"></i>
        </button>
      </div>
    </div>
  </app-layout>
</template>

<script>
app.component("motivation-quotes", {
  template: "#motivation-quotes",
  data: () => ({
    healthtips: [],
  }),
  created() {
    this.fetchRandomQuote();
  },
  methods: {
    fetchHealthTips: function () {
      axios.get("/api/quote")
          .then(res => {
            console.log("Response from server:", res);
            this.healthtips = res.data;
          })
          .catch(error => {
            console.error("Error while fetching motivation quotes:", error);
            alert("Error while fetching motivation quotes");
          });
    },
    fetchRandomQuote: function () {
      axios.get("/api/quote")
          .then(res => {
            console.log("Response from server:", res);
            this.quotes = res.data;
          })
          .catch(error => {
            console.error("Error while fetching motivation quotes:", error);
            alert("Error while fetching motivation quotes");
          });
    }
  }
});
</script>

<style scoped>
.tips-container {
  text-align: center;
}
.quote-link {
  color: #000000;
  text-decoration: none;
  font-weight: bold;
  margin-right: 10px;
  margin-top: 7px;
  font-size: 25px; /* Set your desired font size */
  font-family: 'Lora', serif;
}

ul {
  list-style-type: none;
  padding: 0;
}

button {
  background-color: #87CEEB;
  color: #fff;
  padding: 10px;
  cursor: pointer;
  border: none;
  border-radius: 4px;
}

.tips-container {
  background-color: #87CEEB; /* Sky Blue */
  opacity: 0.7;
}


</style>



