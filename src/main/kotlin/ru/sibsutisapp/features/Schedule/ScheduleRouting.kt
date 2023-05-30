package ru.sibsutisapp.features.Schedule

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.sibsutisapp.database.schedule.ScheduleDTO
import ru.sibsutisapp.database.schedule.ScheduleModel.fetchLessonsByGroup

fun Application.configureScheduleRouting() {
    routing {
        post("/schedule"){
            val recv = call.receive<ScheduleRemote>()
            val schedule:MutableList<ScheduleDTO> = fetchLessonsByGroup(recv)
            call.respond(schedule)
        }
    }
}