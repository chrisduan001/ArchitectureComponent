package com.example.chris.architecturecomponent.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

/**
 * Created by Chris on 1/10/18.
 */
class ToastMessageObserver : MutableLiveData<String>() {
    fun observe(owner: LifecycleOwner, observer: ToastObserver) {
        super.observe(owner, Observer<String> {
            t: String? -> t?.let { observer.onNewMessage(it) }
        })
    }

    interface ToastObserver {
        fun onNewMessage(message: String)
    }
}