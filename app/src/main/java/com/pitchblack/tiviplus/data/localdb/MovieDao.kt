package com.pitchblack.tiviplus.data.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pitchblack.tiviplus.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getPopular() : LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(repositories: List<Movie>)

    @Query("")
    fun update()
}