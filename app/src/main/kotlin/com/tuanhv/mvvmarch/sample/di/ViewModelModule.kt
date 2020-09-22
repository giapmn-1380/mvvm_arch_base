package com.tuanhv.mvvmarch.sample.di

import com.tuanhv.mvvmarch.sample.ui.home.HomeViewModel
import com.tuanhv.mvvmarch.sample.ui.home.fragment.login.LoginViewModel
import com.tuanhv.mvvmarch.sample.ui.home.fragment.register.RegisterViewModel
import com.tuanhv.mvvmarch.sample.ui.main.MainViewModel
import com.tuanhv.mvvmarch.sample.ui.main.fragment.homefeed.HomeFeedViewModel
import com.tuanhv.mvvmarch.sample.ui.main.fragment.setting.SettingViewModel
import com.tuanhv.mvvmarch.sample.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by hoang.van.tuan on 9/22/20.
 */
val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel() }
    viewModel { MainViewModel() }
    viewModel { HomeFeedViewModel((get())) }
    viewModel { SettingViewModel(get()) }
}
