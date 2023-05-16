package ru.sibsutisapp.database.schedule

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.sibsutisapp.features.Schedule.ScheduleRemote


object ScheduleModel : Table("schedule") {
    private val dayOfWeek = ScheduleModel.varchar("dayOfWeek", 25)
    private val groupID = ScheduleModel.varchar("groupID", 25)
    private val subjectName = ScheduleModel.varchar("subjectName", 25)
    private val subjectType = ScheduleModel.varchar("subjectType", 25)
    private val teacherName = ScheduleModel.varchar("teacherName", 25)
    private val startTime = ScheduleModel.varchar("startTime", 25)
    private val studyRoom = ScheduleModel.varchar("studyRoom", 25)
    private val weekType = ScheduleModel.varchar("weekType", 25)

    private val weekDays: List<String> = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ")

    fun insert(scheduleDTO: ScheduleDTO) {
        transaction {
            ScheduleModel.insert {
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

    fun sortGroupLessonsByDays(groupID: ScheduleRemote): Map<String, MutableList<ScheduleDTO>> {
        val mondayList = mutableListOf<ScheduleDTO>()
        val tuesdayList = mutableListOf<ScheduleDTO>()
        val wednesdayList = mutableListOf<ScheduleDTO>()
        val thursdayList = mutableListOf<ScheduleDTO>()
        val fridayList = mutableListOf<ScheduleDTO>()
        val saturdayList = mutableListOf<ScheduleDTO>()

        var resMap: MutableMap<String, MutableList<ScheduleDTO>> = mutableMapOf()

        val tempList: MutableList<ScheduleDTO> = fetchLessonsByGroup(groupID)
        tempList.forEach {
            when (it.dayOfWeek) {
                "ПН" -> {
                    mondayList.add(mondayList.lastIndex + 1, it)
                }

                "ВТ" -> {
                    tuesdayList.add(tuesdayList.lastIndex + 1, it)
                }

                "СР" -> {
                    wednesdayList.add(wednesdayList.lastIndex + 1, it)
                }

                "ЧТ" -> {
                    thursdayList.add(thursdayList.lastIndex + 1, it)
                }

                "ПТ" -> {
                    fridayList.add(fridayList.lastIndex + 1, it)
                }

                "СБ" -> {
                    saturdayList.add(saturdayList.lastIndex + 1, it)
                }
            }
        }
        for (day in weekDays) {
            when (day) {
                "ПН" -> {
                    resMap[day] = mondayList
                }

                "ВТ" -> {
                    resMap[day] = tuesdayList
                }

                "СР" -> {
                    resMap[day] = wednesdayList
                }

                "ЧТ" -> {
                    resMap[day] = thursdayList
                }

                "ПТ" -> {
                    resMap[day] = fridayList
                }

                "СБ" -> {
                    resMap[day] = saturdayList
                }
            }
        }
        resMap.forEach {
            it.value.sortBy {
                it.startTime
            }
        }
        return resMap
    }
}