package ie.setu.domain

import org.joda.time.DateTime

data class Trainee (
    var id :Int,
    var name :String,
    var email :String,
    var phone :String,
    var address :String,
    var city :String,
    var eircode :String,
    var country :String,
    var date_of_birth :DateTime,
    var gender :String,
    var emergency_contact_name :String,
    var emergency_contact_phone :String,
    var emergency_contact_relation :String,
    var membership_type :String,
    var membership_start_date :DateTime,
    var payment_method :String,
    var amount : Double,
    var height :Int,
    var weight :Int,
    var login :Int
)

data class Login (
    var id :Int,
    var username :String,
    var password :String,
    var createddate :DateTime,
)