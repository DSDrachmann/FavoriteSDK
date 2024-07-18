package com.Dandd.favorite.internal.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.Dandd.favorite.domain.model.FavoriteEntity
import com.Dandd.favorite.domain.model.FavoriteEntityHighlight


//this is your interface, the interface that decides how you interact with the database
//The FavoriteRoomDatabase inherits this
@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setFavorite(favorite: FavoriteEntity): Long

    @Delete
    suspend fun removeFavorite(favorite: FavoriteEntity): Int

    @Query("Select * FROM favoriteEntity WHERE accountName = :inputAccountName AND entityType = :inputEntityType")
    suspend fun getFavorites(inputAccountName: String, inputEntityType: String): List<FavoriteEntity>

    @Query("Delete FROM favoriteEntity WHERE accountName = :inputAccountName AND entityType = :inputEntityType")
    suspend fun removeAllFavoritesOnEntityTypeForUser(inputAccountName: String, inputEntityType: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setFavoriteHighlight(favorite: FavoriteEntityHighlight): Long

    @Query("Select * FROM favoriteEntityHighlight WHERE accountName = :inputAccountName AND entityType = :inputEntityType")
    suspend fun getHighlights(inputAccountName: String, inputEntityType: String): List<FavoriteEntityHighlight>

    @Delete
    suspend fun removeFavoriteHighlight(favorite: FavoriteEntityHighlight): Int

    @Query("Delete FROM favoriteEntityHighlight WHERE accountName = :inputAccountName AND entityType = :inputEntityType")
    suspend fun removeAllFavoriteHighlightsOnEntityTypeForUser(inputAccountName: String, inputEntityType: String): Int
}
