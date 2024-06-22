package com.Dandd.favorite.internal.database.model

import androidx.room.Entity

//this is the basic entity (this also dictates the table)
@Entity (primaryKeys = ["entityId", "accountName", "entityType"])
data class FavoriteEntity(
    val entityId: String,
    val accountName: String,
    val entityType: String
)
