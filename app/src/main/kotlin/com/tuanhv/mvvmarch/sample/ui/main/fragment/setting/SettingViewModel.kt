package com.tuanhv.mvvmarch.sample.ui.main.fragment.setting

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuanhv.mvvmarch.base.api.common.ErrorState
import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.repository.auth.AuthRepository
import com.tuanhv.mvvmarch.base.repository.common.Resource.Status.ERROR
import com.tuanhv.mvvmarch.base.repository.common.Resource.Status.SUCCESS
import com.tuanhv.mvvmarch.base.ui.SingleLiveData
import kotlinx.coroutines.launch

/**
 * Created by hoang.van.tuan on 8/20/18.
 */
class SettingViewModel constructor(
        private val authRepository: AuthRepository
) : ViewModel() {

    companion object {
        private const val TAG = "SettingViewModel"
    }

    private val successLogout = SingleLiveData<SuccessState>()
    private val errorLogout = SingleLiveData<ErrorState>()

    val isLoading = ObservableField(false)

    fun getSuccessLogout(): SingleLiveData<SuccessState> {
        return successLogout
    }

    fun getErrorLogout(): SingleLiveData<ErrorState> {
        return errorLogout
    }

    fun logout() {
        isLoading.set(true)
        viewModelScope.launch {
            val response = authRepository.logout()
            isLoading.set(false)
            when (response.status) {
                SUCCESS -> {
                    Log.d(TAG, "logout: onSuccess - ${response.data}")
                    authRepository.clearOauthToken()
                    successLogout.postValue(response.data)
                }
                ERROR -> {
                    Log.d(TAG, "logout: onError - ${response.errorState}")
                    errorLogout.postValue(response.errorState)
                }
            }
        }
    }

}
