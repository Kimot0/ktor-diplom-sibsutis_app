package ru.sibsutisapp.database.users

import kotlinx.serialization.Serializable

@Serializable
class UserDTO(
    val login:String,
    val password:String,
    val groupID:String,
    val role:String,
    val fio:String,
)