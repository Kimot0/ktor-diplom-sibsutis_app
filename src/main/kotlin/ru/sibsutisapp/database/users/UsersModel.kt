package ru.sibsutisapp.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UsersModel: Table("users") {
    private val login = UsersModel.varchar("login",25)
    private val password = UsersModel.varchar("password",25)
    private val groupID = UsersModel.varchar("groupID",25)
    private val isStudent = UsersModel.bool("isStudent")
    private val isHead = UsersModel.bool("isHead")

    fun insert(userDTO: UserDTO){
        transaction {
            UsersModel.insert{
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[groupID] = userDTO.groupID
                it[isStudent] = userDTO.isStudent
                it[isHead] = userDTO.isHead
            }
        }
    }

    fun fetchUser(login:String):UserDTO?{
        return try{
            transaction {
                val userModel = UsersModel.select{ UsersModel.login.eq(login)}.single()
                UserDTO(
                    login = userModel[UsersModel.login],
                    password = userModel[password],
                    groupID = userModel[groupID],
                    isStudent = userModel[isStudent],
                    isHead = userModel[isHead]
                )
            }
        }catch (e:Exception){
            null
        }

    }
}