package com.mpokket.network

import com.mpokket.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface ISearchRepositories {

    @GET("repositories?language=&since=daily")
    fun getData(): Observable<MutableList<TrendingRepositoriesModel>>

    @GET("repositories")
    fun getRepositoriesFromTopics(
        @Query("q") topic: String,
        @Query("sort") sort: String = "watchers",
        @Query("order") order: String = "desc"
    ): Observable<SearchApiModel>

    @GET
    fun getContributors(@Url url: String): Observable<MutableList<ContributorsModel>>

    @GET
    fun getContributorInfo(@Url url: String): Observable<ContributorInfoModel>

    @GET
    fun getRepos(@Url url: String): Observable<List<Item>>

}