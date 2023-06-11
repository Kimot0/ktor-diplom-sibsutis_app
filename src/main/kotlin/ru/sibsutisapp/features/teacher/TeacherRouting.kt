package ru.sibsutisapp.features.teacher

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.sibsutisapp.database.teacher.TeacherDTO
import ru.sibsutisapp.database.teacher.TeacherModel


fun Application.configureTeacherRouting() {
    routing {
        post("/teacher"){
            val request = call.receive<TeacherReceive>()
            val teachers:MutableList<TeacherDTO> = TeacherModel.fetchTeacherByGroup(request.groupID)
            call.respond(teachers)
        }
    }
}