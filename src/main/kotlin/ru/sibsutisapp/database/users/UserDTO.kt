package ru.sibsutisapp.database.users

class UserDTO(
    val login:String,
    val password:String,
    val groupID:String,
    val isStudent:Boolean,
    val isHead:Boolean,
)