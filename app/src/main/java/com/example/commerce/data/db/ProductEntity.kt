package com.example.commerce.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity (
    val imagePath: String,
    val price: Int,
    val subject: String,
    @PrimaryKey val id: Int,
    val name: String,
    val rate: Double,
    val thumbnail: String
        )