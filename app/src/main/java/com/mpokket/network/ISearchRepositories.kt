package com.mpokket.network

import com.mpokket.models.SearchApiModel
import com.mpokket.models.TrendingRepositoriesModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ISearchRepositories {

    @GET("repositories?language=&since=daily")
    fun getData(): Observable<MutableList<TrendingRepositoriesModel>>

    @GET("repositories")
    fun getRepositoriesFromTopics(
        @Query("q") topic: String,
        @Query("sort") sort: String = "watchers",
        @Query("order") order: String = "desc"
    ): Observable<SearchApiModel>
}