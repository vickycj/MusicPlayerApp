package com.vicky.apps.datapoints.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable


class MainViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
):ViewModel() {


    private val response: MutableLiveData<ArtistsNameList> = MutableLiveData()

    fun getSubscription(): MutableLiveData<ArtistsNameList> = response

    private lateinit var compositeDisposable: CompositeDisposable


    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }


    fun generateArtistsListApiCall(name: String): Observable<ArtistsNameList> {
        return repository.getArtistsNameList(name)
            .compose(schedulerProvider.getSchedulersForObservable<ArtistsNameList>())
    }







}