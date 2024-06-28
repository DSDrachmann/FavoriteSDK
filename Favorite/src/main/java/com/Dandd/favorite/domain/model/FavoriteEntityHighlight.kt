package com.Dandd.favorite.domain.model

import androidx.room.Entity

//this is the basic entity (this also dictates the table)
@Entity (primaryKeys = ["entityId", "accountName", "entityType"])
data class FavoriteEntityHighlight(
    val entityId: String,
    val accountName: String,
    val entityType: String,
)
