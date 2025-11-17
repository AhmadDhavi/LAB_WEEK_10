package com.example.lab_week_10.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Entity untuk tabel total di database dengan embedded object untuk menyimpan value dan date
 */
@Entity(tableName = "total")
data class Total(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    /**
     * Embedded object yang menyimpan value (total) dan date (waktu terakhir update)
     */
    @Embedded
    val total: TotalObject = TotalObject()
)

/**
 * Data class yang di-embed ke dalam Entity Total
 * Menyimpan nilai counter dan timestamp terakhir update
 */
data class TotalObject(
    @ColumnInfo(name = "value")
    val value: Int = 0,

    @ColumnInfo(name = "date")
    val date: String = Date().toString()
)
