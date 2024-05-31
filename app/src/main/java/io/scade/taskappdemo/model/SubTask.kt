package io.scade.taskappdemo.model

import java.io.Serializable

data class SubTask (val subTaskID: String, val parentTaskID: String, val title: String, val isCompleted: Boolean = false): Serializable