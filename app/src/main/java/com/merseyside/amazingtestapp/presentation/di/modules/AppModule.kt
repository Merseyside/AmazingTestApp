package com.merseyside.amazingtestapp.presentation.di.modules

import android.app.Application
import android.content.Context
import com.merseyside.amazingtestapp.data.executor.JobExecutor
import com.merseyside.amazingtestapp.data.repository.DataRepositoryImpl
import com.merseyside.amazingtestapp.data.repository.datastore.CloudDataStore
import com.merseyside.amazingtestapp.data.repository.datastore.CloudDataStoreImpl
import com.merseyside.amazingtestapp.domain.repository.DataRepository
import com.merseyside.amazingtestapp.presentation.UIThread
import com.merseyside.testapi.TestApi
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application : Application) {

    @Provides
    @ApplicationContext
    fun provideContext() : Context {
        return application
    }

    @Provides
    fun provideApplication() : Application {
        return application
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideDataRepository(dataRepository: DataRepositoryImpl) : DataRepository {
        return dataRepository
    }

    @Provides
    @Singleton
    fun provideCloudRepository(cloudRepository : CloudDataStoreImpl) : CloudDataStore {
        return cloudRepository
    }

    @Provides
    @Singleton
    fun provideTestApi(@ApplicationContext context: Context) : TestApi {
        return TestApi(context)
    }
}