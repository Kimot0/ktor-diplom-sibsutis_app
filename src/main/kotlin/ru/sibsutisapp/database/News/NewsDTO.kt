package ru.sibsutisapp.database.News

import kotlinx.serialization.Serializable

@Serializable
class NewsDTO (
    val title:String,
    val descrition:String,
    val dateTime:String,
    val author:String
)