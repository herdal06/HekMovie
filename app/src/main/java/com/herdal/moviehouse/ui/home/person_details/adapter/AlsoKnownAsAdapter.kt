package com.herdal.moviehouse.ui.home.person_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.common.base.BaseListAdapter
import com.herdal.moviehouse.databinding.ItemAlsoKnownAsBinding

class AlsoKnownAsAdapter : BaseListAdapter<String>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        AlsoKnownAsViewHolder(
            ItemAlsoKnownAsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AlsoKnownAsViewHolder -> {
                getItem(position)?.let { alsoKnownAs -> holder.bind(alsoKnownAs) }
            }
        }
    }
}