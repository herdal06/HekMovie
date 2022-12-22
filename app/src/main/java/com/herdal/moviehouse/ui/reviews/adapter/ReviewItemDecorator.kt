package com.herdal.moviehouse.ui.reviews.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.utils.extensions.dpToPx

private const val ITEM_HORIZONTAL_MARGIN = 16
private const val ITEM_VERTICAL_MARGIN = 4

class ReviewItemDecorator(context: Context) : RecyclerView.ItemDecoration() {

    private val marginHorizontal by lazy {
        ITEM_HORIZONTAL_MARGIN.dpToPx(context)
    }
    private val marginVertical by lazy {
        ITEM_VERTICAL_MARGIN.dpToPx(context)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = marginHorizontal
        outRect.top = marginVertical
        outRect.bottom = marginVertical
        outRect.right = marginHorizontal
    }
}