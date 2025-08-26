package com.example.studyai.planner

import com.example.studyai.data.StudyTaskEntity

class AiPlanner {
	data class PlanSuggestion(val tasks: List<StudyTaskEntity>, val totalMinutes: Int, val rationale: String)

	fun suggestDailyPlan(allTasks: List<StudyTaskEntity>, maxMinutes: Int = 180): PlanSuggestion {
		val pending = allTasks.filter { !it.completed }
		val sorted = pending.sortedWith(
			compareBy<StudyTaskEntity> { it.dueAtMillis ?: Long.MAX_VALUE }
				.thenByDescending { it.priority }
				.thenByDescending { it.estimatedMinutes }
		)
		val picked = mutableListOf<StudyTaskEntity>()
		var total = 0
		for (t in sorted) {
			if (total + t.estimatedMinutes <= maxMinutes) {
				picked += t
				total += t.estimatedMinutes
			}
		}
		val reason = "Ordenado por prazo, prioridade e carga horária dentro de ${maxMinutes}min."
		return PlanSuggestion(picked, total, reason)
	}
}