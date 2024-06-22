package domain

import domain.model.FavoriteModel

interface IFavorite {
    fun getFavorites(accountName: String, entityType: String): List<FavoriteModel>
    fun setFavorite(favorite: FavoriteModel) : Boolean
    fun removeFavorite(favorite: FavoriteModel) : Boolean
}
