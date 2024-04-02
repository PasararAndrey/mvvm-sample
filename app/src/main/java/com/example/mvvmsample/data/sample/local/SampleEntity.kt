package com.example.mvvmsample.data.sample.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SampleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long
)
