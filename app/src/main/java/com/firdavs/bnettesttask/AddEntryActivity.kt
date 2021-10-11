package com.firdavs.bnettesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firdavs.bnettesttask.utils.Constants
import com.firdavs.bnettesttask.viewmodels.EntryListViewModel
import com.firdavs.bnettesttask.viewmodels.EntryListViewModelFactory
import kotlinx.android.synthetic.main.activity_add_entry.*

import com.firdavs.bnettesttask.utils.isNetworkAvailable

class AddEntryActivity : AppCompatActivity() {

    private lateinit var viewModel: EntryListViewModel
    private lateinit var session: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)

        session = intent.extras?.get(Constants.SESSION_TAG).toString()

        val factory = EntryListViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(EntryListViewModel::class.java)
    }

    fun addEntry(view: View) {
        if (!isNetworkAvailable()) {
            Toast
                .makeText(this, "Check your internet connection and try again", Toast.LENGTH_LONG)
                .show()
            return
        }
        if (editText.text.toString() == "") {
            Toast
                .makeText(this, "Empty entry is not possible", Toast.LENGTH_LONG)
                .show()
            return
        }
        viewModel.addEntry(session, editText.text.toString())
        viewModel.addEntryResponse.observe(this, Observer {
            if (it.status == 1) {
                Toast.makeText(this, "New entry was added", Toast.LENGTH_LONG).show()
            }
        })
        finish()
    }

    fun cancel(view: View) {
        finish()
    }
}
