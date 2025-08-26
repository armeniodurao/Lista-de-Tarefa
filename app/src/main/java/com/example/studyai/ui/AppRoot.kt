package com.example.studyai.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studyai.R

sealed class Screen(val route: String, val label: String, val iconRes: Int) {
	data object Dashboard : Screen("dashboard", "Planejamento", android.R.drawable.ic_menu_agenda)
	data object Tasks : Screen("tasks", "Tarefas", android.R.drawable.ic_menu_sort_by_size)
	data object Insights : Screen("insights", "IA", android.R.drawable.ic_menu_search)
}

@Composable
fun AppRoot() {
	val navController = rememberNavController()
	val items = listOf(Screen.Dashboard, Screen.Tasks, Screen.Insights)

	Scaffold(
		bottomBar = {
			NavigationBar {
				val navBackStackEntry by navController.currentBackStackEntryAsState()
				val currentDestination = navBackStackEntry?.destination
				items.forEach { screen ->
					NavigationBarItem(
						selected = currentDestination?.route == screen.route,
						onClick = {
							navController.navigate(screen.route) {
								popUpTo(navController.graph.findStartDestination().id) { saveState = true }
								launchSingleTop = true
								restoreState = true
							}
						},
						icon = { Icon(painterResource(id = screen.iconRes), contentDescription = screen.label) },
						label = { Text(screen.label) }
					)
				}
			}
		}
	) { paddingValues ->
		NavHost(navController, startDestination = Screen.Dashboard.route, builder = {
			composable(Screen.Dashboard.route) { DashboardScreen() }
			composable(Screen.Tasks.route) { TasksScreen() }
			composable(Screen.Insights.route) { InsightsScreen() }
		})
	}
}