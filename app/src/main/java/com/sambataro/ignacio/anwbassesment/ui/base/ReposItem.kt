package com.sambataro.ignacio.anwbassesment.ui.base

import android.util.Log
import com.bumptech.glide.request.RequestOptions
import com.sambataro.ignacio.anwbassesment.R
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse
import com.sambataro.ignacio.anwbassesment.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.repos_cardview.*

class ReposItem(
    val repo : CurrentUserReposResponse
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            updateRepoName()
            updateAvatarImage()
            updateRepoLastUpdate()
        }
    }

    override fun getLayout() = R.layout.repos_cardview

    private fun ViewHolder.updateRepoName() {
        repos_name_cardview_id.text = repo.name
    }

    private fun ViewHolder.updateRepoLastUpdate() {
        repos_description_cardview_id.text = repo.updatedAt
    }

    private fun ViewHolder.updateAvatarImage() {
        Log.d("SAMBALOIDE", "THE PATH TO THE AVATAR IMAGE IS: " + "http:" + repo.owner.avatarUrl)
        GlideApp.with(this.containerView)
            .load(repo.owner.avatarUrl)
            .into(avatar_img)
    }
}