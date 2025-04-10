package com.develop.ecommerceapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.develop.ecommerceapp.model.Product
import com.develop.ecommerceapp.viewmodel.MyViewModel

@Composable
fun CategoryItemsMainScreen(innerPadding : PaddingValues,
    viewModel : MyViewModel,
    catName : String ,
    onProdClicked : (Product) -> Unit
) {
    Column(modifier = Modifier.padding(innerPadding)) {

        val itemList by viewModel.fetchProducts(catName).observeAsState(mutableListOf())

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(itemList) { index , prod ->
                CategoryItemsView(prod) {
                    onProdClicked(prod)
                }
            }
        }
    }
}

@Composable
private fun CategoryItemsView(item : Product, onClick : () -> Unit) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){

        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ){
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 8.dp)
                    .wrapContentWidth(),
                textAlign = TextAlign.Start,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                text = item.title,
                maxLines = 1,
                fontSize = 16.sp,
            )

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(top = 4.dp, start = 8.dp, bottom = 4.dp)
                    .wrapContentWidth(),
                textAlign = TextAlign.Start,
                color = Color.DarkGray,
                text = "${item.price} $",
                fontSize = 14.sp,
            )
        }

    }
}