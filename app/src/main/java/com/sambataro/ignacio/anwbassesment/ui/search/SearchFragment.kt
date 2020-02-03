package com.sambataro.ignacio.anwbassesment.ui.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.sambataro.ignacio.anwbassesment.R
import com.sambataro.ignacio.anwbassesment.ui.base.Communicator
import com.sambataro.ignacio.anwbassesment.ui.base.ScopeFragment
import com.sambataro.ignacio.anwbassesment.ui.infinum.InfinumFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : ScopeFragment(){

    private var model: Communicator?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()
        model= ViewModelProviders.of(this).get(Communicator::class.java)

        search_button_id.setOnClickListener {
            context?.hideKeyboard(it)
            //set the message to share to another fragment
            val action = SearchFragmentDirections.sendUserToInfinumFragment(user_name_edit_search_id.text.toString())
            view?.findNavController()?.navigate(action)
        }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onStop() {
        super.onStop()
        activity?.actionBar?.show()
    }

}