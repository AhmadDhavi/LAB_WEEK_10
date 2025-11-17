package com.example.lab_week_10.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Konfigurasi RoomDatabase untuk aplikasi dengan timestamp tracking
 * version 2: Ditambahkan Embedded TotalObject dengan date field
 */
@Database(entities = [Total::class], version = 2)
abstract class TotalDatabase : RoomDatabase() {

    /**
     * Fungsi abstract untuk mengakses TotalDao
     */
    abstract fun totalDao(): TotalDao
}
