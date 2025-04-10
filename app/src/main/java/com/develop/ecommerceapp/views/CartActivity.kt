package com.develop.ecommerceapp.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Scaffold
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.develop.ecommerceapp.R
import com.develop.ecommerceapp.model.Product
import com.develop.ecommerceapp.screens.CartScreen
import com.develop.ecommerceapp.ui.theme.EcommerceAppTheme
import com.develop.ecommerceapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()

    private val onClearCartBtnClick : ( () -> Unit )= {
        viewModel.clearCart()
    }

    private val onCartItemDeleteBtnClick : (Product) -> Unit = {
        viewModel.removeFromCart(it.id)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceAppTheme {
//                Scaffold {
                    CartScreen(viewModel,onClearCartBtnClick,onCartItemDeleteBtnClick)
//                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCartItems()
    }
}