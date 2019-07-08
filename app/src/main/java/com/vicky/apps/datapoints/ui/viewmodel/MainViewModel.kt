package com.vicky.apps.datapoints.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class MainViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
):ViewModel() {




    private val response: MutableLiveData<Boolean> = MutableLiveData()

    fun getSubscription():MutableLiveData<Boolean> = response

    private lateinit var compositeDisposable: CompositeDisposable


    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }


    fun getArtistNameList(name: String) {

        compositeDisposable.add(generateArtistsListApiCall(name).subscribeBy(onSuccess = {
            response.postValue(true)
        }, onError = {
            Log.d("valuessss",it.message)
        } ))


    }

    fun generateArtistsListApiCall(name: String): Single<ArtistsNameList> {
        return repository.getArtistsNameList(name)
            .compose(schedulerProvider.getSchedulersForSingle())
    }







}