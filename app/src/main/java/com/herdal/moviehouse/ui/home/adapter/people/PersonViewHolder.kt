package com.herdal.moviehouse.ui.home.adapter.people

import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.databinding.ItemPersonBinding
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel
import com.herdal.moviehouse.utils.extensions.executeWithAction

class PersonViewHolder(
    private val binding: ItemPersonBinding,
    private val onClickPersonListener: OnClickPersonListener?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(person: PersonUiModel) = binding.apply {
        binding.executeWithAction {
            this.person = person
        }

        root.setOnClickListener {
            person.id?.let {
                onClickPersonListener?.onClick(it)
            }
        }
    }
}