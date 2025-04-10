package com.develop.ecommerceapp.di.module

import android.content.Context
import androidx.room.Room
import com.develop.ecommerceapp.repository.Repository
import com.develop.ecommerceapp.room.CartDao
import com.develop.ecommerceapp.room.CartDatabase
import com.develop.ecommerceapp.viewmodel.MyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): CartDatabase {
        return CartDatabase.getCartDatabase(context)
    }

    @Provides
    fun provideCartDao(cartDB: CartDatabase): CartDao {
        return cartDB.cartDao()
    }
}