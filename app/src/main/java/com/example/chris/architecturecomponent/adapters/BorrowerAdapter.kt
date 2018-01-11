package com.example.chris.architecturecomponent.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.chris.architecturecomponent.R
import com.example.chris.architecturecomponent.model.BorrowModel
import com.example.chris.architecturecomponent.util.BorrowDiffCallback
import com.example.chris.architecturecomponent.viewmodel.BorrowedListViewModel

/**
 * Created by Chris on 1/10/18.
 */
class BorrowerAdapter(
        private var borrowItems: List<BorrowModel>,
        private val viewModel: BorrowedListViewModel
        ) : RecyclerView.Adapter<BorrowerVH>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BorrowerVH {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_borrow, parent, false)

        return BorrowerVH(view) {t -> onDeleteItem(t)}
    }

    fun updateItems(borrowItems: List<BorrowModel>) {
        val diffResult = DiffUtil.calculateDiff(BorrowDiffCallback(borrowItems, this.borrowItems))

        this.borrowItems = borrowItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = borrowItems.size

    override fun onBindViewHolder(holder: BorrowerVH?, position: Int) {
        holder?.let {
            val borrowModel = borrowItems[position]
            it.itemName.text = borrowModel.itemName
            it.borrowerName.text = borrowModel.personName
            if (borrowModel.address != null) {
                it.borrowLocation.text = String.format("%s %s", borrowModel.address!!.street, borrowModel.address!!.city)
            }
            it.borrowDate.text = borrowModel.borrowDate.toString()
        }

    }

    private fun onDeleteItem(pos: Int) {
        viewModel.deleteBorrowItem(borrowItems[pos])
    }
}

class BorrowerVH(view: View, onButtonClick: (pos: Int) -> Unit) : RecyclerView.ViewHolder(view) {
    val itemName: TextView = view.findViewById(R.id.item_name)
    val borrowerName: TextView = view.findViewById(R.id.borrower_name)
    val borrowDate: TextView = view.findViewById(R.id.borrow_date)
    val borrowLocation:TextView = view.findViewById(R.id.borrower_location)

    init {
        view.findViewById<Button>(R.id.delete_button).setOnClickListener { onButtonClick(adapterPosition) }
    }
}