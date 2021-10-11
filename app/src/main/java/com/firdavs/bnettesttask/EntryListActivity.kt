package com.firdavs.bnettesttask

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firdavs.bnettesttask.models.Entries
import com.firdavs.bnettesttask.models.Entry
import com.firdavs.bnettesttask.models.EntryAdapter
import com.firdavs.bnettesttask.models.Session
import com.firdavs.bnettesttask.utils.Constants
import com.firdavs.bnettesttask.viewmodels.EntryListViewModel
import com.firdavs.bnettesttask.viewmodels.EntryListViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_entry_list.*

import com.firdavs.bnettesttask.utils.isNetworkAvailable

class EntryListActivity : AppCompatActivity() {

    val gson = Gson()
    private lateinit var adapter: EntryAdapter
    private lateinit var viewModel: EntryListViewModel
    private lateinit var sharedPref: SharedPreferences
    private var entries: List<Entry> = mutableListOf()
    private var session = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_list)

        sharedPref = getPreferences(android.content.Context.MODE_PRIVATE)
        session = sharedPref.getString(Constants.SESSION_TAG, "")!!

        val factory = EntryListViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(EntryListViewModel::class.java)

        if (session == "" && isNetworkAvailable()) {
            viewModel.newSession()
        }
        viewModel.sessionResponse.observe(this, Observer {
            saveSession(it)
        })

        if (isNetworkAvailable())
            viewModel.getEntries(session)
        viewModel.entriesResponse.observe(this, Observer {
            updateAdapter(it)
        })

        setupRecyclerView()

    }

    private fun saveSession(session: Session?) {
        this.session = session?.data?.session!!
        with(sharedPref.edit()) {
            putString(Constants.SESSION_TAG, this@EntryListActivity.session)
            apply()
        }
    }

    private fun updateAdapter(entries: Entries) {
        this.entries = entries.data[0]
        adapter.entries = this.entries
        adapter.notifyDataSetChanged()
    }


    private fun setupRecyclerView() {
        adapter = EntryAdapter(entries)
        adapter.listener = object : EntryAdapter.Listener {
            override fun onClick(position: Int) {
                showEntryDetail(position)
            }
        }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private fun showEntryDetail(position: Int) {
        val intent = Intent(this@EntryListActivity, EntryDetailActivity::class.java)
        intent.putExtra(Constants.ENTRY_TAG, gson.toJson(entries[position]))
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

        if (isNetworkAvailable()) {
            viewModel.getEntries(session)
        } else {
            Toast
                .makeText(this, "Check your internet connection and press refresh button", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun addEntry(view: View) {
        if (session == "") {
            Toast
                .makeText(this, "Get your session id by refreshing entries", Toast.LENGTH_LONG)
                .show()
            return
        }
        val intent = Intent(this, AddEntryActivity::class.java)
        intent.putExtra(Constants.SESSION_TAG, session)
        startActivity(intent)
    }

    fun refresh(view: View) {
        if (isNetworkAvailable()) {
            if (session == "") {
                viewModel.newSession()
            }
            viewModel.getEntries(session)
        } else {
            Toast
                .makeText(this, "Check your internet connection and press refresh button", Toast.LENGTH_LONG)
                .show()
        }
    }
}
