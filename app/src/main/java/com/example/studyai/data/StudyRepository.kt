package com.example.studyai.data

import kotlinx.coroutines.flow.Flow

interface StudyRepository {
	fun observeAll(): Flow<List<StudyTaskEntity>>
	fun observePending(): Flow<List<StudyTaskEntity>>
	suspend fun addTask(title: String, subject: String, estimatedMinutes: Int, priority: Int, dueAtMillis: Long?)
	suspend fun toggleCompleted(id: Long, completed: Boolean)
	suspend fun deleteTask(id: Long)
}

class StudyRepositoryImpl(
	private val dao: StudyDao
) : StudyRepository {
	override fun observeAll(): Flow<List<StudyTaskEntity>> = dao.observeAll()
	override fun observePending(): Flow<List<StudyTaskEntity>> = dao.observePending()

	override suspend fun addTask(title: String, subject: String, estimatedMinutes: Int, priority: Int, dueAtMillis: Long?) {
		dao.insert(
			StudyTaskEntity(
				title = title,
				subject = subject,
				estimatedMinutes = estimatedMinutes,
				priority = priority,
				dueAtMillis = dueAtMillis
			)
		)
	}

	override suspend fun toggleCompleted(id: Long, completed: Boolean) {
		dao.updateCompletedById(id, completed)
	}

	override suspend fun deleteTask(id: Long) {
		dao.deleteById(id)
	}
}