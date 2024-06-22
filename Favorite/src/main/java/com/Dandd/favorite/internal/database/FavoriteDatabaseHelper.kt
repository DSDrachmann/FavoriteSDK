package internal.database

import domain.model.FavoriteModel
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class FavoriteDatabaseHelper(val connection: Connection): IFavoriteDatabase {
    fun createTable() {
        val statement = connection?.createStatement()
        statement?.executeUpdate(
            "CREATE TABLE IF NOT EXISTS Favorite (" +
                    "entityId TEXT, " +
                    "accountName TEXT, " +
                    "entityType TEXT, " +
                    "PRIMARY KEY(entityId, accountName, entityType))"
        )
    }

    override fun setFavorite(favorite: FavoriteModel) {
        val preparedStatement = connection?.prepareStatement(
            "INSERT INTO FavoriteDBEntity (entityId, accountName, entityType) VALUES (?, ?, ?)"
        )
        preparedStatement?.setString(1, favorite.entityId)
        preparedStatement?.setString(2, favorite.accountName)
        preparedStatement?.setString(3, favorite.entityType)
        preparedStatement?.executeUpdate()
    }

    override fun removeFavorite(favorite: FavoriteModel) {
        val preparedStatement = connection?.prepareStatement(
            "DELETE FROM Favorite WHERE entityId = ? AND accountName = ? AND entityType = ?"
        )
        preparedStatement?.setString(1, favorite.entityId)
        preparedStatement?.setString(2, favorite.accountName)
        preparedStatement?.setString(3, favorite.entityType)
        preparedStatement?.executeUpdate()
    }

    override fun getFavorites(accountName: String, entityType: String): List<FavoriteModel> {
        val statement = connection?.createStatement()
        val resultSet = statement?.executeQuery("SELECT * FROM FavoriteDBEntity WHERE accountName = $accountName AND entityType = $entityType")
        val favorites = mutableListOf<FavoriteModel>()
        while (resultSet?.next() == true) {
            val entityId = resultSet.getString("entityId")
            val accountName = resultSet.getString("accountName")
            val entityType = resultSet.getString("entityType")
            favorites.add(FavoriteModel(entityId, accountName, entityType))
        }
        return favorites
    }
}