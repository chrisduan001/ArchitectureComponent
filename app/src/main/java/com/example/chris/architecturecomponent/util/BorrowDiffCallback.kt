package com.example.chris.architecturecomponent.util

import android.support.v7.util.DiffUtil

/**
 * Created by Chris on 1/11/18.
 */
class BorrowDiffCallback<T>(private val newList: List<T>,
                            private val oldList: List<T>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //this method only called when are itemsTheSame returns true
        //Will need to implement this method If we want to check if the content of the object is same
        //eg: if we want to modify the content of the items in the list
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    //this method called when areContentsTheSame returns false, will need to modify this method to
    //return the particular field for changed item
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}