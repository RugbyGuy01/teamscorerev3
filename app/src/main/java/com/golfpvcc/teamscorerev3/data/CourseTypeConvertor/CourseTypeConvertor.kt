package com.golfpvcc.teamscorerev3.data.CourseTypeConvertor

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class CourseTypeConvertor {

    @TypeConverter
    fun arrayToString(myArray: IntArray): String? {
        if (myArray == null || myArray.isEmpty()) {
            return ""
        }
        var str = myArray[0].toString()
        for (i in 1 until myArray.size) {
            str = str + "," + myArray[i].toString()
        }
        return str
    }

    @TypeConverter
    fun stringToArray(arrayString: String): IntArray {
        var holesList: List<String> = arrayString.split(",") // return a list of strings

        val holeInts = IntArray(holesList.size) { holesList[it].toInt() }

        return holeInts
    }

}