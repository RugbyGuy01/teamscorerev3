package com.golfpvcc.teamscorerev3
//Full Room Database Tutorial - Build Notes App With Room DB
//https://www.youtube.com/watch?v=5pjdE2wnJ0s&t=1910s

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.golfpvcc.teamscorerev3.data.TeamScoreDatabase
import com.golfpvcc.teamscorerev3.presentation.courselistscreen.AddCourseScreen
import com.golfpvcc.teamscorerev3.presentation.courselistscreen.CourseViewModel
import com.golfpvcc.teamscorerev3.presentation.courselistscreen.CoursesScreen
import com.golfpvcc.teamscorerev3.presentation.util.Constants
import com.golfpvcc.teamscorerev3.ui.theme.TeamScoreRev3Theme

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            TeamScoreDatabase::class.java,
            "TeamScoreDb"
        ).build()
    }

    private val viewModel by viewModels<CourseViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun<T: ViewModel> create(modelClass: Class<T>): T {
                    return CourseViewModel(database.courseDao) as T
                }
            }
        }
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamScoreRev3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()

                    NavHost(navController= navController, startDestination = "CoursesScreen") {
                        composable("NotesScreen") {
                            CoursesScreen(
                                state = state,
                                navController = navController,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable("AddCourseScreen") {
                            AddCourseScreen(
                                state = state,
                                navController = navController,
                                onEvent = viewModel::onEvent
                            )
                        }
                    }

                }
            }
        }
    }
}
