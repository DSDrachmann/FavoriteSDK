package com.Dandd.favorite.internal.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.Dandd.favorite.domain.model.FavoriteEntity


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
    suspend fun removeAllFavorites(inputAccountName: String, inputEntityType: String)
}