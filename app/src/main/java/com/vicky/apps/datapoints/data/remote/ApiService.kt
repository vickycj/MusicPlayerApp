package com.vicky.apps.datapoints.data.remote


import com.vicky.apps.datapoints.ui.viewmodel.ArtistsNameList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("https://api.deezer.com/search/artist")
    fun getArtistsNameList(@Query("q") name: String): Observable<ArtistsNameList>
}
