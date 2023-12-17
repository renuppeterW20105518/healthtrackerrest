<template id="activity-profile">
  <application-layout>
    <div class="card bg-light mb-3">
      <div class="card-header">
        <div class="row">
          <div class="col-6">Activity Profile</div>
          <div class="col" align="right">
            <button rel="tooltip" title="Update"
                    class="btn btn-info btn-simple btn-link">
              <i class="far fa-save" aria-hidden="true"></i>
            </button>
            <button rel="tooltip" title="Delete"
                    class="btn btn-info btn-simple btn-link">
              <i class="fas fa-trash" aria-hidden="true"></i>
            </button>
          </div>
        </div>
      </div>
      <div class="card-body">
        <form>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="input-activity-id">Activity ID</span>
            </div>
            <input class="form-control" v-model="activity.id" name="id" type="number" readonly placeholder="Activity ID"/>
          </div>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="input-activity-description">Description</span>
            </div>
            <input class="form-control" v-model="activity.description" name="description" type="text" placeholder="Activity Description">
          </div>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="input-activity-duration">Duration</span>
            </div>
            <input class="form-control" v-model="activity.duration" name="duration" type="time" placeholder="Activity Duration">
          </div>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="input-activity-calories">Calories</span>
            </div>
            <input class="form-control" v-model="activity.calories" name="calories" type="number" placeholder="Calories">
          </div>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="input-activity-started">Activity Started On</span>
            </div>
            <input class="form-control" v-model="activity.started" name="started" type="datetime-local" placeholder="Activity Started On">

          </div>
          <div class="input-group mb-3">

          </div>
        </form>
      </div>
      <div class="card-footer">

      </div>
    </div>
  </application-layout>
</template>
<script>
app.component("activity-profile", {
  template: "#activity-profile",
  data: () => ({
    activity: null,
    noUserFound: false,
    //activities: [],
  })
});
</script>