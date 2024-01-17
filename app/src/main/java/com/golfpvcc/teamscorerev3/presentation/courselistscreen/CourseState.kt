package com.golfpvcc.teamscorerev3.presentation.courselistscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.golfpvcc.teamscorerev3.data.model.CourseRecord

data class CourseState(
    val courses:List<CourseRecord> = emptyList(),
    val courseName: MutableState<String> = mutableStateOf("")

)
