package io.scade.taskappdemo.util

class StringUtils {
    companion object {
        fun getStringArray(tags: String?): List<String> {
            return tags?.split(",")?.toList() ?: mutableListOf()
        }
    }
}