package com.develop.ecommerceapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.develop.ecommerceapp.model.Product
import com.develop.ecommerceapp.utils.Constants

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class CartDatabase :  RoomDatabase() {
    abstract fun cartDao() : CartDao

    companion object {
        @Volatile
        private var INSTANCE : CartDatabase? = null


        fun getCartDatabase(context : Context) : CartDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    Constants.CART_DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}