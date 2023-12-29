<template id="healthy-tips">
  <app-layout>
    <div>
      <h3>Health Tip</h3>
      <ul>
<!--        <li v-for="healthytip in healthytips">-->
<!--          {{healthytip.id}}: {{healthytip.tips}}-->
<!--        </li>-->

      </ul>
      <div class="col" align="centre">
        <button rel="tooltip" title="Refresh"
                class="btn btn-info btn-simple btn-link"
                @click="fetchRandomTip">
          <i class="fa fa-refresh" aria-hidden="true"></i>
        </button>
      </div>
    </div>
  </app-layout>
</template>

<script>
app.component("healthy-tips", {
  template: "#healthy-tips",
  data: () => ({
    healthtips: [],
  }),
  created() {
    this.fetchRandomTip();
  },
  methods: {
    fetchHealthTips: function () {
      axios.get("/api/tip")
          .then(res => {
            console.log("Response from server:", res);
            this.healthtips = res.data;
          })
          .catch(error => {
            console.error("Error while fetching motivation tips:", error);
            alert("Error while fetching motivation tips");
          });
    },
    fetchRandomTip: function () {
      axios.get("/api/tip")
          .then(res => {
            console.log("Response from server:", res);
            this.tips = res.data;
          })
          .catch(error => {
            console.error("Error while fetching motivation tips:", error);
            alert("Error while fetching motivation tips");
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



