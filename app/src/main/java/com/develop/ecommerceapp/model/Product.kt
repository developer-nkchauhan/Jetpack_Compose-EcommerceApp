package com.develop.ecommerceapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.develop.ecommerceapp.utils.Constants
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.CART_TABLE_NAME)
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val title : String = "",
    val price : Double = 0.0,
    val imageUrl : String = ""
) : Parcelable
