package com.develop.ecommerceapp.utils

import android.content.Context
import android.widget.Toast

object Constants {

    const val TAG_LOG = "TAGY"
    const val KEY_CATEGORY_NAME = "CATEGORY_NAME"
    const val KEY_PRODUCT_DETAILS = "product_details"
    const val CART_TABLE_NAME = "cart_items"
    const val CART_DB_NAME = "cart_database"

    fun Context.showToast(msg : String,length : Int) {
        if(length == 0){
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        }
    }
}

