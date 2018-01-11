package com.example.chris.architecturecomponent.repository

import com.example.chris.architecturecomponent.dao.BorrowModelDao
import com.example.chris.architecturecomponent.model.BorrowModel
import com.example.chris.architecturecomponent.util.RxExecutor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.annotation.Nonnull

/**
 * Created by Chris on 1/10/18.
 */
class BorrowLocalRepository private constructor(
        private val borrowModelDao: BorrowModelDao,
        private val rxExecutor: RxExecutor)
    : BorrowDataSource {

    override fun saveBorrowInfo(info: BorrowModel, callback: BorrowDataSource.SaveBorrowDataCallback) {
        rxExecutor.executeSingle(
                {borrowModelDao.addBorrow(info)},
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                {_: Unit -> callback.onDataSaved() },
                {_: Throwable? ->  callback.onFailedToSave()})
    }

    override fun getAllData(callback: BorrowDataSource.GetBorrorDataCallback) {
        rxExecutor.executeSingle(
                {borrowModelDao.getAllBorrowedItems()},
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                {t -> callback.onGetBorrowedData(t) },
                {t: Throwable? -> t.let { callback.onError() } }
        )
    }

    override fun deleteData(@Nonnull borrowModel: BorrowModel, @Nonnull callback: BorrowDataSource.DeleteDataCallback) {
        rxExecutor.executeSingle(
                {borrowModelDao.deleteBorrow(borrowModel)},
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                {_ -> callback.onDeleteBorrowItem()},
                {_: Throwable? ->  }
        )
    }

    companion object {
        private var INSTANCE: BorrowLocalRepository? = null

        fun getInstance(borrowModelDao: BorrowModelDao, rxExecutor: RxExecutor): BorrowLocalRepository {
            if (INSTANCE == null) {
                INSTANCE = BorrowLocalRepository(borrowModelDao, rxExecutor)
            }

            return INSTANCE!!
        }
    }
}