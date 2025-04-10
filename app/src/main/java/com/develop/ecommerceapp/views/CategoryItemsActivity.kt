package com.develop.ecommerceapp.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.develop.ecommerceapp.model.Product
import com.develop.ecommerceapp.screens.CategoryItemsMainScreen
import com.develop.ecommerceapp.ui.theme.EcommerceAppTheme
import com.develop.ecommerceapp.utils.Constants
import com.develop.ecommerceapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryItemsActivity : ComponentActivity() {

    private val viewModel : MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val categoryName = intent.getStringExtra(Constants.KEY_CATEGORY_NAME) ?: ""
        setContent {
            EcommerceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CategoryItemsMainScreen(innerPadding,viewModel,categoryName) { prodName ->
                        startProductDetailActivity(prodName)
//                        this.showToast("$prodName clicked..",1)
                    }
                }
            }
        }
    }

    private fun startProductDetailActivity(product: Product) {
        val prodDetailIntent = Intent(this,ProductDetailsActivity::class.java)
        prodDetailIntent.putExtra(Constants.KEY_PRODUCT_DETAILS,product)
        startActivity(prodDetailIntent)
    }
}