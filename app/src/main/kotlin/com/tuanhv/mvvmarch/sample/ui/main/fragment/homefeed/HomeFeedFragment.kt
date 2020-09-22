package com.tuanhv.mvvmarch.sample.ui.main.fragment.homefeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities.Companion.REQUEST_ID_FIRST_TIME
import com.tuanhv.mvvmarch.base.entity.Post
import com.tuanhv.mvvmarch.base.platform.AppManager
import com.tuanhv.mvvmarch.base.ui.common.LoadMoreSimpleAdapter
import com.tuanhv.mvvmarch.sample.R
import com.tuanhv.mvvmarch.sample.databinding.FragmentHomeFeedBinding
import com.tuanhv.mvvmarch.sample.ui.main.MainActivity
import com.tuanhv.mvvmarch.sample.ui.main.MainViewModel
import com.tuanhv.mvvmarch.sample.ui.main.fragment.homefeed.adapter.PostAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by hoang.van.tuan on 9/14/20.
 */
class HomeFeedFragment : Fragment(),
        PostAdapter.OnItemClickListener, LoadMoreSimpleAdapter.OnLoadMoreListener {

    companion object {
        private const val TAG = "HomeFeedFragment"
    }

    private val appManager: AppManager by inject()

    private lateinit var homeFeedBinding: FragmentHomeFeedBinding

    private val mainViewModel: MainViewModel by sharedViewModel()
    private val homeFeedViewModel: HomeFeedViewModel by viewModel()

    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeFeedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_feed, container, false)
        return homeFeedBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).updateToolbar()

        initiateView()
        handleObservable()

        homeFeedViewModel.fetchPosts(REQUEST_ID_FIRST_TIME)
    }

    private fun initiateView() {
        homeFeedBinding.viewModel = homeFeedViewModel
        homeFeedBinding.postRv.setHasFixedSize(true)
        homeFeedBinding.postRv.layoutManager = LinearLayoutManager(context)
        postAdapter = PostAdapter(homeFeedBinding.postRv, homeFeedViewModel, this, this)
        homeFeedBinding.postRv.adapter = postAdapter

        homeFeedBinding.postRefresh.setOnRefreshListener {
            homeFeedViewModel.fetchPosts(REQUEST_ID_FIRST_TIME, true)
        }
    }

    private fun handleObservable() {
        homeFeedViewModel.getPaginatedPosts().observe(
                viewLifecycleOwner,
                Observer {
                    homeFeedBinding.postRefresh.isRefreshing = false
                    it?.let { paginatedPosts ->
                        if (paginatedPosts.requestId == REQUEST_ID_FIRST_TIME) {
                            postAdapter.clearAllItems()
                        } else {
                            postAdapter.loadMoreSuccess()
                        }

                        val posts = paginatedPosts.entities
                        if (posts.isNotEmpty()) {
                            postAdapter.updatePosts(posts)
                        }
                    }
                }
        )

        homeFeedViewModel.getErrorWithRequestId().observe(
                viewLifecycleOwner,
                Observer {
                    homeFeedBinding.postRefresh.isRefreshing = false
                    when (it) {
                        REQUEST_ID_FIRST_TIME -> {
                            postAdapter.clearAllItems()
                        }
                        else -> {
                            postAdapter.loadMoreFail()
                        }
                    }
                }
        )
    }

    override fun onItemClick(item: Post) {
        // preventing double click
        if (appManager.isPreventingDoubleClick) {
            return
        }

        //Todo Click Item
    }

    override fun onLoadMore() {
        val requestAfterId = homeFeedViewModel.getPaginatedPosts().value?.afterId ?: REQUEST_ID_FIRST_TIME
        if (requestAfterId == REQUEST_ID_FIRST_TIME) {
            // No more posts, no start load more
            return
        }
        postAdapter.startLoadMoreItem()
        homeFeedViewModel.fetchPosts(requestAfterId)
    }
}
