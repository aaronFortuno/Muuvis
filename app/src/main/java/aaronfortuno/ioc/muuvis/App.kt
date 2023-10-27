package aaronfortuno.ioc.muuvis

import aaronfortuno.ioc.muuvis.data.db.MovieDatabase
import android.app.Application
import androidx.room.Room

class App : Application() {

    companion object {
        lateinit var db: MovieDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java,
            "movie-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}