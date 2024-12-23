package com.example.diaryapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.diaryapp.data.model.Task
import com.example.diaryapp.ui.components.AddButton
import com.example.diaryapp.ui.components.RoundedCornerTextField
import com.example.diaryapp.utils.validateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTaskScreen(onAddTask: (Task) -> Unit) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    var isDatePickerOpen by remember { mutableStateOf(false) }

    // Состояния для ошибок
    var titleError by remember { mutableStateOf<String?>(null) }
    var descriptionError by remember { mutableStateOf<String?>(null) }
    var startTimeError by remember { mutableStateOf<String?>(null) }
    var endTimeError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Новая задача", style = MaterialTheme.typography.titleLarge, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        RoundedCornerTextField(
            text = taskTitle,
            label = "Название",
            onValueChange = { taskTitle = it },
            errorMessage = titleError
        )

        Spacer(modifier = Modifier.height(8.dp))

        RoundedCornerTextField(
            text = taskDescription,
            label = "Описание",
            onValueChange = { taskDescription = it },
            maxLines = 7,
            singleLine = false,
            errorMessage = descriptionError
        )

        Spacer(modifier = Modifier.height(8.dp))

        RoundedCornerTextField(
            text = startTime,
            label = "Время начала (часы, 0-23)",
            onValueChange = { startTime = it },
            placeholder = "00:00",
            errorMessage = startTimeError
        )

        Spacer(modifier = Modifier.height(8.dp))

        RoundedCornerTextField(
            text = endTime,
            label = "Время окончания (часы, 0-23)",
            onValueChange = { endTime = it },
            placeholder = "00:00",
            errorMessage = endTimeError
        )

        Spacer(modifier = Modifier.height(8.dp))

        AddButton(
            text = "Выбрать дату: ${selectedDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))}",
            onClick = { isDatePickerOpen = true }
        )

        if (isDatePickerOpen) {
            DatePickerDialog(
                LocalContext.current,
                { _, year, month, dayOfMonth ->
                    selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                    isDatePickerOpen = false
                },
                selectedDate.year,
                selectedDate.monthValue - 1,
                selectedDate.dayOfMonth
            ).show()
        }

        Spacer(modifier = Modifier.height(16.dp))

        AddButton(
            text = "Добавить задачу",
            onClick = {
                // Сбрасываем ошибки
                titleError = null
                descriptionError = null
                startTimeError = null
                endTimeError = null

                if (taskTitle.isBlank()) {
                    titleError = "Название не может быть пустым"
                }
                if (taskDescription.isBlank()) {
                    descriptionError = "Описание не может быть пустым"
                }

                val startTimeValid = validateTime(startTime)
                val endTimeValid = validateTime(endTime)

                if (!startTimeValid) {
                    startTimeError = "Некорректное время начала"
                }
                if (!endTimeValid) {
                    endTimeError = "Некорректное время окончания"
                }

                if (startTimeValid && endTimeValid) {
                    val startTimeInt = startTime.split(":")[0].toInt()
                    val endTimeInt = endTime.split(":")[0].toInt()

                    if (startTimeInt >= endTimeInt) {
                        startTimeError = "Время начала должно быть меньше времени окончания"
                    }
                }

                if (titleError == null && descriptionError == null && startTimeError == null && endTimeError == null) {
                    val newTask = Task(
                        id = 0,
                        title = taskTitle,
                        description = taskDescription,
                        startTime = startTime.split(":")[0].toInt(),
                        endTime = endTime.split(":")[0].toInt(),
                        date = selectedDate
                    )
                    onAddTask(newTask)
                }
            },
            modifier = Modifier
        )
    }
}




