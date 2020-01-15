package com.sambataro.ignacio.anwbassesment.ui.jakewharton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sambataro.ignacio.anwbassesment.R
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse
import com.sambataro.ignacio.anwbassesment.ui.base.ReposItem
import com.sambataro.ignacio.anwbassesment.ui.base.ScopeFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.jakewharton_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class JakeWhartonFragment : ScopeFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: JakeWhartonViewModelFactory by instance()

    private lateinit var viewModel: JakeWhartonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jakewharton_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(JakeWhartonViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch{
        val jakeUser = viewModel.user.await()
        jakeUser.observe(this@JakeWhartonFragment, Observer { it ->
            if(it == null) {
                return@Observer
            }
            group_loading_jake_fragment.visibility = View.GONE
            initRecyclerView(it.toUserItems())
        })
    }

    private fun initRecyclerView(items: List<ReposItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        jakewharton_recyclerView.apply {
            layoutManager = LinearLayoutManager(this@JakeWhartonFragment.context)
            adapter = groupAdapter
        }
//        groupAdapter.setOnItemClickListener { item, view ->
//            (item as? ReposItem)?.let {
//                showWeatherDetail(it.repo., view)
//            }
//        }
    }

    private fun List<CurrentUserReposResponse>.toUserItems() : List<ReposItem> {
        return this.map {
            ReposItem(it)
        }
    }
}