package com.merseyside.amazingtestapp.presentation.base

import androidx.databinding.ViewDataBinding
import com.merseyside.amazingtestapp.AmazingTestApplication
import com.merseyside.amazingtestapp.presentation.di.components.AppComponent
import com.upstream.basemvvmimpl.presentation.activity.BaseMvvmActivity

abstract class BaseTestActivity<B : ViewDataBinding, M : BaseTestViewModel> : BaseMvvmActivity<B, M>() {

    var appComponent: AppComponent = AmazingTestApplication.application.appComponent
        private set

}