package com.prime.pageingwithdi.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.prime.pageingwithdi.paging.QuotePagingSource
import com.prime.pageingwithdi.retrofit.QuoteAPI
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteAPI: QuoteAPI) {
    fun getQuotes() = Pager(
        //maxSize = pageSize + 2*pageSize
        config = PagingConfig(pageSize = 20, maxSize = 60),
        pagingSourceFactory = { QuotePagingSource(quoteAPI) }
    ).liveData
}