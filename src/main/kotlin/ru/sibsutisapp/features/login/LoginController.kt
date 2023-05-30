package ru.sibsutisapp.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.sibsutisapp.database.users.UsersModel

//TODO: create security fun

class LoginController(private val call: ApplicationCall) {
    suspend fun performLogin() {
        val receive = call.receive<LoginRecieveRemote>()
        val userDTO = UsersModel.fetchUser(receive.login)
        print(userDTO?.login)
        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid login")
        } else {
            if (userDTO.password == receive.password) {
                val sendGroup: String = userDTO.groupID
                val role: String = userDTO.role
                val fio:String = userDTO.fio
                call.respond(LoginResponseRemote(group = sendGroup, role = role,fio = fio))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }

    suspend fun getUsers(){
        val receive = call.receive<UsersReceiveRemote>()
        val userDtoList = UsersModel.fetchUserByGroup(receive.group)
        val usersFioList:MutableList<UsersResponseRemote> = mutableListOf()
        userDtoList.forEach {
            usersFioList.add(
                UsersResponseRemote(
                    it.fio
                )
            )
        }
        call.respond(usersFioList)
    }
}