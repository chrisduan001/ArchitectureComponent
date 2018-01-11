package com.example.chris.architecturecomponent.util

import android.content.Context
import com.example.chris.architecturecomponent.AppDatabase
import com.example.chris.architecturecomponent.repository.BorrowLocalRepository

/**
 * Created by Chris on 1/10/18.
 */
class Injection {
    companion object {
        fun provideBorrowRepository(context: Context) : BorrowLocalRepository {
            val database = AppDatabase.getDatabase(context)

            return BorrowLocalRepository.getInstance(database.borrowModelDao(), RxExecutor())
        }
    }
}