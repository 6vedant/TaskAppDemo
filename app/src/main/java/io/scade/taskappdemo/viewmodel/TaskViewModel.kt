package io.scade.taskappdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.scade.taskappdemo.model.Task

class TaskViewModel : ViewModel() {
    private val _tasksList: MutableLiveData<List<Task>> = MutableLiveData<List<Task>>()
    public val tasksList: LiveData<List<Task>> = _tasksList


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

}