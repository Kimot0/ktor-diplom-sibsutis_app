package ru.sibsutisapp.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRecieveRemote(
    val login:String,
    val password:String,
)

@Serializable
data class LoginResponseRemote(
    val group: String,
    val role: String
)
