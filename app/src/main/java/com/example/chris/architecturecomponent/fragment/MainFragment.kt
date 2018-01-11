package com.example.chris.architecturecomponent.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.chris.architecturecomponent.AppDatabase
import com.example.chris.architecturecomponent.R
import com.example.chris.architecturecomponent.dao.BorrowModelDao
import com.example.chris.architecturecomponent.model.Address
import com.example.chris.architecturecomponent.model.BorrowModel
import com.example.chris.architecturecomponent.util.ToastMessageObserver
import com.example.chris.architecturecomponent.viewmodel.MainViewModel
import com.example.chris.architecturecomponent.viewmodel.ViewModelFactory
import java.util.*

/**
 * Created by Chris on 1/8/18.
 */
class MainFragment : Fragment() {
    private lateinit var borrowInfo: EditText
    private lateinit var borrowerName: EditText
    private lateinit var borrowDao: BorrowModelDao

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_main, container, false) as View

        borrowInfo = view.findViewById(R.id.borrow_edit_text)
        borrowerName = view.findViewById(R.id.borrow_person_name)
        val button = view.findViewById<Button>(R.id.btn_add)

        val addressStreet = view.findViewById<EditText>(R.id.borrow_address_street)
        val addressCity = view.findViewById<EditText>(R.id.borrow_address_city)

        borrowDao = AppDatabase.getDatabase(activity).borrowModelDao()

        val vmFactory = ViewModelFactory.getInstance(activity.application)
        val viewModel = ViewModelProviders.of(this, vmFactory).get(MainViewModel::class.java)
        viewModel.toastMessageObserver.observe(this, toastOb)

        button.setOnClickListener {
            val address = Address(addressStreet.text.toString(), addressCity.text.toString())
            val borrowModel = BorrowModel(null, borrowInfo.text.toString(), borrowerName.text.toString(), Date(), address)
            viewModel.saveBorrowData(borrowModel)
        }


        return view
    }

    private val toastOb = object : ToastMessageObserver.ToastObserver {
        override fun onNewMessage(message: String) {
            displayToast(message)
        }
    }

    private fun displayToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }
}