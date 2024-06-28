package com.Dandd.favorite.domain

import android.content.Context
import androidx.room.Room
import com.Dandd.favorite.internal.database.FavoriteRoomDatabase

//this provides the database, that is, it creates it and returns it.
//this is the first class you create when you want to instantiate the method
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
