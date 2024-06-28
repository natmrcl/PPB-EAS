package com.example.cgv

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Profile section
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.movie1),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Gray, shape = CircleShape)
                    .clip(CircleShape)
                    .padding(2.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "XWZ Mart",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "selleroperations",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { /*TODO: Handle visit store*/ }, colors = ButtonDefaults.buttonColors(containerColor  = Color.Red)) {
                Text(text = "Kunjungi Toko")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)

        // Order Status and Sales History section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Status Pesanan", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Riwayat Penjualan", color = Color.Red, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderStatusItem("10", "Perlu Dikirim")
            OrderStatusItem("0", "Pembatalan")
            OrderStatusItem("0", "Pengembalian")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)

        val menuItems = listOf(
            MenuItem(iconId = R.drawable.produk, label = "Produk"),
            MenuItem(iconId = R.drawable.icon2, label = "Keuangan"),
            MenuItem(iconId = R.drawable.icon3, label = "Performa Toko"),
            MenuItem(iconId = R.drawable.icon4, label = "Promosi Toko"),
            MenuItem(iconId = R.drawable.icon5, label = "Program Penjual"),
            MenuItem(iconId = R.drawable.icon6, label = "Pusat Bantuan")
        )
        MenuGrid(items = menuItems)

    }
}

@Composable
fun OrderStatusItem(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
    }
}

data class MenuItem(val iconId: Int, val label: String)

@Composable
fun MenuGrid(items: List<MenuItem>) {
    Column {
        val rows = items.chunked(3)
        rows.forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {
                rowItems.forEach { item ->
                    MenuItemCard(item)
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(item: MenuItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = item.iconId),
            contentDescription = item.label,
            modifier = Modifier.size(64.dp)
        )
        Text(text = item.label, fontSize = 14.sp, color = Color.Gray)
    }
}