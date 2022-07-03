package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.we.Us
import com.melvin.ongandroid.model.entities.we.UsResponse
import com.melvin.ongandroid.view.fragment.UsFragmentDirections
import com.melvin.ongandroid.view.viewHolders.TestimonialsViewHolder
import com.melvin.ongandroid.view.viewHolders.UsViewHolder
import javax.inject.Inject

class UsAdapter @Inject constructor(
    private val us: List<Us>
    ): RecyclerView.Adapter<UsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsViewHolder(
            layoutInflater
                .inflate(R.layout.fragment_us_item, parent, false))
    }

    override fun onBindViewHolder(holder: UsViewHolder, position: Int) {
        val item = us[position]
        holder.drawUs(item)

        
        holder.itemView.setOnClickListener { mView ->
            val direction = UsFragmentDirections
                .actionUsFragmentToMembersFragment2(item)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return us.size
    }
}