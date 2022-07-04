package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.whatWeDo.WhatWeDo
import com.melvin.ongandroid.view.viewHolders.WhatweDoViewHolder
import javax.inject.Inject

class WhatWeDoAdapter @Inject constructor(
    private val whatWeDo: List<WhatWeDo>
) : RecyclerView.Adapter<WhatweDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhatweDoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WhatweDoViewHolder(
            layoutInflater.inflate(R.layout.fragment_whatwedo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WhatweDoViewHolder, position: Int) {
        val item = whatWeDo[position]
        holder.drawWhatWeDo(item)
    }

    override fun getItemCount(): Int {
        return whatWeDo.size
    }
}
