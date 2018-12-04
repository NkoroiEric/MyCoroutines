package com.scaleup.admin.mycoroutines

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import kotlinx.coroutines.*
import kotlin.random.Random


class Repository {
    private val stringLiveData = MutableLiveData<String>()

    val string : LiveData<String>
        get() = stringLiveData


    suspend fun getString(){
        withContext(Dispatchers.IO){
            runBlocking {
                Log.w("Log", "repeat before delay ${Random.nextInt()}")
                Log.w("Log", "repeat after delay ${Random.nextInt()}")
                stringLiveData.postValue( "From Repository ${Random.nextInt()}")
            }
        }
    }
}