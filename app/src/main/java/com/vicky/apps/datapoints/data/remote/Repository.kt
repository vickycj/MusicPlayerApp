package com.vicky.apps.datapoints.data.remote

import com.vicky.apps.datapoints.ui.viewmodel.ArtistsNameList
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getArtistsNameList(name: String): Observable<ArtistsNameList> = apiService.getArtistsNameList(name)

}