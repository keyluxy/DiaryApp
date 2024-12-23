package com.example.diaryapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.time.LocalDate

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<Task>)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM tasks WHERE date = :date ORDER BY startTime")
    suspend fun getTasksByDate(date: LocalDate): List<Task>

    @Query("SELECT * FROM tasks ORDER BY date, startTime")
    suspend fun getAllTasks(): List<Task>

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()
}
