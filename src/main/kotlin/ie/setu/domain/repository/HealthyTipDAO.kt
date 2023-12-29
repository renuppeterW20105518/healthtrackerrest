package ie.setu.domain.repository

import ie.setu.domain.HealthyTip
import ie.setu.domain.db.HealthyTips
import ie.setu.utils.mapToHealthyTip
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.random.Random
import org.jetbrains.exposed.sql.*

class HealthyTipDAO {
    fun getAllTips(): ArrayList<HealthyTip> {
        val tipList: ArrayList<HealthyTip> = arrayListOf()
        transaction {
            HealthyTips.selectAll().map {
                tipList.add(mapToHealthyTip(it))}
        }
        return tipList
    }

    fun save(healthytip: HealthyTip): Int{
        return transaction {
            HealthyTips.insert {
                it[tips]=healthytip.tips
            }
        }get HealthyTips.id
    }

    fun update(id: Int, tip: HealthyTip): Int{
        return transaction {
            HealthyTips.update ({
                HealthyTips.id eq id}) {
                it[tips] = tip.tips
            }
        }
    }

    fun delete (tipId: Int): Int{
        return transaction{
            HealthyTips.deleteWhere { HealthyTips.id eq tipId }
        }
    }

    fun getRandom(): List<HealthyTip> {
        val tablesize = getAllTips().size
        if (tablesize > 1) {
            val randomId = Random.nextInt(1, tablesize)
            return transaction {
                HealthyTips
                    .select { HealthyTips.id eq randomId }
                    .map { mapToHealthyTip(it) }
            }
        } else {
            return transaction {
                HealthyTips
                    .select { HealthyTips.id eq 1 }
                    .map { mapToHealthyTip(it) }
            }
        }
    }

}