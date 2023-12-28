package ie.setu.domain.db

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Trainees : Table("trainees") {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name",50)
    val email = varchar("email",50)
    val phone = varchar("phone", 20)
    val address = varchar("address",100)
    val city = varchar("city",50)
    val eircode = varchar("eircode",10)
    val country = varchar("country",50)
    val date_of_birth =datetime("date_of_birth")
    val gender = varchar("gender",10)
    val emergency_contact_name = varchar("emergency_contact_name",50)
    val emergency_contact_phone = varchar("emergency_contact_phone",20)
    val emergency_contact_relation = varchar("emergency_contact_relation",50)
    val membership_type = varchar("membership_type",50)
    val membership_start_date =datetime("membership_start_date")
    val payment_method = varchar("payment_method",50)
    val amount = double("amount")
    val height = integer("height")
    val weight = integer("weight")
    val login = integer("login").autoIncrement()
}

object Login : Table("login") {
    val id = integer("id").references(Trainees.login, onDelete = ReferenceOption.CASCADE)
    val username = varchar("username",100)
    val password = varchar("password",255)
    val createddate = datetime("createddate")
}