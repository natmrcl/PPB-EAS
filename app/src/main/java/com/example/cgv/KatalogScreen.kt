package com.example.cgv

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

data class Product(val imageRes: Int, val title: String, val price: String, val originalPrice: String, val discount: String)

@Composable
fun KatalogScreen(navController: NavHostController) {
    val products = listOf(
        Product(R.drawable.product1, "Artsy", "Rp. 140.000", "Rp. 165.000", "52% Off"),
        Product(R.drawable.product3, "Prince", "Rp. q40.000", "Rp. q65.000", "52% Off"),
        Product(R.drawable.product4, "Bjorg", "Rp. 40.000", "Rp. 65.000", "52% Off"),
        Product(R.drawable.product2, "Capucinus", "Rp. 200.000", "Rp. 250.000", "52% Off"),
        Product(R.drawable.nuttela, "Bjorg", "Rp. 40.000", "Rp. 65.000", "52% Off"),
        Product(R.drawable.nuttela, "Danone", "Rp. 40.000", "Rp. 65.000", "52% Off")
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoalfamind),
            contentDescription = "Aflamind Logo",
            modifier = Modifier
                .height(65.dp)
                .padding(end = 16.dp), // Padding di sebelah kanan logo
            contentScale = ContentScale.Fit
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Gray.copy(alpha = 0.1f), // Color abu dengan opacity 0.1
                    shape = RoundedCornerShape(8.dp) // Border radius 8.dp
                )
                .padding(7.dp)
        ) {
            Text(
                text = "Tambahkan katalog",
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp, start = 10.dp)
            )
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor  = Color.Red)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        val selectedCategory = remember { mutableStateOf("All") }
        val categories = listOf("All", "Top", "Bottoms", "Jackets", "Bag")

        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            categories.forEach { category ->
                Text(
                    text = category,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .clickable {
                            selectedCategory.value = category
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = if (category == selectedCategory.value) Color(android.graphics.Color.parseColor("#2382AA")) else Color.Gray,
                        textDecoration = if (category == selectedCategory.value) TextDecoration.Underline else TextDecoration.None
                    )
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products.size) { index ->
                ProductItem(products[index])
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .height(120.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)) {
                Text(text = product.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = product.price, color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = product.originalPrice,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough,
                        style = androidx.compose.ui.text.TextStyle()
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = product.discount, color = Color(android.graphics.Color.parseColor("#2382AA")))
                }
            }
        }
    }
}

@Composable
fun BottomMenu(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomMenuItem(navController = navController, route = "home", label = "Home")
        BottomMenuItem(navController = navController, route = "profile", label = "Profile")
    }
}

@Composable
fun BottomMenuItem(navController: NavController, route: String, label: String) {
    Button(
        onClick = { navController.navigate(route) },
//        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}