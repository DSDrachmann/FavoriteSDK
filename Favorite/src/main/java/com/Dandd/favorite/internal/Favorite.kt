package internal

import domain.IFavorite
import domain.model.FavoriteModel
import internal.database.FavoriteDatabaseHelper

class Favorite(
    private val db: FavoriteDatabaseHelper
): IFavorite {

    init {
        db.createTable()
    }

    override fun getFavorites(accountName: String, entityType: String): List<FavoriteModel> {
        val favorites = db.getFavorites(accountName, entityType)
        return favorites
    }

    override fun setFavorite(favorite: FavoriteModel): Boolean {
        try {
            db.setFavorite(favorite)
            return true
        } catch (e: Exception)
        {
            //log exception
            return false
        }
    }

    override fun removeFavorite(favorite: FavoriteModel): Boolean {
        try {
            db.removeFavorite(favorite)
            return true
        } catch (e: Exception) {
            //log exception
            return false
        }
    }
}
