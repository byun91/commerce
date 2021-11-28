package com.example.commerce.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.commerce.data.response.Description
import com.example.commerce.data.response.Product

@Entity
data class ProductEntity (
    val imagePath: String,
    val price: Int,
    val subject: String,
    @PrimaryKey val id: Int,
    val name: String,
    val rate: Double,
    val thumbnail: String
        ){
    fun toProduct() : Product =
        Product(
            id = id,
            name = name,
            rate = rate,
            thumbnail = thumbnail,
            description = Description(imagePath,subject = subject,price = price)
        )
}