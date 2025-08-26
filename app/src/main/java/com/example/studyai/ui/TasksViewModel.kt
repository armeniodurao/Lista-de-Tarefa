package com.example.studyai.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyai.data.StudyRepository
import com.example.studyai.data.StudyTaskEntity
import com.example.studyai.planner.AiPlanner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
	private val repository: StudyRepository,
	private val planner: AiPlanner
) : ViewModel() {
	val tasks: StateFlow<List<StudyTaskEntity>> = repository.observeAll()
		.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

	val suggestion: StateFlow<AiPlanner.PlanSuggestion> = tasks
		.map { planner.suggestDailyPlan(it) }
		.stateIn(viewModelScope, SharingStarted.Lazily, planner.suggestDailyPlan(emptyList()))

	fun addSampleData() {
		viewModelScope.launch {
			repository.addTask("Revisar Álgebra Linear", "Matemática", 60, 3, System.currentTimeMillis() + 86_400_000)
			repository.addTask("Ler capítulo Redes Neurais", "IA", 45, 4, null)
			repository.addTask("Exercícios de Kotlin", "Programação", 30, 2, System.currentTimeMillis() + 2 * 86_400_000)
		}
	}
}