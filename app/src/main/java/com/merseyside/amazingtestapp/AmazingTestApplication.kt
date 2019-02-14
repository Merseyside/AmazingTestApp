package com.merseyside.amazingtestapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.merseyside.amazingtestapp.presentation.di.components.AppComponent
import com.merseyside.amazingtestapp.presentation.di.components.DaggerAppComponent
import com.merseyside.amazingtestapp.presentation.di.modules.AppModule

class AmazingTestApplication : Application() {

    companion object {
        lateinit var application : AmazingTestApplication
            private set
    }

    lateinit var appComponent : AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        application = this
        appComponent = buildComponent()
        appComponent.inject(this)
    }

    private fun buildComponent() : AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }


}