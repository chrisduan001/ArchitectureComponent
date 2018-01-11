package com.example.chris.architecturecomponent.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.chris.architecturecomponent.model.BorrowModel
import com.example.chris.architecturecomponent.model.DateConverter

/**
 * Created by Chris on 1/8/18.
 */
@Dao
@TypeConverters(DateConverter::class)
interface BorrowModelDao {
    @Query("select * from BorrowModel")
    fun getAllBorrowedItems() : List<BorrowModel>

    @Query("select * from Borrowmodel where id = :id")
    fun getItembyId(id : String) : BorrowModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBorrow(borrowModel: BorrowModel)

    @Delete
    fun deleteBorrow(borrowModel: BorrowModel)
}