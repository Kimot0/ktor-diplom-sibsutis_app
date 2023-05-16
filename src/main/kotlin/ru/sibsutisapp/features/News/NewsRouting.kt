package ru.sibsutisapp.features.News

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.sibsutisapp.database.News.NewsModel

fun Application.configureNewsRouting() {
    routing {
        post("/news"){
            val news = NewsModel.fetchNews()
            call.respond(news)
        }
    }
}