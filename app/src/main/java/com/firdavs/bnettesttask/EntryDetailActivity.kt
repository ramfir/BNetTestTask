package com.firdavs.bnettesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firdavs.bnettesttask.models.Entry
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_entry_detail.*

class EntryDetailActivity : AppCompatActivity() {

    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_detail)

        val entry = gson.fromJson<Entry>(intent.extras?.get("entryTAG").toString(), Entry::class.java)
        entryID.text = entry.id
        entryBody.text = entry.body
        entryDA.text = entry.da
        entryDM.text = entry.dm
    }
}
