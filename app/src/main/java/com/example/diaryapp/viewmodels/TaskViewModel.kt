package com.example.diaryapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.model.Task
import com.example.diaryapp.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
            fetchAllTasks()
        }
    }

    fun fetchAllTasks() {
        viewModelScope.launch {
            val allTasks = repository.getAllTasks()
            _tasks.value = allTasks
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.update(task)
            fetchAllTasks()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
            fetchAllTasks()
        }
    }
}

