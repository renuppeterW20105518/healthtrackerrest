package ie.setu.utils

import ie.setu.domain.*
import ie.setu.domain.Login
import org.jetbrains.exposed.sql.ResultRow
import ie.setu.domain.db.*

fun mapToUser(it: ResultRow) = User(
    id = it[Users.id],
    name = it[Users.name],
    email = it[Users.email]
)

fun mapToActivity(it: ResultRow) = Activity(
    id = it[Activities.id],
    description = it[Activities.description],
    duration = it[Activities.duration],
    started = it[Activities.started],
    calories = it[Activities.calories],
    userId = it[Activities.userId]
)

fun mapToTrainee(it: ResultRow) = Trainee(
    id = it[Trainees.id],
    name = it[Trainees.name],
    email = it[Trainees.email],
    phone = it[Trainees.phone],
    address = it[Trainees.address],
    city = it[Trainees.city],
    eircode = it[Trainees.eircode],
    country = it[Trainees.country],
    date_of_birth = it[Trainees.date_of_birth],
    gender = it[Trainees.gender],
    emergency_contact_name = it[Trainees.emergency_contact_name],
    emergency_contact_phone = it[Trainees.emergency_contact_phone],
    emergency_contact_relation = it[Trainees.emergency_contact_relation],
    membership_type = it[Trainees.membership_type],
    membership_start_date = it[Trainees.membership_start_date],
    payment_method = it[Trainees.payment_method],
    amount = it[Trainees.amount],
    height = it[Trainees.height],
    weight = it[Trainees.weight],
    login = it[Trainees.login]
)

fun mapToLogin(it: ResultRow) = Login(
    id = it[Logins.id],
    username = it[Logins.username],
    password = it[Logins.password],
    createddate = it[Logins.createddate]
)

fun mapToHealthyTip(it:ResultRow) = HealthyTip(
    id = it[HealthyTips.id],
    tips = it[HealthyTips.tips]
)

fun mapToBmi(it: ResultRow) = Bmi(
    id = it[Bmies.id],
    weight = it[Bmies.weight],
    height = it[Bmies.height],
    bmiCalculator = it[Bmies.bmiCalculator],
    timestamp = it[Bmies.timestamp],
    userId = it[Bmies.userId]
)