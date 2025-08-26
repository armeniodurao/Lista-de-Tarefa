package com.example.studyai.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_tasks")
data class StudyTaskEntity(
	@PrimaryKey(autoGenerate = true) val id: Long = 0L,
	val title: String,
	val subject: String,
	val estimatedMinutes: Int,
	val priority: Int,
	val dueAtMillis: Long?,
	val completed: Boolean = false,
	val createdAtMillis: Long = System.currentTimeMillis()
)