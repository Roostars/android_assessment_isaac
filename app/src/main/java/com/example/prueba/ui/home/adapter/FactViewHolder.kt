package com.example.prueba.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba.data.model.Facts
import com.example.prueba.databinding.ItemFactBinding

class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemFactBinding.bind(itemView)
    fun bind(fact: Facts) {
        binding.tvOrganization.text = fact.organization
        binding.tvDateSet.text = fact.dataset
        binding.tvFact.text = fact.fact
    }
}