package ru.sibsutisapp.cache

import ru.sibsutisapp.features.login.LoginRecieveRemote

data class TokenCache(
    val login:String,
    val token:String
)

object InMemoryCache {
    val userList: MutableList<LoginRecieveRemote> = mutableListOf(
        LoginRecieveRemote(login = "nikita", password = "12345"),
        LoginRecieveRemote(login = "egor", password = "54321")
    )
    val tokenList: MutableList<TokenCache> = mutableListOf()
    val groupList: Map<String,String> = mapOf(
        Pair("nikita","IP-916"),
        Pair("egor","IP-917")
    )
}