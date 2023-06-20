package ru.sibsutisapp.features.ListOfAttandance

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.sibsutisapp.database.Attendance.AttendanceDTO
import ru.sibsutisapp.database.Attendance.AttendanceModel

fun Application.configureListOfAttandanceRouting() {
    routing {
        post("/sendlist") {
            val receiveHeadList = call.receive<ListOfAttandanceRemote>()
            val result = AttendanceModel.insertInDb(receiveHeadList)
            call.respondText(result)
        }

        post("/getlist") {
            val receiveFIO = call.receive<TeacherFioReceive>()
            val result: MutableList<AttendanceDTO> = AttendanceModel.getFromDb(receiveFIO) ?: mutableListOf()
            call.respond(result)
        }

        post("/sendteacherlist") {
            val receive = call.receive<AttendanceDTO>()
            call.respond("Success")
        }
    }
}