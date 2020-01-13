package com.sambataro.ignacio.anwbassesment.ui.infinum

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sambataro.ignacio.anwbassesment.R
import com.sambataro.ignacio.anwbassesment.internal.InfinumViewModelFactory
import com.sambataro.ignacio.anwbassesment.ui.base.ScopeFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class InfinumFragment : ScopeFragment(), KodeinAware{

    override val kodein by closestKodein()
    private val viewModelFactory: InfinumViewModelFactory by instance()

    private lateinit var viewModel: InfinumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.infinum_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(InfinumViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch{
        val infinumUser = viewModel.user.await()
        infinumUser.observe(this@InfinumFragment, Observer { it ->
            if(it == null) {
                return@Observer
            }
            Log.d("SAMBALOIDE", "SIZE IS: " + it.size)
            it.forEach {
                Log.d("SAMBALOIDE", "INFINUM IS: " + it.owner.login)
            }
        })
    }
}