package ru.sibsutisapp.features.teacher

import kotlinx.serialization.Serializable

@Serializable
data class TeacherReceive(
    val groupID:String
)