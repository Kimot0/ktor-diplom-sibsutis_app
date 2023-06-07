package ru.sibsutisapp.features.Groups

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.sibsutisapp.database.Group.GroupModel

fun Application.configureGroupRouting() {
    routing {
        post("/groups"){
            val groups = GroupModel.fetchGroups()
            call.respond(groups)
        }
    }
}