package com.tuanhv.mvvmarch.sample.ui.main.fragment.setting

import android.util.Log
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.tuanhv.mvvmarch.base.api.common.ErrorState
import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.api.common.rxjava.RetrofitObserver
import com.tuanhv.mvvmarch.base.repository.auth.AuthRepository
import com.tuanhv.mvvmarch.base.ui.SingleLiveData
import com.tuanhv.mvvmarch.base.util.extension.plusAssign
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by hoang.van.tuan on 8/20/18.
 */
class SettingViewModel @ViewModelInject constructor(
        private val authRepository: AuthRepository
) : ViewModel() {

    companion object {
        private const val TAG = "SettingViewModel"
    }

    private val compositeDisposable = CompositeDisposable()

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
        compositeDisposable += authRepository
                .logout()
                .subscribeWith(LogoutObserver())
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private inner class LogoutObserver : RetrofitObserver<SuccessState>() {
        override fun onSuccess(t: SuccessState) {
            Log.d(TAG, "LogoutObserver: onSuccess - $t")
            isLoading.set(false)
            successLogout.postValue(t)
        }

        override fun onError(error: ErrorState) {
            Log.d(TAG, "LogoutObserver: onError - $error")
            isLoading.set(false)
            errorLogout.postValue(error)
        }
    }

}
