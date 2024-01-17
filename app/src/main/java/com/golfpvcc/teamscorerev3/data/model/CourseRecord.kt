package com.golfpvcc.teamscorerev3.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CourseTable")
data class CourseRecord(
    val mCoursename: String,   // this is the database key for this course in the CourseListRecord class
    val mUsstate: String?,
    var mPar: String,
    var mHandicap: String,
//    var mPar: IntArray,
//    var mHandicap: IntArray,
    @PrimaryKey(autoGenerate = true)    // default is false
    val mId: Int = 0,

    )