package com.example.prueba.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba.data.model.Facts
import com.example.prueba.R

class FactsAdapter : RecyclerView.Adapter<FactViewHolder>() {

    var facts = mutableListOf<Facts>()
    var onClickItem: ((Facts)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fact, parent, false)
        return FactViewHolder(view)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        val fact = facts[position]
        holder.bind(fact)
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(fact)
        }
    }

    override fun getItemCount(): Int = facts.size

}