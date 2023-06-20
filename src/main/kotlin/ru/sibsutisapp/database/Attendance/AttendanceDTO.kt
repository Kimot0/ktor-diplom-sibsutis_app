package ru.sibsutisapp.database.Attendance

import kotlinx.serialization.Serializable

@Serializable
data class AttendanceDTO(
    val id: Int,
    val group: String,
    val discipline: String,
    val lessonType: String,
    val teacherFIO: String,
    val date: String,
    val groupList: String,
    val confirm: Boolean,
)

