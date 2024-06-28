package com.Dandd.favorite.internal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.Dandd.favorite.domain.model.FavoriteEntity
import com.Dandd.favorite.domain.model.FavoriteEntityHighlight

@Database(entities = [FavoriteEntity::class, FavoriteEntityHighlight::class], version = 2, exportSchema = false)
abstract class FavoriteRoomDatabase: RoomDatabase() {
    abstract fun favoriteItemDao(): FavoriteDao
}