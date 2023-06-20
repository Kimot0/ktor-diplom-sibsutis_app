package ru.sibsutisapp.features.ListOfAttandance

import kotlinx.serialization.Serializable

@Serializable
data class ListOfAttandanceRemote (
    val id: Int,
    val group: String,
    val discipline: String,
    val lessonType: String,
    val teacherFIO: String,
    val date: String,
    val groupList: Map<String,String>,
    val confirm: Boolean,
    )

@Serializable
data class TeacherFioReceive (
    val teacherFIO: String
        )