package com.example.chris.architecturecomponent.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.chris.architecturecomponent.model.BorrowModel
import com.example.chris.architecturecomponent.repository.BorrowDataSource
import com.example.chris.architecturecomponent.repository.BorrowLocalRepository

/**
 * Created by Chris on 1/9/18.
 */
class BorrowedListViewModel(
        private val context: Application,
        private val borrowLocalRepository: BorrowLocalRepository)
    : AndroidViewModel(context),
        BorrowDataSource.GetBorrorDataCallback, BorrowDataSource.DeleteDataCallback{

    var borrowItemList: MutableLiveData<List<BorrowModel>> = MutableLiveData()

    fun getBorrowItemList() {
        borrowLocalRepository.getAllData(this)
    }

    fun deleteBorrowItem(borrowModel: BorrowModel) {
        borrowLocalRepository.deleteData(borrowModel, this)
    }

    override fun onDeleteBorrowItem() {
        getBorrowItemList()
    }

    override fun onError() {
    }

    override fun onGetBorrowedData(data: List<BorrowModel>) {
        borrowItemList.value = data
    }
}