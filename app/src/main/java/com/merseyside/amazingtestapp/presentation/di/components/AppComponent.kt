package com.merseyside.amazingtestapp.presentation.di.components

import android.app.Application
import android.content.Context
import com.merseyside.amazingtestapp.AmazingTestApplication
import com.merseyside.amazingtestapp.data.repository.datastore.CloudDataStore
import com.merseyside.amazingtestapp.domain.repository.DataRepository
import com.merseyside.amazingtestapp.presentation.di.modules.AppModule
import com.merseyside.testapi.TestApi
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(application: AmazingTestApplication)

    @ApplicationContext
    fun context() : Context

    fun application() : Application

    fun dataRepository() : DataRepository

    fun cloudRepository() : CloudDataStore

    fun threadExecutor() : ThreadExecutor

    fun postExecutionThread() : PostExecutionThread

    fun testApi() : TestApi
}