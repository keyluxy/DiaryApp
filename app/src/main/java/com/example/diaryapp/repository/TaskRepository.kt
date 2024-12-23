package com.example.diaryapp.repository

import com.example.diaryapp.data.Task
import com.example.diaryapp.data.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun insert(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.insert(task)
        }
    }

    suspend fun update(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.update(task)
        }
    }

    suspend fun delete(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.delete(task)
        }
    }

    suspend fun getTasksByDate(date: LocalDate): List<Task> {
        return withContext(Dispatchers.IO) {
            taskDao.getTasksByDate(date)
        }
    }

    suspend fun getAllTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            taskDao.getAllTasks()
        }
    }
}
