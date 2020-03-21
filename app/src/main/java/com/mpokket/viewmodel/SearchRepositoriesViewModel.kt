package com.mpokket.viewmodel

import androidx.lifecycle.ViewModel
import com.mpokket.helper.processRequest
import com.mpokket.network.ISearchRepositories
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class SearchRepositoriesViewModel(
    private val searchRepositoryService: ISearchRepositories
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getTrendingRepositories(callback: ApiResult, topic: String) {
        disposable.add(searchRepositoryService.getRepositoriesFromTopics("topic:$topic")
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

interface ApiResult {
    fun onSuccess(data: Any)
    fun onError(error: String?)
}
