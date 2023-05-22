package ru.sibsutisapp.database.News

import kotlinx.serialization.Serializable

@Serializable
class NewsDTO (
    val title:String,
    val content:String,
    val dateTime:String,
    val author:String
)