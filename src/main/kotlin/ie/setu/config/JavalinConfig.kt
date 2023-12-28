package ie.setu.config

import io.javalin.Javalin
import ie.setu.controllers.HealthTrackerController
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.json.JavalinJackson
import ie.setu.utils.jsonObjectMapper
import io.javalin.vue.VueComponent

class JavalinConfig {
        val app = Javalin.create{
            //added this jsonMapper for our integration tests - serialise objects to json
            it.jsonMapper(JavalinJackson(jsonObjectMapper()))
            it.staticFiles.enableWebjars()
            it.vue.vueAppName = "app" // only required for Vue 3, is defined in layout.html
        }.apply {
            exception(Exception::class.java) { e, _ -> e.printStackTrace() }
            error(404) { ctx -> ctx.json("404 : Not Found") }
        }
		
    fun startJavalinService(): Javalin {	
        app.start(getRemoteAssignedPort())
        registerRoutes(app)
        return app
    }

	fun getJavalinService(): Javalin {
        registerRoutes(app)
        return app
    }
    private fun getRemoteAssignedPort(): Int {
        val remotePort = System.getenv("PORT")
        return if (remotePort != null) {
            Integer.parseInt(remotePort)
        } else 7000
    }

    private fun registerRoutes(app: Javalin) {
        app.routes {
            path("/api/users") {
                get(HealthTrackerController::getAllUsers)
                post(HealthTrackerController::addUser)
                path("{user-id}"){
                    get(HealthTrackerController::getUserByUserId)
                    delete(HealthTrackerController::deleteUser)
                    patch(HealthTrackerController::updateUser)
                    //The overall path is: "/api/users/:user-id/activities"
                    path("activities"){
                        get(HealthTrackerController::getActivitiesByUserId)
                        delete(HealthTrackerController::deleteActivityByUserId)
                    }
                }
                path("/email/{email}"){
                    get(HealthTrackerController::getUserByEmail)
                }
            }
            path("/api/activities") {
                get(HealthTrackerController::getAllActivities)
                post(HealthTrackerController::addActivity)
                path("{activity-id}") {
                    get(HealthTrackerController::getActivitiesByActivityId)
                    delete(HealthTrackerController::deleteActivityByActivityId)
                    patch(HealthTrackerController::updateActivity)
                }
            }
            path("/api/trainees"){
                get(HealthTrackerController::getAllTrainees)
                post(HealthTrackerController::addTrainee)
                path("{trainee-id}") {
                    delete(HealthTrackerController::deleteTrainee)
                    patch(HealthTrackerController::updateTrainee)
                }
            }
            path("/api/login"){
                get(HealthTrackerController::getAllLogin)
                path("{login-id}") {
                    get(HealthTrackerController::getTraineeByLoginId)
                }
            }
            path("/api/quote"){
                post(HealthTrackerController::addQuote)
                get(HealthTrackerController::getAllQuotes)
                path("{quote-id}"){
                    patch(HealthTrackerController::updateQuote)
                    delete(HealthTrackerController::deleteQuote)
                }
            }
            path("/api/quotes"){
                get(HealthTrackerController::getAllQuotes)
            }
            // The @routeComponent that we added in layout.html earlier will be replaced
            // by the String inside the VueComponent. This means a call to / will load
            // the layout and display our <home-page> component.
            get("/", VueComponent("<home-page></home-page>"))
            get("/users", VueComponent("<user-overview></user-overview>"))
            get("/users/{user-id}", VueComponent("<user-profile></user-profile>"))
            get("/users/{user-id}/activities", VueComponent("<user-activity-overview></user-activity-overview>"))
            get("/activities", VueComponent("<activity-overview></activity-overview>"))
            get("/activities/{activity-id}", VueComponent("<activity-profile></activity-profile>"))
            get("/fitness-page", VueComponent("<fitness-page></fitness-page>"))
            get("/trainees/{trainee-id}", VueComponent("<trainees></trainees>"))
            get("/cart", VueComponent("<cart></cart>"))
            get("/quote", VueComponent("<motivation-quotes></motivation-quotes>"))
            get("/fitness-center-registration", VueComponent("<fitness-center-registration></fitness-center-registration>"))
        }
    }
}