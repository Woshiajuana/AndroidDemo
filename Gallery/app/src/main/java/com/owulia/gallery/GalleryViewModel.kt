package com.owulia.gallery

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import kotlin.math.ceil

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive : LiveData<List<PhotoItem>>
    get() = _photoListLive

    private val keyWords = arrayOf("cat", "dog", "car", "beauty");
    private var currentPage = 1
    private var totalPage = 1
    private var currentKey = "cat"
    private var isNewQuery = true
    private var isLoading = false
    private val perPage = 30

    fun resetQuery () {
        currentPage = 1
        totalPage = 1
        currentKey = keyWords.random()
        isNewQuery = true
        fetchData()
    }

    fun fetchData () {
        if (isLoading) return
        isLoading = true
        if (currentPage > totalPage) return
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                with(Gson().fromJson(it, Pixabay::class.java)) {
                    totalPage = ceil( totalHits.toDouble() / perPage).toInt()
                    if (isNewQuery) {
                        _photoListLive.value = hits.toList()
                    } else {
                        _photoListLive.value = arrayListOf(_photoListLive.value!!, hits.toList()).flatten()
                    }
                }
                isLoading = false
                isNewQuery = false
                currentPage++
            },
            Response.ErrorListener {
                Log.d("hello", it.toString())
                isLoading = false
            }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest);
    }

    private fun getUrl(): String {
        return "https://pixabay.com/api/?key=12472743-874dc01dadd26dc44e0801d61&q=${currentKey}&per_page=${perPage}&page=${currentPage}"
    }



}