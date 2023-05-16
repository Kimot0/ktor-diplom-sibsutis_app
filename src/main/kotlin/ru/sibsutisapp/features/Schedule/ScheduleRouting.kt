package ru.sibsutisapp.features.Schedule

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.sibsutisapp.database.schedule.ScheduleDTO
import ru.sibsutisapp.database.schedule.ScheduleModel.fetchLessonsByGroup
import ru.sibsutisapp.database.schedule.ScheduleModel.sortGroupLessonsByDays

fun Application.configureScheduleRouting() {
    routing {
        post("/schedule"){
            val recv = call.receive<ScheduleRemote>()
            val schedule:MutableList<ScheduleDTO> = fetchLessonsByGroup(recv)
            //val schedule:Map<String,MutableList<ScheduleDTO>> = sortGroupLessonsByDays(recv)
            /*val schedule:MutableList<ScheduleDTO> = mutableListOf(
                ScheduleDTO("Monday","IP-916","STP2","Lecture","Zaycev","08:00","210","both"),
                ScheduleDTO("Tuesday","IP-916","PGI","Lecture","Percev","09:50","218","both")
            )*/
            call.respond(schedule)
        }
    }
}