package com.golfpvcc.teamscorerev3.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.golfpvcc.teamscorerev3.data.CourseTypeConvertor.CourseTypeConvertor
import com.golfpvcc.teamscorerev3.data.dao.CourseDao
import com.golfpvcc.teamscorerev3.data.model.CourseRecord

@Database(
    entities = [CourseRecord::class],
    version = 1,
//    exportSchema = false
)
//@TypeConverters(CourseTypeConvertor::class)

abstract class TeamScoreDatabase :RoomDatabase() {
    abstract val courseDao : CourseDao
}