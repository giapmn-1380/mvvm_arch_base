package com.tuanhv.mvvmarch.sample.ui.main.fragment.homefeed

import android.util.Log
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities.Companion.REQUEST_ID_FIRST_TIME
import com.tuanhv.mvvmarch.base.entity.Post
import com.tuanhv.mvvmarch.base.repository.post.PostRepository
import com.tuanhv.mvvmarch.base.repository.common.Resource.Status.ERROR
import com.tuanhv.mvvmarch.base.repository.common.Resource.Status.SUCCESS
import com.tuanhv.mvvmarch.base.ui.SingleLiveData
import com.tuanhv.mvvmarch.sample.ui.main.adapter.helper.OnLoadMoreViewModel
import kotlinx.coroutines.launch

/**
 * Created by hoang.van.tuan on 9/14/20.
 */
class HomeFeedViewModel @ViewModelInject constructor(
        private val postRepository: PostRepository
) : ViewModel(), OnLoadMoreViewModel {

    companion object {
        private const val TAG = "HomeFeedViewModel"
    }

    private val paginatedPosts = SingleLiveData<PaginatedEntities<Post>>()
    private val errorWithRequestId = SingleLiveData<Long>()

    val isLoadingFirstTime = ObservableField(false)
    val isLoadFirstTimeFail = ObservableField(false)

    private val isLoadMoreFail = ObservableField(false)
    override fun isLoadMoreFail(): ObservableField<Boolean> = isLoadMoreFail

    fun getErrorWithRequestId(): SingleLiveData<Long> {
        return errorWithRequestId
    }

    fun getPaginatedPosts(): SingleLiveData<PaginatedEntities<Post>> {
        return paginatedPosts
    }

    fun fetchPosts(
            afterId: Long,
            isRefresh: Boolean = false
    ) {
        if (afterId == REQUEST_ID_FIRST_TIME) {
            isLoadingFirstTime.set(!isRefresh)
            isLoadFirstTimeFail.set(false)
        } else {
            isLoadMoreFail.set(false)
        }

        viewModelScope.launch {
            val response = postRepository.getPosts(afterId)
            when (response.status) {
                SUCCESS -> {
                    if (afterId == REQUEST_ID_FIRST_TIME) {
                        isLoadingFirstTime.set(false)
                    }
                    response.data?.let {
                        Log.d(TAG, "getPosts: requestId = $afterId; onSuccess - $it")
                        it.requestId = afterId
                        paginatedPosts.postValue(it)
                    }
                }
                ERROR -> {
                    Log.d(TAG, "getPosts: requestId = $afterId; onError - ${response.errorState}")
                    if (afterId == REQUEST_ID_FIRST_TIME) {
                        isLoadingFirstTime.set(false)
                        isLoadFirstTimeFail.set(true)
                    } else {
                        isLoadMoreFail.set(true)
                    }

                    errorWithRequestId.postValue(afterId)
                }
            }
        }
    }

}