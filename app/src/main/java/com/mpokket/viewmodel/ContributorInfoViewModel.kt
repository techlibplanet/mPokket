package com.mpokket.viewmodel

import androidx.lifecycle.ViewModel
import com.mpokket.helper.processRequest
import com.mpokket.network.ApiResult
import com.mpokket.network.ISearchRepositories
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class ContributorInfoViewModel(private val searchRepositoryService: ISearchRepositories) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getContributorInfo(callback: ApiResult, url : String) {
        disposable.add(searchRepositoryService.getContributorInfo(url)
            .processRequest(
                {
                    Timber.d(it.toString())
                    callback.onSuccess(it)
                }, {
                    Timber.d(it)
                    it?.let { err -> callback.onError(err) }
                }
            ))
    }
}