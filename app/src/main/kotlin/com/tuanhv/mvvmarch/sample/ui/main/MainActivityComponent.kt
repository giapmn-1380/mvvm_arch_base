package com.tuanhv.mvvmarch.sample.ui.main

import dagger.BindsInstance
import dagger.Subcomponent
import com.tuanhv.mvvmarch.base.injection.PerActivity
import com.tuanhv.mvvmarch.sample.ui.main.fragment.homefeed.HomeFeedModule

/**
 * Created by hoang.van.tuan on 2/2/18.
 */

@PerActivity
@Subcomponent(
        modules = [
            (MainActivityModule::class),
            (HomeFeedModule::class)
        ]
)
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity): MainActivity

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun activity(mainActivity: MainActivity): Builder

        fun build(): MainActivityComponent

    }

}
