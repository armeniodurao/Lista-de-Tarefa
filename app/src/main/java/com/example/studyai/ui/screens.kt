package com.example.studyai.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardScreen(vm: TasksViewModel = hiltViewModel()) {
	val suggestion by vm.suggestion.collectAsState()
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text("Seu plano de estudos com IA")
		Text("Cargas total: ${suggestion.totalMinutes} min")
		if (suggestion.tasks.isEmpty()) {
			Button(onClick = { vm.addSampleData() }) { Text("Gerar exemplos") }
		} else {
			suggestion.tasks.forEach { Text("• ${it.title} (${it.estimatedMinutes} min)") }
			Text(suggestion.rationale)
		}
	}
}

@Composable
fun TasksScreen(vm: TasksViewModel = hiltViewModel()) {
	val tasks by vm.tasks.collectAsState()
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text("Tarefas de estudo: ${tasks.size}")
		Button(onClick = { vm.addSampleData() }) { Text("Adicionar exemplos") }
	}
}

@Composable
fun InsightsScreen(vm: TasksViewModel = hiltViewModel()) {
	val suggestion by vm.suggestion.collectAsState()
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text("Sugestões da IA aparecerão aqui")
		Text("Sugestão diária: ${suggestion.tasks.size} tarefas, ${suggestion.totalMinutes} min")
	}
}