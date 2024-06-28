package com.Dandd.favorite.domain

import com.Dandd.favorite.domain.model.DatabaseOperationException
import com.Dandd.favorite.domain.model.FavoriteEntity
import com.Dandd.favorite.internal.database.FavoriteRoomDatabase

//this is the class that you interact with that interacts with the object that interacts with the database.
//So first create the favoriteDatabaseProvider and give that database result to this class.

class FavoriteDatabaseRepository(private val database: FavoriteRoomDatabase){
    private val favoriteDao = database.favoriteItemDao()

    suspend fun insertFavoriteEntity(item: FavoriteEntity) {
        try {
            favoriteDao.setFavorite(item)
        } catch (e: Exception) {
            val message = "an insert related error on set a favorite happened, see exception: $e, it happened on accountName: ${item.accountName} and entityType: ${item.entityType} and entityId: ${item.entityId}"
            throw DatabaseOperationException(message, e)
        }
    }

    suspend fun removeFavoriteEntity(item: FavoriteEntity) {
        try {
            favoriteDao.removeFavorite(item)
        } catch (e: Exception) {
            val message = "a remove related error on single remove a favorite happened, see exception: $e, it happened on accountName: ${item.accountName} and entityType: ${item.entityType} and entityId: ${item.entityId}"
            throw DatabaseOperationException(message, e)
        }
    }

    suspend fun getFavorites(inputAccountName: String, inputEntityType: String): List<FavoriteEntity> {
        try {
            return favoriteDao.getFavorites(inputAccountName, inputEntityType)
        } catch (e: Exception) {
            val message = "a get query related error on get all favorites happened, see exception: $e, it happened on accountName: $inputAccountName and entityType: $inputEntityType"
            throw DatabaseOperationException(message, e)
        }
    }

    suspend fun removeAllFavorites(inputAccountName: String, inputEntityType: String) {
        try {
            favoriteDao.removeAllFavorites(inputEntityType = inputEntityType, inputAccountName = inputAccountName)
        } catch (e: Exception) {
            val message = "a remove related error on remove all favorites happened, see exception: $e, it happened on accountName: $inputAccountName and entityType: $inputEntityType"
            throw DatabaseOperationException(message, e)
        }
    }
}
