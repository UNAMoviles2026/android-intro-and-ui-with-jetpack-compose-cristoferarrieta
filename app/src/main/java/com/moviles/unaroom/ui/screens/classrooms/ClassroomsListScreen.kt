package com.moviles.unaroom.ui.screens.classrooms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moviles.unaroom.data.Classroom
import com.moviles.unaroom.ui.components.ClassroomCard
import com.moviles.unaroom.ui.theme.*

private val mockClassrooms = listOf(
    Classroom("Aula A101", 30, "Building 1"),
    Classroom("Aula B205", 25, "Building 2"),
    Classroom("Lecture Hall 101", 150, "Building 3"),
    Classroom("Aula C310", 24, "Building 1"),
    Classroom("Meeting Room 201", 12, "Building 2")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassroomsScreen(
    classrooms: List<Classroom> = mockClassrooms,
    onLogoutClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Available Classrooms") },
                actions = {
                    IconButton(onClick = onLogoutClick) {
                        Icon(Icons.AutoMirrored.Outlined.Logout, contentDescription = "Logout")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = AppPrimary
            ) {
                Icon(Icons.Filled.Add, contentDescription = null, tint = AppBackground)
            }
        },
        bottomBar = { BottomBar() }
    ) { padding ->

        if (classrooms.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("No classrooms available", color = AppSecondaryText)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(classrooms) {
                    ClassroomCard(classroom = it)
                }
            }
        }
    }
}

@Composable
private fun BottomBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.CalendarToday, contentDescription = null) },
            label = { Text("Calendar") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Person, contentDescription = null) },
            label = { Text("Profile") }
        )
    }
}