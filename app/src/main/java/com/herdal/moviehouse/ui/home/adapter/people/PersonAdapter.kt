package com.herdal.moviehouse.ui.home.adapter.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.common.base.BasePagingAdapter
import com.herdal.moviehouse.databinding.ItemPersonBinding
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel

class PersonAdapter(
    private val onClickPerson: ((personId: Int) -> Unit)?
) : BasePagingAdapter<PersonUiModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        PersonViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickPerson
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PersonViewHolder -> {
                getItem(position)?.let { person -> holder.bind(person) }
            }
        }
    }
}