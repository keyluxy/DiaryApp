package com.example.diaryapp.ui.screens

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun TaskDetailScreen(
    task: Task,
    onSaveTask: (Task) -> Unit,
    onDeleteTask: (Task) -> Unit,
    onBack: () -> Unit
) {
    var taskTitle by remember { mutableStateOf(task.title) }
    var taskDescription by remember { mutableStateOf(task.description) }
    var startTime by remember { mutableStateOf(task.startTime.toString()) }
    var endTime by remember { mutableStateOf(task.endTime.toString()) }
    var selectedDate by remember { mutableStateOf(task.date) }
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
        Text(text = "Редактирование задачи", style = MaterialTheme.typography.titleLarge, color = Color.White)
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AddButton(
                text = "Удалить",
                onClick = { onDeleteTask(task) },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            AddButton(
                text = "Сохранить",
                onClick = {
                    titleError = null
                    descriptionError = null
                    startTimeError = null
                    endTimeError = null

                    // Проверка на пустые поля
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
                        val updatedTask = task.copy(
                            title = taskTitle,
                            description = taskDescription,
                            startTime = startTime.split(":")[0].toInt(),
                            endTime = endTime.split(":")[0].toInt(),
                            date = selectedDate
                        )
                        onSaveTask(updatedTask)
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}