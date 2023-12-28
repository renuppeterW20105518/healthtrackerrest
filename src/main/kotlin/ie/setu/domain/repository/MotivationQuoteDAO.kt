package ie.setu.domain.repository

import ie.setu.domain.MotivationQuote
import ie.setu.domain.db.MotivationQuotes
import ie.setu.domain.db.Trainees
import ie.setu.utils.mapToMotivationQuote
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.random.Random
import org.jetbrains.exposed.sql.*

class MotivationQuoteDAO {
    fun getAllquotes(): ArrayList<MotivationQuote> {
        val quoteList: ArrayList<MotivationQuote> = arrayListOf()
        transaction {
            Trainees.selectAll().map {
                quoteList.add(mapToMotivationQuote(it))}
        }
        return quoteList
    }

    fun save(motivationquote: MotivationQuote): Int{
        return transaction {
            MotivationQuotes.insert {
                it[tips]=motivationquote.tips
            }
        }get MotivationQuotes.id
    }



    fun update(id: Int, quote: MotivationQuote): Int{
        return transaction {
            MotivationQuotes.update ({
                MotivationQuotes.id eq id}) {
                it[tips] = quote.tips
            }
        }
    }

    fun delete (quoteId: Int): Int{
        return transaction{
            MotivationQuotes.deleteWhere { MotivationQuotes.id eq quoteId }
        }
    }

    fun getRandom(): List<MotivationQuote> {
        val tablesize = getAllquotes().size
        if (tablesize > 1) {
            val randomId = Random.nextInt(1, tablesize)
            return transaction {
                MotivationQuotes
                    .select { MotivationQuotes.id eq randomId }
                    .map { mapToMotivationQuote(it) }
            }
        } else {
            return transaction {
                MotivationQuotes
                    .select { MotivationQuotes.id eq 1 }
                    .map { mapToMotivationQuote(it) }
            }
        }
    }

}