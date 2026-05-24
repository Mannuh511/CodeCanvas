package com.example.data

import kotlinx.coroutines.flow.Flow

class AppRepository(private val dao: CodeCanvasDao) {

    suspend fun insertUser(user: User) = dao.insertUser(user)

    suspend fun login(email: String, pin: String): User? = dao.login(email, pin)

    suspend fun getUser(email: String): User? = dao.getUser(email)

    val allProjects: Flow<List<Project>> = dao.getAllProjects()

    suspend fun insertProject(project: Project) = dao.insertProject(project)

    val allReviews: Flow<List<Review>> = dao.getAllReviews()

    suspend fun insertReview(review: Review) = dao.insertReview(review)
}
