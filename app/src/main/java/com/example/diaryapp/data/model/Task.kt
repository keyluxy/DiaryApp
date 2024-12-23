package com.example.diaryapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val startTime: Int,
    val endTime: Int,
    val date: LocalDate
)



