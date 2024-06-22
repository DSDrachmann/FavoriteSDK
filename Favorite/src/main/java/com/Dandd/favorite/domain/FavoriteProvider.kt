package domain

import internal.Favorite
import internal.database.DatabaseConnectionHelper
import internal.database.FavoriteDatabaseHelper

object FavoriteProvider {

    fun createFavorite(): IFavorite {
//        val helper = DatabaseConnectionPostGreSQLHelper("jdbc:postgresql://localhost:5432/mydatabase", "myuser", "mypassword")
//        if(helper.getConnection() == null) throw Exception("Database connection failed")
//        val favoriteDatabasePostGreSQLHelper = FavoriteDatabaseHelper(helper.getConnection()!!)
//        return Favorite(favoriteDatabasePostGreSQLHelper)

        val database = DatabaseConnectionHelper("favorite")
        if(database.getConnection() == null) throw Exception("Database connection failed")

        val favoriteDatabaseHelper = FavoriteDatabaseHelper(database.getConnection()!!)
        return Favorite(favoriteDatabaseHelper)
    }
}