package com.Dandd.favorite.domain

import com.Dandd.favorite.domain.model.FavoriteEntity
import com.Dandd.favorite.internal.database.FavoriteRoomDatabase

//this is the class that you interact with that interacts with the object that interacts with the database.
//So first create the favoriteDatabaseProvider and give that database result to this class.

class FavoriteDatabaseRepository(private val database: FavoriteRoomDatabase){
    private val favoriteDao = database.favoriteItemDao()

    suspend fun insertFavoriteEntity(item: FavoriteEntity) {
        favoriteDao.setFavorite(item)
    }

    suspend fun removeFavoriteEntity(item: FavoriteEntity) {
        favoriteDao.removeFavorite(item)
    }

    suspend fun getFavorites(inputAccountName: String, inputEntityType: String): List<FavoriteEntity> {
        return favoriteDao.getFavorites(inputAccountName, inputEntityType)
    }

    suspend fun removeAllFavorites(inputAccountName: String, inputEntityType: String) {
        favoriteDao.removeAllFavorites(inputEntityType = inputEntityType, inputAccountName = inputAccountName)
    }
}