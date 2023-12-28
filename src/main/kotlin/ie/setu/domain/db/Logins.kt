package ie.setu.domain.db

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Logins : Table("login") {
    val id = integer("id").references(Trainees.login, onDelete = ReferenceOption.CASCADE)
    val username = varchar("username",100)
    val password = varchar("password",255)
    val createddate = datetime("createddate")
}