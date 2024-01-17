package com.golfpvcc.teamscorerev3.presentation.courselistscreen

import com.golfpvcc.teamscorerev3.data.model.CourseRecord

sealed interface CourseListEvent {

    object SortCourses : CourseListEvent

    data class DeleteCourse(val courseRecord: CourseRecord) : CourseListEvent
    data class SaveCourse(val courseRecord: CourseRecord) : CourseListEvent
}