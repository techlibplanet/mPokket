package com.mpokket.network

import com.mpokket.models.TrendingRepositoriesModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ITrendingRepositories {

    @GET("repositories?language=&since=daily")
    fun getData(): Observable<MutableList<TrendingRepositoriesModel>>
}