package com.Dandd.favorite.internal.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.Dandd.favorite.internal.database.model.FavoriteEntity


//this is your interface, the interface that decides how you interact with the database
@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun removeFavorite(favorite: FavoriteEntity)

    @Query("Select * FROM favoriteEntity WHERE accountName = :inputAccountName AND entityType = :inputEntityType")
    suspend fun getFavorites(inputAccountName: String, inputEntityType: String): List<FavoriteEntity>
}