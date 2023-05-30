package ru.sibsutisapp.database.schedule

import kotlinx.serialization.Serializable

@Serializable
class ScheduleDTO(
    val id:String,
    val dayOfWeek: String,
    val groupID: String,
    val subjectName: String,
    val subjectType: String,
    val teacherName: String,
    val startTime: String,
    val studyRoom: String,
    val weekType: String,
)