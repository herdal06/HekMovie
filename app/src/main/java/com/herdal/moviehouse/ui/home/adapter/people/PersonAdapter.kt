package com.herdal.moviehouse.ui.home.adapter.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.herdal.moviehouse.databinding.ItemPersonBinding
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel

class PersonAdapter(
    private val onClickPerson: ((personId: Int) -> Unit)?
) : PagingDataAdapter<PersonUiModel, PersonViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PersonUiModel>() {
            override fun areItemsTheSame(
                oldItem: PersonUiModel,
                newItem: PersonUiModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PersonUiModel,
                newItem: PersonUiModel
            ): Boolean = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(currentItem) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickPerson
        )
}