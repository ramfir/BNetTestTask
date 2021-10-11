package com.firdavs.bnettesttask.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firdavs.bnettesttask.R
import kotlinx.android.synthetic.main.entry_item.view.*

class EntryAdapter(var entries: List<Entry>): RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {

    inner class EntryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val card = itemView.card_view
        val addDate = itemView.add_date
        val modDate = itemView.mod_date
        val body = itemView.body
    }

    var listener: Listener? = null
    interface Listener {
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.entry_item, parent, false)

        return EntryViewHolder(itemView)
    }

    override fun getItemCount() = entries.size

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.addDate.text = entries[position].getFormattedDa()
        holder.modDate.text = entries[position].getFormattedDm()
        if (entries[position].da == entries[position].dm) {
            holder.modDate.visibility = View.INVISIBLE
        }
        if (entries[position].body.length > 200)
            holder.body.text = entries[position].body.substring(0, 200)
        else
            holder.body.text = entries[position].body

        holder.card.setOnClickListener {
            listener?.onClick(position)
        }
    }
}