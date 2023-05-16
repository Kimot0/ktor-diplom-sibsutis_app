package ru.sibsutisapp.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.sibsutisapp.database.users.UsersModel

class LoginController(private val call:ApplicationCall) {

    suspend fun performLogin(){
        val receive = call.receive<LoginRecieveRemote>()
        val userDTO = UsersModel.fetchUser(receive.login)
        print(userDTO?.login)
        if(userDTO == null ){
            call.respond(HttpStatusCode.BadRequest,"Invalid login")
        }else{
            if(userDTO.password == receive.password){
                val sendGroup:String = userDTO.groupID
                var role:String
                if(!userDTO.isHead && userDTO.isStudent){
                    role = "STUDENT"
                }
                else{
                    role = if(userDTO.isStudent) "HEAD"
                    else "TEACHER"
                }
                call.respond(LoginResponseRemote(group = sendGroup,role = role))
            }else {
                call.respond(HttpStatusCode.BadRequest,"Invalid password")
            }
        }
    }
}