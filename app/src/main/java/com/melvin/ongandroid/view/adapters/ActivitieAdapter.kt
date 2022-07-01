package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.activities.Activitie
import com.melvin.ongandroid.view.viewHolders.ActivitieViewHolder
import javax.inject.Inject

class ActivitieAdapter @Inject constructor(
    private val activities: List<Activitie>
) : RecyclerView.Adapter<ActivitieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActivitieViewHolder(
            layoutInflater.inflate(R.layout.fragment_activities_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActivitieViewHolder, position: Int) {
        val item = activities[position]
        holder.drawActivitie(item)
    }

    override fun getItemCount(): Int {
        return activities.size
    }
}
