package com.example.lab_week_10.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Konfigurasi RoomDatabase untuk aplikasi
 * Menampung entitas Total dan versi schema
 */
@Database(entities = [Total::class], version = 1)
abstract class TotalDatabase : RoomDatabase() {

    /**
     * Fungsi abstract untuk mengakses TotalDao
     * Room akan generate implementasi otomatis
     */
    abstract fun totalDao(): TotalDao
}
