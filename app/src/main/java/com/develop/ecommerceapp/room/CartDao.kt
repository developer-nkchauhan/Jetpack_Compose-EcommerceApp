package com.develop.ecommerceapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query
import com.develop.ecommerceapp.model.Product
import com.develop.ecommerceapp.utils.Constants
import kotlinx.coroutines.flow.Flow


@Dao
// Define methods for interacting with Room Database
interface CartDao {

    @Insert
    suspend fun addToCart(cartItem : Product)

    // Here, Flow is already Async so suspend keyword is not there
    @Query("SELECT * FROM ${Constants.CART_TABLE_NAME}")
    fun getCartItems() : Flow<List<Product>>

    @Query("DELETE FROM ${Constants.CART_TABLE_NAME} WHERE id = :productId")
    suspend fun removeFromCart(productId : Int)

    @Query("DELETE FROM ${Constants.CART_TABLE_NAME}")
    suspend fun clearCart()
}