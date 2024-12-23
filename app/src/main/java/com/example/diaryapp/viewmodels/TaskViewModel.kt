package com.example.diaryapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.Task
import com.example.diaryapp.repository.TaskRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.insert(task) // Добавляем задачу в базу данных
            fetchAllTasks() // Обновляем список всех задач
        }
    }

    fun fetchAllTasks() {
        viewModelScope.launch {
            val allTasks = repository.getAllTasks() // Получаем все задачи
            _tasks.value = allTasks // Обновляем LiveData
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.update(task)
            fetchAllTasks() // Обновляем список всех задач
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
            fetchAllTasks() // Обновляем список всех задач
        }
    }
}

