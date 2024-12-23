package com.example.diaryapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaryapp.data.Task

@Composable
fun TaskItem(task: Task, onTaskClicked: (Task) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onTaskClicked(task) },
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = task.title, color = Color.Black, fontSize = 20.sp)
            Text(text = task.description, color = Color.Blue, fontSize = 16.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Начало: ${task.startTime}:00", color = Color.Black, fontSize = 12.sp)
                Text(text = "Конец: ${task.endTime}:00", color = Color.Black, fontSize = 12.sp)
            }
        }
    }
}