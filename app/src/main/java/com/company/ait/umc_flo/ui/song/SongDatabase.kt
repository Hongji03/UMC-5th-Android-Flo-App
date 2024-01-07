package com.company.ait.umc_flo.ui.song

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.company.ait.umc_flo.data.entities.UserDao
import com.company.ait.umc_flo.data.entities.Album
import com.company.ait.umc_flo.data.entities.Like
import com.company.ait.umc_flo.data.entities.Song
import com.company.ait.umc_flo.data.entities.User
import com.company.ait.umc_flo.ui.main.album.AlbumDao

@Database(entities = [Song::class, User::class, Like::class, Album::class], version = 1)
abstract class SongDatabase: RoomDatabase() {
    abstract fun SongDao(): SongDao
    abstract fun AlbumDao(): AlbumDao
    abstract fun UserDao(): UserDao

    companion object {
        private var instance: SongDatabase? = null
        @Synchronized
        fun getInstance(context: Context): SongDatabase? {
            if(instance == null){
                synchronized(SongDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SongDatabase::class.java,
                        "song-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}