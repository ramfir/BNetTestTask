package com.firdavs.bnettesttask.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firdavs.bnettesttask.R
import kotlinx.android.synthetic.main.entry_item.view.*

class EntryAdapter(val entries: List<Entry>): RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {

    inner class EntryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val add_date = itemView.add_date
        val mod_date = itemView.mod_date
        val body = itemView.body
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.entry_item, parent, false)

        return EntryViewHolder(itemView)
    }

    override fun getItemCount() = entries.size

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.add_date.text = entries[position].da
        holder.mod_date.text = entries[position].dm
        if (entries[position].da == entries[position].dm) {
            holder.mod_date.visibility = View.INVISIBLE
        }
        if (entries[position].body.length > 200)
            holder.body.text = entries[position].body.substring(0, 200)
        else
            holder.body.text = entries[position].body
    }
}