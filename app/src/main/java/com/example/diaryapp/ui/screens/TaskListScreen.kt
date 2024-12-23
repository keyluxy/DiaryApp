package com.example.diaryapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.diaryapp.data.model.Task
import com.example.diaryapp.ui.components.AddButton
import com.example.diaryapp.ui.components.TimeSlot
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onAddTaskTriggered: () -> Unit,
    onTaskClicked: (Task) -> Unit
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text(text = "Задачи на ${selectedDate.format(dateFormatter)}", style = MaterialTheme.typography.titleMedium, color = Color.Black)

            Spacer(modifier = Modifier.height(16.dp))

            com.example.diaryapp.ui.components.DatePicker(
                date = selectedDate,
                onDateChange = { selectedDate = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(24) { hour ->
                    val tasksForHour = tasks.filter { task ->
                        task.startTime <= hour && task.endTime > hour && task.date == selectedDate
                    }
                    TimeSlot(hour, tasksForHour, onTaskClicked)
                }
            }
        }

        AddButton(
            text = "Новая задача",
            onClick = onAddTaskTriggered,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}


