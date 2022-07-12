package com.prime.pageingwithdi.retrofit

import com.prime.pageingwithdi.model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface QuoteAPI {
    @GET("quotes")
    suspend fun getQuote(@Query("page") page: Int) : QuoteList
}