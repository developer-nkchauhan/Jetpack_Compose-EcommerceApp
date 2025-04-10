package com.develop.ecommerceapp.views

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import com.develop.ecommerceapp.model.Product
import com.develop.ecommerceapp.screens.ProductDetailScreen
import com.develop.ecommerceapp.ui.theme.EcommerceAppTheme
import com.develop.ecommerceapp.utils.Constants
import com.develop.ecommerceapp.utils.Constants.showToast
import com.develop.ecommerceapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsActivity : ComponentActivity() {

    val viewModel : MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val prod : Product? = if(Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(Constants.KEY_PRODUCT_DETAILS,Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(Constants.KEY_PRODUCT_DETAILS)
        }
        setContent {
            EcommerceAppTheme {
                Scaffold {
                    ProductDetailScreen(it,viewModel,prod) { product ->
                        addToCart(product)
                    }
                }
            }
        }
    }

    private fun addToCart(product: Product) {
        viewModel.addProductToCart(product)
        this.showToast("${product.title} added to Cart",0)
    }
}