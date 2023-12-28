<template id="fitness-page">
  <app-layout>
    <div class="row">
      <div class="col-12">
        <h5 class="card-header">Welcome to Nathans Fitness Center</h5>
        <div class="row">
          <div class="col-6">1</div>
          <div class="col-6">
            <div class="card">
              <h5 class="card-header">Login</h5>
              <div class="card-body">
                <form>

                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="input-username">Username:</span>
                    </div>
                    <input type="text" class="form-control" id="username" v-model="username" placeholder="Username" required />
                  </div>

                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="input-password">Password:</span>
                    </div>
                    <input type="password" class="form-control" type="password" id="password" v-model="password" required />

                    <br/>
                    <a href="/login" class="btn btn-primary" onclick="login()">Login</a>
                <!-- <button type="submit">Submit</button>-->
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </app-layout>
</template>

<script>
app.component("fitness-page", {
  template: "#fitness-page",
  data() {
    return {
      trainees: [],
      login: [],
      formData: { name: "", email: "", phone: "", address: "", city: "", eircode: "", country: "", date_of_birth: "", gender: "",
        emergency_contact_name: "", emergency_contact_phone: "", emergency_contact_relation: "",
        membership_type: "", membership_start_date: "", membership_end_date: "", payment_method: "", amount: "",
        height: "", weight: "" },
      currentPage: 1,
      itemsPerPage: 5,
      hideForm: true
    };
  },
  created() {
    this.fetchTrainees();
    this.Login();
    // Use `axios.get("/api/trainees")` instead of `axios.get("/api/login")`
    axios.get("/api/login")
        .then(res => {
          this.login = res.data;
        }).catch(() => alert("Error while fetching trainees"));

  },
  methods: {
    login() {

      const validUsername = 'user';
      const validPassword = 'password';

      if (this.username === validUsername && this.password === validPassword) {
        // Successful login
        console.log('Login successful!');
      } else {
        // Failed login
        this.errorMessage = 'Invalid username or password';
      }
    },
    async fetchTrainees() {
      try {
        const response = await axios.get("api/trainees");
        this.trainees = response.data;
      } catch (error) {
        alert("Error while fetching trainees");
      }
    },
    login: function() {
      alert('enter login')
      const url = `/api/trainees`;
      const credentials = {
        username: this.formData.username,
        password: this.formData.password,
      };
      axios.post(url, credentials)
          .then(response => {
            if (response.status === 200) {
              // window.location.href = '/'
              this.$router.push('/trainees');
            } else {
              // Handle other status codes if needed
              console.log('Login failed');
            }
          })
          .catch(error => {
            console.error('Login error:', error);
          });
    },
    setPage(page) {
      this.currentPage = page;
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
  },
});
</script>