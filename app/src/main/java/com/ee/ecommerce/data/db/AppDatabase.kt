package com.ee.ecommerce.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ee.ecommerce.data.db.dao.UserDao
import com.ee.ecommerce.data.db.entities.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao


    companion object{
        private val DB: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            synchronized(this){
                var instance: AppDatabase? = DB

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "e_commerce_db").build()
                }
                return instance
            }
        }
    }
}