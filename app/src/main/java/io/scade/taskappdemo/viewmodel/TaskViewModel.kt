package io.scade.taskappdemo.viewmodel

import android.app.ActivityManager.TaskDescription
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.scade.taskappdemo.model.SubTask
import io.scade.taskappdemo.model.Task
import io.scade.taskappdemo.util.StringUtils
import java.lang.Exception

class TaskViewModel : ViewModel() {
    private val _tasksList: MutableLiveData<List<Task>?> = MutableLiveData<List<Task>?>()
    public val tasksList: MutableLiveData<List<Task>?> = _tasksList


    public fun updateTask(updatedTask: Task) {
        val currentList = _tasksList.value ?: return

        val updatedList = currentList.map { task ->
            if (task.taskID == updatedTask.taskID) {
                updatedTask
            } else {
                task
            }
        }
        _tasksList.value = updatedList
    }

    fun addTask(newTask: Task) {
        val currentList = _tasksList.value ?: listOf()
        val updatedList = currentList + newTask
        _tasksList.value = updatedList
    }

    fun deleteTask(taskID: String): Boolean {
        val currentList = _tasksList.value ?: return false
        val updatedList = currentList.filter { it.taskID != taskID }
        _tasksList.value = updatedList
        return true
    }

    fun getTasks(
        taskIDs: Array<String>,
        taskTitles: Array<String>,
        taskDescriptions: Array<String>?,
        tags: Array<String>?,
        isCompleted: Array<Boolean>?,
        dates: Array<String>?
    ) {
        val tasks = mutableListOf<Task>()
        for (i in taskIDs.indices) {
            try {
                val task = Task(
                    taskID = taskIDs[i],
                    taskTitles[i],
                    taskDescriptions?.get(i) ?: "",
                    isCompleted = isCompleted?.get(i) ?: false,
                    subTasks = null,
                    tags = StringUtils.getStringArray(tags?.get(i)),
                    date = dates?.get(i)
                )
                tasks.add(task)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        _tasksList.postValue(tasks)
    }

    fun getSubTasks(
        subTaskIDs: Array<String>,
        parentTaskIDs: Array<String>,
        subTaskTitles: Array<String>,
        isCompletedList: Array<Boolean>?
    ) {
        val subTasks = mutableListOf<SubTask>()
        val tasks = _tasksList.value
        for (i in subTaskIDs.indices) {
            try {
                val subTask = SubTask(
                    subTaskID = subTaskIDs[i],
                    parentTaskID = parentTaskIDs[i],
                    subTaskTitles[i], isCompletedList?.get(i) ?: false
                )
                subTasks.add(subTask)

            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (tasks != null) {
                for(task in tasks) {
                    val subTaskArrayForTask = mutableListOf<SubTask>()
                    for(subTask in subTasks) {
                        if(task.taskID == subTask.parentTaskID) {
                            subTaskArrayForTask.add(subTask)
                        }
                    }
                    task.subTasks = subTaskArrayForTask
                }
            }
            _tasksList.postValue(tasks)
        }

    }

}