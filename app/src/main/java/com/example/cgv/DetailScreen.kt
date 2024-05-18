
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cgv.R

@Composable
fun DetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Image and Button Overlay
        Box(modifier = Modifier
            .height(450.dp)
            .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.hmovie1),
                contentDescription = "Movie Poster",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Button(
                onClick = { navController.navigate("seat")},
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD98639))
            ) {
                Text("Get Tickets")
            }
        }

        // Tags
        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("13+", fontSize = 12.sp, modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)).padding(5.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Action", fontSize = 12.sp, modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)).padding(5.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("IMAX", fontSize = 12.sp, modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)).padding(5.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("2h 13m", fontSize = 12.sp, modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)).padding(5.dp))
        }

        // Title and Description
        Column(modifier = Modifier.padding(20.dp)) {
            Text("AFTER", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("With Anna's identity now revealed, our friendly neighborhood web-slinger is unmasked and no longer able to separate his normal life as Peter Parker from the high stakes of being a superhero. When Peter asks for help from Doctor Strange, the stakes become even more dangerous, forcing him to discover what it...", fontSize = 16.sp, )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true, name = "My Preview")
//@Composable
//fun DetailScreenPreview() {
//    DetailScreen()
//}
