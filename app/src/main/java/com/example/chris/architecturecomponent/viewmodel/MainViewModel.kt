package com.example.chris.architecturecomponent.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.chris.architecturecomponent.model.BorrowModel
import com.example.chris.architecturecomponent.repository.BorrowDataSource
import com.example.chris.architecturecomponent.repository.BorrowLocalRepository
import com.example.chris.architecturecomponent.util.ToastMessageObserver
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Chris on 1/10/18.
 */
class MainViewModel(
        private val context: Application,
        private val borrowLocalRepository: BorrowLocalRepository)
    : AndroidViewModel(context), BorrowDataSource.SaveBorrowDataCallback {

    val toastMessageObserver: ToastMessageObserver by lazy {
        ToastMessageObserver()
    }

    fun saveBorrowData(borrowModel: BorrowModel) {
        borrowLocalRepository.saveBorrowInfo(borrowModel, this)
    }

    fun showToastMessage(message: String) {
        toastMessageObserver.value = message
    }

    override fun onDataSaved() {
        showToastMessage("Save successful")
    }

    override fun onFailedToSave() {
        showToastMessage("Save failed")
    }
}