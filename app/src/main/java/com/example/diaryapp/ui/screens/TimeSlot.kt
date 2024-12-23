package com.example.diaryapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaryapp.data.Task

@Composable
fun TimeSlot(hour: Int, tasks: List<Task>, onTaskClicked: (Task) -> Unit) {
    val timeSlot = String.format("%02d:00 - %02d:00", hour, hour + 1)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = timeSlot,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = Color.Black
        )

        // Отображаем задачи для текущего часа
        tasks.forEach { task ->
            TaskItem(task, onTaskClicked)
        }
    }
}