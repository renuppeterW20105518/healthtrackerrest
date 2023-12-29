package ie.setu.domain

import org.joda.time.DateTime

data class Bmi(
    var id: Int?,
    var weight: Double,
    var height: Double,
    var userId: Int,
    val bmiCalculator: Double,
    var timestamp: DateTime
)


