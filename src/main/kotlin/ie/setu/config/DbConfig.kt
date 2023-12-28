package ie.setu.config

import org.jetbrains.exposed.sql.Database
import mu.KotlinLogging
import org.jetbrains.exposed.sql.name

class DbConfig{

    private val logger = KotlinLogging.logger {}

    fun getDbConnection() :Database{

		logger.info{"Starting DB Connection..."}

        val PGHOST = "trumpet.db.elephantsql.com"
        val PGPORT = "5432"
        val PGUSER = "csdicsau"
        val PGPASSWORD = "KxRJaRJKBoeEaoWn2ktiQLTqVw4Tkc_2"
        val PGDATABASE = "csdicsau"

        //url format should be jdbc:postgresql://host:port/database
        val dbUrl = "jdbc:postgresql://$PGHOST:$PGPORT/$PGDATABASE"

        val dbConfig = Database.connect(
            url = dbUrl,
            driver="org.postgresql.Driver",
            user = PGUSER,
            password = PGPASSWORD
        )

        logger.info{"DbConfig name = " + dbConfig.name}
        logger.info{"DbConfig url = " + dbConfig.url}

        return dbConfig
    }
}
