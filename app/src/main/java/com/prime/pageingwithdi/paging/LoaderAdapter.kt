package com.prime.pageingwithdi.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prime.pageingwithdi.R

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoaderAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loader_item, parent, false)
        return LoaderAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoaderAdapterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoaderAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progress: ProgressBar = itemView.findViewById<ProgressBar>(R.id.progress_circular)

        fun bind(loadState: LoadState) {
            progress.isVisible = loadState == LoadState.Loading
        }
    }
}