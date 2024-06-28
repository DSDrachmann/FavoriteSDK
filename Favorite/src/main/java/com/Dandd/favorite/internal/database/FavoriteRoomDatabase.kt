package com.Dandd.favorite.internal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.Dandd.favorite.domain.model.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FavoriteRoomDatabase: RoomDatabase() {
    abstract fun favoriteItemDao(): FavoriteDao
}