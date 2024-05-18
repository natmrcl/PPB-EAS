package com.example.cgv

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


val movies = listOf(
    Pair("Movie 1", R.drawable.movie1),
    Pair("Movie 2", R.drawable.movie1),
    Pair("Movie 3", R.drawable.movie1),
    // dan seterusnya
)

val images = listOf(
    R.drawable.images1,
    R.drawable.images2,
    R.drawable.images3
    // Add your drawable resources here
)

@Composable
fun HomeScreen(navController: NavHostController) {
    Column {
//        Text(text = "Or sign in with")
        Spacer(modifier = Modifier.height(35.dp))
        // TAB ROW
        TabRow(
            selectedTabIndex = 0, // index of the selected tab
            indicator = { tabPositions ->
                // Custom indicator
                // You can customize the indicator as per your requirement
            },
            divider = {  }

        ) {
            // First tab
            Tab(selected = true, onClick = {}) {
                Text(text = "All Movies", color = Color(0xFFD98639))
            }
            // Second tab
            Tab(selected = false, onClick = {}) {
                Text(text = "My Tickets", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

        // CHIP GROUP
        Categories()
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "Now Showing",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .padding(start = 20.dp, end = 16.dp)
                .padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        MovieCardList(movies, navController)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Promo",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .padding(start = 20.dp, end = 16.dp)
        )
        AutoSlidingImageSlider(images = images)
        }
    }

@Composable
fun Categories() {
    val categories = listOf(
        "Action",
        "Comedy",
        "Romance",
        "Thriller",
        "Fantasy",
        "Sci-fi",
        "History",
        "Adventure",
    )
    val scrollState = rememberScrollState()
    var selectedCategoryIndex by remember { mutableStateOf(-1) } // -1 means no category is selected initially
    val selectedColor = Color(0xFFD98639) // Warna kuning
    val defaultBackgroundColor = Color.Black
    val defaultTextColor = Color.White

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {
        categories.forEachIndexed { index, category ->
            val backgroundColor = if (selectedCategoryIndex == index) selectedColor else defaultBackgroundColor
            val textColor = defaultTextColor
            Box(
                modifier = Modifier
                    .padding(
                        start = if (index == 0) 24.dp else 0.dp,
                        end = 12.dp,
                    )
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(backgroundColor)
                    .clickable { selectedCategoryIndex = index }
                    .padding(6.dp)
            ) {
                Text(
                    text = category,
                    color = textColor,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}

@Composable
fun MovieCard(movieName: String, imageRes: Int, navController: NavHostController) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .clickable(onClick = { navController.navigate("detail")}),

        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
//            modifier = Modifier.padding(16.dp)
        ) {
            // Gambar
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movieName,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun MovieCardList(movieList: List<Pair<String, Int>>, navController: NavHostController) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .fillMaxWidth()
            .padding(start = 15.dp)
    ) {
        for ((movie, imageRes) in movieList) {
            MovieCard(movie, imageRes, navController)
        }
    }
}

@Composable
fun AutoSlidingImageSlider(images: List<Int>, slideDuration: Long = 3000L) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = currentIndex) {
        delay(slideDuration)
        currentIndex = (currentIndex + 1) % images.size
        scope.launch {
            listState.animateScrollToItem(currentIndex)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(10 / 5f)
            .clip(RoundedCornerShape(8.dp))
            .padding(15.dp)
    ) {
        LazyRow(state = listState) {
            items(images.size) { index ->
                Image(
                    painter = painterResource(id = images[index]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillParentMaxSize()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}



//@Preview(showBackground = true, showSystemUi = true, name = "My Preview" )
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}
