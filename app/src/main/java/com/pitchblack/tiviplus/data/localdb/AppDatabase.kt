package com.pitchblack.tiviplus.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pitchblack.tiviplus.data.model.Movie
import com.pitchblack.tiviplus.data.model.TV

@Database(entities = [Movie::class, TV::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
    abstract fun tvDao() : TVDao
    abstract fun peopleDao() : CelebsDao
}