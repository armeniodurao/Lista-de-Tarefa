package com.example.studyai.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
	entities = [StudyTaskEntity::class],
	version = 1,
	exportSchema = false
)
abstract class StudyDatabase : RoomDatabase() {
	abstract fun studyDao(): StudyDao
}