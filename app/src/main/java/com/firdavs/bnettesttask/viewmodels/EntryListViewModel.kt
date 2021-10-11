package com.firdavs.bnettesttask.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firdavs.bnettesttask.models.AddEntry
import com.firdavs.bnettesttask.models.Entries
import com.firdavs.bnettesttask.models.Session
import com.firdavs.bnettesttask.repository.Repository
import kotlinx.coroutines.launch

class EntryListViewModel: ViewModel() {

    private val repository = Repository()

    val sessionResponse: MutableLiveData<Session> = MutableLiveData()
    val entriesResponse: MutableLiveData<Entries> = MutableLiveData()
    val addEntryResponse: MutableLiveData<AddEntry> = MutableLiveData()

    fun newSession() {
        viewModelScope.launch {
            val res = repository.newSession()
            sessionResponse.value = res
        }
    }

    fun getEntries(session: String) {
        viewModelScope.launch {
            val res = repository.getEntries(session)
            entriesResponse.value = res
        }
    }

    fun addEntry(session: String, body: String) {
        viewModelScope.launch {
            val res = repository.addEntry(session, body)
            addEntryResponse.value = res
        }
    }
}