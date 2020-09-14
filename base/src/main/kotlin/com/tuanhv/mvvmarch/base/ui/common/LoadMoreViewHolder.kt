package com.tuanhv.mvvmarch.base.ui.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by hoang.van.tuan on 9/12/18.
 */

abstract class LoadMoreViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)
