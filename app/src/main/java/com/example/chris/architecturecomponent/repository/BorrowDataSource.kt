package com.example.chris.architecturecomponent.repository

import com.example.chris.architecturecomponent.model.BorrowModel
import javax.annotation.Nonnull

/**
 * Created by Chris on 1/10/18.
 */
interface BorrowDataSource {
    interface SaveBorrowDataCallback {
        fun onDataSaved()
        fun onFailedToSave()
    }

    interface GetBorrorDataCallback {
        fun onGetBorrowedData(data: List<BorrowModel>)
        fun onError()
    }

    interface DeleteDataCallback {
        fun onDeleteBorrowItem()
    }

    fun saveBorrowInfo(info: BorrowModel, @Nonnull callback: SaveBorrowDataCallback)
    fun getAllData(@Nonnull callback: GetBorrorDataCallback)
    fun deleteData(@Nonnull borrowModel: BorrowModel, @Nonnull callback: BorrowDataSource.DeleteDataCallback)
}