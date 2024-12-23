package com.example.diaryapp.utils

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.diaryapp.R
import com.example.diaryapp.data.model.Task
import com.example.diaryapp.data.dao.TaskDao
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@JsonClass(generateAdapter = true)
data class TaskJson(
    val id: Long,
    val date_start: Long,
    val date_finish: Long,
    val name: String,
    val description: String
)

@RequiresApi(Build.VERSION_CODES.O)
suspend fun initializeDatabaseFromJson(context: Context, taskDao: TaskDao) {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val type = Types.newParameterizedType(List::class.java, TaskJson::class.java)
    val adapter = moshi.adapter<List<TaskJson>>(type)

    try {
        val jsonFile = context.resources.openRawResource(R.raw.initial_data)
        val jsonString = jsonFile.bufferedReader().use { it.readText() }

        val taskJsons = adapter.fromJson(jsonString)

        if (taskJsons == null) {
            Log.e("initializeDatabaseFromJson", "Failed to parse JSON")
            return
        }

        val tasks = taskJsons.map { json ->
            Log.d("initializeDatabaseFromJson", "Task ID from JSON: ${json.id}")
            Task(
                id = json.id,
                title = json.name,
                description = json.description,
                startTime = Instant.ofEpochMilli(json.date_start).atZone(ZoneId.systemDefault()).toLocalTime().hour,
                endTime = Instant.ofEpochMilli(json.date_finish).atZone(ZoneId.systemDefault()).toLocalTime().hour,
                date = LocalDate.now()
            )
        }

        withContext(Dispatchers.IO) {
            taskDao.insertAll(tasks)
            Log.d("initializeDatabaseFromJson", "Tasks inserted successfully")
        }
    } catch (e: Exception) {
        Log.e("initializeDatabaseFromJson", "Error initializing database from JSON", e)
    }
}