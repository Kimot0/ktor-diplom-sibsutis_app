package ru.sibsutisapp.database.Group

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object GroupModel : Table("groups") {
    private val groupID = GroupModel.varchar("groupID", 10)

    fun fetchGroups():MutableList<GroupDTO>{
        val groupList:MutableList<GroupDTO> = mutableListOf()
        try {
            transaction {
                val groupModel = GroupModel.selectAll()
                groupModel.forEach{
                    groupList.add(
                        GroupDTO(
                            groupID = it[groupID]
                        )
                    )
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return groupList
    }
}