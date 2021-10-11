package com.firdavs.bnettesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firdavs.bnettesttask.models.Entry
import com.firdavs.bnettesttask.utils.Constants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_entry_detail.*

class EntryDetailActivity : AppCompatActivity() {

    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_detail)

        val entry = gson.fromJson<Entry>(intent.extras?.get(Constants.ENTRY_TAG).toString(), Entry::class.java)

        entryInfo.append("Entry ID:\n${entry.id}\n\n")
        entryInfo.append("Entry body:\n${entry.body}\n\n")
        entryInfo.append("Entry add date:\n${entry.getFormattedDa()}\n\n")
        entryInfo.append("Entry modification date:\n${entry.getFormattedDm()}\n\n")
    }
}
