package com.prime.pageingwithdi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prime.pageingwithdi.paging.LoaderAdapter
import com.prime.pageingwithdi.paging.QuotePagingAdapter
import com.prime.pageingwithdi.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var quoteViewModel: QuoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: QuotePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvQuote)
        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)
        mAdapter = QuotePagingAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = mAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(),
                footer = LoaderAdapter()
            )
        }

        quoteViewModel.quoteList.observe(this, Observer {
            mAdapter.submitData(lifecycle, it)
        })
    }
}