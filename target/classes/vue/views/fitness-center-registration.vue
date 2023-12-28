<template id="fitness-center-registration">
  <app-layout>
  <div class="fitness-center-registration">
    <h2>Registration Form</h2>
    <form @submit.prevent="submitForm" class="registration-form">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username" required />
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" required />
      <label for="confirmPassword">Confirm Password:</label>
      <input type="password" id="confirmPassword" v-model="confirmPassword" required />
      <button type="submit">Register</button>
    </form>
    <p v-if="registrationStatus !== null" class="registration-status">{{ registrationStatus }}</p>
  </div>
  </app-layout>
</template>
<template id="fitness-center-registration">
  <app-layout>
    <div class="fitness-center-registration">
      <h2>Registration Form</h2>
<div class="input-group mb-3">
<div class="input-group-prepend">
  <span class="input-group-text" id="input-activity-name">Description</span>
</div>
<input type="text" class="form-control" v-model="formData.description" name="description" placeholder="Description"/>
</div>
<div class="input-group mb-3">
<div class="input-group-prepend">
  <span class="input-group-text" id="input-activity-duration">Duration</span>
</div>
<input type="text" class="form-control" v-model="formData.duration" name="duration" placeholder="Duration"/>
</div>
<div class="input-group mb-3">
<div class="input-group-prepend">
  <span class="input-group-text" id="input-activity-calories">Calories</span>
</div>
<input type="text" class="form-control" v-model="formData.calories" name="calories" placeholder="Calories"/>
</div>
<div class="input-group mb-3">
<div class="input-group-prepend">
  <span class="input-group-text" id="input-activity-started">Started</span>
</div>
<input type="date" class="form-control" v-model="formData.started" name="started" placeholder="Started"/>
</div>
<div class="input-group mb-3">
<div class="input-group-prepend">
  <span class="input-group-text" id="input-userId-started">User Id</span>
</div>
<input type="number" class="form-control" v-model="formData.userId" name="userId" placeholder="User Id"/>
</div>
</app-layout>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      confirmPassword: "",
      registrationStatus: null,
    };
  },
  methods: {
    submitForm() {
      // Perform registration logic here
      if (this.password !== this.confirmPassword) {
        this.registrationStatus = "Passwords do not match.";
      } else {
        // Send registration data to the server or perform other actions
        // For simplicity, we'll just log the data for now
        console.log("Registration Data:", {
          username: this.username,
          password: this.password,
        });

        // Reset form fields and status
        this.username = "";
        this.password = "";
        this.confirmPassword = "";
        this.registrationStatus = "Registration successful!";
      }
    },
  },
};
</script>
<script setup>
</script>