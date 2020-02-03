package com.sambataro.ignacio.anwbassesment.ui.infinum

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sambataro.ignacio.anwbassesment.R
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse
import com.sambataro.ignacio.anwbassesment.ui.base.Communicator
import com.sambataro.ignacio.anwbassesment.ui.base.DetailDialog
import com.sambataro.ignacio.anwbassesment.ui.base.ReposItem
import com.sambataro.ignacio.anwbassesment.ui.base.ScopeFragment
import com.sambataro.ignacio.anwbassesment.ui.search.SearchFragmentDirections
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import kotlinx.android.synthetic.main.infinum_fragment.*

class InfinumFragment : ScopeFragment(), KodeinAware{

    override val kodein by closestKodein()
    private val viewModelFactory: InfinumViewModelFactory by instance()

    private lateinit var viewModel: InfinumViewModel
    private lateinit var model : Communicator

    private lateinit var userName : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.infinum_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(InfinumViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user_name = InfinumFragmentArgs.fromBundle(arguments).stringUserNameSearch

        (activity as AppCompatActivity).supportActionBar?.setTitle(user_name.toUpperCase())

        bindUI(user_name)

        infinum_swipeRefreshLayout.setOnRefreshListener {
            bindUI(user_name)
            Log.d("SAMBALOIDE", "00000000000000000000000000000000")
        }
    }

    private fun bindUI(user: String) = launch{
        viewModel!!.setUserName(user)
        val infinumUser = viewModel.user.await()
        infinumUser.observe(this@InfinumFragment, Observer { it ->
            if(it == null) {
                return@Observer
            }
            group_loading.visibility = View.GONE
            initRecyclerView(it.toUserItems())
            Log.d("SAMBALOIDE", "1111111111111111111111111111111111111")
            infinum_swipeRefreshLayout.setRefreshing(false)
            Log.d("SAMBALOIDE", "33333333333333333333333333333333333333")
        })
        Log.d("SAMBALOIDE", "22222222222222222222222222222222222222222")
    }

    private fun initRecyclerView(items: List<ReposItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }


        infiunm_recyclerView.apply {
            layoutManager = LinearLayoutManager(this@InfinumFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? ReposItem)?.let {
                showDetail(it.repo, view)
            }
        }
    }

    private fun List<CurrentUserReposResponse>.toUserItems() : List<ReposItem> {
        return this.map {
            ReposItem(it)
        }
    }

    private fun showDetail(repo : CurrentUserReposResponse, view : View) {
        val dialog = DetailDialog(repo)
        dialog.show(this@InfinumFragment.fragmentManager, "Description Dialog")
    }
}