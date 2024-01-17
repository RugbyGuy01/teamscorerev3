package com.golfpvcc.teamscorerev3.presentation.courselistscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.golfpvcc.teamscorerev3.data.dao.CourseDao
import com.golfpvcc.teamscorerev3.data.model.CourseRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseViewModel(
    private val courseDao: CourseDao
) : ViewModel() {
    private val isSortedByAtoZ: MutableStateFlow<Boolean> = MutableStateFlow(true)

    private var courses: StateFlow<List<CourseRecord>> =
        isSortedByAtoZ.flatMapLatest { sort ->
            if (sort) {
                courseDao.getAllCoursesRecordAsc()
            } else {
                courseDao.getAllCoursesRecordDesc()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state: MutableStateFlow<CourseState> = MutableStateFlow(CourseState())
    val state: StateFlow<CourseState> =
        combine(_state, isSortedByAtoZ, courses) { state, isSortedByAtoZ, courses ->
            state.copy(
                courses = courses
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), CourseState())

    fun onEvent(event: CourseListEvent) {
        when (event) {
            is CourseListEvent.DeleteCourse -> {
                viewModelScope.launch {
                    courseDao.deleteCourseRecord(event.courseRecord)
                }
            }

            is CourseListEvent.SaveCourse -> {
                val courseRecord = CourseRecord(
                    mCoursename = state.value.courseName.value,
                    mUsstate = "",
                    mPar = "",
                    mHandicap =""
                )
                viewModelScope.launch {
                    courseDao.addUpdateCourseRecord(courseRecord)
                }
                _state.update {
                    it.copy(
                        courseName = mutableStateOf("") // this from the class CourseState
                    )
                }
            }

            CourseListEvent.SortCourses -> {
                isSortedByAtoZ.value = !isSortedByAtoZ.value
            }
        }
    }
}