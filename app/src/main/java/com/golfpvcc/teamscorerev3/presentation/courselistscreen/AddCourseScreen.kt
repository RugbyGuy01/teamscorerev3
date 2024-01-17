package com.golfpvcc.teamscorerev3.presentation.courselistscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.golfpvcc.teamscorerev3.data.model.CourseRecord


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseScreen(
    state: CourseState,
    navController: NavController,
    onEvent: (CourseListEvent) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val courseRecord = CourseRecord(
                    mCoursename = state.courseName.value,
                    mUsstate = "",
                    mPar = "",
                    mHandicap = ""
//                            mPar = IntArray(18),
//                    mHandicap = IntArray(18)
                )
                onEvent(CourseListEvent.SaveCourse(courseRecord))

                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Save Course"
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.courseName.value,
                onValueChange = {
                    state.courseName.value = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(text = "Course Name")
                }
            )
        }
    }
}