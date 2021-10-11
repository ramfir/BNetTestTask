package com.firdavs.bnettesttask.repository

import com.firdavs.bnettesttask.api.RetrofitInstance
import com.firdavs.bnettesttask.models.AddEntry
import com.firdavs.bnettesttask.models.Entries
import com.firdavs.bnettesttask.models.Session

class Repository {

    suspend fun newSession(): Session {
        return RetrofitInstance.api.newSession("new_session")
    }

    suspend fun getEntries(session: String): Entries {
        return RetrofitInstance.api.getEntries("get_entries", session)
    }

    suspend fun addEntry(session: String, body: String): AddEntry {
        return RetrofitInstance.api.addEntry("add_entry", session, body)
    }
}