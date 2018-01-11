package com.example.chris.architecturecomponent

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.chris.architecturecomponent.dao.BorrowModelDao
import com.example.chris.architecturecomponent.model.BorrowModel
import com.example.chris.architecturecomponent.model.DateConverter

/**
 * Created by Chris on 1/8/18.
 */
@Database(
        entities = [(BorrowModel::class)],
        version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun borrowModelDao() : BorrowModelDao

    companion object {
        private var INSTANCE : AppDatabase? = null
        fun getDatabase(context: Context) : AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(context, AppDatabase::class.java, "borrow_db")
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return INSTANCE!!
        }
    }
}