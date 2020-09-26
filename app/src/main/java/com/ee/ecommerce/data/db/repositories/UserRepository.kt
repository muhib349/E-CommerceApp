package com.ee.ecommerce.data.db.repositories

import com.ee.ecommerce.data.db.dao.UserDao
import com.ee.ecommerce.data.db.entities.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun verifyUser(email: String, password: String) = userDao.verifyUser(email,password)
}