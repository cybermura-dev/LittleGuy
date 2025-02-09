package ru.takeshiko.littleguy.data.repo

import ru.takeshiko.littleguy.data.User
import ru.takeshiko.littleguy.data.dao.UserDao

/**
 * Manages user profile data operations
 * @property userDao Data access object for user table
 */
class UserRepository(private val userDao: UserDao) {
    /**
     * Creates new user profile
     * @param user User object to insert
     */
    suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    /**
     * Updates existing user profile
     * @param user Modified user object with same ID
     */
    suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    /**
     * Retrieves user by ID
     * @param userId Unique user identifier
     * @return User object or null if not found
     */
    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }
}