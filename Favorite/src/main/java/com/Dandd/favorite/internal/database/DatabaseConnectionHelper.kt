package internal.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnectionHelper(nameOfDatabase: String) {
    private var connection: Connection? = null

    init {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:$nameOfDatabase.db")
        } catch (e: SQLException) {
            println("this is a test")
            println(e.message)
        }
    }

    fun getConnection(): Connection? {
        return connection
    }
}
