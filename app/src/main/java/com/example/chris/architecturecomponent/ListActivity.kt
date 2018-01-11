package com.example.chris.architecturecomponent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.chris.architecturecomponent.fragment.ListFragment
import com.example.chris.architecturecomponent.fragment.MainFragment

/**
 * Created by Chris on 1/10/18.
 */
class ListActivity : AppCompatActivity() {

    private val toolbar: Toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ListFragment.newInstance(), ListFragment::class.java.name)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context.applicationContext, ListActivity::class.java)
        }
    }
}