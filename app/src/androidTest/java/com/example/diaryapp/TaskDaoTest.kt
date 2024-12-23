package com.example.diaryapp

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.Task
import com.example.diaryapp.data.TaskDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import java.time.LocalDate

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var taskDao: TaskDao

    @Before
    fun setUp() {
        // Создаем тестовую базу данных
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        taskDao = database.taskDao()
    }

    @After
    fun tearDown() {
        // Закрываем базу данных после тестов
        database.close()
    }

    @Test
    fun insertAndGetTasksByDate() = runBlocking {
        // Создаем тестовые данные
        val task1 = Task(
            id = 1,
            title = "Task 1",
            description = "Description 1",
            startTime = 10,
            endTime = 12,
            date = LocalDate.of(2024, 12, 23)
        )
        val task2 = Task(
            id = 2,
            title = "Task 2",
            description = "Description 2",
            startTime = 14,
            endTime = 16,
            date = LocalDate.of(2024, 12, 23)
        )

        // Вставляем задачи в базу данных
        taskDao.insertAll(listOf(task1, task2))

        // Получаем задачи на конкретную дату
        val tasks = taskDao.getTasksByDate(LocalDate.of(2024, 12, 23))

        // Проверяем, что задачи извлечены корректно
        assertEquals(2, tasks.size)
        assertEquals("Task 1", tasks[0].title)
        assertEquals("Task 2", tasks[1].title)
    }

    @Test
    fun deleteTask() = runBlocking {
        // Создаем тестовую задачу
        val task = Task(
            id = 1,
            title = "Task 1",
            description = "Description 1",
            startTime = 10,
            endTime = 12,
            date = LocalDate.of(2024, 12, 23)
        )

        // Вставляем задачу в базу данных
        taskDao.insert(task)

        // Удаляем задачу
        taskDao.delete(task)

        // Проверяем, что задача удалена
        val tasks = taskDao.getTasksByDate(LocalDate.of(2024, 12, 23))
        assertEquals(0, tasks.size)
    }
}
