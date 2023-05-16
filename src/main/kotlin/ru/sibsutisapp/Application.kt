package ru.sibsutisapp

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.sibsutisapp.features.News.configureNewsRouting
import ru.sibsutisapp.features.Schedule.configureScheduleRouting
import ru.sibsutisapp.features.login.configureLoginRouting
import ru.sibsutisapp.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/sibsutis_app", driver = "org.postgresql.Driver","postgres","12345")
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureScheduleRouting()
    configureNewsRouting()
    configureSerialization()
}
