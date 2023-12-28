package ie.setu.controllers

import ie.setu.domain.User
import io.javalin.http.Context
import ie.setu.domain.Activity
import ie.setu.domain.MotivationQuote
import ie.setu.domain.Trainee
import ie.setu.domain.repository.*
import ie.setu.utils.jsonToObject

object HealthTrackerController {

    private val userDao = UserDAO()
    private val activityDAO = ActivityDAO()
    private val traineeDAO = TraineeDAO()
    private val loginDAO = LoginDAO()
    private val motivationQuoteDAO = MotivationQuoteDAO()

    //--------------------------------------------------------------
    // UserDAO specifics
    //-------------------------------------------------------------
    fun getAllUsers(ctx: Context) {
        val users = userDao.getAll()
        if (users.size != 0) {
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(users)
    }

    fun getUserByUserId(ctx: Context){
        val user = userDao.findById(ctx.pathParam("user-id").toInt())
        if (user != null) {
            ctx.json(user)
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
    }

    fun addUser(ctx: Context) {
        val user : User = jsonToObject(ctx.body())
        val userId = userDao.save(user)
        if (userId != null) {
            user.id = userId
            ctx.json(user)
            ctx.status(201)
        }
    }

    fun getUserByEmail(ctx: Context){
        val user = userDao.findByEmail(ctx.pathParam("email"))
        if (user != null) {
            ctx.json(user)
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
    }

    fun deleteUser(ctx: Context){
        if (userDao.delete(ctx.pathParam("user-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun updateUser(ctx: Context){
        val foundUser : User = jsonToObject(ctx.body())
        if ((userDao.update(id = ctx.pathParam("user-id").toInt(),
                user=foundUser)) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    //--------------------------------------------------------------
    // ActivityDAO specifics
    //-------------------------------------------------------------

    fun getAllActivities(ctx: Context) {
        val activities = activityDAO.getAll()
        if (activities.size != 0) {
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(activities)
    }

    fun getActivitiesByUserId(ctx: Context) {
        if (userDao.findById(ctx.pathParam("user-id").toInt()) != null) {
            val activities = activityDAO.findByUserId(ctx.pathParam("user-id").toInt())
            if (activities.isNotEmpty()) {
                ctx.json(activities)
                ctx.status(200)
            } else {
                ctx.status(404)
            }
        } else{
            ctx.status(404)
        }
    }
	
    fun getActivitiesByActivityId(ctx: Context) {
        val activity = activityDAO.findByActivityId((ctx.pathParam("activity-id").toInt()))
        if (activity != null){
            ctx.json(activity)
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
    }	

    fun addActivity(ctx: Context) {
        val activity : Activity = jsonToObject(ctx.body())
        val userId = userDao.findById(activity.userId)
        if (userId != null) {
            val activityId = activityDAO.save(activity)
            activity.id = activityId
            ctx.json(activity)
            ctx.status(201)
        }
        else{
            ctx.status(404)
        }
    }


    fun deleteActivityByActivityId(ctx: Context) {
      if(activityDAO.deleteByActivityId(ctx.pathParam("activity-id").toInt()) != 0)
          ctx.status(204)
      else
          ctx.status(404)
    }

    fun deleteActivityByUserId(ctx: Context){
       if(activityDAO.deleteByUserId(ctx.pathParam("user-id").toInt()) != 0)
           ctx.status(204)
       else
           ctx.status(404)
    }

    fun updateActivity(ctx: Context){
        val activity : Activity = jsonToObject(ctx.body())
        if (activityDAO.updateByActivityId(
                activityId = ctx.pathParam("activity-id").toInt(),
                activityToUpdate = activity) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }


    //--------------------------------------------------------------
    // FitnessDAO specifics
    //-------------------------------------------------------------
    fun addTrainee(ctx: Context) {
        val trainee : Trainee = jsonToObject(ctx.body())
        val traineeId = traineeDAO.save(trainee)
        if (traineeId != null) {
            trainee.id = traineeId
            ctx.json(trainee)
            ctx.status(201)
        }
    }

    fun getAllTrainees(ctx: Context) {
        val trainees = traineeDAO.getAllTrainees()
        if (trainees.size != 0) {
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(trainees)
    }

    fun deleteTrainee(ctx: Context){
        if (traineeDAO.delete(ctx.pathParam("trainee-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun updateTrainee(ctx: Context){
        val foundTrainee : Trainee = jsonToObject(ctx.body())
        if ((traineeDAO.update(id = ctx.pathParam("trainee-id").toInt(), trainee = foundTrainee)) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun getAllLogin(ctx: Context) {
        val login = loginDAO.getAlllogin()
        if (login.size != 0) {
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(login)
    }

    fun getTraineeByLoginId(ctx: Context) {
        val trainee = traineeDAO.findTraineeByLoginId((ctx.pathParam("login-id").toInt()))
        if (trainee != null){
            ctx.json(trainee)
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
    }

    fun getAllQuotes(ctx: Context) {
        val quote = motivationQuoteDAO.getAllquotes()
        if (quote.size != 0) {
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(quote)
    }

    fun updateQuote(ctx: Context)
    {
        val foundQuote: MotivationQuote = jsonToObject(ctx.body())
        if((motivationQuoteDAO.update(id = ctx.pathParam("quote-id").toInt(), quote = foundQuote)) != 0){
            ctx.status(204)
        } else
            ctx.status(404)
    }

    fun deleteQuote(ctx:Context){
        if(motivationQuoteDAO.delete(ctx.pathParam("quote-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun addQuote(ctx: Context){
        val quotes: MotivationQuote = jsonToObject(ctx.body())
        val quoteId = motivationQuoteDAO.save(quotes)
        if (quoteId != null){
            ctx.json(quotes)
            ctx.status(201)
        }
    }
}