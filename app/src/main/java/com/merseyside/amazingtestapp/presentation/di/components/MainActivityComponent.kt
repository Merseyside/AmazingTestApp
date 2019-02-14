package com.merseyside.amazingtestapp.presentation.di.components

import com.merseyside.amazingtestapp.presentation.di.modules.MainActivityModule
import com.merseyside.amazingtestapp.presentation.view.activity.main_activity.view.MainActivity
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun inject(activity : MainActivity)
}