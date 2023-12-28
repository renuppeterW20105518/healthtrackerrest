package ie.setu.utils

import kong.unirest.HttpResponse
import kong.unirest.JsonNode
import kong.unirest.Unirest
import org.joda.time.DateTime
import ie.setu.helpers.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class helpermethods {

    private val app = ServerContainer.instance
    private val origin = "http://localhost:" + app.port()

    //helper function to add a test user to the database
    private fun addUser(name: String, email: String): HttpResponse<JsonNode> {
        return Unirest.post(origin + "/api/users")
            .body("{\"name\":\"$name\", \"email\":\"$email\"}")
            .asJson()
    }

    //helper function to retrieve a test user from the database by email
    private fun retrieveUserByEmail(email: String): HttpResponse<String> {
        return Unirest.get(origin + "/api/users/email/${email}").asString()
    }

    //helper function to delete a test user from the database
    private fun deleteUser(id: Int): HttpResponse<String> {
        return Unirest.delete(origin + "/api/users/$id").asString()
    }

    //helper function to add a test user to the database
    private fun updateUser(id: Int, name: String, email: String): HttpResponse<JsonNode> {
        return Unirest.patch(origin + "/api/users/$id")
            .body("{\"name\":\"$name\", \"email\":\"$email\"}")
            .asJson()
    }

    //helper function to retrieve a test user from the database by id
    private fun retrieveUserById(id: Int): HttpResponse<String> {
        return Unirest.get(origin + "/api/users/${id}").asString()
    }

    private fun addActivity(
        description: String, duration: Double, calories: Int,
        started: DateTime, userId: Int
    ): HttpResponse<JsonNode> {
        return Unirest.post(origin + "/api/activities")
            .body(
                """
                {
                   "description":"$description",
                   "duration":$duration,
                   "calories":$calories,
                   "started":"$started",
                   "userId":$userId
                }
            """.trimIndent()
            )
            .asJson()
    }

    private fun retrieveAllActivities(): HttpResponse<JsonNode> {
        return Unirest.get(origin + "/api/activities").asJson()
    }

    private fun retrieveActivityByActivityId(id: Int): HttpResponse<JsonNode> {
        return Unirest.get(origin + "/api/activities/${id}").asJson()
    }

    private fun retrieveActivitiesByUserId(id: Int): HttpResponse<JsonNode> {
        return Unirest.get(origin + "/api/users/${id}/activities").asJson()
    }

    private fun updateActivity(id: Int, description: String, duration: Double, calories: Int,
                               started: DateTime, userId: Int): HttpResponse<JsonNode> {
        return Unirest.patch(origin + "/api/activities/$id")
            .body("{\"description\":\"$description\"," +
                    "\"duration\":\"$duration\"," +
                    "\"calories\":\"$calories\"," +
                    "\"started\":\"$started\"," +
                    "\"userId\":\"$userId\"}").asJson()
    }

    private fun deleteActivityByActivityId(id: Int): HttpResponse<String> {
        return Unirest.delete(origin + "/api/activities/$id").asString()
    }
    private fun deleteActivitiesByUserId(id: Int): HttpResponse<String> {
        return Unirest.delete(origin + "/api/users/$id/activities").asString()
    }
}