package com.example.diaryapp.utils.validate_time

// Функция для валидации времени
fun validateTime(time: String): Boolean {
    val timePattern = Regex("^\\d{1,2}:\\d{2}$")
    if (!timePattern.matches(time)) {
        return false
    }

    val parts = time.split(":")
    val hour = parts[0].toIntOrNull() ?: return false
    val minute = parts[1].toIntOrNull() ?: return false

    return hour in 0..23 && minute in 0..59
}