package com.tuanhv.mvvmarch.sample.ui.main.fragment.homefeed

import androidx.lifecycle.ViewModel
import com.tuanhv.mvvmarch.base.injection.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by hoang.van.tuan on 9/14/20.
 */
@Module
abstract class HomeFeedModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeFeedViewModel::class)
    abstract fun bindHomeFeedViewModel(homeFeedViewModel: HomeFeedViewModel): ViewModel

}
