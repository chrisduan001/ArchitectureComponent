package com.example.chris.architecturecomponent

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by Chris on 1/8/18.
 */
class ComponentApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }


}