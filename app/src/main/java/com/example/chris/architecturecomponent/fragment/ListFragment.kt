package com.example.chris.architecturecomponent.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chris.architecturecomponent.R
import com.example.chris.architecturecomponent.adapters.BorrowerAdapter
import com.example.chris.architecturecomponent.model.BorrowModel
import com.example.chris.architecturecomponent.viewmodel.BorrowedListViewModel
import com.example.chris.architecturecomponent.viewmodel.MainViewModel
import com.example.chris.architecturecomponent.viewmodel.ViewModelFactory

/**
 * Created by Chris on 1/10/18.
 */
class ListFragment : Fragment() {
    private lateinit var adapter: BorrowerAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_list, container, false)!!

        val vmFactory = ViewModelFactory.getInstance(activity.application)
        val viewModel = ViewModelProviders.of(this, vmFactory).get(BorrowedListViewModel::class.java)

        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        adapter = BorrowerAdapter(arrayListOf(), viewModel)
        recyclerview.adapter = adapter

        viewModel.borrowItemList.observe(this, itemListOb)

        viewModel.getBorrowItemList()

        return view
    }

    private val itemListOb = Observer<List<BorrowModel>> {
        if (it != null) {
            adapter.updateItems(it)
        }
    }

    companion object {
        fun newInstance() : Fragment {
            return ListFragment()
        }
    }
}