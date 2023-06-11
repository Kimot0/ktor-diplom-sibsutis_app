package ru.sibsutisapp.database.teacher

import kotlinx.serialization.Serializable

@Serializable
data class TeacherDTO(
    val id:Int,
    val UUID:String,
    val teacherFIO:String,
    val discipline:String,
    val academicYear:String,
    val course:Int,
    val group:String,
    val lessonType:String,
    val academicSemestr:Int
)
