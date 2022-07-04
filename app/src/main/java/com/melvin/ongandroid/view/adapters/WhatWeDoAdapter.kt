package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.whatWeDo.WhatWeDo
import com.melvin.ongandroid.view.viewHolders.WhatWeDoViewHolder
import javax.inject.Inject

class WhatWeDoAdapter @Inject constructor(
    private val whatWeDo: List<WhatWeDo>
) : RecyclerView.Adapter<WhatWeDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhatWeDoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WhatWeDoViewHolder(
            layoutInflater.inflate(R.layout.fragment_whatwedo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WhatWeDoViewHolder, position: Int) {
        val item = whatWeDo[position]
        holder.drawWhatWeDo(item)
    }

    override fun getItemCount(): Int {
        return whatWeDo.size
    }
}
