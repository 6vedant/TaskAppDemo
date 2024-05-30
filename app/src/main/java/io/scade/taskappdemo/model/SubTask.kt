package io.scade.taskappdemo.model

data class SubTask (val subTaskID: String, val parentTaskID: String, val title: String, val isCompleted: Boolean = false)