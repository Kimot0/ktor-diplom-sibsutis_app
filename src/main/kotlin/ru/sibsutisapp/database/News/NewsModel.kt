package ru.sibsutisapp.database.News

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object NewsModel: Table("news") {
    private val title = NewsModel.varchar("title",50)
    private val content = NewsModel.varchar("content",250)
    private val author = NewsModel.varchar("author",40)
    private val dateTime = NewsModel.varchar("dateTime",20)

    fun insert(newsDTO: NewsDTO) {
        transaction {
            NewsModel.insert {
                it[title] = newsDTO.title
                it[content] = newsDTO.content
                it[author] = newsDTO.author
                it[dateTime] = newsDTO.dateTime
            }
        }
    }


    fun fetchNews():MutableList<NewsDTO>{
        val newsList:MutableList<NewsDTO> = mutableListOf()
        try {
            transaction {
                val newsModel = NewsModel.selectAll()
                newsModel.forEach{
                    newsList.add(
                        NewsDTO(
                            title = it[title],
                            content = it[content],
                            author = it[author],
                            dateTime = it[dateTime]
                        )
                    )
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return newsList
    }
}