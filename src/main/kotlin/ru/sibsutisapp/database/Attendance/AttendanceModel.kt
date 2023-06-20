package ru.sibsutisapp.database.Attendance

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.sibsutisapp.features.ListOfAttandance.ListOfAttandanceRemote
import ru.sibsutisapp.features.ListOfAttandance.TeacherFioReceive

object AttendanceModel : Table("sendheadlist") {
    private val id = AttendanceModel.integer("id")
    private val group = AttendanceModel.varchar("group", 50)
    private val discipline = AttendanceModel.varchar("discipline", 50)
    private val lessonType = AttendanceModel.varchar("lessonType", 50)
    private val teacherFIO = AttendanceModel.varchar("teacherFIO", 50)
    private val date = AttendanceModel.varchar("date", 50)
    private val groupList = AttendanceModel.text("groupList")
    private val confirm = AttendanceModel.bool("confirm")


    fun random(): Int = Math.random().toInt()

    fun insertInDb(list: ListOfAttandanceRemote): String {
        var result = ""
        var stringTemp = ""
        try {
            list.groupList.forEach { _, _ ->
                stringTemp += list.groupList.keys.toString() + " " + list.groupList.values.toString() + "\n"
            }
            transaction {
                AttendanceModel.insert {
                    AttendanceDTO(
                        id = list.id,
                        group = list.group,
                        discipline = list.discipline,
                        lessonType = list.lessonType,
                        date = list.date,
                        confirm = list.confirm,
                        groupList = stringTemp,
                        teacherFIO = list.teacherFIO,
                    )
                }
            }
            result = "Success"
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun getFromDb(teacherfio: TeacherFioReceive): MutableList<AttendanceDTO>? {
        val resList: MutableList<AttendanceDTO> = mutableListOf()
        try {
            transaction {
                val teacherFIO = AttendanceModel.select(AttendanceModel.teacherFIO.eq(teacherfio.teacherFIO))
                teacherFIO.forEach{
                    resList.add(
                        AttendanceDTO(
                            id = it[AttendanceModel.id],
                            group = it[group],
                            discipline = it[discipline],
                            lessonType = it[lessonType],
                            teacherFIO = it[AttendanceModel.teacherFIO],
                            date = it[date],
                            groupList = it[groupList],
                            confirm = it[confirm]
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return resList
    }

}