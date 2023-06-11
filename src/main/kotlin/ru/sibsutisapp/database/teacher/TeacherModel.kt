package ru.sibsutisapp.database.teacher

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object TeacherModel:Table("teachers") {
    private val id = TeacherModel.integer("id")
    private val UUID = TeacherModel.varchar("uuid",100)
    private val teacherFIO = TeacherModel.varchar("teacherFIO",75)
    private val discipline = TeacherModel.varchar("discipline",100)
    private val academicYear = TeacherModel.varchar("academicYear",50)
    private val course = TeacherModel.integer("course")
    private val group = TeacherModel.varchar("group",50)
    private val lessonType = TeacherModel.varchar("lessonType",50)
    private val academicSemestr = TeacherModel.integer("academicSemester")

    fun fetchTeacherByGroup(group:String):MutableList<TeacherDTO>{
        val resListOfTeachers = mutableListOf<TeacherDTO>()
        try {
            transaction {
                val teacherModel = TeacherModel.select{TeacherModel.group.eq(group)}
                teacherModel.forEach {
                    resListOfTeachers.add(
                        TeacherDTO(
                            id = it[TeacherModel.id],
                            UUID = it[UUID],
                            academicSemestr = it[academicSemestr],
                            academicYear = it[academicYear],
                            course = it[course],
                            discipline = it[discipline],
                            group = it[TeacherModel.group],
                            lessonType = it[lessonType],
                            teacherFIO = it[teacherFIO]
                        )
                    )
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return resListOfTeachers
    }
}