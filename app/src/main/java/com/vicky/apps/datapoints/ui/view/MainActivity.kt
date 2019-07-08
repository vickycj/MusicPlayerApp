package com.vicky.apps.datapoints.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChanges
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.adapter.DataAdapter
import com.vicky.apps.datapoints.ui.viewmodel.ArtistsNameList
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivity : BaseActivity() {



    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:MainViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vicky.apps.datapoints.R.layout.activity_main)
        inilializingRecyclerView()
        initializeValues()

    }

    private fun inilializingRecyclerView() {

        recyclerView = artistRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DataAdapter(getEmptyData())

        recyclerView.adapter = adapter
    }

    private fun getEmptyData(): ArtistsNameList {
        val data: List<ArtistsNameList.Data>? = ArrayList()
        return ArtistsNameList(data, "", 300)
    }

    private fun initializeValues() {

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.setCompositeData(compositeDisposable)

        viewModel.getSubscription().observe(this, Observer {
            if (it != null) {
                successCallback(it)
            }else{
                failureCallback()
            }
        })


        var subscription = artistNameSearchBox.textChanges()

            .skip(1)
            .map { it.toString() }
            .doOnNext {
                indicatorText.text = getString(R.string.searching)
            }
            .debounce(800, TimeUnit.MILLISECONDS)
            .filter { it.length > 2 }
            .flatMap {
                viewModel.generateArtistsListApiCall(it).subscribeOn(Schedulers.io())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnEach {
                indicatorText.text = getString(R.string.results)
            }
            .doOnError { failureCallback() }
            .retry()
            .subscribe {
                successCallback(it)
            }

        compositeDisposable.addAll(subscription)

    }


    private fun successCallback(response: ArtistsNameList) {
        updateData(response)
    }

    private fun updateData(response: ArtistsNameList) {
        adapter.updateData(response)
    }


    private fun failureCallback(){
        Toast.makeText(this,"API failed",Toast.LENGTH_LONG).show()
    }






}
