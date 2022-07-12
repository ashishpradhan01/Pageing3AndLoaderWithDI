package com.prime.pageingwithdi.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.prime.pageingwithdi.R
import com.prime.pageingwithdi.model.Result

class QuotePagingAdapter : PagingDataAdapter<Result, QuotePagingAdapter.QuotePagingViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotePagingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuotePagingViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuotePagingViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.apply {
                authorText.text = it.author
                contextText.text = it.content
            }
        }
    }
    class QuotePagingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val authorText: TextView = itemView.findViewById<TextView>(R.id.txAuthor)
        val contextText: TextView = itemView.findViewById<TextView>(R.id.txContent)
    }
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem._id == newItem._id
            }
            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }
}