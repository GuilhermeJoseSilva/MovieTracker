package com.example.movietracker.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movietracker.DAO.MovieDao
import com.example.movietracker.DAO.SeriesDao
import com.example.movietracker.model.Movie
import com.example.movietracker.model.Series

@Database(
    entities = [Movie::class, Series::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun seriesDao(): SeriesDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return  INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_tracker_database"
                ).fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                instance
            }
        }

    }
}