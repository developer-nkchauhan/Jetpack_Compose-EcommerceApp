package com.develop.ecommerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.ecommerceapp.model.Category
import com.develop.ecommerceapp.model.Product
import com.develop.ecommerceapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _cartItem = MutableStateFlow<List<Product>>(emptyList())
    val cartItems : StateFlow<List<Product>> = _cartItem

    // Fetch Categories
    fun fetchCategories(): MutableLiveData<List<Category>> {
        return repository.fetchCategories()
    }

    // Fetch Products by Category
    fun fetchProducts(categoryName : String) : MutableLiveData<List<Product>> {
        return repository.fetchProducts(categoryName)
    }

    // Room :: Insert Product in Cart
    fun addProductToCart(product: Product) = viewModelScope.launch {
        repository.addProductToCart(product)
    }

    // Room :: get All Cart Items
    fun getCartItems() = viewModelScope.launch {
        repository.getCartItems().collect { items ->
            _cartItem.value = items
        }
    }

    // Room :: remove specific Item from Cart
    fun removeFromCart(id : Int) = viewModelScope.launch {
        repository.removeFromCart(id)
    }

    // Room :: remove all Cart Items
    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
    }
}