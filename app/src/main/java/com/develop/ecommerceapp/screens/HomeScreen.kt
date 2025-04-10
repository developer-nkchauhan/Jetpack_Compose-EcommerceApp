package com.develop.ecommerceapp.screens

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.develop.ecommerceapp.R
import com.develop.ecommerceapp.model.Category
import com.develop.ecommerceapp.viewmodel.MyViewModel

//@Preview(showBackground = true)
@Composable
fun MainScreen(innerPaddingValues: PaddingValues = PaddingValues.Absolute(),
   onCategoryClicked : (String) -> Unit,
   onCartClicked : () -> Unit
) {
    val viewModel: MyViewModel = hiltViewModel()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(innerPaddingValues)) {
        Column(modifier = Modifier.fillMaxSize()) {
            val itemList by viewModel.fetchCategories().observeAsState(mutableListOf())
//            val itemList = viewModel.fetchCategories().value

            Text(
                text = "Ecommerce App",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
            )

            Spacer(Modifier.height(30.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(itemList) { index , cat ->
                    CategoryItemView(cat) {
                        onCategoryClicked(cat.name)
                    }
                }
            }
            /*LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 80.dp), // Extra padding to prevent overlapping
            ) {
                items(itemList ?: emptyList()) { cat ->
                    CategoryItemView(cat) {
                        Toast.makeText(cont,"${cat.name} clicked..",Toast.LENGTH_LONG).show()
                    }
                }
            }*/
        }

        FloatingActionButton(
            onClick = onCartClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                /*.background( brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFF6F61), Color(0xFFFFD166)),
                    start = Offset(0f, 0f),
                    end = Offset(100f, 100f) // approximate 45° angle
                ),
                    shape = RoundedCornerShape(8.dp))*/
        ) {
            Image(ImageVector.vectorResource(R.drawable.shopping_cart), contentDescription = "Add to Cart")
        }
    }

}

@Composable
fun CategoryItemView(item: Category, onClick : () -> Unit ) {

    Card(
        modifier = Modifier
            .height(150.dp)
//        .width(100.dp)
            .wrapContentWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
//            .height(150.dp)
//    //        .width(100.dp)
//            .wrapContentWidth()
//            .padding(16.dp)
//            .clickable { onClick() }
            .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = item.catImage),
//                imageVector = ImageVector.vectorResource(item.catImage),
                modifier = Modifier.size(64.dp).align(Alignment.CenterHorizontally),
                contentDescription = "")

            Text(
                text = item.name,
                maxLines = 1,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
                    .wrapContentWidth(),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )

        }
    }
}
/*Column(modifier = Modifier.fillMaxSize()
        .padding(innerPaddingValues)
    ) {
        val itemsList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

        Text(
            text = "Ecommerce App",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 20.dp)
        )

        Spacer(Modifier.height(30.dp))

        LazyColumn (modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(itemsList) {item ->
                ItemView(item)
            }
        }

//        Spacer(modifier = Modifier.weight(1f))

        FloatingActionButton(onClick = {
            },
            modifier = Modifier.padding(16.dp).align(Alignment.End).height(72.dp).width(72.dp)
        ) {
            Image(ImageVector.vectorResource(R.drawable.shopping_cart), contentDescription = "Add to Cart")
        }
    }*/