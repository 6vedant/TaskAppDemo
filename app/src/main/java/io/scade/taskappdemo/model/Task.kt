package io.scade.taskappdemo.model

import java.io.Serializable

data class Task(val taskID: String, val title: String, val description: String?, val isCompleted: Boolean = false, val subTasks: List<SubTask>?, val tags: List<String>?): Serializable
