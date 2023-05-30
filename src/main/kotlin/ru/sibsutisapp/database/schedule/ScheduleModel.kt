package ru.sibsutisapp.database.schedule

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.sibsutisapp.features.Schedule.ScheduleRemote


object ScheduleModel : Table("schedule") {
    private val id = ScheduleModel.varchar("id",25)
    private val dayOfWeek = ScheduleModel.varchar("dayOfWeek", 25)
    private val groupID = ScheduleModel.varchar("groupID", 25)
    private val subjectName = ScheduleModel.varchar("subjectName", 25)
    private val subjectType = ScheduleModel.varchar("subjectType", 25)
    private val teacherName = ScheduleModel.varchar("teacherName", 25)
    private val startTime = ScheduleModel.varchar("startTime", 25)
    private val studyRoom = ScheduleModel.varchar("studyRoom", 25)
    private val weekType = ScheduleModel.varchar("weekType", 25)


    fun insert(scheduleDTO: ScheduleDTO) {
        transaction {
            ScheduleModel.insert {
                it[id] = scheduleDTO.id
                it[dayOfWeek] = scheduleDTO.dayOfWeek
                it[groupID] = scheduleDTO.groupID
                it[subjectName] = scheduleDTO.subjectName
                it[subjectType] = scheduleDTO.subjectType
                it[teacherName] = scheduleDTO.teacherName
                it[startTime] = scheduleDTO.startTime
                it[studyRoom] = scheduleDTO.studyRoom
                it[weekType] = scheduleDTO.weekType
            }
        }
    }

    fun fetchLessonsByGroup(groupID: ScheduleRemote): MutableList<ScheduleDTO> {
        val scheduleList = mutableListOf<ScheduleDTO>()
        try {
            transaction {
                val scheduleModel = ScheduleModel.select { ScheduleModel.groupID.eq(groupID.groupID) }
                scheduleModel.forEach {
                    scheduleList.add(
                        ScheduleDTO(
                            id = it[ScheduleModel.id],
                            dayOfWeek = it[dayOfWeek],
                            groupID = it[ScheduleModel.groupID],
                            subjectName = it[subjectName],
                            subjectType = it[subjectType],
                            teacherName = it[teacherName],
                            startTime = it[startTime],
                            studyRoom = it[studyRoom],
                            weekType = it[weekType]
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return scheduleList
    }
}