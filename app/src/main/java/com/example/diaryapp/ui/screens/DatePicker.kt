package com.example.diaryapp.ui.screens

import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import java.time.LocalDate
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(date: LocalDate, onDateChange: (LocalDate) -> Unit) {
    val context = LocalContext.current

    AndroidView(
        factory = { CalendarView(context).apply {
            setDate(date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000)
            setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateChange(LocalDate.of(year, month + 1, dayOfMonth))
            }
        }},
        update = { view ->
            view.date = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000
        }
    )
}
