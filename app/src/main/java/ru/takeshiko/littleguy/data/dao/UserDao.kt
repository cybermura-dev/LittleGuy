package ru.takeshiko.littleguy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.takeshiko.littleguy.data.User

/**
 * User profile data access operations
 */
@Dao
interface UserDao {
    /**
     * Creates new user profile, replacing on conflict
     * @param user User object to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    /**
     * Deletes user profile
     * @param user User to remove
     */
    @Delete
    suspend fun delete(user: User)

    /**
     * Retrieves user by ID
     * @param userId Unique user identifier
     * @return User object or null if not found
     */
    @Query("SELECT * FROM Users WHERE ID = :userId")
    suspend fun getUserById(userId: Int): User?

    /**
     * Observes all user profiles (admin feature)
     * @return Flow emitting list of all users
     */
    @Query("SELECT * FROM Users")
    fun getAllUsers(): Flow<List<User>>
}
