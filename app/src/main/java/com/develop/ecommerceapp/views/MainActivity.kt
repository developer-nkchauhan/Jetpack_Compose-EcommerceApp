package com.develop.ecommerceapp.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.develop.ecommerceapp.screens.MainScreen
import com.develop.ecommerceapp.ui.theme.EcommerceAppTheme
import com.develop.ecommerceapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(innerPadding,onCategoryClicked,onCartButtonClicked)
                }
            }
        }
    }

    private val onCategoryClicked : (String) -> Unit = { catName ->
        val intent = Intent(this,CategoryItemsActivity::class.java)
        intent.putExtra(Constants.KEY_CATEGORY_NAME,catName)
        startActivity(intent)
    }

    private val onCartButtonClicked : () -> Unit = {
        val intent = Intent(this,CartActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcommerceAppTheme {
        Greeting("Android")
    }
}