package com.Dandd.favorite.internal.database

import android.content.Context
import androidx.room.Room

//this provides the database, that is, it creates it and returns it.
object favoriteDatabaseProvider {
    private var instance: FavoriteRoomDatabase? = null

    fun getInstance(context: Context): FavoriteRoomDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also {instance = it}
        }
    }

    private fun buildDatabase(context: Context): FavoriteRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FavoriteRoomDatabase::class.java,
            "favorite_clock_database"
        ).build()
    }
}