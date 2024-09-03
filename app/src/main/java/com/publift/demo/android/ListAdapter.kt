package com.publift.demo.android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.publift.fuseappsdk.ads.FuseAdView

class ListAdapter(
    private val dataSet: Array<String>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var adViews = mutableMapOf<Int, FuseAdView>()

    private fun getAdView(context: Context, position: Int): FuseAdView {
        val adView = adViews[position] ?: FuseAdView(context, code = "list_incontent_1").also {
            it.setConstraintLayoutFillParent()
            adViews[position] = it
        }
        return adView
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_view_1)
    }

    class AdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val adContainer: ConstraintLayout = view.findViewById(R.id.ad_container)
        var adView: FuseAdView? = null

        fun unbind() {
            adView?.removeFromParent()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            // Text row.
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.text_row_item, viewGroup, false)
            return TextViewHolder(view)
        } else {
            // Banner ad row.
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.ad_row_item, viewGroup, false)
            return AdViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is AdViewHolder) {
            viewHolder.adView = getAdView(viewHolder.adContainer.context, position)
            viewHolder.adView?.apply {
                setConstraintLayoutFillParent()
                viewHolder.adContainer.addView(this)
            }
        } else if (viewHolder is TextViewHolder) {
            viewHolder.textView.text = dataSet[position]
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if (holder is AdViewHolder) {
            holder.unbind()
        }
    }

    override fun getItemCount() = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return if (dataSet[position] == "ADSLOT") {
            1
        } else {
            0
        }
    }
}

fun View.removeFromParent() {
    if (parent is ViewGroup) {
        (parent as ViewGroup).removeView(this)
    }
}

fun View.setConstraintLayoutFillParent() {
    if (layoutParams != null) {
        return
    }

    layoutParams = ConstraintLayout.LayoutParams(0, 0).apply {
        startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
    }
}
