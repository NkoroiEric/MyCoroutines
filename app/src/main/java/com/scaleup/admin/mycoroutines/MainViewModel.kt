package com.scaleup.admin.mycoroutines

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel() : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //mutableLiveData
    private val _snackBar = MutableLiveData<Boolean>()
    private val _toast = MutableLiveData<String>()

    val snackBar : LiveData<Boolean>
        get() = _snackBar
    val toast : LiveData<String>
        get() = _toast


    fun showMessage(repository: Repository){
//        uiScope.launch {
////            repeat(10){
////                delay(500)
////                _toast.value = it.toString()
////            }
//            repository.getString()
//            _toast.value = repository.string.value
//        }
        launchRepo(repository){
            repository.getString()
        }
    }

    private fun launchRepo(repository: Repository, block: suspend () -> Unit) {
       uiScope.launch {
           try {
               block()
           } catch (e: Exception) {
               _toast.value = e.localizedMessage
           }finally {
               println("Thread is : " + this.coroutineContext.toString())
               println("Thread is " + Thread.currentThread())
               _toast.value = repository.string.value

           }
       }
    }

    fun showSnack(){
        uiScope.launch {
            _snackBar.value = true
            delay(900)
            withContext(Dispatchers.IO) {
                _snackBar.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}