package com.example.mvvm_sample.data.sample.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SampleEntity::class], version = 1)
abstract class SampleDatabase : RoomDatabase()