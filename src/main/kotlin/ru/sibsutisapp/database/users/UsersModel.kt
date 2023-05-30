package ru.sibsutisapp.database.users

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UsersModel: Table("users") {
    private val login = UsersModel.varchar("login",25)
    private val password = UsersModel.varchar("password",25)
    private val groupID = UsersModel.varchar("groupID",25)
    private val role = UsersModel.varchar("role",25)
    private val fio = UsersModel.varchar("fio",50)

    fun insert(userDTO: UserDTO){
        transaction {
            UsersModel.insert{
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[groupID] = userDTO.groupID
                it[role] = userDTO.role
                it[fio] = userDTO.fio
            }
        }
    }

    fun fetchUser(login:String):UserDTO?{
        return try{
            transaction {
                val userModel = UsersModel.select(UsersModel.login.eq(login)).single()
                UserDTO(
                    login = userModel[UsersModel.login],
                    password = userModel[password],
                    groupID = userModel[groupID],
                    role = userModel[role],
                    fio = userModel[fio]
                )
            }
        }catch (e:Exception){
            null
        }
    }

    fun fetchUserByGroup(group:String):MutableList<UserDTO>{
        val usersList : MutableList<UserDTO> = mutableListOf()
        try{
            transaction {
                val userModel = UsersModel.select { UsersModel.groupID.eq(group) }
                userModel.forEach{
                    usersList.add(
                        UserDTO(
                            login = it[login],
                            password = it[password],
                            groupID = it[groupID],
                            role = it[role],
                            fio = it[fio]
                        )
                    )
                }
            }
        }catch (e:Exception){
            null
        }
        return usersList
    }
}