package com.pitchblack.tiviplus.di

import android.content.Context
import androidx.room.Room
import com.pitchblack.tiviplus.data.localdb.AppDatabase
import com.pitchblack.tiviplus.data.localdb.MovieDao
import com.pitchblack.tiviplus.data.localdb.CelebsDao
import com.pitchblack.tiviplus.data.localdb.TVDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "tiviplusdb"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: AppDatabase) : MovieDao {
        return database.movieDao()
    }

    @Provides
    fun provideTVDao(database: AppDatabase) : TVDao {
        return database.tvDao()
    }

    @Provides
    fun providePeopleDao(database: AppDatabase) : CelebsDao {
        return database.peopleDao()
    }
}
