package com.prime.pageingwithdi.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prime.pageingwithdi.model.QuoteList
import com.prime.pageingwithdi.model.Result
import com.prime.pageingwithdi.retrofit.QuoteAPI
import java.lang.Exception

class QuotePagingSource(private val quoteAPI: QuoteAPI) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val pos = params.key ?: 1
            val response = quoteAPI.getQuote(pos)
            LoadResult.Page(
                data = response.results,
                nextKey = if(pos == response.totalPages) null else pos+1,
                prevKey = if (pos == 1) null else pos-1
            )

        } catch (e :Exception) {
            LoadResult.Error(e)
        }
    }
}