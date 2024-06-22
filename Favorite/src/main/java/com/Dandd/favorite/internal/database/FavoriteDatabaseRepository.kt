package com.Dandd.favorite.internal.database

import com.Dandd.favorite.internal.database.model.FavoriteEntity

//this is the class that you interact with that interacts with the object that interacts with the database.

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
}