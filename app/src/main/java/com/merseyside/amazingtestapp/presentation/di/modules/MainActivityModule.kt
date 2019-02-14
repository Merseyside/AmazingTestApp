package com.merseyside.amazingtestapp.presentation.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.merseyside.amazingtestapp.domain.interactors.GetDataPageInteractor
import com.merseyside.amazingtestapp.presentation.view.activity.main_activity.model.MainActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity: AppCompatActivity) {

    @Provides
    internal fun mainViewModelProvider(getDataPageUseCase : GetDataPageInteractor): ViewModelProvider.Factory {
        return MainViewModelProviderFactory(getDataPageUseCase)
    }

    @Provides
    internal fun provideMainViewModel(factory: ViewModelProvider.Factory): MainActivityViewModel {
        return ViewModelProviders.of(activity, factory).get(MainActivityViewModel::class.java)
    }

    class MainViewModelProviderFactory(private val getDataPageUseCase : GetDataPageInteractor)
        : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == MainActivityViewModel::class.java) {
                return MainActivityViewModel(getDataPageUseCase) as T
            }
            throw IllegalArgumentException("Unknown class title")
        }
    }
}