package com.example.chris.architecturecomponent.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.chris.architecturecomponent.repository.BorrowLocalRepository
import com.example.chris.architecturecomponent.util.Injection

/**
 * Created by Chris on 1/10/18.
 */
class ViewModelFactory private constructor(
        private val application: Application,
        private val borrowLocalRepository: BorrowLocalRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(application, borrowLocalRepository) as T
        } else if (modelClass.isAssignableFrom(BorrowedListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BorrowedListViewModel(application, borrowLocalRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) : ViewModelFactory{
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory(application, Injection.provideBorrowRepository(application))
            }

            return INSTANCE!!
        }
    }
}