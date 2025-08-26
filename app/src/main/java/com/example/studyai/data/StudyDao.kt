package com.example.studyai.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyDao {
	@Query("SELECT * FROM study_tasks ORDER BY completed ASC, dueAtMillis IS NULL, dueAtMillis ASC")
	fun observeAll(): Flow<List<StudyTaskEntity>>

	@Query("SELECT * FROM study_tasks WHERE completed = 0 ORDER BY dueAtMillis IS NULL, dueAtMillis ASC")
	fun observePending(): Flow<List<StudyTaskEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(task: StudyTaskEntity): Long

	@Update
	suspend fun update(task: StudyTaskEntity)

	@Query("DELETE FROM study_tasks WHERE id = :id")
	suspend fun deleteById(id: Long)
}