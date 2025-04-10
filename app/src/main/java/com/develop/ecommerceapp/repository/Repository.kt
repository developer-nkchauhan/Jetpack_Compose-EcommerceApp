package com.develop.ecommerceapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.develop.ecommerceapp.R
import com.develop.ecommerceapp.model.Category
import com.develop.ecommerceapp.model.Product
import com.develop.ecommerceapp.room.CartDao
import com.develop.ecommerceapp.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Centralized data operation (FireStore & Room)
class Repository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val cartDao: CartDao
){

    fun fetchCategories() : MutableLiveData<List<Category>>{
        val categoryList = MutableLiveData<List<Category>>()

        val catImages = mapOf(
            "Electronics" to R.drawable.electronics,
            "Jewelery" to R.drawable.jewelery,
            "Men's clothing" to R.drawable.mensclothing,
            "Women's clothing" to R.drawable.womenclothing,
            "Footwear" to R.drawable.runningshoes,
            "Furniture" to R.drawable.sofa,
            "Toy" to R.drawable.toys,
            "Cosmetic" to R.drawable.cosmetics,
            "Automobile Parts" to R.drawable.brake
        )

        // fetching data from Firestore
        firestore.collection("categories")
            .get() // retrieves data Asynchronously
            .addOnSuccessListener { documents ->
                val category = documents.map { doc ->
                    val imgRes = catImages.get(doc.id) ?: R.drawable.ic_launcher_background

                    Category(name = doc.id,catImage = imgRes)
                }
                categoryList.postValue(category)
                Log.v(Constants.TAG_LOG,"Category : $categoryList")
            }
        return categoryList;

    }


    fun fetchProducts(categoryName : String) : MutableLiveData<List<Product>> {

        val productList = MutableLiveData<List<Product>>()

        firestore.collection("categories")
            .document(categoryName)
            .collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val product = documents.map { document ->
                    Product(
                        id = 0,
                        title = document.getString("title") ?: "",
                        price = document.getDouble("price") ?: 0.0,
                        imageUrl = document.getString("imageUrl") ?: "",
                    )
                }
                productList.postValue(product)
                Log.v(Constants.TAG_LOG,"Product List = $productList")
            }
            .addOnFailureListener { exc ->
                Log.e(Constants.TAG_LOG,exc.message,exc)
            }
        return productList
    }

    // Room :: Insert Product in Cart
    suspend fun addProductToCart(product: Product) {
        cartDao.addToCart(product)
    }

    // Room :: get All Cart Items
    suspend fun getCartItems() : Flow<List<Product>> {
        return cartDao.getCartItems()
    }

    // Room :: remove specific Item from Cart
    suspend fun removeFromCart(id : Int) {
        cartDao.removeFromCart(id)
    }

    // Room :: remove all Cart Items
    suspend fun clearCart() {
        cartDao.clearCart()
    }

}