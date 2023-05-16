package ru.sibsutisapp.features.News

import kotlinx.serialization.Serializable

@Serializable
data class NewsRemote(
    val title:String,
    val content: String,
    val author:String,
    val date:String,
)
