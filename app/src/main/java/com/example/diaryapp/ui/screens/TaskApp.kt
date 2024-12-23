package com.example.diaryapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi


import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.diaryapp.data.model.Task
import com.example.diaryapp.viewmodels.TaskViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskApp(viewModel: TaskViewModel) {
    var currentScreen by remember { mutableStateOf("TaskList") }
    var selectedTask by remember { mutableStateOf<Task?>(null) }
    val tasks by viewModel.tasks.observeAsState(initial = listOf())

    LaunchedEffect(Unit) {
        viewModel.fetchAllTasks()
    }

    when (currentScreen) {
        "AddTask" -> AddTaskScreen(
            onAddTask = { newTask ->
                viewModel.addTask(newTask)
                currentScreen = "TaskList"
            }
        )
        "TaskList" -> TaskListScreen(
            tasks = tasks,
            onAddTaskTriggered = { currentScreen = "AddTask" },
            onTaskClicked = { task ->
                selectedTask = task
                currentScreen = "TaskDetail"
            }
        )
        "TaskDetail" -> selectedTask?.let { task ->
            TaskDetailScreen(
                task = task,
                onSaveTask = { updatedTask ->
                    viewModel.updateTask(updatedTask)
                    currentScreen = "TaskList"
                },
                onDeleteTask = { taskToDelete ->
                    viewModel.deleteTask(taskToDelete)
                    currentScreen = "TaskList"
                },
                onBack = { currentScreen = "TaskList" }
            )
        }
    }
}
