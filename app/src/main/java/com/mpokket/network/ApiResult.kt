package com.mpokket.network

interface ApiResult {
    fun onSuccess(data: Any)
    fun onError(error: String?)
}