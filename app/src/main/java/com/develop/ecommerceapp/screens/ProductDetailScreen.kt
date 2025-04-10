package com.develop.ecommerceapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.develop.ecommerceapp.R
import com.develop.ecommerceapp.model.Product
import com.develop.ecommerceapp.viewmodel.MyViewModel

@Composable
fun ProductDetailScreen(paddingValues: PaddingValues,
    viewModel : MyViewModel,
    product: Product?,
    addToCartButtonClicked : (Product) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ){
        AsyncImage(
            model = product?.imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 20.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.White),
        )

        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .wrapContentHeight()
                .wrapContentWidth(),
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            text = product?.title!!,
            overflow = TextOverflow.Ellipsis,
//            maxLines = 1,
            fontSize = 16.sp,
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, start = 16.dp, bottom = 16.dp)
                .wrapContentWidth(),
            textAlign = TextAlign.Start,
            color = colorResource(R.color.purple_700),
            fontWeight = FontWeight.SemiBold,
            text = "${ product.price } $" ,
            fontSize = 14.sp,
        )

        ElevatedButton(
            onClick = { addToCartButtonClicked(product) },
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp, start = 16.dp),
            colors = ButtonColors(Color.Black,Color.White,Color.DarkGray,Color.White)
        ) {
            Text(text = "Add to Cart")
        }
    }

}