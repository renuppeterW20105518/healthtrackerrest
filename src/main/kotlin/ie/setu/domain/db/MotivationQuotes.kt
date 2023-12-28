package ie.setu.domain.db

import org.jetbrains.exposed.sql.Table

object MotivationQuotes : Table("healthytips ") {
    val id = integer("id").primaryKey()
    val tips = varchar("tips", 1000)
}