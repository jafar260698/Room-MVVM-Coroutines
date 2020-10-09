package com.example.portfolia.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.portfolia.database.Dao.PassportDao
import com.example.portfolia.database.Dao.RegisterDao
import com.example.portfolia.database.Entity.*
import com.example.portfolia.database.PassportEntity.higher_edu
import com.example.portfolia.database.PassportEntity.primary_edu
import com.example.portfolia.database.PassportEntity.secondary_edu

@Database(entities = [
         RegistrationEntity::class,
         Certificate::class,
         Writing::class,
         MyAims::class,
         MyDiary::class,
         SelfAssesment::class,
         primary_edu::class,secondary_edu::class,higher_edu::class],version = 1,exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun getRegister() :RegisterDao
    abstract fun getPassport(): PassportDao

    companion object {
        @Volatile
        private var instance: MainDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MainDatabase::class.java,
                "portfolia_db.db"
            ).build()
    }
}
