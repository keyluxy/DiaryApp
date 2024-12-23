package com.example.diaryapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.repository.TaskRepository
import com.example.diaryapp.ui.screens.TaskApp
import com.example.diaryapp.ui.theme.DiaryAppTheme
import com.example.diaryapp.utils.initializeDatabaseFromJson
import com.example.diaryapp.viewmodels.TaskViewModel
import com.example.diaryapp.viewmodels.TaskViewModelFactory
import kotlinx.coroutines.launch

import android.content.Context
import android.content.SharedPreferences

class MainActivity : ComponentActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val repository = TaskRepository(database.taskDao())
        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(repository))[TaskViewModel::class.java]

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        // Проверяем, была ли уже выполнена инициализация базы данных
        if (!sharedPreferences.getBoolean("is_database_initialized", false)) {
            lifecycleScope.launch {
                initializeDatabaseFromJson(this@MainActivity, database.taskDao())
                // Устанавливаем флаг, что инициализация выполнена
                sharedPreferences.edit().putBoolean("is_database_initialized", true).apply()
            }
        }

        setContent {
            DiaryAppTheme {
                TaskApp(taskViewModel)
            }
        }
    }
}