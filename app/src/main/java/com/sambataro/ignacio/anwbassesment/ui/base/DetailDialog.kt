package com.sambataro.ignacio.anwbassesment.ui.base

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.sambataro.ignacio.anwbassesment.R
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse

class DetailDialog(
    val repo : CurrentUserReposResponse
) : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(repo.name + " Details: ")
            builder.setMessage(repo.description)
                .setPositiveButton("Open in explorer",
                    DialogInterface.OnClickListener { dialog, id ->
                        val openURL = Intent(Intent.ACTION_VIEW)
                        openURL.data = Uri.parse(repo.svnUrl)
                        startActivity(openURL)
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
