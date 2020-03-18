package com.mpokket.models

data class TrendingRepositoriesModel(
    val author: String,
    val avatar: String,
    val builtBy: List<BuiltBy>,
    val currentPeriodStars: Int,
    val description: String,
    val forks: Int,
    val language: String,
    val languageColor: String,
    val name: String,
    val stars: Int,
    val url: String,
    var expanded: Boolean
)

data class BuiltBy(
    val avatar: String,
    val href: String,
    val username: String
)