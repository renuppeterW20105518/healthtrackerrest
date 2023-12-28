<template id="trainees">
  <app-layout>
    <div>
      <h3>Trainees list </h3>
      <ul>
        <li v-for="trainee in trainees">
          {{trainee.id}}: {{trainee.name}} for {{activity.email}}
        </li>
      </ul>
    </div>
  </app-layout>
</template>

<script>
app.component("trainees",{
  template: "#trainees",
  data: () => ({
    trainees: [],
  }),
  created() {
    const userId = this.$javalin.pathParams["user-id"];
    axios.get(`/api/login/${login-id}/trainees`)
        .then(res => this.trainees = res.data)
        .catch(() => alert("Error while fetching activities"));
  }
});
</script>