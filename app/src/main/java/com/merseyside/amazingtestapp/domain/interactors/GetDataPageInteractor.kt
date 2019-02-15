package com.merseyside.amazingtestapp.domain.interactors

import com.merseyside.amazingtestapp.domain.Record
import com.merseyside.amazingtestapp.domain.repository.DataRepository
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.domain.interactor.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetDataPageInteractor @Inject constructor(threadExecutor: ThreadExecutor,
                                                postExecutionThread: PostExecutionThread,
                                                private val dataRepository: DataRepository)
    : SingleUseCase<List<Record>, GetDataPageInteractor.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: Params?): Single<List<Record>> {
        return dataRepository.loadPages(params!!.startPage, params.endPage)
    }

    data class Params(val startPage : Int,
                      val endPage : Int = startPage
    )
}