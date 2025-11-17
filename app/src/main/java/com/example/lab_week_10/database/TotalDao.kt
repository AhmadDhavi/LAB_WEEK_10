package com.example.lab_week_10.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * Data Access Object untuk operasi CRUD pada Entity Total
 */
@Dao
interface TotalDao {

    /**
     * Insert data total baru atau replace jika sudah ada
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(total: Total)

    /**
     * Update data total yang sudah ada
     */
    @Update
    fun update(total: Total)

    /**
     * Delete data total
     */
    @Delete
    fun delete(total: Total)

    /**
     * Query data total berdasarkan ID
     */
    @Query("SELECT * FROM total WHERE id = :id")
    fun getTotal(id: Long): List<Total>
}
