package com.example.cgv

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CinemaSeatSelectionScreen() {
    var selectedTimeSlot by remember { mutableStateOf("2:30 PM - 4:25 PM") }
    val seatRows = 6
    val seatColumns = 8
    val seatPrices = 30000
    var selectedSeats by remember { mutableStateOf(setOf<Pair<Int, Int>>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Choose Cinema & Seats",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Galaxy Mall",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TimeSlotButton(
                time = "2:30 PM - 4:25 PM",
                isSelected = selectedTimeSlot == "2:30 PM - 4:25 PM",
                onClick = { selectedTimeSlot = "2:30 PM - 4:25 PM" }
            )
            TimeSlotButton(
                time = "2:30 PM - 4:25 PM",
                isSelected = selectedTimeSlot == "2:30 PM - 4:25 PM",
                onClick = { selectedTimeSlot = "2:30 PM - 4:25 PM" }
            )

        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Seats",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Screen",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)){
            SeatSelectionGrid(
                seatRows = seatRows,
                seatColumns = seatColumns,
                selectedSeats = selectedSeats,
                onSeatSelected = { row, col ->
                    selectedSeats = if (selectedSeats.contains(row to col)) {
                        selectedSeats - (row to col)
                    } else {
                        selectedSeats + (row to col)
                    }
                }
            )
        }

        Text(
            text = "${selectedSeats.size} Seats",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Rp. ${selectedSeats.size * seatPrices}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Handle buy action */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD98639))
        ) {
            Text(text = "Buy Now")
        }
    }
}

@Composable
fun TimeSlotButton(time: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFFE87E23) else Color.Gray,
            contentColor = Color.White
        ),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = time)
    }
}

@Composable
fun SeatSelectionGrid(
    seatRows: Int,
    seatColumns: Int,
    selectedSeats: Set<Pair<Int, Int>>,
    onSeatSelected: (Int, Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        for (row in 0 until seatRows) {
            Row {
                for (col in 0 until seatColumns) {
                    if (col == 4) {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    val isSelected = selectedSeats.contains(row to col)
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .background(
                                color = when {
                                    isSelected -> Color(0xFFE87E23)
                                    else -> Color.LightGray
                                },
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clickable { onSeatSelected(row, col) }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "My Preview")
@Composable
fun CinemaSeatSelectionScreenPreview() {
    CinemaSeatSelectionScreen()
}
