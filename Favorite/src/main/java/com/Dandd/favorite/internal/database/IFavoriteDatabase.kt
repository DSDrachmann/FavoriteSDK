package internal.database

import domain.model.FavoriteModel

interface IFavoriteDatabase {
    fun getFavorites(accountName: String, entityType: String): List<FavoriteModel>
    fun setFavorite(favorite: FavoriteModel)
    fun removeFavorite(favorite: FavoriteModel)
}