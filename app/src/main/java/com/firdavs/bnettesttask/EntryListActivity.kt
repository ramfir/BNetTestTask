package com.firdavs.bnettesttask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.firdavs.bnettesttask.models.Entry
import com.firdavs.bnettesttask.models.EntryAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class EntryListActivity : AppCompatActivity() {

    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val entries = listOf(
            Entry("1", "asdfasdhfkasjdhfkjsadhfkjsdhfkjsdhfksadhfkjsahdfkjashdfkjshadfkjashdfkjhsadkfjhsadkjfhaskjdfhaksjdfhkasjdhfkajsdhfksjdhfkasdhfkajsdhfkajshdfkajsdhfkjashdfkjashdfkjashdfkjsahdfkhasdkfjhasdkfjhaskdjURAfhaskdjfhkasjdhfkasjhdfkajshdfkjashdfkjahsdfkjhasdkfjhaskdjfhaskjdfhkasjhdfkjashdfLLL", "10/8/2021", "10/8/2021"),
            Entry("2", "second", "10/8/2021", "10/8/2021"),
            Entry("3", "third", "11/8/2021", "10/8/2021"),
            Entry("4", "fourth", "10/8/2021", "10/8/2021"),
            Entry("5", "fifth", "10/8/2021", "10/8/2021"))

        val adapter = EntryAdapter(entries)
        adapter.listener = object : EntryAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(this@EntryListActivity, EntryDetailActivity::class.java)
                intent.putExtra("entryTAG", gson.toJson(entries[position]))
                startActivity(intent)
            }
        }

        val recView = recycler_view
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(this)

    }
}
