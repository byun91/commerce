package com.example.commerce.data.db

import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    suspend fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE id=:id")
    suspend fun getById(id: Int): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productList: List<ProductEntity>)

    @Query("DELETE FROM ProductEntity WHERE id=:id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM ProductEntity")
    suspend fun deleteAll()

    @Update
    suspend fun update(product: ProductEntity)

}